// package eLearning;

// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.Matchers.equalTo;
// import static org.hamcrest.Matchers.greaterThan;
// import static org.junit.Assert.assertTrue;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import com.amazonaws.auth.AWSCredentials;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3Client;
// import com.amazonaws.services.s3.model.GetObjectRequest;

// import cucumber.api.java.Before;
// import cucumber.api.java.en.Given;
// import cucumber.api.java.en.Then;
// import cucumber.api.java.en.When;
// import eLearning.controller.AdminController;
// import eLearning.controller.AuthorizationController;
// import eLearning.controller.IndexController;
// import eLearning.controller.LearnerController;
// import eLearning.controller.LecturerController;
// import eLearning.domain.Administrator;
// import eLearning.domain.Chapter;
// import eLearning.domain.Course;
// import eLearning.domain.Learner;
// import eLearning.domain.Lecturer;
// import eLearning.domain.Meetup;
// import eLearning.domain.Reviews;
// import eLearning.domain.Role;
// import eLearning.domain.SubTopic;
// import eLearning.domain.User;
// import eLearning.repository.AdminRepository;
// import eLearning.repository.ChapterRepository;
// import eLearning.repository.CourseRepository;
// import eLearning.repository.LearnersRepository;
// import eLearning.repository.LecturerRepository;
// import eLearning.repository.MeetupRepository;
// import eLearning.repository.ReviewsRepository;
// import eLearning.repository.SubTopicRepository;
// import eLearning.repository.UserRepository;

// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @ContextConfiguration(classes = { ELearningApp.class, WebConfig.class, DbConfig.class, AuthorizationController.class,
// 		LearnerController.class, LecturerController.class, IndexController.class, AdminController.class })
// @Transactional

// public class StepDefs {

// 	@Autowired
// 	private WebApplicationContext wac;

// 	private MockMvc mockMvc;
// 	private ResultActions result;

// 	@Autowired
// 	LecturerRepository lecturerRepo;
// 	@Autowired
// 	LearnersRepository learnerRepo;
// 	@Autowired
// 	AdminRepository adminRepo;
// 	@Autowired
// 	UserRepository userRepo;
// 	@Autowired
// 	CourseRepository courseRepo;
// 	@Autowired
// 	ChapterRepository chapterRepo;
// 	@Autowired
// 	SubTopicRepository subtopicRepo;
// 	@Autowired
// 	MeetupRepository meetupRepo;
// 	@Autowired
// 	ReviewsRepository reviewsRepo;

// 	Administrator admin;
// 	com.amazonaws.services.s3.model.S3Object s4object;
// 	Lecturer lecturer;
// 	Reviews review;
// 	Learner learner;
// 	Role role;
// 	User user;
// 	Course course;
// 	Chapter chapter;
// 	String tmpString;
// 	int tempInt;
// 	SubTopic subtopic;
// 	Meetup meetup;

// 	@Before
// 	public void setup() {
// 		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
// 	}

// 	@Given("^I have a lecturer with name \"([^\"]*)\"$")
// 	public void i_have_a_lecturer_with_name(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");
// 		tmpString = username;
// 		lecturerRepo.save(lecturer);
// 	}

// 	@When("^I add a new course X for the lecturer with name \"([^\"]*)\"$")
// 	public void i_add_a_new_course_X_for_the_lecturer_with_name(String name) throws Throwable {
// 		course = new Course();
// 		course.setName(name);
// 		lecturer.getCourseList().add(course.getCourseId());
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Then("^A new course is added in the database for the lecturer$")
// 	public void a_new_course_is_added_in_the_database_for_the_lecturer() throws Throwable {
// 		assertThat("Course not added for lecturer", lecturer.getCourseList().size(), greaterThan(0));
// 	}

// 	@Given("^I have a lecturer X in the database with name \"([^\"]*)\"$")
// 	public void i_have_a_lecturer_X_in_the_database_with_name(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");
// 		lecturerRepo.save(lecturer);
// 		tmpString = username;
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Given("^I have a course X with name \"([^\"]*)\" in the database$")
// 	public void i_have_a_course_X_with_name_in_the_database(String name) throws Throwable {
// 		course = new Course();
// 		lecturer.getCourseList().add(course.getCourseId());
// 		courseRepo.save(course);
// 		lecturerRepo.save(lecturer);

