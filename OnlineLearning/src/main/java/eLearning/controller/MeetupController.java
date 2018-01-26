package eLearning.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

import eLearning.domain.Course;
import eLearning.domain.Learner;
import eLearning.domain.Lecturer;
import eLearning.domain.Meetup;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.MeetupRepository;

@Controller
@RequestMapping("/meetups")
public class MeetupController {

	@Autowired
	MeetupRepository meetupRepo;
	@Autowired
	LecturerRepository lecturerRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	LearnersRepository learnerRepo;

	@RequestMapping(value = "/")
	public String index(Model model, Principal principal) {
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		List<Course> courseList = new ArrayList<>();
		for(int courseId : lecturer.getCourseList()) {
			courseList.add(courseRepo.findBycourseId(courseId));
		}
		
		//Credentials are setup here
	    AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
	    @SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    //Get meetups icon
		com.amazonaws.services.s3.model.S3Object s5object;
		s5object = s3client.getObject(new GetObjectRequest("meetupsicon","meetupsImg1234"));
		model.addAttribute("meetupPic", s5object.getObjectContent().getHttpRequest().getURI().toString());
		
		model.addAttribute("meetupList", lecturer.getMeetupList());
		model.addAttribute("courseList", courseList);
		return "meetups/index";
	}

	@RequestMapping(value = "/courseChosen", method = RequestMethod.GET)
	public String courseChosen(@RequestParam("courseId") int courseId, Model model) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("meetup", new Meetup());
		return "meetups/createMeetups";
	}

	@RequestMapping(value = "/setMeetup", method = RequestMethod.POST)
	public String setMeetup(@RequestParam("description") String desc, @RequestParam("buildingNumber") int buildNo, @RequestParam("street") String street,
			@RequestParam("dayAndDate") String dayAndDate1, @RequestParam("atHour") int atHour, @RequestParam("atMinute") int atMinute, 
			@RequestParam("estimatedHours") int estimatedHours, @RequestParam("estimatedMinutes") int estimatedMinutes, @RequestParam("course") String course1,
			@RequestParam("postcode") String postcode, Principal principal, Model model) {

	
		String[] parts = dayAndDate1.split("/");
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int date = Integer.parseInt(parts[2]);
		
		int hour = atHour;
		int minute = atMinute;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar(year, month, date, hour, minute, 00);
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String day = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
		String dayAndDate = day + " " + sdf.format(calendar.getTime());
				
		Meetup meetup = new Meetup();
		Course course=new Course();
		for(Course c: courseRepo.findAll()){
			if(c.getName().equals(course1)){
				course=c;
			}
		}

		meetup.setDayAndDate(dayAndDate);
		meetup.setCourse(course);
		meetup.setDescription(desc);
		meetup.setBuildingNumber(Integer.toString(buildNo));
		meetup.setPostcode(postcode); 
		meetup.setStreet(street);
		meetup.setAtHour(atHour);
		meetup.setAtMinute(atMinute);
		meetup.setEstimatedHours(estimatedHours);
		meetup.setEstimatedMinutes(estimatedMinutes);
		
		
		
		List<Learner> learnerList = Lists.newArrayList(learnerRepo.findAll());
		for(Learner learner : learnerList) {
			if(learner.getCourseList().contains(course))  {
				learner.getMeetups().add(meetup);
				learnerRepo.save(learner);
				Learner checkMeetup = learnerRepo.findByusername(learner.getUsername());
			}
		}
		
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		lecturer.getMeetupList().add(meetup);
		lecturerRepo.save(lecturer);
		meetupRepo.save(meetup);
		
		
		//Credentials are setup here
	    AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
	    @SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    //Get meetups icon
		com.amazonaws.services.s3.model.S3Object s5object;
		s5object = s3client.getObject(new GetObjectRequest("meetupsicon","meetupsImg1234"));
		model.addAttribute("meetupPic", s5object.getObjectContent().getHttpRequest().getURI().toString());
		
		model.addAttribute("meetupList", lecturer.getMeetupList());
		
		return "redirect:/meetups/";
	}
	
	@RequestMapping(value = "/editMeetup", method = RequestMethod.GET) 
	public String editMeetup(@RequestParam("meetupId") int meetupId, Model model) {
		Meetup meetup = meetupRepo.findByMeetupId(meetupId);
		model.addAttribute("meetup", meetup);
		return "meetups/editMeetups";
	}

	@RequestMapping(value = "/editMeetup1", method = RequestMethod.POST)
	public String editMeetup1(@RequestParam("description") String desc, @RequestParam("buildingNumber") int buildNo, @RequestParam("street") String street,
			@RequestParam("dayAndDate") String dayAndDate1, @RequestParam("atHour") int atHour, @RequestParam("atMinute") int atMinute, 
			@RequestParam("estimatedHours") int estimatedHours, @RequestParam("estimatedMinutes") int estimatedMinutes, @RequestParam("course") String course1,
			@RequestParam("postcode") String postcode, Principal principal, Model model, @RequestParam("meetupId") int meetupId) {
		
		/*String[] parts = meetup.getDayAndDate().split("/");
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int date = Integer.parseInt(parts[2]);
		int hour = meetup.getAtHour();
		int minute = meetup.getAtMinute();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar(year, month, date, hour, minute, 00);
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String day = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
		String dayAndDate = day + " " + sdf.format(calendar.getTime());
		meetup.setDayAndDate(dayAndDate);
		List<Learner> learnerList = Lists.newArrayList(learnerRepo.findAll());
		Meetup tmpMeetup = meetupRepo.findByMeetupId(meetupId);
		tmpMeetup.setDescription(meetup.getDescription());
		tmpMeetup.setBuildingNumber(meetup.getBuildingNumber());
		tmpMeetup.setStreet(meetup.getStreet());
		tmpMeetup.setPostcode(meetup.getPostcode());
		tmpMeetup.setAtHour(meetup.getAtHour());
		tmpMeetup.setAtMinute(meetup.getAtHour());
		tmpMeetup.setEstimatedHours(meetup.getEstimatedHours());
		tmpMeetup.setEstimatedMinutes(meetup.getEstimatedMinutes());
		tmpMeetup.setRecurring(meetup.getRecurring());
		
		
		
		for(Learner learner : learnerList) {
			if(learner.getMeetups().stream().filter(c -> c.getMeetupId()==meetupId).findFirst().orElse(null)!=null) {
				learner.getMeetups().removeIf(mtp -> mtp.getMeetupId() == meetupId);
				learner.getMeetups().add(tmpMeetup);
				learnerRepo.save(learner);
			}
		}
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		lecturer.getMeetupList().removeIf(mtp -> mtp.getMeetupId() == meetupId);
		lecturer.getMeetupList().add(meetup);
		lecturerRepo.save(lecturer);
		model.addAttribute("meetupList", lecturer.getMeetupList());
		meetupRepo.delete(meetupId);
		meetupRepo.save(meetup);*/
		return "redirect:/meetups/";
	}
	
	
	@Transactional
	@RequestMapping(value = "/deleteMeetup", method = RequestMethod.GET)
	public String deleteMeetup(@ModelAttribute("meetupId") int meetupId, Model model, Principal principal) {		
		Meetup meetup = meetupRepo.findByMeetupId(meetupId);
		Lecturer lecturer = lecturerRepo.findByusername(principal.getName());
		lecturer.getMeetupList().removeIf(mtp -> mtp.getMeetupId() == meetupId);
		lecturerRepo.save(lecturer);
		for (Learner learner : meetup.getLearners()) {
			learner.getMeetups().removeIf(mtp -> mtp.getMeetupId() == meetupId);
			learnerRepo.save(learner);
		}
		meetupRepo.delete(meetupId);
		model.addAttribute("meetupList", lecturer.getMeetupList());
		
		//Credentials are setup here
	    AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ", "0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");		
	    @SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    //Get meetups icon
		com.amazonaws.services.s3.model.S3Object s5object;
		s5object = s3client.getObject(new GetObjectRequest("meetupsicon","meetupsImg1234"));
		model.addAttribute("meetupPic", s5object.getObjectContent().getHttpRequest().getURI().toString());
		
		return "meetups/index";
	}

}
