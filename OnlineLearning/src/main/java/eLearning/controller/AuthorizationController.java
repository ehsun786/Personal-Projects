package eLearning.controller;


import static eLearning.ELearningApp.ROLE_ADMIN;
import static eLearning.ELearningApp.ROLE_LEARNER;
import static eLearning.ELearningApp.ROLE_LECTURER;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eLearning.domain.Course;
import eLearning.repository.AdminRepository;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.UserRepository;;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {
		
	@Autowired UserRepository userRepo;
	@Autowired LearnersRepository learnerRepo;
	@Autowired LecturerRepository lecturerRepo;
	@Autowired AdminRepository adminRepo;
	@Autowired CourseRepository courseRepo;
	
	
	@RequestMapping(value="/user-login", method=RequestMethod.GET)
	public String loginForm(Model model) {
		List<Course> courseList = new ArrayList<Course>();
		int count=1;
		while(count!=7){
			courseList.add(courseRepo.findBycourseId(count));
			count++;
		}
		//model.addAttribute("courseList",courseRepo.findAll());
		model.addAttribute("courseList",courseList);
		model.addAttribute("lecturerList",lecturerRepo.findAll());
		return "index";
	}

	@RequestMapping(value="/error-login", method=RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", "Incorrect username or password! Please Try Again!");
		model.addAttribute("courseList",courseRepo.findAll());
		model.addAttribute("lecturerList",lecturerRepo.findAll());
		return "index";
	}
	
	@RequestMapping(value="/success-login", method=RequestMethod.GET)
	public String successLogin() { //HttpServletRequest httpServletRequest, Authentication authentication
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        String username = authUser.getUsername();
        eLearning.domain.User user;
        user = userRepo.findByUsername(username);
        if(user.getSuspended()) {
        	return "suspended";
        }
        if(user.getRole().getRole().equals("ADMINISTRATOR")) {
        	user = adminRepo.findByusername(authUser.getUsername());	
        } else if(user.getRole().getRole().equals("LEARNER")){
        	user = learnerRepo.findByusername(authUser.getUsername());
        } else if(user.getRole().getRole().equals("LECTURER")) {
        	user = lecturerRepo.findByusername(authUser.getUsername());
        }
        
        String view;

        switch (user.getRole().getId()) {
        	case ROLE_ADMIN:
        		view = "redirect:/twoFacAuth";
        		break;
        	case ROLE_LECTURER: 
        		view = "redirect:/twoFacAuth";
        		break;
        	case ROLE_LEARNER: 
        		view = "redirect:/twoFacAuth";
        		break;
        	default: 
        		view = "security/success-login"; 
        		break;
        }
  
		return view;
	}
		
	@RequestMapping(value="/access-denied", method=RequestMethod.GET)
	public String error() {
		return "security/error-message";
	}
}