// 	}

// 	@When("^I add a new chapter with name \"([^\"]*)\"$")
// 	public void i_add_a_new_chapter_with_name(String name) throws Throwable {
// 		chapter = new Chapter();
// 		chapter.setName(name);
// 		chapterRepo.save(chapter);
// 		course.getChapterList().add(chapter);
// 		courseRepo.save(course);
// 	}

// 	@Then("^A new chapter is added to the repository$")
// 	public void a_new_chapter_is_added_to_the_repository() throws Throwable {
// 		assertThat("Chapter not added for course", course.getChapterList().size(), greaterThan(0));
// 	}

// 	@Given("^I have a lecturer X with name \"([^\"]*)\" in the database$")
// 	public void i_have_a_lecturer_X_with_name_in_the_database(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Given("^I have a course Y with name \"([^\"]*)\" in the database for the lecturer$")
// 	public void i_have_a_course_Y_with_name_in_the_database_for_the_lecturer(String name) throws Throwable {
// 		course = new Course();
// 		course.setName(name);
// 		lecturer.getCourseList().add(course.getCourseId());
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Given("^I have a chapter Z with name \"([^\"]*)\"$")
// 	public void i_have_a_chapter_Z_with_name(String name) throws Throwable {
// 		chapter = new Chapter();
// 		chapter.setName(name);
// 	}

// 	@When("^I add a new sub topic in the database for the chapter$")
// 	public void i_add_a_new_sub_topic_in_the_database_for_the_chapter() throws Throwable {
// 		subtopic = new SubTopic();
// 		subtopicRepo.save(subtopic);
// 		chapter.getSubtopics().add(subtopic);
// 		chapterRepo.save(chapter);
// 		course.getChapterList().add(chapter);
// 		courseRepo.save(course);

// 	}

// 	@Then("^A new sub topic is added to the database for that course$")
// 	public void a_new_sub_topic_is_added_to_the_database_for_that_course() throws Throwable {
// 		assertThat("Subtopic not added for course", chapter.getSubtopics().size(), greaterThan(0));
// 	}

// 	@When("^I add a new administrator to the database with name \"([^\"]*)\"$")
// 	public void i_add_a_new_administrator_to_the_database_with_name(String username) throws Throwable {
// 		admin = new Administrator();
// 		admin.setUsername(username);
// 		adminRepo.save(admin);
// 	}

// 	@Then("^The database contains a new administrator with name \"([^\"]*)\"$")
// 	public void the_database_contains_a_new_administrator_with_name(String username) throws Throwable {
// 		admin = adminRepo.findByusername(username);
// 		assertThat("Subtopic not added for course", admin.getUsername(), equalTo(username));
// 	}

// 	@Given("^I have a user X in the database with name \"([^\"]*)\"$")
// 	public void i_have_a_user_X_in_the_database_with_name(String username) throws Throwable {
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner.setFirstName("Melvin");
// 		learner.setLastName("Gregg");
// 		learner.setPassword("hmcew42sfw");
// 		learner.setEmail("melvin.gregg@gmail.com");
// 		learner.setGender("Male");
// 		learner.setSecurityCode(2345);
// 		learnerRepo.save(learner);
// 	}

// 	@When("^I delete the user from the repository$")
// 	public void i_delete_the_user_from_the_repository() throws Throwable {
// 		learnerRepo.delete(learner);
// 	}

// 	@Then("^The user with name \"([^\"]*)\" no longer exists in the repository$")
// 	public void the_user_with_name_no_longer_exists_in_the_repository(String username) throws Throwable {
// 		assertTrue("User is deleted", learnerRepo.findByusername(username) == null);
// 	}

