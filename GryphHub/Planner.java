import java.util.*;
import java.time.*;

public class Planner {

    private static Planner _planner = null;
    private HashMap<UUID, User> users;
    private HashMap<UUID, Course> courses;
    private HashMap<UUID, RegistrationInfo> registrationInfo;

    private Planner()
    {
        users = new HashMap<UUID, User>();
        courses = new HashMap<UUID, Course>();
        registrationInfo = new HashMap<UUID, RegistrationInfo>();
    }

    public static synchronized Planner getInstance()
    {
        if(_planner == null)
        {
            _planner = new Planner();
        }
        return _planner;
    }

    //======== Misc. Methods ========
    public boolean verifyProf(UUID professorId, UUID courseId)
    {
        if(users.containsKey(professorId) && courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            User user = users.get(professorId);
            if(user instanceof Professor)
            {
                Professor professor = (Professor)user;
                if(course.getInstructor() == professor.getName());
                    return true;
            }
        }
        return false;
    }

    public boolean completeCourse(UUID userId, UUID courseId, UUID sectionId)
    {
        if(!registrationInfo.containsKey(userId))
        {
            registrationInfo.put(userId, new RegistrationInfo(userId));
        }
        
        if(users.containsKey(userId) && courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            Section section = course.getSection(sectionId);
            if(registrationInfo.containsKey(userId) && section != null)
            {
                RegistrationInfo rInfo = registrationInfo.get(userId);

                rInfo.removeCourse(course.getId());
                rInfo.removeSection(course.getId(), section.getId());

                rInfo.addCompleted(course.getId(), section.getId());

                section.setRegistered(section.getRegistered() - 1);

                course.addSection(section);
                registrationInfo.put(userId, rInfo);
                courses.put(course.getId(), course);

                return true;
            }  
        }
        return true;
    }

    public Timetable createTimetable(UUID studentId)
    {
        if(!registrationInfo.containsKey(studentId))
        {
            registrationInfo.put(studentId, new RegistrationInfo(studentId));
        }

        if(registrationInfo.containsKey(studentId))
        {
            RegistrationInfo info = registrationInfo.get(studentId);
            ArrayList<Course> userCourses = new ArrayList<Course>();
            ArrayList<Section> userSections = new ArrayList<Section>();
            ArrayList<LocalDateTime> userTimes = new ArrayList<LocalDateTime>();

            for(UUID courseId : info.getRegisteredCourses())
            {
                Course course = courses.get(courseId);

                userCourses.add(course);
                
                for(UUID sectionId : info.getRegisteredSections())
                {
                    Section temp = course.getSection(sectionId);
                    if(temp != null)
                    {
                        userSections.add(temp);
                        userTimes.addAll(temp.getTimes());
                    }
                }

            }

            return new Timetable(studentId, userCourses, userSections, userTimes);
        }
        else
        {
            return null;
        }
    }

    public ClassList createClassList(UUID courseId)
    {

        if (courseId == null) {
            return null;
        }
        
        // Go through registration info to determine if each student belongs to the course
        ArrayList<UUID> studentIds = new ArrayList<UUID>();
        for(RegistrationInfo info : registrationInfo.values())
        {
            if(info.getRegisteredCourses().contains(courseId))
            {
                studentIds.add(info.getStudentId());
            }
        }

        // Return the new ClassList object
        return new ClassList(courseId, studentIds);
    }

    //======== Add Methods ========
    public boolean registerCourse(UUID userId, UUID courseId, UUID sectionId)
    {
        if(!registrationInfo.containsKey(userId))
        {
            registrationInfo.put(userId, new RegistrationInfo(userId));
        }

        if(users.containsKey(userId) && courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);

            Section section = course.getSection(sectionId);

            if(registrationInfo.containsKey(userId) && section != null)
            {
                if(section.getRegistered() < section.getCapacity())
                {
                    RegistrationInfo rInfo = registrationInfo.get(userId);

                    rInfo.addCourse(course.getId());
                    rInfo.addSection(course.getId(), section.getId());

                    section.setRegistered(section.getRegistered() + 1);

                    course.addSection(section);
                    registrationInfo.put(userId, rInfo);
                    courses.put(course.getId(), course);

                    return true;
                }
            }  
        }

        return false;
    }

    public boolean registerCourse(UUID userId, String courseCode)
    {
        Course course = null;
        for(Course c : courses.values())
        {
            if(c.getCode().equals(courseCode))
            {
                course = c;
                break;
            }
        }

        if(course != null)
        {
            ArrayList<Section> sections = course.getAllSections();

            if(!sections.isEmpty())
            {
                boolean result = registerCourse(userId, course.getId(), sections.get(0).getId());
                return result;
            }
        }
        
        return false;
    }

    public void addCourse(Course course)
    {
        courses.put(course.getId(), course);
    }

    public void addUser(UUID id, User user)
    {
        users.put(id, user);
    }
    
    //======== Remove Methods ========
    public boolean dropCourse(UUID userId, UUID courseId, UUID sectionId)
    {
        if(!registrationInfo.containsKey(userId))
        {
            registrationInfo.put(userId, new RegistrationInfo(userId));
        }
        
        if(users.containsKey(userId) && courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            Section section = course.getSection(sectionId);

            if(registrationInfo.containsKey(userId) && section != null)
            {
                RegistrationInfo rInfo = registrationInfo.get(userId);

                rInfo.removeCourse(course.getId());
                rInfo.removeSection(courseId ,section.getId());

                section.setRegistered(section.getRegistered() - 1);

                course.addSection(section);
                registrationInfo.put(userId, rInfo);
                courses.put(course.getId(), course);

                return true;
            }  
        }
        return true;
    }

    public boolean dropCourse(UUID userId, String courseCode)
    {
        Course course = null;
        for(Course c : courses.values())
        {
            if(c.getCode().equals(courseCode))
            {
                course = c;
                break;
            }
        }

        if(course != null)
        {
            ArrayList<Section> sections = course.getAllSections();

            if(!sections.isEmpty())
            {
                return dropCourse(userId, course.getId(), sections.get(0).getId());
            }
        }
        
        return false;
    }

    //======== Getters ========
    public String getCourseInfo(UUID courseId)
    {
        if(courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            return course.toString();
        }
        return "";
    }

    public String getSectionInfo(UUID courseId, UUID sectionId)
    {
        if(courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            Section section = course.getSection(sectionId);

            if(section != null)
            {
                return section.toString();
            }
        }
        return "";
    }

    public RegistrationInfo getUserInfo(UUID userId)
    {
        if(registrationInfo.containsKey(userId))
        {
            registrationInfo.put(userId, new RegistrationInfo(userId));
        }

        return registrationInfo.get(userId);
    }

    public ClassList getClassList(String courseCode)
    {
        Course course = getCourse(courseCode);
        if(course!=null){
            return createClassList(course.getId());
        }
        return null;
    }

    public Course getCourse(UUID courseId)
    {
        if(courses.containsKey(courseId))
        {
            return courses.get(courseId);
        }
        return null;
    }

    public Course getCourse(String courseCode)
    {
        Course course = null;
        for(Course c: courses.values()){
            if(c.getCode().equals(courseCode)){
                course = c;
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getAllCourses()
    {
        return new ArrayList<Course>(courses.values());
    }

    //======== Setters ========
    public boolean setOfficeHours(UUID courseId, LocalDateTime start, LocalDateTime end)
    {
        if(courses.containsKey(courseId))
        {
            Course course = courses.get(courseId);
            course.addOfficeHours(start, end);
            courses.put(course.getId(), course);
            return true;
        }
        return false;
    }
}

