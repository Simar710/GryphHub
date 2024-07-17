import java.time.*;
import java.time.format.*;
import java.util.*;

public class UI {

	public static void main(String[] args){
		Planner planner;
		Random rand = new Random();

		System.out.println("Welcome to the Academic Planner");

		Course dataStructure = new Course("CIS*2520", "Data Structures", "Mister Mackey","This course is a study of basic data structures, such as lists, stacks, queues, trees, and tables. Topics which will be examined include abstract data types, sequential and linked representations, and an introduction to algorithm analysis; various traversal, search, insertion, removal, and sorting algorithms.", "Fall", "Remote", 0.50f);
		Course systemAnalysisDesign = new Course("CIS*3750", "System Analysis and Design in Applications", "Amy Tang","This course is an introduction to the issues and techniques encountered in the design and construction of software systems, focusing on the theory and models of software evolution. Topics include requirements and specifications, prototyping, design principles, object-oriented analysis and design, standards, integration, risk analysis, testing and debugging.", "Winter", "Remote", 0.75f);
		Course businessConsumerLaw  = new Course("MCS*3040", "Business and Consumer Law ", "Peter Parker", "This course introduces students to statutory and common law concerning business and consumer transactions. An overview of the laws of contracts and torts forms the basis of business and producer/consumer relationships. Discussion topics include sale of goods and consumer protection legislation; debtor-creditor relations; competition law; intellectual property rights and manufacturers' product liability.", "Winter", "Remote", 0.50f);
		Course econ = new Course("ECON*1050", "Introductory Microeconomics", "Nicki Bell","This course is an introduction to the issues and techniques encountered in the design and construction of software systems, focusing on the theory and models of software evolution. Topics include requirements and specifications, prototyping, design principles, object-oriented analysis and design, standards, integration, risk analysis, testing and debugging.", "Fall", "Remote", 0.50f);

		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(dataStructure);
		courses.add(systemAnalysisDesign);
		courses.add(businessConsumerLaw);
		courses.add(econ);
		for(Course c : courses)
		{
			for(int i = 0; i < 2; i++)
			{
				Section s = new Section(
					(rand.nextInt(10)+1)*10,
					0
				);
				s.addLocation("Guelph");
				s.addTime(LocalDateTime.of(2023, Month.MARCH, 7, 1, 0));
				c.addSection(s);
			}
		}

        planner = Planner.getInstance();
		if(planner != null){
			//add dummy courses
			for(Course c : courses)
			{
				planner.addCourse(c);
			}
		}

		//set applicant
        Applicant applicant1 = new Applicant("John Smith", "password123");

		//set student
		Student student1;
		ArrayList<Student> studentArr = new ArrayList<Student>();
		student1 = new Student("John Doe", "password123");
        student1.setMajor(new ArrayList<String>(List.of("Computer Science")));
        student1.setMinor(new ArrayList<String>(List.of("Mathematics")));
		student1.addCompletedCourse(dataStructure);
		student1.addCompletedCourse(businessConsumerLaw);

		studentArr.add(student1);

		//set professor
		Professor professor1 = new Professor("Mister Mackey", "mmmkay", "Faculty of Science and Engineering", "8655309", "South Park Elementary", "Woohoo I can teach");
		
		//set student counselor
		StudentCounselor studcounselor1 = new StudentCounselor("STUDENT COUNSELOR", "PASSWORD123", "Arts", "9059059059");

		planner.addUser(student1.getId(), student1);
		planner.addUser(professor1.getId(), professor1);
		planner.addUser(studcounselor1.getId(), studcounselor1);

		Scanner userScanner = new Scanner(System.in);
		Integer userInput = 0;

		while(userInput != 5)
		{
			System.out.println("Would you like to log in as:");
			System.out.println("1. Applicant");
			System.out.println("2. Student");
			System.out.println("3. Professor");
			System.out.println("4. Student Counselor");
			System.out.println("5. Quit Program");
			System.out.print("> ");
			userInput = userScanner.nextInt();
			userScanner.nextLine();

			if (userInput.equals(1)){
				//Applicant
				Integer applicantInput = 0;

				scanner: while(true){
					System.out.println("== MENU ==");
					System.out.println("1. View courses");
					System.out.println("2. Go Back");
					System.out.print("> ");

					applicantInput = userScanner.nextInt();
					userScanner.nextLine();

					switch(applicantInput){
						case 1:
							ArrayList<String> temp = applicant1.viewAllCourses();
							for(String s : temp)
							{
								System.out.println(s);
							}
							break;
						case 2:
							break scanner;
						default:
							System.out.println("That is not a valid menu option");
							break;
					}
				}
			}
			else if (userInput.equals(2)){
				//Student
				Integer studentInput = 0;
				String courseToRegister;
				String courseToDrop;

				scanner: while(true){
					System.out.println("== MENU ==");
					System.out.println("1. View courses");
					System.out.println("2. Register for a course");
					System.out.println("3. Drop a course");
					System.out.println("4. View timetable");
					System.out.println("5. Go Back");
					System.out.print("> ");

					studentInput = userScanner.nextInt();
					userScanner.nextLine();

					switch(studentInput){
						// view course
						case 1:
							ArrayList<String> temp = student1.viewAllCourses();
							for(String s : temp)
							{
								System.out.println(s);
							}
							break;

						// register course
						case 2:
							System.out.println("Please enter course code to register for");
							System.out.print("> ");
							courseToRegister = userScanner.nextLine().strip();
							
							// check eligibility before attempting to register course
							if (student1.checkEligibility(courseToRegister)) {
								Boolean isRegistered = false;
								isRegistered = student1.registerCourse(student1.getId(), courseToRegister);
								if (isRegistered){
									System.out.println("Successfully Registered Course");
								}
								else{
									System.out.println("Failed to register course");
								}
							}
							else{
								System.out.println("You are not eligible to register for this course");
							}
				
							break;

						// drop course
						case 3:
							System.out.println("Please enter course code to drop");
							System.out.print("> ");
							courseToDrop = userScanner.nextLine();
							
							// check eligibility before attempting to drop course
							if (student1.checkEligibility(courseToDrop)) {
								Boolean droppedCourse = false;
								droppedCourse = student1.dropCourse(student1.getId(), courseToDrop);
								if (droppedCourse) {
									System.out.println("Successfully Dropped Course");
								}
								else {
									System.out.println("Failed to drop course");
								}
							}
							else {
								System.out.println("Not eligible to drop this course");
							}		
							break;

						// view timetable
						case 4: 
							System.out.println(student1.viewTimetable().toString());
							break;
							
						// quit
						case 5: 
							break scanner;
							
						default:
							System.out.println("That is not a valid menu option");
							break;
						
					}
				}

			}
			else if (userInput.equals(3)){
				//Professor
				Integer professorInput = 0;

				scanner: while(true){
					System.out.println("== MENU ==");
					System.out.println("1. View courses");
					System.out.println("2. View class list for a course");
					System.out.println("3. Input office hours");
					System.out.println("4. Go Back");
					System.out.print("> ");

					professorInput = userScanner.nextInt();
					userScanner.nextLine();

					switch(professorInput){
						case 1:
							ArrayList<String> temp = professor1.viewAllCourses();
							for(String s : temp)
							{
								System.out.println(s);
							}
							break;

						case 2:
							String classList;
							System.out.println("Please enter a course code to view class list");
							System.out.print("> ");
							classList = userScanner.nextLine().strip();		
							System.out.println(professor1.viewClasslist(classList).toString());				
							break;
							
						case 3:
							String officeHoursStart;
							String officeHoursEnd;
							boolean officeHoursSuccess = false;
							String courseCode;
							DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

							System.out.println("Please enter a course code to input office hours for");
							System.out.print("> ");
							courseCode = userScanner.nextLine().strip();
							Course inputCourse = null;

							for(Course c : planner.getAllCourses())
							{
								if(c.getCode().equals(courseCode))
								{
									inputCourse = c;
								}
							}

							if(inputCourse == null)
							{
								System.out.println("There is no such course code");
								break;
							}

							try
							{
								System.out.println("Please enter a start time for office hours (HH:mm)");
								System.out.print("> ");
								officeHoursStart = userScanner.nextLine().strip();
								LocalTime startTime  = LocalTime.parse(officeHoursStart, format);

								System.out.println("Please enter a end time for office hours (HH:mm)");
								System.out.print("> ");
								officeHoursEnd = userScanner.nextLine().strip();
								LocalTime endTime  = LocalTime.parse(officeHoursEnd, format);

								officeHoursSuccess = professor1.inputOfficeHours(inputCourse.getId(), LocalDateTime.of(LocalDate.now(), startTime), LocalDateTime.of(LocalDate.now(), endTime));
								if(officeHoursSuccess){
									System.out.println("Successfully input office hours");
								}
								else{
									System.out.println("Failed to input office hours");
								}
							}
							catch(DateTimeParseException e)
							{
								System.out.println("Failed to parse time");
							}
							break;

						case 4:
							break scanner;

						default:
							System.out.println("That is not a valid menu option");
						break;
					}
				}
			}
			else if (userInput.equals(4)){
				//Student Counselor
				
				Integer councInteger = 0;
				
				scanner: while(true){
				
					System.out.println("MENU");
					System.out.println("1. View courses");
					System.out.println("2. Register a course for a student");
					System.out.println("3. Drop a course for a student");
					System.out.println("4. View class list for a course");
					System.out.println("5. Go Back");
					System.out.print("> ");

					councInteger = userScanner.nextInt();
					userScanner.nextLine();
				
					switch(councInteger){
						case 1:
							ArrayList<String> temp = studcounselor1.viewAllCourses();
							for(String s : temp)
							{
								System.out.println(s);
							}
							break;

						case 2:
						{
							String courseToRegister;
							String nameToRegister;
							
							System.out.println("Please enter the name of the student");
							System.out.print("> ");
							nameToRegister = userScanner.nextLine().strip();

							Student student = new Student(null, null);

							for(Student singleStudent: studentArr){
								if(singleStudent.getName().equals(nameToRegister)){
									student = singleStudent;
								}
							}

							System.out.println("Please enter course code to register a student for");
							System.out.print("> ");
							courseToRegister = userScanner.nextLine().strip();
							
							if (student.checkEligibility(courseToRegister)) {
								Boolean isRegistered = false;
								isRegistered = student.registerCourse(student.getId(), courseToRegister);
								if (isRegistered){
									System.out.println("Successfully Registered Course");
								}
								else{
									System.out.println("Failed to register course");
								}
							}
							else{
								System.out.println("You are not eligible to register for this course");
							}
							break;
						}

						case 3:
						{
							String courseToRegister;
							String nameToRegister;
							
							System.out.println("Please enter the name of the student");
							System.out.print("> ");
							nameToRegister = userScanner.nextLine().strip();

							Student student = new Student(null, null);

							for(Student singleStudent: studentArr){
								if(singleStudent.getName().equals(nameToRegister)){
									student = singleStudent;
								}
							}

							System.out.println("Please enter course code to drop a student from");
							System.out.print("> ");
							courseToRegister = userScanner.nextLine().strip();
							
							if (student.checkEligibility(courseToRegister)) {
								Boolean isRegistered = false;
								isRegistered = student.dropCourse(student.getId(), courseToRegister);
								if (isRegistered){
									System.out.println("Successfully Dropped Course");
								}
								else{
									System.out.println("Failed to drop course");
								}
							}
							else{
								System.out.println("You are not eligible to drop this course");
							}
							break;
						}

						case 4:
							String classList;
							System.out.println("Please enter a course code to view class list");
							System.out.print("> ");

							classList = userScanner.nextLine().strip();	

							ClassList classListObj = studcounselor1.viewClasslist(classList);
							if (classListObj != null) {
								System.out.println(classListObj.toString());
							} else {
								System.out.println("No Student is registered for this course");
							}
							
							break;

						case 5: 
							break scanner;

						default:
							System.out.println("That is not a valid menu option");
							break;
					}
				}
			}
			else if(userInput.equals(5))
			{
				break;
			}
			else
			{
				System.out.println("That is not a valid menu option");
			}
		}

		userScanner.close();

	}
}
