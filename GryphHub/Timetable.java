import java.util.*;
import java.time.*;

public class Timetable 
{
    private UUID studentId;
    private ArrayList<Course> courses;
    private ArrayList<Section> sections;
    private ArrayList<LocalDateTime> lectureTimes;

    public Timetable(
        UUID studentId, 
        ArrayList<Course> courses, 
        ArrayList<Section> sections,
        ArrayList<LocalDateTime> lectureTimes
    ) 
    {
        this.studentId = studentId;
        this.courses = new ArrayList<Course>(courses);
        this.sections = new ArrayList<Section>(sections); 
        this.lectureTimes = new ArrayList<LocalDateTime>(lectureTimes);
    }

    public Timetable(
        Timetable other
    )
    {
        this.studentId = other.studentId;
        this.courses = other.courses;
        this.sections = other.sections;
        this.lectureTimes = other.lectureTimes;
    }

    //======== Misc. Methods ========
    public boolean isLectureConflict(LocalDateTime lectureTime) 
    {
        if(lectureTimes.contains(lectureTime))
        {
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

    public ArrayList<Course> getCourses() 
    {
        return courses;
    }

    public ArrayList<Section> getSections() 
    {
        return sections;
    }

    public ArrayList<LocalDateTime> getLectureTimes() 
    {
        return lectureTimes;
    }

    @Override
    public String toString() {
        String string = 
        "==== Timetable ====\n" +
        "Student ID: " + studentId + "\n";

        for(Course course : courses)
        {

            ArrayList<Section> courseSections = course.getAllSections();

            string += "   Course Code: " + course.getCode() + "\n";

            for(Section section : sections)
            {
                if(courseSections.contains(section))
                {
                    string += "   Times: " + section.getTimes() + "\n";
                    string += "   Location: " + section.getLocations() + "\n";
                }
            }

            string += "\n";

        }

        return string;
    }

}