// 	@Given("^There is a user X in the database withe name \"([^\"]*)\"$")
// 	public void there_is_a_user_X_in_the_database_withe_name(String username) throws Throwable {
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner.setFirstName("Melvin");
// 		learner.setLastName("Gregg");
// 		learner.setPassword("hmcew42sfw");
// 		learner.setEmail("melvin.gregg@gmail.com");
// 		learner.setGender("Male");
// 		learner.setSecurityCode(2345);
// 	}

// 	@When("^I suspend the user then$")
// 	public void i_suspend_the_user_then() throws Throwable {
// 		// Write code here that turns the phrase above into concrete actions
// 		learner.setSuspend(true);
// 		learnerRepo.save(learner);
// 	}

// 	@Then("^the user with name \"([^\"]*)\" is suspended from using the website$")
// 	public void the_user_with_name_is_suspended_from_using_the_website(String arg1) throws Throwable {
// 		assertTrue("Not suspended", learner.getSuspended() == true);
// 	}

// 	@When("^I add a new learner to the database with name \"([^\"]*)\"$")
// 	public void i_add_a_new_learner_to_the_database_with_name(String username) throws Throwable {
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner.setFirstName("Melvin");
// 		learner.setLastName("Gregg");
// 		learner.setPassword("hmcew42sfw");
// 		learner.setEmail("melvin.gregg@gmail.com");
// 		learner.setGender("Male");
// 		learner.setSecurityCode(2345);
// 		learnerRepo.save(learner);
// 	}

// 	@Then("^A new learner is added to the database with name \"([^\"]*)\"$")
// 	public void a_new_learner_is_added_to_the_database_with_name(String username) throws Throwable {
// 		assertTrue("Learner is deleted", learnerRepo.findByusername(username) != null);
// 	}

// 	@When("^I add a new lecturer to the database with name \"([^\"]*)\"$")
// 	public void i_add_a_new_lecturer_to_the_database_with_name(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");

// 		lecturerRepo.save(lecturer);
// 	}

// 	@Then("^A new lecturer is added to the database with name \"([^\"]*)\"$")
// 	public void a_new_lecturer_is_added_to_the_database_with_name(String username) throws Throwable {
// 		assertTrue("Lectuer is added", lecturerRepo.findByusername(username) != null);
// 	}

// 	@Given("^I have a meetup X with description \"([^\"]*)\"$")
// 	public void i_have_a_meetup_X_with_description(String description) throws Throwable {
// 		meetup = new Meetup();
// 		meetup.setDescription(description);
// 		meetup.setAtHour(2);
// 		meetup.setAtMinute(20);
// 		meetup.setBuildingNumber("2");
// 		meetup.setDayAndDate("22/07/2017");
// 		meetup.setDescription("Discuss Automata");
// 		meetup.setEstimatedHours(1);
// 		meetup.setEstimatedMinutes(40);
// 		meetup.setPostcode("LE27TG)");
// 		meetup.setRecurring(true);
// 		meetup.setStreet("Wellington Road");
// 		meetupRepo.save(meetup);
// 	}

// 	@Given("^I have a lecturer X with name \"([^\"]*)\" in the repository$")
// 	public void i_have_a_lecturer_X_with_name_in_the_repository(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");
// 		tmpString = username;
// 		lecturerRepo.save(lecturer);
// 	}

// 	@When("^I add the meetup to the repository for the lecturer$")
// 	public void i_add_the_meetup_to_the_repository_for_the_lecturer() throws Throwable {
// 		lecturer.getMeetupList().add(meetup);
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Then("^a new meetup is stored in the repository for the lecturer with name \"([^\"]*)\"$")
// 	public void a_new_meetup_is_stored_in_the_repository_for_the_lecturer_with_name(String username) throws Throwable {
// 		assertThat("Subtopic not added for course", lecturerRepo.findByusername(tmpString).getMeetupList().size(),
// 				greaterThan(0));
// 	}

// 	@Given("^I have a meetup X with description \"([^\"]*)\" in the database$")
// 	public void i_have_a_meetup_X_with_description_in_the_database(String description) throws Throwable {
// 		meetup = new Meetup();
// 		meetup.setDescription(description);
// 		meetupRepo.save(meetup);
// 	}

