import java.util.*;
public class RegistrationInfo 
{
    private UUID studentId;
    private ArrayList<UUID> registeredCourses;
    private ArrayList<UUID> registeredSections;
    private ArrayList<UUID> completedCourses;
    private ArrayList<UUID> completedSections;
    
    public RegistrationInfo(
        UUID studentId
    ) 
    {
        this.studentId = studentId;
        this.registeredCourses = new ArrayList<UUID>();
        this.registeredSections = new ArrayList<UUID>();
        this.completedCourses = new ArrayList<UUID>();
        this.completedSections = new ArrayList<UUID>();
    }

    public RegistrationInfo(
        RegistrationInfo other
    )
    {
        this.studentId = other.studentId;
        this.registeredCourses = other.registeredCourses;
        this.registeredSections = other.registeredSections;
        this.completedCourses = other.completedCourses;
        this.completedSections = other.completedSections;
    }

    //======== Add Methods ========
    public boolean addCourse(UUID courseId) 
    {
        if(registeredCourses.contains(courseId))
        {
            return false;
        }
        else
        {
            registeredCourses.add(courseId);
            return true;
        }
    }

    public boolean addSection(UUID courseId, UUID sectionId) 
    {
        if(registeredCourses.contains(courseId) && !registeredSections.contains(sectionId))
        {
            registeredSections.add(sectionId);
            return true;
        }
        else if(!registeredCourses.contains(courseId) && !registeredSections.contains(sectionId))
        {
            registeredCourses.add(courseId);
            registeredSections.add(sectionId);
            return true;
        }
        else
        {
            return false;
        }
        
    }

    public boolean addCompleted(UUID courseId, UUID sectionId) 
    {
        if(registeredCourses.contains(courseId) && registeredSections.contains(sectionId))
        {
            registeredCourses.remove(courseId);
            registeredSections.remove(sectionId);
            completedCourses.add(courseId);
            completedSections.add(sectionId);
            return true;
        }
        else
        {
            return false;
        }
    }

    //======== Remove Methods ========
    public boolean removeCourse(UUID courseId) 
    {
        if(registeredCourses.contains(courseId))
        {
            registeredCourses.remove(courseId);
        }

        if(completedCourses.contains(courseId))
        {
            completedCourses.remove(courseId);
        }

        return true;
    }
  
    public boolean removeSection(UUID courseId, UUID sectionId) 
    {
        if(registeredCourses.contains(courseId) && registeredSections.contains(sectionId))
        {
            registeredSections.remove(sectionId);      
            return true;      
        }
        else
        {
            return false;
        }
    }
    
    public boolean removeCompleted(UUID courseId, UUID sectionId) 
    {
        if(completedCourses.contains(courseId) && completedSections.contains(sectionId))
        {
            completedCourses.remove(courseId);
            completedSections.remove(sectionId);
            return true;
        }
        else
        {
            return false;
        }
    }

    //======== Getters ========
    public UUID getStudentId()
    {
        return studentId;
    }

    public ArrayList<UUID> getRegisteredCourses()
    {
        return registeredCourses;
    }

    public ArrayList<UUID> getRegisteredSections()
    {
        return registeredSections;
    }

    public ArrayList<UUID> getCompletedCourses()
    {
        return completedCourses;
    }

    public ArrayList<UUID> getCompletedSections()
    {
        return completedSections;
    }

    public String toString()
    {
        return
        "======== Registration Info ========\n" +
        "Student ID: " + studentId + "\n" +
        "Registered Courses: " + registeredCourses + "\n" +
        "Registered Sections: " + registeredSections + "\n" +
        "Completed Courses: " + completedCourses + "\n" +
        "Completed Sections: " + completedSections + "\n\n";
    }

}
