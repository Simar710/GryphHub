import java.time.*;
import java.util.*;

public class Professor implements User
{
    private Planner planner;
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String department;
    private String phone;
    private String office;
    private String description;

    public Professor(
        String name, 
        String password, 
        String department, 
        String phone,
        String office, 
        String description) 
    {
        planner = Planner.getInstance();
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = name + "@uoguelph.ca";
        this.password = password;
        this.department = department;
        this.phone = phone;
        this.office = office;
        this.description = description;
    }

    public Professor(
        Professor other
    )
    {
        planner = Planner.getInstance();
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
        this.password = other.password;
        this.department = other.department;
        this.phone = other.phone;
        this.office = other.office;
        this.description = other.description;
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
        return planner.getCourse(courseId).toString();
    }

    public boolean inputOfficeHours(UUID courseID, LocalDateTime startTime, LocalDateTime endTime)
    {
        return planner.setOfficeHours(courseID, startTime, endTime);
    }

    public ClassList viewClasslist(String courseCode) 
    {
        return planner.getClassList(courseCode);
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

    public String getOffice() 
    {
        return office;
    }

    public String getDescription() 
    {
        return description;
    }

    //======== Setters ========
    public void setName(String name) 
    {
        this.name = name;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public void setOffice(String office) 
    {
        this.office = office;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String toString()
    {
        return 
        "==== Professor ====\n" + 
        "ID: " + id + "\n" +
        "Name: " + name + "\n" +
        "Email: " + email + "\n" +
        "Password: " + password + "\n" +
        "Department: " + department + "\n" +
        "Phone: " + phone + "\n" +
        "Office: " + office + "\n" +
        "Description: " + description + "\n\n";
    }
}
