import java.util.*;

public class Student implements User
{
    private Planner planner;
    private ArrayList<Course> completedCourses;
    private UUID id;
    private String name;
    private String email;
    private String password;
    private ArrayList<String> major;
    private ArrayList<String> minor;

    public Student(
        String name, 
        String password
    )
    {
        this.planner = Planner.getInstance();
        this.completedCourses = new ArrayList<Course>();
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.email = name + "@uoguelph.ca";
        this.major = new ArrayList<String>();
        this.minor = new ArrayList<String>();
    }

    public Student(
        Student other
    )
    {
        this.planner = Planner.getInstance();
        this.completedCourses = other.completedCourses;
        this.id = other.id;
        this.name = other.name;
        this.password = other.password;
        this.email = other.email;
        this.major = other.major;
        this.minor = other.minor;
    }

    //======== Misc. Methods ========
    public ArrayList<String> viewAllCourses()
    {
        ArrayList<Course> courses = planner.getAllCourses();
        ArrayList<String> summary = new ArrayList<String>();

        for(Course c : courses)
        {
            summary.add(c.toString());
        }

        return summary;
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
            return null;
        }
        
    }

    public Timetable viewTimetable()
    {
        return planner.createTimetable(id);
    }

    public boolean checkEligibility(UUID courseId)
    {
        Course course = planner.getCourse(courseId);
        if(course == null){
            return false;
        }

        Set<UUID> requisites = course.getRequisites();
        if(requisites.isEmpty()){
            return true;
        }
        
        for(UUID requisite: requisites){
            boolean found = false;
            for(Course completedCourse: completedCourses){
                if(requisite.equals(completedCourse.getId())){
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEligibility(String courseCode)
    {
        Course course = null;
        for(Course c : planner.getAllCourses())
        {
            if(c.getCode().equals(courseCode))
            {
                course = c;
                break;
            }
        }

        if(course != null)
        {
            return checkEligibility(course.getId());
        }
        
        return false;
    }

    //======== Add Methods ========
    public boolean registerCourse(UUID userId, UUID courseId, UUID sectionId)
    {
        return planner.registerCourse(userId, courseId, sectionId);
    }

    public boolean registerCourse(UUID userId, String courseCode)
    {
        return planner.registerCourse(userId, courseCode);
    }

    public void addCompletedCourse(Course course)
    {
        completedCourses.add(course);
    }

    //======== Remove Methods ========
    public boolean dropCourse(UUID userId, UUID courseId, UUID sectionId)
    {
        return planner.dropCourse(userId, courseId, sectionId);
    }

    public boolean dropCourse(UUID userId, String courseCode)
    {
        return planner.dropCourse(userId, courseCode);
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

    public String password()
    {
        return password;
    }

    public ArrayList<String> getMajor()
    {
        return major;
    }

    public ArrayList<String> getMinor()
    {
        return minor;
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

    public boolean setMajor(ArrayList<String> major)
    {
        if(major == null)
        {
            return false;
        }
        else
        {
            this.major = major;
            return true;
        }
    }

    public boolean setMinor(ArrayList<String> minor)
    {
        if(minor == null)
        {
            return false;
        }
        else
        {
            this.minor = minor;
            return true;
        }
    }

    @Override
    public String toString()
    {
        return 
        "==== Student ====\n" +
        "ID: " + id + "\n" +
        "Name: "  + name + "\n" +
        "Email: "  + email + "\n" + 
        "Major: "  + major + "\n" + 
        "Minor: "  + minor + "\n\n";
    }

}