// 	@Given("^I have a lecturer X with name \"([^\"]*)\"$")
// 	public void i_have_a_lecturer_X_with_name(String username) throws Throwable {
// 		lecturer = new Lecturer();
// 		lecturer.setUsername(username);
// 		lecturer.setFirstName("Melvin");
// 		lecturer.setLastName("Gregg");
// 		lecturer.setPassword("hmcew42sfw");
// 		lecturer.setEmail("melvin.gregg@gmail.com");
// 		lecturer.setGender("Male");
// 		lecturer.setSecurityCode(2345);
// 		lecturer.setNetworking("melvin.gregg@gamil.com");
// 		lecturer.setDescription("My name is Melvin and I am a doctor of Computer Science.");
// 		lecturer.getMeetupList().add(meetup);
// 		lecturerRepo.save(lecturer);
// 	}

// 	@When("^I delete the meetup X from the database for the lecturer$")
// 	public void i_delete_the_meetup_X_from_the_database_for_the_lecturer() throws Throwable {
// 		lecturer.getMeetupList().remove(meetup);
// 		lecturerRepo.save(lecturer);
// 	}

// 	@Then("^The meetup with description \"([^\"]*)\" no longer exists in the repository for the repository$")
// 	public void the_meetup_with_description_no_longer_exists_in_the_repository_for_the_repository(String arg1)
// 			throws Throwable {
// 		assertTrue("Meetup not deleted", lecturer.getMeetupList().size() == 0);
// 	}

// 	@Given("^I have a review X with statement \"([^\"]*)\"$")
// 	public void i_have_a_review_X_with_statement(String statement) throws Throwable {
// 		review = new Reviews();
// 		review.getReviews().add(statement);
// 		reviewsRepo.save(review);
// 	}

// 	@Given("^I have a learner X with name \"([^\"]*)\"$")
// 	public void i_have_a_learner_X_with_name(String username) throws Throwable {
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner = new Learner();
// 		learner.setUsername(username);
// 		learner.setFirstName("Melvin");
// 		learner.setLastName("Gregg");
// 		learner.setPassword("hmcew42sfw");
// 		learner.setEmail("melvin.gregg@gmail.com");
// 		learner.setGender("Male");
// 		learner.setSecurityCode(2345);
// 		learnerRepo.save(learner);
// 	}

// 	@When("^I add a new review to the learner review list repository$")
// 	public void i_add_a_new_review_to_the_learner_review_list_repository() throws Throwable {
// 		learner.getCourseReviews().put(1, review);
// 		learnerRepo.save(learner);
// 	}

// 	@Then("^a new review is stored in the repository for the learner with statement \"([^\"]*)\"$")
// 	public void a_new_review_is_stored_in_the_repository_for_the_learner_with_statement(String statement)
// 			throws Throwable {
// 		String review = learner.getCourseReviews().get(1).getReviews().get(0);
// 		assertThat("Review added for learner", review.equals(statement));
// 	}

// 	// Amazon AWS

// 	@When("^I get an object with name \"([^\"]*)\" from an Amazon bucket with name \"([^\"]*)\"$")
// 	public void i_get_an_object_with_name_from_an_Amazon_bucket_with_name(String nameOfObject, String bucketName)
// 			throws Throwable {

// 		AWSCredentials credentials = new BasicAWSCredentials("AKIAIQ3NO6F72FXEXH4Q ",
// 				"0ItEyI9GJDNROckIZDwvwFAfDADrhckjCAXV2Sdu");
// 		@SuppressWarnings("deprecation")
// 		AmazonS3 s3client = new AmazonS3Client(credentials);

// 		s4object = s3client.getObject(new GetObjectRequest(bucketName, nameOfObject));

// 	}

// 	@Then("^I receive a non-null subject from the clouds$")
// 	public void i_receive_a_non_null_subject_from_the_cloud() throws Throwable {
// 		assertTrue("The object is not null.", s4object != null);
// 	}

// }
