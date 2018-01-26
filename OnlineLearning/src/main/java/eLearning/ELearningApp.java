
package eLearning;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import eLearning.domain.Administrator;
import eLearning.domain.Chapter;
import eLearning.domain.ChartData;
import eLearning.domain.Course;
import eLearning.domain.Learner;
import eLearning.domain.Lecturer;
import eLearning.domain.Reviews;
import eLearning.domain.Role;
import eLearning.domain.SubTopic;
import eLearning.repository.AdminRepository;
import eLearning.repository.ChapterRepository;
import eLearning.repository.ChartDataRepository;
import eLearning.repository.CourseRepository;
import eLearning.repository.LearnersRepository;
import eLearning.repository.LecturerRepository;
import eLearning.repository.ReviewsRepository;
import eLearning.repository.SubTopicRepository;
import eLearning.repository.UserRepository;

@SpringBootApplication
public class ELearningApp implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private LearnersRepository learnerRepo;
	@Autowired
	private LecturerRepository lecturerRepo;
	@Autowired
	private SubTopicRepository subtopicRepo;
	@Autowired
	private ChapterRepository chapterRepo;
	@Autowired
	private ChartDataRepository chartDataRepo;
	@Autowired
	private ReviewsRepository reviewRepo;

	
	public static final int ROLE_USER = 0;
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_LECTURER = 2;
	public static final int ROLE_LEARNER = 3;

	public static void main(String[] args) {
		SpringApplication.run(ELearningApp.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {


		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		//ONLY 1 ADMIN NEEDED**********************************************************************
		Administrator admin = new Administrator();
		admin.setUsername("alice786");
		admin.setPassword(pe.encode("password"));
		admin.setFirstName("Alice");
		admin.setLastName("Isaac");
		admin.setEmail("temp@hotmail.co.uk"); 
		admin.setGender("Female");
		admin.setDate(LocalDate.now());
		Role role = new Role();
		role.setId(ROLE_ADMIN);
		role.setRole("ADMINISTRATOR");
		admin.setRole(role);
		adminRepo.save(admin);

		//CREATED 3 LEARNERS*************************************************************************
		Learner learner = new Learner();
		learner.setUsername("james786");
		learner.setPassword(pe.encode("oliver"));
		learner.setFirstName("James");
		learner.setLastName("Croyden");
		learner.setEmail("temp@hotmail.co.uk");
		learner.setGender("Male");
		learner.setDate(LocalDate.now());
		role = new Role();
		role.setId(ROLE_LEARNER);
		role.setRole("LEARNER");
		learner.setRole(role);
	
		Learner learner1 = new Learner();
		learner1.setUsername("dan");
		learner1.setPassword(pe.encode("smith"));
		learner1.setFirstName("Dan");
		learner1.setLastName("Smtih");
		learner1.setEmail("temp@124com");					
		learner1.setGender("Male");
		learner1.setDate(LocalDate.now());
		role = new Role();
		role.setId(ROLE_LEARNER);
		role.setRole("LEARNER");
		learner1.setRole(role);
		learnerRepo.save(learner1);
		
		Learner learner2 = new Learner();
		learner2.setUsername("dan1");
		learner2.setPassword(pe.encode("oliver"));
		learner2.setFirstName("dan1");
		learner2.setLastName("owen");
		learner2.setEmail("temp@124com"); 					
		learner2.setGender("Male");
		learner2.setDate(LocalDate.now());
		role = new Role();
		role.setId(ROLE_LEARNER);
		role.setRole("LEARNER");
		learner2.setRole(role);
		learnerRepo.save(learner2);
		
		
		//5 CHAPTERS FOR COURSE****************************************************************
		Chapter chapter = new Chapter();
		chapter.setName("Analysing security risks");
		chapterRepo.save(chapter);
		
		Chapter chapter1 = new Chapter();
		chapter1.setName("Managing the risks");
		chapterRepo.save(chapter1);
		
		Chapter chapter2 = new Chapter();
		chapter2.setName("Taking stock");
		chapterRepo.save(chapter2);
		
		Chapter chapter3 = new Chapter();
		chapter3.setName("Can risk be good?");
		chapterRepo.save(chapter3);
		
		Chapter chapter4 = new Chapter();
		chapter4.setName("Identifying risks");
		chapterRepo.save(chapter4);

		Course course = new Course();
		course.setName("Risk Fundametals");
		course.setDescription("Identify and analyse the risks to your own information assets.");
		course.setPrice(0.00);
		course.getChapterList().add(chapter);
		course.getChapterList().add(chapter1);
		course.getChapterList().add(chapter2);
		course.getChapterList().add(chapter3);
		course.getChapterList().add(chapter4);
		courseRepo.save(course);

		
		
		//0 CHAPTERS FOR COURSE1--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course1 = new Course();
		course1.setName("Introduction to Linked Data");
		course1.setDescription("In this course, you will learn the fundamental principles behind linked data.");
		course1.setRating(2.0);
		course1.setPrice(0.00);
		courseRepo.save(course1);

		//0 CHAPTERS FOR COURSE2--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course2 = new Course();
		course2.setName("Begin Robotics");
		course2.setDescription("Find out about the relationship between instinct and learning in nature.");
		course2.setRating(4.0);
		course2.setPrice(0.00);
		courseRepo.save(course2);

		//0 CHAPTERS FOR COURSE3--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course3 = new Course();
		course3.setName("SMART-ASD");
		course3.setDescription("Matching appropriate technological resources for children with autism");
		course3.setRating(2.0);
		course3.setPrice(30.00);
		courseRepo.save(course3);

		//0 CHAPTERS FOR COURSE4--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course4 = new Course();
		course4.setName("Begin Programming");
		course4.setRating(4.0);
		course4.setDescription("Have you wondered how the games you play on your mobile are created.");
		course4.setPrice(0.00);
		courseRepo.save(course4);
		
		//0 CHAPTERS FOR COURSE5--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course5 = new Course();
		course5.setName("Social Media in Healthcare");
		course5.setRating(4.0);
		course5.setDescription("Understand how social media is used in healthcare, to improve communication ");
		course5.setPrice(0.00);
		courseRepo.save(course5);
		
		//0 CHAPTERS FOR COURSE6--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course6 = new Course();
		course6.setName("Introduction to R for Data Science");
		course6.setRating(4.0);
		course6.setDescription("Work with airline data to learn the fundamentals of the R platform.");
		course6.setPrice(0.00);
		courseRepo.save(course6);
		
		//0 CHAPTERS FOR COURSE7--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course7 = new Course();
		course7.setName("Big Data: from Data to Decisions");
		course7.setRating(4.0);
		course7.setDescription("Get a practical insight into big data analytics.");
		course7.setPrice(0.00);
		courseRepo.save(course7);
		
		//0 CHAPTERS FOR COURSE8--EMPTY COURSE FOR INDEX PAGE*****************************************************************************
		Course course8 = new Course();
		course8.setName("Using Data to Improve Healthcare");
		course8.setRating(4.0);
		course8.setDescription("Learn how to draw together information, to understand and improve the quality of care.");
		course8.setPrice(0.00);
		courseRepo.save(course8);

		
		//CREATED 3 LECTURERS*****************************************************************************
		//AUTHORIZED****************************
		Lecturer lecturer = new Lecturer();
		lecturer.setUsername("bob786");
		lecturer.setPassword(pe.encode("admin"));
		lecturer.setFirstName("Bob");
		lecturer.setLastName("Smith");
		lecturer.setEmail("temp@hotmail.co.uk");
		lecturer.setGender("Male");
		lecturer.setDescription("Hi, my name is Bob Smith. My field of expertise is computer science and I hope you can take my application into account.");
		lecturer.setAuthorized(true);
		lecturer.setBucketName("1234bob7861234");
		lecturer.setDate(LocalDate.now());
		lecturer.getCourseList().add(course.getCourseId());
		lecturer.getCourseList().add(course1.getCourseId());
		lecturer.getCourseList().add(course2.getCourseId());
		lecturer.getCourseList().add(course3.getCourseId());
		lecturer.getCourseList().add(course4.getCourseId());
		lecturer.getCourseList().add(course5.getCourseId());
		lecturer.getCourseList().add(course6.getCourseId());
		lecturer.getCourseList().add(course7.getCourseId());
		lecturer.getCourseList().add(course8.getCourseId());
		role = new Role();
		role.setId(ROLE_LECTURER);
		role.setRole("LECTURER");
		lecturer.setRole(role);
		lecturerRepo.save(lecturer);
		
		
		
		//SET LECTURER TO ALL COURSES*****************************************************************************
		course.setLecturer(lecturer);
		course1.setLecturer(lecturer);
		course2.setLecturer(lecturer);
		course3.setLecturer(lecturer);
		course4.setLecturer(lecturer);
		course5.setLecturer(lecturer);
		course6.setLecturer(lecturer);
		course7.setLecturer(lecturer);
		course8.setLecturer(lecturer);
		
		//SAVE ALL COURSES****************************************************************************************
		courseRepo.save(course); 
		courseRepo.save(course1);
		courseRepo.save(course2);
		courseRepo.save(course3);
		courseRepo.save(course4);
		courseRepo.save(course5); 
		courseRepo.save(course6);
		courseRepo.save(course7);
		courseRepo.save(course8);
		
		//LEARNER ENROLLED ON 5 OUT OF 8 COURSES ***************************************************************************
		learner.getCourseList().add(course); 
		learner.getCourseList().add(course1);
		learner.getCourseList().add(course2);
		learner.getCourseList().add(course3);
		learner.getCourseList().add(course4);
		learnerRepo.save(learner);

		//LEARNER 1 ENROLLED ON 4 COURSES*****************************************************************************
		learner1.getCourseList().add(course); 
		learner1.getCourseList().add(course1);
		learner1.getCourseList().add(course2); 
		learner1.getCourseList().add(course5);		
		
		//LEARNER 2 ENROLLED ON 4 COURSES*****************************************************************************
		learner2.getCourseList().add(course); 
		learner2.getCourseList().add(course1);
		learner2.getCourseList().add(course2); 
		learner2.getCourseList().add(course7);
		
				
		//2 REVIEWS ON COURSE 5 ***************************************************************************************
		Reviews review1 = new Reviews();
		review1.getReviews().add("Course was fantastic! LOve every bit of it. Lecturer was great and material was perfect.");
		reviewRepo.save(review1);
		
		Reviews review2 = new Reviews();
		review2.getReviews().add("Course was ok! Could have been a lot better though.");
		reviewRepo.save(review2);
		
		learner1.getCourseReviews().put(course5.getCourseId(),review1);
		learner2.getCourseReviews().put(course5.getCourseId(),review2);

		learnerRepo.save(learner1);
		learnerRepo.save(learner2);
		
		
		//CHART DATA FOR ADMINS TO VIEW**************************************************************************
		ChartData firstChartData = new ChartData();
		LocalDate date = LocalDate.now();
		date = date.minusMonths(3);
		firstChartData.setDate(date);
		firstChartData.setAdministrators(3);
		firstChartData.setLearners(100);
		firstChartData.setLecturers(8);
		chartDataRepo.save(firstChartData);
		
		ChartData secondChartData = new ChartData();
		date = date.minusMonths(2);
		secondChartData.setDate(date);
		secondChartData.setAdministrators(6);
		secondChartData.setLearners(200);
		secondChartData.setLecturers(16);
		chartDataRepo.save(secondChartData);
		
		ChartData thirdChartData = new ChartData();
		date = date.minusMonths(1);
		thirdChartData.setDate(date);
		thirdChartData.setAdministrators(10);
		thirdChartData.setLearners(300);
		thirdChartData.setLecturers(24);
		chartDataRepo.save(thirdChartData);
		
		ChartData fourthChartData = new ChartData();
		date = LocalDate.now();
		fourthChartData.setDate(date);
		fourthChartData.setAdministrators(12);
		fourthChartData.setLearners(500);
		fourthChartData.setLecturers(30);
		chartDataRepo.save(fourthChartData);
		
	}

}
