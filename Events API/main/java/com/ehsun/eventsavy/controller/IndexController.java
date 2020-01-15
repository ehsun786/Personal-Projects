package com.ehsun.eventsavy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehsun.eventsavy.entities.Category;
import com.ehsun.eventsavy.entities.Event;
import com.ehsun.eventsavy.entities.User;
import com.ehsun.eventsavy.repos.CategoryRepository;
import com.ehsun.eventsavy.repos.EventRepository;
import com.ehsun.eventsavy.repos.UserRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/main")
public class IndexController {

	//Import repostiories for database CRUD operations.
	@Autowired
	UserRepository userRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	EventRepository eventRepo;

	@RequestMapping(value = "/signup/{name}/{email}/{username}/{password}")
	public @ResponseBody String signUp(@PathVariable("name") String name, @PathVariable("email") String email,
			@PathVariable("username") String username, @PathVariable("password") String password) {
		User user = userRepo.findByUsername(username);
		if(user==null) {
			user= new User();
			user.setFullName(name);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			userRepo.save(user);
			return "User Created!";
		} else {
			return "Username Taken!";
		}
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

		User user = userRepo.findByUsername(username);
		if (user == null) {
			return "Not Registered";
		}
		if (user.getPassword().equals(password)) {
			return "Authenticated";
		} else {
			return "Wrong Credentials";
		}

	}

	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public String createEvent(@RequestParam("username") String username, @RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("date") String date,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude) {

		if (title.equals("") || category.equals("") || date.equals("") || startTime.equals("") || endTime.equals("")) {
			return "Empty Field";
		}
		User user = userRepo.findByUsername(username);
		Event event = new Event();
		event.setTitle(title);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setDate(date);
		event.setLatitude(latitude);
		event.setLongitude(longitude);
		event.setUsername(username);
		event.setCategory(category);
		
		user.getEventsCreated().add(event);
		eventRepo.save(event);
		userRepo.save(user);
		return "Event Created";
	}

	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent(@RequestParam("username") String username, @RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("date") String date,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("eventId") String eventId) {

		if (title.equals("") || category.equals("") || date.equals("") || startTime.equals("") || endTime.equals("")) {
			return "Empty Field";
		}

		User user = userRepo.findByUsername(username);
		Optional<Event> event = eventRepo.findById(Long.parseLong(eventId));
		Event tmpEvent = event.get();
		tmpEvent.setTitle(title);
		tmpEvent.setStartTime(startTime);
		tmpEvent.setEndTime(endTime);
		tmpEvent.setCategory(category);
		
		user.getEventsCreated().removeIf(p -> p.getId() == Long.parseLong(eventId));
		user.getEventsCreated().add(tmpEvent);
		eventRepo.save(tmpEvent);
		userRepo.save(user);
		return "Event Edited!";
	}

	// Handling Request to get identification number for every event in a set
	@RequestMapping(value = "/getAllEvents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<Long> getEvents() {
		Set<Long> eventIds = new HashSet<>();
		for (Event event : eventRepo.findAll()) {
			eventIds.add(event.getId());
		}
		return eventIds;
	}

	// Handling Request to get an event given the id of the event.
	@RequestMapping(value = "/getEventById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getEventById(@PathVariable("id") String id) {
		Optional<Event> event = eventRepo.findById(Long.parseLong(id));
		Gson gson = new Gson();
		return gson.toJson(event.get());
	}

	// Handling Request to get all users and returning the response in JSON format.
	@RequestMapping(value = "/getAllUsers")
	public @ResponseBody List<User> getUsers() {
		List<User> users = new ArrayList<>();
		for (User user : userRepo.findAll()) {
			users.add(user);
		}
		return users;
	}

	// Handling requests to get all categories for the events.
	@RequestMapping(value = "/getAllCategories")
	public @ResponseBody List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		for (Category category : categoryRepo.findAll()) {
			categories.add(category);
		}
		return categories;
	}

	// Handling Request to get a user to given the id of the event.
	@RequestMapping(value = "/getUserByUsername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getUserByUsername(@PathVariable("username") String username) {
		Optional<User> user = userRepo.findById(username);
		Gson gson = new Gson();
		return gson.toJson(user.get());
	}

	@RequestMapping(value = "/getEventAttendees/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getEventAttendees(@PathVariable("eventId") long eventId) {
		Event event = eventRepo.findById(eventId).get();
		return String.valueOf(event.getAttendees().size());
	}

	@RequestMapping(value = "/deleteEvent/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteEvent(@PathVariable("eventId") long eventId,
			@PathVariable("username") String username) {
		Event event = eventRepo.findById(eventId).get();
		eventRepo.delete(event);
		return "Event Deleted!";
	}

	@RequestMapping(value = "/sendHelpEmail/{username}/{eventId}", method = RequestMethod.GET)
	public @ResponseBody String helpEmail(@PathVariable("username") String username,
			@PathVariable("eventId") long eventId) {
		User helper = userRepo.findByUsername(username);
		Event event = eventRepo.findById(eventId).get();
		User creator = userRepo.findByUsername(event.getUsername());
		String to = creator.getEmail();
		String from = helper.getEmail();

		String host = "localhost";

		// Get the session object details
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		// We compose the message here
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Help for Event: " + event.getId() + "." + event.getTitle());
			message.setText("Hi "+creator.getFullName()+",\n\n"+helper.getFullName() + " has volunteered to help! \n\nTeam Event Savvy");

			// Send message
			Transport.send(message);
			System.out.println("The message has been successfully sent.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Email Sent";
	}
	
	
	@RequestMapping(value = "/attendEvent/{username}/{eventId}", method = RequestMethod.GET)
	public @ResponseBody String attendEvent(@PathVariable("username") String username,
			@PathVariable("eventId") long eventId) {
		Event event = eventRepo.findById(eventId).get();
		event.getAttendees().add(username);
		eventRepo.save(event);
		return "Attendance Registered!";
	}

	@RequestMapping(value = "/deleteUser/{username}/")
	public @ResponseBody String deleteUser(@PathVariable("username") String username) {
		User user = userRepo.findByUsername(username);
		userRepo.delete(user);
		return "User Deleted!";
	}
	
	@RequestMapping(value = "/editUser/{name}/{email}/{username}/{password}")
	public @ResponseBody String editUser(@PathVariable("name") String name,
			@PathVariable("email") String email,
			@PathVariable("username") String username,
			@PathVariable("password") String password) {
		User user = userRepo.findByUsername(username);
		user.setFullName(name);
		user.setEmail(email);
		user.setPassword(password);
		userRepo.save(user);
		return "User Edited!";
	}
	
	@RequestMapping(value = "/report/{username}/{text}")
	public @ResponseBody String report(@PathVariable("username") String username,
			@PathVariable("text") String text) {
		
		User reporter = userRepo.findByUsername(username);
	
	
		String to = "ehsun_m_hanif@hotmail.com";
		String from = reporter.getEmail();

		String host = "localhost";

		// Get the session object details
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		// We compose the message here
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Report - "+username);
			message.setText(text);

			// Send message
			Transport.send(message);
			System.out.println("The message has been successfully sent.");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Report Sent";
		
	}
	
}
