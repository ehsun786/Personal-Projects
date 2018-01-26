package eLearning.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.collect.Lists;

import eLearning.domain.Chapter;
import eLearning.domain.Course;
import eLearning.domain.Learner;
import eLearning.domain.Lecturer;
import eLearning.domain.SubTopic;
import eLearning.repository.ChapterRepository;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.SubTopicRepository;
import eLearning.repository.UserRepository;

@MultipartConfig(maxFileSize = 900000)
@Controller()
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	ChapterRepository chapterRepo;
	@Autowired
	SubTopicRepository subTopicRepo;
	@Autowired
	LecturerRepository lecturerRepo;
	@Autowired
	LearnersRepository learnerRepo;
	@Autowired
	UserRepository userRepo;

	com.amazonaws.services.s3.model.Bucket myBucket = new com.amazonaws.services.s3.model.Bucket();

	// Credentials are setup here
	AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
			"0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
	@SuppressWarnings("deprecation")
	AmazonS3 s3client = new AmazonS3Client(credentials);

	@RequestMapping("/")
	public String index(Principal principal, Model model) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("ADMINISTRATOR")) {
			return "redirect:/admin/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LEARNER")) {
			return "redirect:/learner/";
		}
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		if (!lecturer.isAuthorized()) {
			return "lecturers/pending";
		}

		com.amazonaws.services.s3.model.S3Object s4object;
		List<Course> courseList = new ArrayList<>();
		for (int x : lecturer.getCourseList()) {
			Course tempCourse = courseRepo.findBycourseId(x);

			// I find the image for the course from the AWS bucket.
			if (tempCourse.getCourseImageName() == null) {
				tempCourse.setTempImageLinkHolder(null);
			} else {
				s4object = s3client.getObject(new GetObjectRequest(lecturer.getBucketName(), tempCourse.getCourseImageName()));
				tempCourse.setTempImageLinkHolder(s4object.getObjectContent().getHttpRequest().getURI().toString());
			}
			courseList.add(tempCourse);
		}
		model.addAttribute("courseList", courseList);

		// //Credentials are setup here
		// AWSCredentials credentials = new
		// BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
		// "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
		// @SuppressWarnings("deprecation")
		// AmazonS3 s3client = new AmazonS3Client(credentials);
		//
		// //Testing Amazon AWS S3 - UPLOADING AND VIEWING PDF
		// //Uploads file to S3 Bucket
		// s3client.putObject(new PutObjectRequest("coursecontent123", "HCI",
		// new File(
		// "/s_home/dq13/Documents/Yr2/UI_HCI/HangmanGame_MiniProject_Complete/Hangman/dq13-hci.pdf"))
		// .withCannedAcl(CannedAccessControlList.PublicRead));
		// //Gets file's URI from S3 Bucket
		// com.amazonaws.services.s3.model.S3Object s3object =
		// s3client.getObject(new GetObjectRequest(
		// "coursecontent123","HCI"));
		// model1.addAttribute("HCIDOC",
		// s3object.getObjectContent().getHttpRequest().getURI().toString());
		//
		//
		// //Testing Amazon AWS S3 - UPLOADING AND VIEWING IMAGES AS SLIDESHOW
		// //Uploads file to S3 Bucket
		// s3client.putObject(new PutObjectRequest("coursecontent123", "Image1",
		// new File( "/s_home/dq13/Documents/Yr2/eLearning1.jpeg"))
		// .withCannedAcl(CannedAccessControlList.PublicRead));
		// s3client.putObject(new PutObjectRequest("coursecontent123", "Image2",
		// new File( "/s_home/dq13/Documents/Yr2/eLearning2.jpeg"))
		// .withCannedAcl(CannedAccessControlList.PublicRead));
		// s3client.putObject(new PutObjectRequest("coursecontent123", "Image3",
		// new File( "/s_home/dq13/Documents/Yr2/eLearning3.jpeg"))
		// .withCannedAcl(CannedAccessControlList.PublicRead));
		//
		// //Gets file's URI from S3 Bucket
		// com.amazonaws.services.s3.model.S3Object s4object =
		// s3client.getObject(new GetObjectRequest(
		// "coursecontent123","Image1"));
		// model1.addAttribute("Image1",
		// s4object.getObjectContent().getHttpRequest().getURI().toString());
		//
		// com.amazonaws.services.s3.model.S3Object s5object =
		// s3client.getObject(new GetObjectRequest(
		// "coursecontent123","Image2"));
		// model1.addAttribute("Image2",
		// s5object.getObjectContent().getHttpRequest().getURI().toString());
		//
		// com.amazonaws.services.s3.model.S3Object s6object =
		// s3client.getObject(new GetObjectRequest(
		// "coursecontent123","Image3"));
		// model1.addAttribute("Image3",
		// s6object.getObjectContent().getHttpRequest().getURI().toString());
		//
		// //Testing Amazon AWS S3 - UPLOADING AND VIEWING VIDEOS
		// //Uploads file to S3 Bucket
		// /* s3client.putObject(new PutObjectRequest("coursecontent123",
		// "Video", new File("https://www.youtube.com/watch?v=qm67wbB5GmI"))
		// .withCannedAcl(CannedAccessControlList.PublicRead));
		// //Gets file's URI from S3 Bucket
		// com.amazonaws.services.s3.model.S3Object s7object =
		// s3client.getObject(new GetObjectRequest(
		// "coursecontent123","Video"));
		// model1.addAttribute("Video",
		// s7object.getObjectContent().getHttpRequest().getURI().toString());*/

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
		model.addAttribute("lecturer", lecturer);
		return "lecturers/success-login";
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Principal principal, Model model) {
		System.out.println(userRepo.findByUsername(principal.getName()).getRole().getRole()+"-------------------------------------------------");
		if(!userRepo.findByUsername(principal.getName()).getRole().equals("ROLE_LECTURER")) {
			return "security/error-message";
		}
		model.addAttribute("lecturer", lecturerRepo.findByusername(principal.getName()));
		return "lecturers/settings";
	}

	@RequestMapping(value = "/goToAddCourse", method = RequestMethod.GET)
	public String addCourse1() {
		return "lecturers/courseAdd";
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public String deleteCourse(@RequestParam("courseId") int courseId, Model model, Principal principal) {
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		Iterator<Integer> itr = lecturer.getCourseList().iterator();
		Course course = courseRepo.findBycourseId(courseId);
		lecturer.getCourseList().removeIf(i -> i==courseId);
		for(Learner l: learnerRepo.findAll()){
			l.getCourseList().removeIf(i -> i.getCourseId()==courseId);
			learnerRepo.save(l);
		}
		lecturerRepo.save(lecturer);
		Course c = courseRepo.findBycourseId(courseId);
		courseRepo.delete(c);
		List<Course> courseList = new ArrayList<>();
		for (int x : lecturer.getCourseList()) {
			courseList.add(courseRepo.findBycourseId(x));
		}
		model.addAttribute("courseList", courseList);
		model.addAttribute("username", principal.getName());

		return "lecturers/success-login";
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String addCourse(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("price") Double price, @RequestParam MultipartFile file, Model model, Principal principal) {

		// Convert Multipart file(Course IMG) to a File
		File convFile = new File(file.getOriginalFilename());

		try {
			convFile.createNewFile();
			FileOutputStream fos;
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File(convFile.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		int IMG_WIDTH = 350;
		int IMG_HEIGHT = 253;
		BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_WIDTH, IMG_HEIGHT);

		String username = principal.getName();
		Course tmpCourse = new Course();
		tmpCourse.setName(name);
		tmpCourse.setDescription(description);
		tmpCourse.setPrice(price);
		int randomCode = (int) (Math.random() * 9000) + 1000;
		String tempContentName = randomCode + file.getOriginalFilename() + randomCode; 

		tmpCourse.setCourseImageName(tempContentName);
		courseRepo.save(tmpCourse);

		Lecturer lec = lecturerRepo.findByusername(username);
		lec.getCourseList().add(tmpCourse.getCourseId());
		lecturerRepo.save(lec);

		Course c = courseRepo.findBycourseId(tmpCourse.getCourseId());
		c.setLecturer(lec);
		courseRepo.save(c);

		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
				"0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);

		// LINK TO HELP RESOURCE --
		// http://stackoverflow.com/questions/6727207/save-a-bufferedimage-object-to-amazon-s3-as-a-file
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(resizeImageJpg, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buffer = os.toByteArray();
		InputStream is = new ByteArrayInputStream(buffer);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(buffer.length);

		s3client.putObject(new PutObjectRequest(lec.getBucketName(), tempContentName, is, meta));
		s3client.setObjectAcl(lec.getBucketName(), tempContentName, CannedAccessControlList.PublicRead);
		
		
		model.addAttribute("chapters", null);
		model.addAttribute("courseId", tmpCourse.getCourseId());
		model.addAttribute("courseName", name);
		model.addAttribute("subTopicList", null);

		Lecturer lecturer = lecturerRepo.findByusername(username);
		// reads form input
		List<Course> lecturerCourseList = new ArrayList<Course>();
		for (int courseId : lecturer.getCourseList()) {
			lecturerCourseList.add(courseRepo.findBycourseId(courseId));
		}
		for (Learner learner : Lists.newArrayList(learnerRepo.findAll())) {
			if (!Collections.disjoint(learner.getCourseList(), lecturerCourseList)) {
				String to = learner.getEmail();
				String from = lecturer.getEmail();
				String host = "localhost";
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", host);
				Session session = Session.getDefaultInstance(properties);
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("New Courss: " + name);
					message.setText("A new course has been added. Don't forget to have a look.\n" + "Description: "
							+ description);
					Transport.send(message);
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}
		}

		return "lecturers/addChapter";

	}

	// LINK TO RESOURCE
	// METHOD----https://www.mkyong.com/java/how-to-resize-an-image-in-java/
	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int WIDTH, int HEIGHT) {
		BufferedImage resizedImage = new BufferedImage(WIDTH, HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		return resizedImage;
	}

	@RequestMapping(value = "/editCourse", method = RequestMethod.GET)
	public String addChapter(Model model, Principal principal, @RequestParam("courseId") int courseId) {
		Course course = new Course();
		course = courseRepo.findBycourseId(courseId);
		if (course.getChapterList().size() == 0) {
			model.addAttribute("chapters", null);
			model.addAttribute("courseId", courseId);
			return "lecturers/addChapter";
		}
		model.addAttribute("courseName", course.getName());
		model.addAttribute("chapters", course.getChapterList());
		model.addAttribute("courseId", courseId);
		model.addAttribute("subTopicList", null);
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/addChapterNull", method = RequestMethod.POST)
	public String addChapter(@ModelAttribute("chapter") Chapter chapter, Model model, Principal principal,
			@RequestParam("courseId") int courseId) {
		String username = principal.getName();
		Lecturer lec = lecturerRepo.findByusername(username);
		Course course = new Course();
		course = courseRepo.findBycourseId(courseId);
		chapterRepo.save(chapter);
		course.getChapterList().add(chapter);
		courseRepo.save(course);
		lecturerRepo.save(lec);
		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/addSubTopic", method = RequestMethod.POST)
	public String addsubtopic(@RequestParam String name, @RequestParam String description,
			@RequestParam MultipartFile file, @RequestParam(value = "chapterId") int chapterId,
			@RequestParam(value = "courseId") int courseId, Model model, Principal principal) {

		// Convert Multipart file to a File
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos;
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String username = principal.getName();
		Lecturer lec = lecturerRepo.findByusername(username);
		SubTopic tempSubTop = new SubTopic();
		tempSubTop.setName(name);
		tempSubTop.setDescription(description);
		int randomCode = (int) (Math.random() * 9000) + 1000;
		String tempContentName = randomCode + file.getOriginalFilename() + randomCode; 

		tempSubTop.setFile1(tempContentName);
		String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
		tempSubTop.setFileExtension(fileExt);
		subTopicRepo.save(tempSubTop);

		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
				"0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);

		for (Bucket bucket : s3client.listBuckets()) {
			if (bucket.getName().equals(lec.getBucketName())) {
				s3client.putObject(new PutObjectRequest(bucket.getName(), tempContentName, convFile));
				s3client.setObjectAcl(bucket.getName(), tempContentName, CannedAccessControlList.PublicRead);
			}
		}
		Course course = new Course();
		course = courseRepo.findBycourseId(courseId);
		Chapter chapter = chapterRepo.findBychapterId(chapterId);
		chapter.getSubtopics().add(tempSubTop);
		chapterRepo.save(chapter);
		lecturerRepo.save(lec);
		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/deleteChapter")
	public String deleteChapter(@RequestParam(value = "chapterId") int chapterId,
			@RequestParam(value = "courseId") int courseId, Model model, Principal principal) {
		Course course = courseRepo.findBycourseId(courseId);
		Chapter chapter = course.getChapterList().stream().filter(o -> o.getId() == chapterId).findFirst().get();
		course.getChapterList().removeIf(ch -> ch.getId() == chapterId);
		courseRepo.save(course);
		chapterRepo.delete(chapter);
		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/editChapter")
	public String editChapter(@RequestParam(value = "chapterId") int chapterId,
			@RequestParam(value = "courseId") int courseId, Model model, Principal principal,
			@ModelAttribute("chapter") Chapter chapter1) {
		Course course = courseRepo.findBycourseId(courseId);
		Chapter chapter = course.getChapterList().stream().filter(o -> o.getId() == chapterId).findFirst().get();
		chapter.setName(chapter1.getName());
		courseRepo.save(course);
		chapterRepo.save(chapter);
		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/viewSubTopic")
	public String viewSubTopic(@RequestParam(value = "subTopicId") int subTopicId,
			@RequestParam(value = "chapterId") int chapterId, @RequestParam(value = "courseId") int courseId,
			Model model, Principal principal) {
		String username = principal.getName();
		Lecturer lec = lecturerRepo.findByusername(username);
		Course course = courseRepo.findBycourseId(courseId);
		Chapter chapter = course.getChapterList().stream().filter(o -> o.getId() == chapterId).findFirst().get();

		SubTopic subTop = subTopicRepo.findBysubtopicId(subTopicId);

		// Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
				"0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		com.amazonaws.services.s3.model.S3Object s3object = null;

		for (Bucket bucket : s3client.listBuckets()) {
			if (bucket.getName().equals(lec.getBucketName())) {
				s3object = s3client.getObject(new GetObjectRequest(lec.getBucketName(), subTop.getFile1()));
			}
		}

		if (subTop.getFileExtension().equalsIgnoreCase("pdf")) {
			model.addAttribute("content", s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("fileType", "pdf");
		} else if (subTop.getFileExtension().equalsIgnoreCase("mp4")) {
			model.addAttribute("content", s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("fileType", "mp4");
		} else if (subTop.getFileExtension().equalsIgnoreCase("jpg")) {
			model.addAttribute("content", s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("fileType", "jpeg");
		} else if (subTop.getFileExtension().equalsIgnoreCase("png")) {
			model.addAttribute("content", s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("fileType", "png");
		} 

		courseRepo.save(course);
		chapterRepo.save(chapter);
		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		model.addAttribute("subTopicName", subTop.getName());
		return "lecturers/viewContent";
	}

	@RequestMapping(value = "/deleteSubTopic")
	public String deleteSubTopic(@RequestParam(value = "subTopicId") int subTopicId,
			@RequestParam(value = "chapterId") int chapterId, @RequestParam(value = "courseId") int courseId,
			Model model, Principal principal) {
		Course course = courseRepo.findBycourseId(courseId);
		Chapter chapter = course.getChapterList().stream().filter(o -> o.getId() == chapterId).findFirst().get();
		SubTopic subTop = chapter.getSubtopics().stream().filter(i -> i.getId() == subTopicId).findFirst().get();

		chapter.getSubtopics().removeIf(ch -> ch.getId() == subTopicId);

		courseRepo.save(course);
		chapterRepo.save(chapter);
		subTopicRepo.delete(subTop);

		model.addAttribute("courseName", course.getName());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapters", course.getChapterList());
		return "lecturers/addChapter";
	}

	@RequestMapping(value = "/linkToReset", method = RequestMethod.GET)
	public String linkToReset() {
		return "/resetPassLecturer";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("newPasswordReEnter") String newPasswordReEnter) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Lecturer lecturer = lecturerRepo.findByusername(username);
		if (pe.matches(oldPassword, lecturer.getPassword()) && newPassword.equals(newPasswordReEnter)) {
			lecturer.setPassword(pe.encode(newPassword));
		} else {
			throw new SpringException("You're two passwords didn't match or your old password is wrong.");
		}
		lecturerRepo.save(lecturer);
		return "/passwordIsReset";
	}

	@RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
	//HttpServletRequest request, 
	public String sendNotification(@RequestParam String name, @RequestParam String description, Principal principal) {
		
		String username = principal.getName();
		Lecturer lecturer = lecturerRepo.findByusername(username);
		// reads form input
		List<Course> lecturerCourseList = new ArrayList<Course>();
		for (int courseId : lecturer.getCourseList()) {
			lecturerCourseList.add(courseRepo.findBycourseId(courseId));
		}			

		for (Learner learner : learnerRepo.findAll()) {
				if (!Collections.disjoint(learner.getCourseList(), lecturerCourseList)) {
					String to = learner.getEmail();
					String from = lecturer.getEmail();
					String host = "localhost";
					Properties properties = System.getProperties();
					properties.setProperty("mail.smtp.host", host);
					Session session = Session.getDefaultInstance(properties);

					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(from));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
						message.setSubject(name);
						BodyPart messageBodyPart = new MimeBodyPart();
						messageBodyPart.setText(description);							
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);
						message.setContent(multipart);							
						message.setText(description); 
						Transport.send(message);
					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}   
				}
			}

		return "redirect:/lecturer/";
	}


	
	@RequestMapping(value = "/editDetails", method = RequestMethod.POST)
	public String editDetails(@ModelAttribute("lecturer") Learner lecturer1, Model model, Principal principal) {
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		lecturer.setFirstName(lecturer1.getFirstName());
		lecturer.setLastName(lecturer1.getLastName());
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		lecturer.setPassword(pe.encode(lecturer1.getPassword()));
		lecturer.setEmail(lecturer1.getEmail());
		lecturer.setGender(lecturer1.getGender());
		lecturerRepo.save(lecturer);
		return "redirect:/lecturer/";
	}
	
}
