import java.util.*;

public class Applicant implements User
{
    private Planner planner;
    private UUID id;
    private String name;
    private String email;
    private String password;

    public Applicant(
        String name, 
        String password
    )
    {
        this.planner = Planner.getInstance();
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
        this.email = name + "@uoguelph.ca";
    }

    public Applicant(
        Applicant other
    )
    {
        this.planner = Planner.getInstance();
        this.id = other.id;
        this.name = other.name;
        this.password = other.password;
        this.email = other.email;
    }

    //======== Misc. Methods ========
    public ArrayList<String> viewAllCourses()
    {
        ArrayList<Course> courses = planner.getAllCourses();
        ArrayList<String> summaryStrings = new ArrayList<String>();

        for(Course course : courses)
        {
            summaryStrings.add(course.toString());
        }

        return summaryStrings;
    }

    public String viewCourse(UUID courseId)
    {
        Course course = planner.getCourse(courseId);

        if(course != null){
            return course.toString();
        }
        else{
            return null;
        }
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

    // ======== Setters ========
    public boolean setName(String name)
    {
        if(name.isEmpty())
        {
            this.name = new String(name);
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
            this.email = new String(email);
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
            this.password = new String(password);
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
        "==== Applicant ====\n" +
        "ID: " + id + "\n" +
        "Name: " + name + "\n" +
        "Email: " + email + "\n" +
        "Password: " + password + "\n\n";
    }

    
}

