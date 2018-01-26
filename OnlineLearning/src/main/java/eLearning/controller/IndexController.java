package eLearning.controller;

import static eLearning.ELearningApp.ROLE_ADMIN;
import static eLearning.ELearningApp.ROLE_LEARNER;
import static eLearning.ELearningApp.ROLE_LECTURER;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import eLearning.domain.Learner;
import eLearning.domain.Lecturer;
import eLearning.domain.Role;
import eLearning.domain.User;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.UserRepository;

@MultipartConfig(maxFileSize=900000)
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired LearnersRepository learnerRepo;
	@Autowired UserRepository userRepo;
	@Autowired LecturerRepository lecturerRepo;
	@Autowired CourseRepository courseRepo;
	
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("courseList",courseRepo.findAll());
		model.addAttribute("lecturerList",lecturerRepo.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/twoFactorAuthCheck", method = RequestMethod.GET)
	public String twoFactorAuthCheck(@RequestParam(value = "verification") int verification, Principal principal, Model model) {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
				
		if (userRepo.findByUsername(username).getSecurityCode() == verification) {
			if(userRepo.findByUsername(username).getRole().getId().equals(ROLE_ADMIN)) {
				return "redirect:/admin/";
			} else if(userRepo.findByUsername(username).getRole().getId().equals(ROLE_LECTURER)) {
				return "redirect:/lecturer/";
			} else if(userRepo.findByUsername(username).getRole().getId().equals(ROLE_LEARNER)) {
				return "redirect:/learner/";
			}
		} 
		model.addAttribute("error2", "You have entered an incorrect code! Please Try Again");
		return "twofa";
	}
	
	   @RequestMapping(value = "/register", method = RequestMethod.GET)
	    public String showRegisterPage(ModelMap model) {
	        return "index1";
	    }

	    @RequestMapping(value = "/register1", method = RequestMethod.POST)
	    public String handleRegisterRequest(ModelMap model, @ModelAttribute("learner") Learner learner) {	    	
	    	for(eLearning.domain.User users: userRepo.findAll()){
	    		if(users.getUsername().equals(learner.getUsername())){
	    			model.addAttribute("error", "Username already exists, please choose another!");
	    			return "index1";
	    		}
	    	}
			BCryptPasswordEncoder en = new BCryptPasswordEncoder();
			Learner learner1 = new Learner();
			learner1.setUsername(learner.getUsername());
			learner1.setFirstName(learner.getFirstName());
			learner1.setLastName(learner.getLastName());
			learner1.setPassword(en.encode(learner.getPassword()));
			learner1.setEmail(learner.getEmail());
			learner1.setBadges(null);
			learner1.setComments(null);
			learner1.setCourseList(null);
			learner1.setGender("Male");
			Role role = new Role();
			role.setId(ROLE_LEARNER);
			role.setRole("LEARNER");
			learner1.setRole(role);
			learnerRepo.save(learner1);
			return "learner/success-login";
	    }
	    
	    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET) 
	    public String forgotPassword() {
	    	return "/forgotPass";
	    }
	    
		@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
		public String resetPassword(@RequestParam("username") String username, Model model) {
			
			if(userRepo.findByUsername(username)==null){
				model.addAttribute("error1", "Username does not exists. Please Try Again!");
				return "forgotPass";
			}
			
			
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			User user = userRepo.findByUsername(username);

			int randomCode1 = (int)(Math.random()*9000)+1000;
			String tempPassword = randomCode1+"jumbojet"+randomCode1;
			
			user.setPassword(pe.encode(tempPassword));
			userRepo.save(user);
			String email = user.getEmail();
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
				message.setSubject("Password Reset");
				// Now set the actual message
				message.setText("Please use the password: "+tempPassword+" to login.");
				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}

			return "passwordIsReset";
		}
	    
	    @RequestMapping(value = "/registerLecturer", method = RequestMethod.POST)
	    public String handleLecturerRegisterRequest(ModelMap model, @ModelAttribute("lecturer") Lecturer lecturer, 
	    		@RequestParam(value = "email1") String email1, @RequestParam(value = "password1") String password1,
	    		@RequestParam(value = "cv") MultipartFile cv, @RequestParam(value = "coverLetter") MultipartFile coverLetter) {
	    	
				    
	    	for(eLearning.domain.User users: userRepo.findAll()){
	    		if(users.getUsername().equals(lecturer.getUsername())){
	    			model.addAttribute("error1", "Username already exists, please choose another!");
	    			return "index1";
	    		}
	    	}
	  
	    	
	    	//Upload CV and Cover Letter of lecturer to AWS bucket.---------------------------------------------------
	    	
	    	//Credentials are setup here
	        AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
	        @SuppressWarnings("deprecation")
	    	AmazonS3 s3client = new AmazonS3Client(credentials);
	        
	        
	    	//Convert Multipart file to a File---CV
			 File convFile = new File(cv.getOriginalFilename());
		
			try {
				convFile.createNewFile();
			    FileOutputStream fos;
				fos = new FileOutputStream(convFile);
				fos.write(cv.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
				
		    //Convert Multipart file to a File--CoverLetter
			File convFile1 = new File(cv.getOriginalFilename());
			
			try {
				convFile1.createNewFile();
			    FileOutputStream fos1;
				fos1 = new FileOutputStream(convFile1);
				fos1.write(coverLetter.getBytes());
				fos1.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
			int randomCode = (int)(Math.random()*9000)+1000;
			String cvName = randomCode + cv.getOriginalFilename() + randomCode; //Ensures 2 subtopics cannot have the same name.
					
			
			int randomCode1 = (int)(Math.random()*9000)+1000;
			String coverLName = randomCode1 + coverLetter.getOriginalFilename() + randomCode1; //Ensures 2 subtopics cannot have the same name.					
				    
				
			//Upload CV to AWS
			s3client.putObject(new PutObjectRequest("cvsandcls", cvName, convFile));
			s3client.setObjectAcl("cvsandcls", cvName, CannedAccessControlList.PublicRead);
			   
			//Upload CoverLetter to AWS
			s3client.putObject(new PutObjectRequest("cvsandcls", coverLName, convFile1));
			s3client.setObjectAcl("cvsandcls", coverLName, CannedAccessControlList.PublicRead);
				    
	    	
			BCryptPasswordEncoder en = new BCryptPasswordEncoder();
			Lecturer lecturer1 = new Lecturer();
			
			lecturer1.setCvName(cvName);
			lecturer1.setCoverLetterName(coverLName); 
			
			lecturer1.setUsername(lecturer.getUsername());
			lecturer1.setFirstName(lecturer.getFirstName());
			lecturer1.setLastName(lecturer.getLastName());
			
			password1 = password1.substring(1); //Did this because the form returns email and password with comma at the front. So I remove the comma.
			email1 = email1.substring(1);
			
			lecturer1.setPassword(en.encode(password1));
			
			lecturer1.setEmail(email1); 
			lecturer1.setGender(lecturer.getGender());
			lecturer1.setAuthorized(false); 
			Role role = new Role();
			role.setId(ROLE_LECTURER);
			role.setRole("LECTURER");
			lecturer1.setRole(role);
			lecturerRepo.save(lecturer1);
			return "lecturers/pending";
	    }

	     
	    @RequestMapping(value = "/twoFacAuth", method = RequestMethod.GET)
	    public String twoFacAuth(Model model,Principal principal){
	    	
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			eLearning.domain.User user = userRepo.findByUsername(username);
	        int securityCode = (int)(Math.random()*9000)+1000;
	        user.setSecurityCode(securityCode);
			userRepo.save(user);
			String email = user.getEmail();
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
				message.setSubject("You Secret Password!"); 
				// Now set the actual message
				message.setText("Hi, to complete your logging in process. You'll need to use the code in this email."+System.getProperty("line.separator")+
								"Please use the following password "+securityCode+"."+System.getProperty("line.separator")+
								"Please note this password will change everytime you try to log in.");
				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully...."+securityCode);
				
				model.addAttribute("username", user.getUsername());
				model.addAttribute("code", user.getSecurityCode());
				
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
			return "twofa"; 
	    }
	    
	    @RequestMapping(value="/maps")
	    public String maps(){
	    	return "maps";
	    }
	    
	    @RequestMapping(value="/contactUs")
	    public String contactUs(@RequestParam("name") String name, @RequestParam("email") String email1, @RequestParam("phone") String phone, @RequestParam("message") String message1){
	    	String to = "danial_123@hotmail.co.uk";
			// Sender's email ID needs to be mentioned
			String from = email1;
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
				message.setSubject("COntact Us Form! - E-Learning"); 
				// Now set the actual message
				message.setText(message1); 
				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");				
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}    	
	    	
	    	
	    	return "redirect:/";
	    }
	    
	    
	    
}
