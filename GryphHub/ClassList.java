import java.util.*;

public class ClassList 
{
    private UUID classId;
    private ArrayList<UUID> studentIds;

    public ClassList(
        UUID classId, 
        ArrayList<UUID> studentIds
    ) 
    {
        this.classId = classId;
        this.studentIds = new ArrayList<UUID>(studentIds);
    }

    public ClassList(
        ClassList other
    )
    {
        this.classId = other.classId;
        this.studentIds = other.studentIds;
    }

    //======== Add Methods ========
    public boolean addStudent(UUID studentId) 
    {
        if(studentIds.contains(studentId))
        {
            return false;
        }
        else
        {
            studentIds.add(studentId);
            return true;
        }
    }

    //======== Remove Methods ========
    public boolean removeStudent(UUID studentId) 
    {
        if(studentIds.contains(studentId))
        {
            studentIds.remove(studentId);
            return true;
        }
        else
        {
            return false;
        }
    }

    //======== Getters ========
    public UUID getClassId() 
    {
        return classId;
    }

    public ArrayList<UUID> getStudentIds() 
    {
        return studentIds;
    }

    @Override
    public String toString() 
    {
        ArrayList<String> string = new ArrayList<String>();

        string.add(
            "==== ClassList ====\n" + 
            "Course ID: " + classId + "\n");

        string.add(
            "Student IDs: " + studentIds + "\n\n");

        return String.join("", string);
    }
}

