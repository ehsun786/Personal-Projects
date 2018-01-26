package eLearning.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

import eLearning.domain.Chapter;
import eLearning.domain.Course;
import eLearning.domain.Learner;
import eLearning.domain.Meetup;
import eLearning.domain.Reviews;
import eLearning.domain.SubTopic;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.MeetupRepository;
import eLearning.repository.ReviewsRepository;
import eLearning.repository.RoleRepository;
import eLearning.repository.SubTopicRepository;
import eLearning.repository.UserRepository;

@Controller
@RequestMapping("/learner")
public class LearnerController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	LearnersRepository learnerRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired 
	LecturerRepository lecturerRepo;
	@Autowired 
	CourseRepository courseRepo;
	@Autowired 
	SubTopicRepository subTopicRepo;
	@Autowired 
	ReviewsRepository reviewRepo;
	@Autowired 
	MeetupRepository meetUpRepo;
	
	
	//Credentials are setup here
    AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
    @SuppressWarnings("deprecation")
	AmazonS3 s3client = new AmazonS3Client(credentials);

	@RequestMapping(value = "/twoFactorAuth")
	public String twoFactorAuth() {
		return "twofa";
	}
	
	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("ADMINISTRATOR")) {
			return "redirect:/admin/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		model.addAttribute("username", username);
	    model.addAttribute("courseList", learner.getCourseList());
	    
	    com.amazonaws.services.s3.model.S3Object s4object;
		if(learner.getProfilePicName()==null){
			model.addAttribute("profilePic", null);
		}else{
			s4object = s3client.getObject(new GetObjectRequest("learnersprofimgs",learner.getProfilePicName()));
			model.addAttribute("profilePic", s4object.getObjectContent().getHttpRequest().getURI().toString());
		}

		com.amazonaws.services.s3.model.S3Object s5object;
		s5object = s3client.getObject(new GetObjectRequest("meetupsicon","meetupsImg1234"));
		model.addAttribute("meetupPic", s5object.getObjectContent().getHttpRequest().getURI().toString());
		
		model.addAttribute("meetupList",learner.getMeetups());
		model.addAttribute("learner", learner);
	   return "learner/success-login";
	}
	
	
	@Transactional
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public String deleteCourse(@RequestParam("courseId") int courseId, Model model, Principal principal) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		
		Course course = courseRepo.findBycourseId(courseId);
		
		Learner learner = learnerRepo.findByusername(username);
		learner.getCourseList().removeIf(c -> c.equals(course));  
		learnerRepo.save(learner);

		model.addAttribute("username", username);
		Learner tempLearner = learnerRepo.findByusername(learner.getUsername());
	    model.addAttribute("courseList", tempLearner.getCourseList());
	    com.amazonaws.services.s3.model.S3Object s4object;
		if(learner.getProfilePicName()==null){
			model.addAttribute("profilePic", null);
		}else{
			s4object = s3client.getObject(new GetObjectRequest("learnersprofimgs",learner.getProfilePicName()));
			model.addAttribute("profilePic", s4object.getObjectContent().getHttpRequest().getURI().toString());
		}
		return "/learner/success-login";
	}
	
	@RequestMapping(value = "/browse-courses", method = RequestMethod.GET)
	public String browsecourses(Model model) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		List<Course> courses = new ArrayList<Course>();
		
		for(Course c :courseRepo.findAll()){
			if(!learner.getCourseList().contains(c)){
				for(Learner l: learnerRepo.findAll()){
					if(l.getCourseList().contains(c))
						if(l.getCourseReviews().get(c.getCourseId())!=null){
							c.getMyReview().put(l, l.getCourseReviews().get(c.getCourseId()).getReviews().get(0));
						}
					}	
				courses.add(c);

				}	
		}
		model.addAttribute("allCourses", courses);
		return "learner/browse-courses";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model, Principal principal) {
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("ADMINISTRATOR")) {
			return "redirect:/admin/";
		}
		if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
			return "redirect:/lecturer/";
		}
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		model.addAttribute("username", username);
		String firstname = learner.getFirstName();
		model.addAttribute("firstname", firstname);
		String lastname = learner.getLastName();
		model.addAttribute("lastname", lastname);
		String email = learner.getEmail();
		model.addAttribute("email", email);
		return "learner/settings";
	}

	
	@RequestMapping(value = "/enrol")
	public String enrol(@RequestParam("courseId") int courseId) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		learner.getCourseList().add(courseRepo.findBycourseId(courseId)); 
		learnerRepo.save(learner);
		return "redirect:/learner/";
	}
	
	
	@RequestMapping(value = "/resumeCourse", method = RequestMethod.GET)
	public String resumeCourse(@RequestParam("courseId") int courseId, Model model) {
		Course course = courseRepo.findBycourseId(courseId);
		model.addAttribute("coursename", course.getName());
		model.addAttribute("chapterList", course.getChapterList());
		model.addAttribute("courseId", courseId);
		model.addAttribute("course", course);
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		com.amazonaws.services.s3.model.S3Object s3object; 
		if(course.getCourseImageName()==null){
			course.setTempImageLinkHolder(null);
		    s3object = s3client.getObject(new GetObjectRequest("defaultcourseimg", "defaultCourseImg"));		    
			model.addAttribute("content",s3object.getObjectContent().getHttpRequest().getURI().toString());
		}else{
			s3object = s3client.getObject(new GetObjectRequest(course.getLecturer().getBucketName(),course.getCourseImageName()));
			course.setTempImageLinkHolder(s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("content", course.getTempImageLinkHolder());
		}
		
		//Get logged in users ratings
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		
		model.addAttribute("showIntro", "YES"); 
		if( learner.getCourseRatings().get(courseId)==null){
			model.addAttribute("rating", 0);	
		}else{
			model.addAttribute("rating", learner.getCourseRatings().get(courseId));		
		}		
		
		
		if(course.getChapterList().size()==0){
			model.addAttribute("reviewDone", "NO");
			return "learner/mycourses";
		}else{
			boolean noSubTopics=false;
			for(Chapter c: course.getChapterList()){
				if(c.getSubtopics().size()!=0){
					noSubTopics=true;
				}
			}
			if(noSubTopics=false){
				model.addAttribute("reviewDone", "NO");
				return "learner/mycourses";				
			}	
		}
			
			
			try{
				if(learner.getCourseReviews().get(courseId).isReviewDone()){
					model.addAttribute("reviewDone", "YES");
				}
			}catch(NullPointerException e){
				boolean finish=true;
				for(Chapter c: course.getChapterList()){
					for(SubTopic s: c.getSubtopics()){
						if(!s.isAccessed()){
							finish=false;
						}
					}
				}
				if(finish){
					model.addAttribute("finish", "YES");
					model.addAttribute("reviewDone", "NO");
				}else{
					model.addAttribute("finish", "NO"); 
					model.addAttribute("reviewDone", "NO");
				}
			
			}
		return "learner/mycourses";
	}

	
	@RequestMapping(value = "/courseIntro", method = RequestMethod.GET)
	public String courseIntro(@RequestParam("courseId") int courseId, Model model) {
		Course course = courseRepo.findBycourseId(courseId);
		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapterList", course.getChapterList());
		
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		com.amazonaws.services.s3.model.S3Object s3object = null; 
		
		if(course.getCourseImageName()==null){
			course.setTempImageLinkHolder(null);
		    s3object = s3client.getObject(new GetObjectRequest("defaultcourseimg", "defaultCourseImg"));		    
			model.addAttribute("content",s3object.getObjectContent().getHttpRequest().getURI().toString());
		}else{
			s3object = s3client.getObject(new GetObjectRequest(course.getLecturer().getBucketName(),course.getCourseImageName()));
			course.setTempImageLinkHolder(s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("content", course.getTempImageLinkHolder());
		}
		model.addAttribute("coursename", course.getName());
		
		//Get logged in users ratings
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		
		model.addAttribute("showIntro", "YES"); 
		if( learner.getCourseRatings().get(courseId)==null){
			model.addAttribute("rating", 0);	
		}else{
			model.addAttribute("rating", learner.getCourseRatings().get(courseId));		
		}
		return "learner/mycourses";
	}
	
	@RequestMapping(value = "/goToSubtopic", method = RequestMethod.GET)
	public String goToSubtopic(@RequestParam("subTopicId") int subTopicId,@RequestParam("courseId") int courseId, @RequestParam("chapterId") int chapterId,
			Model model) {
		SubTopic subTopic = subTopicRepo.findBysubtopicId(subTopicId);
		
		//Set subtopic accessed to true
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		Course course = learner.getCourseList().stream().filter(o -> o.getCourseId()==courseId).findFirst().get();
		Chapter chap = course.getChapterList().stream().filter(o -> o.getId()==chapterId).findFirst().get();
		SubTopic subTopc = chap.getSubtopics().stream().filter(o -> o.getId()==subTopicId).findFirst().get();
		subTopc.setAccessed(true);
		
		learnerRepo.save(learner);
		
		model.addAttribute("fileType", subTopic.getFileExtension());
		model.addAttribute("courseId", courseId);
		
		model.addAttribute("coursename", course.getName());
		model.addAttribute("chapterList", course.getChapterList());
		model.addAttribute("courseId", courseId);
		
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		com.amazonaws.services.s3.model.S3Object s3object = null; 
	    s3object = s3client.getObject(new GetObjectRequest(course.getLecturer().getBucketName(), subTopic.getFile1()));				
		model.addAttribute("content", s3object.getObjectContent().getHttpRequest().getURI().toString());
		
		if(subTopic.getFileExtension().equalsIgnoreCase("pdf")){
			model.addAttribute("fileType","pdf");
		}else if(subTopic.getFileExtension().equalsIgnoreCase("mp4")){
			model.addAttribute("fileType", "mp4"); 
		}else if(subTopic.getFileExtension().equalsIgnoreCase("jpg")){
			model.addAttribute("fileType", "jpeg"); 
		}else if(subTopic.getFileExtension().equalsIgnoreCase("png")){
			model.addAttribute("fileType", "png"); 
		}
		
		model.addAttribute("showIntro", "NO");
		
		return "learner/mycourses";
	}
	
	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public String ratings(@RequestParam("ratingId") int ratingId, @RequestParam("courseId") int courseId, Model model) {
				
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		Course course = learner.getCourseList().stream().filter(o -> o.getCourseId()==courseId).findFirst().get();
		course.setRating(ratingId);
		learnerRepo.save(learner);

		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapterList", course.getChapterList());
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		com.amazonaws.services.s3.model.S3Object s3object = null; 
		
		if(course.getCourseImageName()==null){
			course.setTempImageLinkHolder(null);
		    s3object = s3client.getObject(new GetObjectRequest("defaultcourseimg", "defaultCourseImg"));		    
			model.addAttribute("content",s3object.getObjectContent().getHttpRequest().getURI().toString());
		}else{
			s3object = s3client.getObject(new GetObjectRequest(course.getLecturer().getBucketName(),course.getCourseImageName()));
			course.setTempImageLinkHolder(s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("content", course.getTempImageLinkHolder());
		}
		model.addAttribute("coursename", course.getName());
		
		learner.getCourseRatings().put(courseId, ratingId);
		learnerRepo.save(learner); 
		model.addAttribute("rating", ratingId);		
		model.addAttribute("showIntro", "YES"); 
		
		//update value of average course rating
		int learnersRatingCounter=0;
		double avarageCourseRating=0;
		for(Learner l: learnerRepo.findAll()){
			if(l.getCourseList().contains(course)){
				try{
					if(l.getCourseRatings().get(courseId)>0){
						learnersRatingCounter+=l.getCourseRatings().get(courseId);
					}
				}catch(NullPointerException e){
					learnersRatingCounter+=0;
				}
			}
		}
		avarageCourseRating = Math.round(learnersRatingCounter / 5);
		course.setRating(avarageCourseRating); 
		return "learner/mycourses";
	}
	
	
/*	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public String createComment(@ModelAttribute("learner") Learner learner1, Model model) {
		
		System.out.println("---------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>HELLLLLLLLL");
		
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		Course course = courseRepo.findBycourseId(courseId);
		learner.getComments().put(courseId, learner1.getComment());
		
		List<Learner> learnerOnCourse = new ArrayList<Learner>();
		for(Learner l: learnerRepo.findAll()){
			if(l.getCourseList().contains(course)){
				learnerOnCourse.add(l);
			}
		}	
		
		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapterList", course.getChapterList());
		
		//Get content from AWS bucket
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		com.amazonaws.services.s3.model.S3Object s3object = null; 
		
		if(course.getCourseImageName()==null){
			course.setTempImageLinkHolder(null);
		    s3object = s3client.getObject(new GetObjectRequest("defaultcourseimg", "defaultCourseImg"));		    
			model.addAttribute("content",s3object.getObjectContent().getHttpRequest().getURI().toString());
		}else{
			s3object = s3client.getObject(new GetObjectRequest(course.getLecturer().getBucketName(),course.getCourseImageName()));
			course.setTempImageLinkHolder(s3object.getObjectContent().getHttpRequest().getURI().toString());
			model.addAttribute("content", course.getTempImageLinkHolder());
		}
		model.addAttribute("coursename", course.getName());
		model.addAttribute("courseId", course.getCourseId());		
		model.addAttribute("learners",learnerOnCourse);
		model.addAttribute("coursename", course.getName());
		model.addAttribute("rating", learner.getCourseRatings().get(courseId)); 
		model.addAttribute("showIntro", "YES"); 
		return "/resetPassLearner";
	}*/
	
	
	@RequestMapping(value="/review", method= RequestMethod.POST)
	public String review(@RequestParam String rev, @RequestParam("courseId") int courseId){
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authUser.getUsername();
		Learner learner = learnerRepo.findByusername(username);
		Reviews review = new Reviews();
		review.getReviews().add(rev);
		review.setReviewDone(true); 
		learner.getCourseReviews().put(courseId, review);
		reviewRepo.save(review);
		learnerRepo.save(learner); 
		return "redirect:/learner/";
	}
	
		@RequestMapping(value = "/linkToReset", method = RequestMethod.GET)
		public String linkToReset(Principal principal) {
			if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("ADMINISTRATOR")) {
				return "redirect:/admin/";
			}
			if(userRepo.findByUsername(principal.getName()).getRole().getRole().equals("LECTURER")) {
				return "redirect:/lecturer";
			}
			return "learner/mycourses";
		}

		@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
		public String resetPassword(@RequestParam("oldPassword") String oldPassword,
				@RequestParam("newPassword") String newPassword,
				@RequestParam("newPasswordReEnter") String newPasswordReEnter) {
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = authUser.getUsername();
			Learner learner = learnerRepo.findByusername(username);
			if (pe.matches(oldPassword, learner.getPassword()) && newPassword.equals(newPasswordReEnter)) {
			    learner.setPassword(pe.encode(newPassword));
			} else {
			    throw new SpringException("You're two passwords didn't match or your old password is wrong.");
			}
			learnerRepo.save(learner);
			return "/passwordIsReset";
		}

		@RequestMapping(value = "/editDetails", method = RequestMethod.POST)
		public String editDetails(@ModelAttribute("learner") Learner learner1, Model model, Principal principal) {
			Learner learner = learnerRepo.findByusername(principal.getName());
			learner.setFirstName(learner1.getFirstName());
			learner.setLastName(learner1.getLastName());
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			learner.setPassword(pe.encode(learner1.getPassword()));
			learner.setEmail(learner1.getEmail());
			learner.setGender(learner1.getGender());
			learnerRepo.save(learner);
			return "redirect:/learner/";
		}

		
		@RequestMapping(value = "/openMeetup", method = RequestMethod.GET)
		public String openMeetup(@RequestParam("meetupId") int meetupId, Model model) {
			System.out.println("--------------------->>>>>>>>>>>>>>>>>>>>2");
			Meetup meetup = meetUpRepo.findByMeetupId(meetupId);
			model.addAttribute("meetup", meetup);
			System.out.println("--------------------->>>>>>>>>>>>>>>>>>>>1");
			return "maps";
		}
		
		
}
