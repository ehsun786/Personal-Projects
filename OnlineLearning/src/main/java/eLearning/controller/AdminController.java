package eLearning.controller;

import static eLearning.ELearningApp.ROLE_ADMIN;
import static eLearning.ELearningApp.ROLE_LEARNER;
import static eLearning.ELearningApp.ROLE_LECTURER;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.google.common.collect.Lists;

import eLearning.domain.Administrator;
import eLearning.domain.ChartData;
import eLearning.domain.Course;
import eLearning.domain.Learner;
import eLearning.domain.Lecturer;
import eLearning.domain.Role;
import eLearning.domain.SubTopic;
import eLearning.domain.User;
import eLearning.repository.AdminRepository;
import eLearning.repository.ChapterRepository;
import eLearning.repository.ChartDataRepository;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.MailRepository;
import eLearning.repository.MeetupRepository;
import eLearning.repository.PaymentRepository;
import eLearning.repository.RoleRepository;
import eLearning.repository.SubTopicRepository;
import eLearning.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	LearnersRepository learnerRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	SubTopicRepository subtopicRepo;
	@Autowired
	ChapterRepository chapterRepo;
	@Autowired
	LecturerRepository lecturerRepo;
	@Autowired
	MailRepository mailRepo;
	@Autowired
	MeetupRepository meetupRepo;
	@Autowired
	PaymentRepository paymentRepo;
	@Autowired
	ChartDataRepository chartDataRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LEARNER")) {
			return "redirect:/learner/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}
		model.addAttribute("lecturers", lecturerRepo.findAll());
		model.addAttribute("learners", learnerRepo.findAll());
		model.addAttribute("admins", adminRepo.findAll());
		model.addAttribute("users", userRepo.findAll());
		
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    List<String> usernameList = new ArrayList<>();
	    for(Lecturer lecturer : lecturerRepo.findAll()) {
	    	usernameList.add(lecturer.getUsername());
	    }
	    for(Learner learner : learnerRepo.findAll()) {
	    	usernameList.add(learner.getUsername());
	    }
	    model.addAttribute("usernameList", usernameList);
		model.addAttribute("username", username);
		model.addAttribute("totalCourses", Lists.newArrayList(courseRepo.findAll()).size());
		model.addAttribute("totalChapters", Lists.newArrayList(chapterRepo.findAll()).size());
		model.addAttribute("totalSubTopics", Lists.newArrayList(subtopicRepo.findAll()).size());
		model.addAttribute("totalUsers", Lists.newArrayList(userRepo.findAll()).size());
		model.addAttribute("totalMeetups", Lists.newArrayList(meetupRepo.findAll()).size());
		model.addAttribute("totalLearners", Lists.newArrayList(learnerRepo.findAll()).size());
		model.addAttribute("totalLecturers", Lists.newArrayList(lecturerRepo.findAll()).size());
		model.addAttribute("learnerToLecturerRatio", (double) (Lists.newArrayList(learnerRepo.findAll()).size()/Lists.newArrayList(lecturerRepo.findAll()).size()));
		model.addAttribute("totalAdministrators", Lists.newArrayList(adminRepo.findAll()).size());

		model.addAttribute("first", chartDataRepo.findById(1));
		model.addAttribute("second", chartDataRepo.findById(2));
		model.addAttribute("third", chartDataRepo.findById(3));
		model.addAttribute("fourth", chartDataRepo.findById(4));
		return "admin/success-login";
	}
	
	@RequestMapping(value = "/barData")
	public String barData(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LEARNER")) {
			return "redirect:/learner/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}

		List<ChartData> chartDataList = Lists.newArrayList(chartDataRepo.findAll());
		ChartData[] chartDataArray = chartDataList.toArray(new ChartData[chartDataList.size()]);
		
		model.addAttribute("chart", chartDataArray);
		return "admin/stats";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String monitor(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LEARNER")) {
			return "redirect:/learner/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		List<eLearning.domain.User> suspendedUsers = new ArrayList<>();
		for(eLearning.domain.User usr :userRepo.findAll()){
			if(usr.getSuspended()){
				suspendedUsers.add(usr);
			}
		}
		model.addAttribute("suspendedUsers",suspendedUsers);
		model.addAttribute("allUsers", userRepo.findAll());
		model.addAttribute("username", username);

		return "admin/users";
	}

	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	public String requests(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LEARNER")) {
			return "redirect:/learner/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}
		List<Lecturer> pendingLecs = new ArrayList<>();
		for (Lecturer lec : lecturerRepo.findAll()) {
			if (lec.isAuthorized() == false) {
				pendingLecs.add(lec);
			}
		}
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
		model.addAttribute("pendingLecs", pendingLecs);
		return "admin/requests";
	}

	
	@RequestMapping(value = "/viewCV", method = RequestMethod.GET)
	public String viewCV(@RequestParam("username") String username, Model model) {
		Lecturer lecturer = lecturerRepo.findByusername(username);
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		com.amazonaws.services.s3.model.S3Object s3object = null; 
		
		s3object = s3client.getObject(new GetObjectRequest("cvsandcls", lecturer.getCvName()));				
		lecturer.setCvNameForAdmin(s3object.getObjectContent().getHttpRequest().getURI().toString());
		model.addAttribute("cvsandcls",s3object.getObjectContent().getHttpRequest().getURI().toString());
		return "admin/viewApplication";
	}
	
	@RequestMapping(value = "/viewCL", method = RequestMethod.GET)
	public String viewCL(@RequestParam("username") String username, Model model) {
		Lecturer lecturer = lecturerRepo.findByusername(username);
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		com.amazonaws.services.s3.model.S3Object s3object = null; 
		
		s3object = s3client.getObject(new GetObjectRequest("cvsandcls", lecturer.getCoverLetterName()));				
		lecturer.setClNameForAdmin((s3object.getObjectContent().getHttpRequest().getURI().toString()));
		model.addAttribute("cvsandcls",s3object.getObjectContent().getHttpRequest().getURI().toString());		
		return "admin/viewApplication";
	}
	
	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	public String monitoring(Model model) {
		model.addAttribute("lecturers", lecturerRepo.findAll());
		model.addAttribute("learners", learnerRepo.findAll());
		model.addAttribute("admins", adminRepo.findAll());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "admin/monitor";
	}
	
	@Transactional
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public String messages(@RequestParam("username") String username) {
		User user = userRepo.findByUsername(username);
		user.setRole(null);
		userRepo.save(user);
		userRepo.removeByusername(username);
		
		return "redirect:/admin/requests";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAdmin(@ModelAttribute("admin") Administrator admin, Model model) {
		
		for(eLearning.domain.User users: userRepo.findAll()){
    		if(users.getUsername().equals(admin.getUsername())){
    			model.addAttribute("error1", "Username already exists, please choose another!");
    			return "admin/users";
    		}
		}
		
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		Administrator m = new Administrator();
		m.setUsername(admin.getUsername());
		m.setFirstName(admin.getFirstName());
		m.setLastName(admin.getLastName());
		m.setPassword(pe.encode(admin.getPassword()));
		m.setEmail(admin.getEmail());
		m.setGender(admin.getGender());
		Role role = new Role();
		role.setId(ROLE_ADMIN);
		role.setRole("ADMINISTRATOR");
		m.setRole(role);
		adminRepo.save(m);
		
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		model.addAttribute("username", username);
		
		return "admin/users";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@ModelAttribute("admin") Administrator admin, Model model) {
		
		if ((adminRepo.findByusername(admin.getUsername()))==null){
			model.addAttribute("error2", "Username does not exist");
			return "admin/users";
		}
		
		Administrator administrator = adminRepo.findByusername(admin.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		administrator.setPassword(encoder.encode(admin.getPassword()));
		adminRepo.save(administrator);
		
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		model.addAttribute("username", username);
		return "admin/users";
	}

	@Transactional
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public String delete(@ModelAttribute("user") User user, Model model) {
		if ((userRepo.findByUsername(user.getUsername()))==null){
			model.addAttribute("error3", "Username does not exist");
			return "admin/users";
		} else {
			Lecturer lecturer = lecturerRepo.findByusername(user.getUsername());
			Learner learner = learnerRepo.findByusername(user.getUsername());
			Administrator admin = adminRepo.findByusername(user.getUsername());
			if(lecturer!=null) {
				lecturer.setChatList(null);
				lecturer.setMeetupList(null);
				lecturer.setRole(null);
				for(int id : lecturer.getCourseList()) {
					courseRepo.delete(id);
					lecturer.getCourseList().remove((int) id);
				}
				lecturerRepo.save(lecturer);
				lecturerRepo.delete(lecturer);
				return "admin/success-login";
			} else if(learner!=null) {
				learner.setBadges(null);
				learner.setChatList(null);
				learner.setComments(null);
				learner.setCourseList(null);
				learner.setCourseRatings(null);
				learner.setCourseReviews(null);
				learner.setRole(null);
				learnerRepo.save(learner);
				learnerRepo.delete(learner);
				return "admin/success-login";
			} else if(admin!=null) {
				admin.setChatList(null);
				admin.setRole(null);
				adminRepo.save(admin);
				adminRepo.delete(admin);
				return "admin/success-login";
			}
		} 
		userRepo.removeByusername(user.getUsername());
		
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		model.addAttribute("username", username);
		return "admin/success-login";
	}

	@RequestMapping(value = "/suspendAccount", method = RequestMethod.POST)
	public String suspend(@ModelAttribute("user") User user, Model model) {
		User user1;
		if ((userRepo.findByUsername(user.getUsername()))==null){
			model.addAttribute("error4", "Username does not exist");
			return "admin/users";
		}
		user1 = userRepo.findByUsername(user.getUsername());
		user1.setSuspend(true);
		userRepo.save(user1);
		
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		model.addAttribute("username", username);
		return "admin/users";
	}

	// authorizeLecs
	@RequestMapping(value = "/authorizeLecs", method = RequestMethod.GET)
	public String suspend(@RequestParam(value = "lecturerUsername", required = true) String username, Model model) {
		Lecturer lecturer1 = lecturerRepo.findByusername(username);
		lecturer1.setAuthorized(true);
		List<Lecturer> pendingLecs = new ArrayList<>();
		for (Lecturer lec : lecturerRepo.findAll()) {
			if (lec.isAuthorized() == false) {
				pendingLecs.add(lec);
			}
		}
		model.addAttribute("pendingLecs", pendingLecs);
	   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username1 = authentication.getName();
	    
		//Credentials are setup here
	    AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
	    @SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    int randomCode = (int)(Math.random()*9000)+1000;
	    String lecsUniqueBucketName = randomCode+lecturer1.getUsername()+randomCode; 
	    s3client.createBucket(lecsUniqueBucketName);
	    lecturer1.setBucketName(lecsUniqueBucketName);
	    lecturerRepo.save(lecturer1);
	    
	    
	    //Send email to lecturer who has been authorized
		String email = lecturer1.getEmail();
		String to = email;
		// Sender's email ID needs to be mentioned
		String from = "avatarLearning@academy.com";
		// Assuming you are sending email from localhost
		String host = "localhost";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: header field
			message.setSubject("Congratulations!!!");
			// Now set the actual message
			message.setText("Hey "+username+". Congrats on becoming a certified lecturer for E-Learning. :)");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	    
		model.addAttribute("username", username1);
		return "admin/requests";
	}	
	
	
	
	@RequestMapping(value = "/findUserMonitor", method = RequestMethod.GET)
	public String findUserMonitor(@RequestParam(value = "search", required = true) String search, Model model) {
		eLearning.domain.User user;
		if(userRepo.findByUsername(search)==null){
			model.addAttribute("error5", "Username does not exists! Please Try Again");
			return "admin/monitor";
		}
		
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
	    
	        
		user = userRepo.findByUsername(search);
		if(user.getRole().getId().equals(ROLE_LEARNER)){
			user = learnerRepo.findByusername(authUser.getUsername());
			model.addAttribute("role","Learner");
			model.addAttribute("name",user.getFirstName()+" "+user.getLastName());
			model.addAttribute("coursesCompleted","5");
			model.addAttribute("coursesInProgress","5");
			model.addAttribute("memberSince",user.getDate());
			model.addAttribute("courseList",user);
		}else if(user.getRole().getId().equals(ROLE_LECTURER)){
			
		}
	
		return "admin/monitor";
	}
	
	@RequestMapping(value="/specific-data", method = RequestMethod.GET)
	public String data(@RequestParam("username") String username, Model model) {
		Lecturer lecturer = Lists.newArrayList(lecturerRepo.findAll()).stream().filter(lec -> lec.getUsername().equals(username)).findFirst().orElse(null);
		Learner learner = Lists.newArrayList(learnerRepo.findAll()).stream().filter(lrnr -> lrnr.getUsername().equals(username)).findFirst().orElse(null);
		if(lecturer != null) {
			List<Course> courseList = new ArrayList<>(); 
			for(int courseId : lecturer.getCourseList()) {
				courseList.add(courseRepo.findBycourseId(courseId));
			}
			model.addAttribute("courseList", courseList);
			model.addAttribute("meetupList", lecturer.getMeetupList());
			model.addAttribute("bucketName", lecturer.getBucketName());
			model.addAttribute("clNameforAdmin", lecturer.getClNameForAdmin());
			model.addAttribute("coverLetterName", lecturer.getCoverLetterName());
			model.addAttribute("cvName", lecturer.getCvName());
			model.addAttribute("cvNameForAdmin", lecturer.getCvNameForAdmin());
			model.addAttribute("dateJoined", lecturer.getDate());
			model.addAttribute("description", lecturer.getDescription());
			model.addAttribute("firstName", lecturer.getFirstName());
			model.addAttribute("gender", lecturer.getGender());
			model.addAttribute("lastName", lecturer.getLastName());
			model.addAttribute("mail", lecturer.getMail());
			model.addAttribute("networking", lecturer.getNetworking());
			model.addAttribute("username", username);
			model.addAttribute("totalCourses", lecturer.getCourseList().size());
			model.addAttribute("totalMeetups", lecturer.getMeetupList().size());
		} else {
			model.addAttribute("courseList", learner.getCourseList());
			List<SubTopic> subtopicList = new ArrayList<SubTopic>();
			for(int subTopicId: learner.getAccessedSubTopics()) {
				subtopicList.add(subtopicRepo.findBysubtopicId(subTopicId));
			}
			
			model.addAttribute("accessedSubtopics", subtopicList);
			model.addAttribute("badgeList", learner.getBadges());
			model.addAttribute("dateJoined", learner.getDate());
			model.addAttribute("email", learner.getEmail());
			model.addAttribute("firstname", learner.getFirstName());
			model.addAttribute("gender", learner.getGender());
			model.addAttribute("lastname", learner.getLastName());
			model.addAttribute("meetupList", learner.getMeetups());
			model.addAttribute("profilePicName", learner.getProfilePicName());
			model.addAttribute("username", username);
			model.addAttribute("totalCourses", learner.getCourseList().size());
			model.addAttribute("totalBadges", learner.getBadges().size());
			model.addAttribute("totalMeetups", learner.getMeetups().size());
			model.addAttribute("totalSubtopicsAccessed", learner.getAccessedSubTopics().size());
		}
		return "admin/success-login";
	}
	
	
	@RequestMapping(value = "/monitorUser", method = RequestMethod.GET)
	public String monitorUser(@RequestParam(value = "username") String username, Model model) {
		eLearning.domain.User user;    
	        
		user = userRepo.findByUsername(username);
		
		if(user.getRole().getRole().equals("LECTURER")) {
			Lecturer lecturer = lecturerRepo.findByusername(username);
			List<Course> courseList = new ArrayList<>(); 
			for(int courseId : lecturer.getCourseList()) {
				courseList.add(courseRepo.findBycourseId(courseId));
			}
			model.addAttribute("courseList", courseList);
			model.addAttribute("meetupList", lecturer.getMeetupList());
			model.addAttribute("bucketName", lecturer.getBucketName());
			model.addAttribute("cvName", lecturer.getCvName());
			model.addAttribute("cvNameForAdmin", lecturer.getCvNameForAdmin());
			model.addAttribute("dateJoined", lecturer.getDate());
			model.addAttribute("description", lecturer.getDescription());
			model.addAttribute("firstName", lecturer.getFirstName());
			model.addAttribute("gender", lecturer.getGender());
			model.addAttribute("lastName", lecturer.getLastName());
			model.addAttribute("mail", lecturer.getMail());
			model.addAttribute("networking", lecturer.getNetworking());
			model.addAttribute("username", username);
			model.addAttribute("totalCourses", lecturer.getCourseList().size());
			model.addAttribute("myRole", "LECTURER");
			
		} else if(user.getRole().getRole().equals("LEARNER")) {
			
			Learner learner = learnerRepo.findByusername(username);
			model.addAttribute("courseList", learner.getCourseList());
			List<SubTopic> subtopicList = new ArrayList<SubTopic>();
			for(int subTopicId: learner.getAccessedSubTopics()) {
				subtopicList.add(subtopicRepo.findBysubtopicId(subTopicId));
			}
			
			model.addAttribute("accessedSubtopics", subtopicList);
			model.addAttribute("badgeList", learner.getBadges());
			model.addAttribute("dateJoined", learner.getDate());
			model.addAttribute("email", learner.getEmail());
			model.addAttribute("firstname", learner.getFirstName());
			model.addAttribute("gender", learner.getGender());
			model.addAttribute("lastname", learner.getLastName());
			model.addAttribute("meetupList", learner.getMeetups());
			model.addAttribute("profilePicName", learner.getProfilePicName());
			model.addAttribute("username", username);
			model.addAttribute("totalCourses", learner.getCourseList().size());
			model.addAttribute("totalBadges", learner.getBadges().size());
			model.addAttribute("totalMeetups", learner.getMeetups().size());
			model.addAttribute("meetups", learner.getMeetups());
			model.addAttribute("totalSubtopicsAccessed", learner.getAccessedSubTopics());

		}
	
		return "admin/monitor";
	}
	
	
	
}

























