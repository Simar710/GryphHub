import java.util.*;
/**
 * 
 */
public class StudentCounselor implements User
{
    private Planner planner;
    private ArrayList<Course> courses;
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String department;
    private String phone;

    public StudentCounselor(
        String name, 
        String password, 
        String department, 
        String phone
    )
    {
        this.planner = Planner.getInstance();
        this.courses = planner.getAllCourses();
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.department = department;
        this.phone = phone;
        this.email = name + "@uoguelph.ca";
    }

    public StudentCounselor(
        StudentCounselor other
    )
    {
        this.planner = Planner.getInstance();
        this.courses = other.courses;
        this.id = other.id;
        this.name = other.name;
        this.password = other.password;
        this.department = other.department;
        this.phone = other.phone;
        this.email = other.email;
    }
    
    //======== Misc. Methods ========
    public ArrayList<String> viewAllCourses()
    {
        ArrayList<String> allCourses = new ArrayList<>();
        for (Course course : courses) {
            allCourses.add(course.toString());
        }
        return allCourses;
    }

    public String viewCourse(UUID courseId)
    {
        Course course = planner.getCourse(courseId);

        if(course != null)
        {
            return course.toString();
        }
        else
        {
            return "";
        }
        
    }

    public Timetable viewTimetable(UUID studentId) 
    {
        return planner.createTimetable(studentId);
    }

    public ClassList viewClasslist(String courseCode) 
    {
        return planner.getClassList(courseCode);
    }

    public boolean checkEligibility(UUID studentId, UUID courseId)
    {
        RegistrationInfo user = planner.getUserInfo(studentId);
        Course course = planner.getCourse(courseId);
        
        if(user != null && course != null)
        {
            Set<UUID> requisites = course.getRequisites();

            return requisites.containsAll(user.getCompletedCourses());
        }
        
        return false;
    }

    //======== Add Methods ========
    public boolean registerCourse(UUID studentId, UUID courseId, UUID sectionId)
    {
       return planner.registerCourse(studentId, courseId, sectionId);
    }

    //======== Remove Methods ========
    public boolean dropCourse(UUID studentId, UUID courseId, UUID sectionId)
    {
        return planner.dropCourse(studentId, courseId, sectionId);
    }

    //======== Getters ========
    public UUID getId() 
    { 
        return id; 
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getPhone()
    {
        return phone;
    }

    //======== Setters ========
    public boolean setName(String name)
    {
        if(name.isEmpty())
        {
            this.name = name;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setEmail(String email)
    {
        if(!email.isEmpty())
        {
            this.email = email;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setPassword(String password)
    {
        if(!password.isEmpty())
        {
            this.password = password;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setDepartment(String department)
    {
        if(!department.isEmpty())
        {
            this.department = department;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setPhone(String phone)
    {
        if(!phone.isEmpty())
        {
            this.phone = phone;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return
        "==== Student Counselor ====\n" +
        "ID: " + id + "\n" +
        "Name: " + name + "\n" +
        "Email: " + email + "\n" +
        "Password: " + password + "\n" +
        "Department: " + department + "\n" +
        "Phone: " + phone + "\n";
    }
}

