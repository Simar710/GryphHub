import java.util.*;
import java.time.*;

public class Course 
{

    private UUID id;
    private String code;
    private String name;
    private String instructor;
    private String description;
    private String term;
    private String offered;
    private float credits;
    private Set<UUID> requisites;
    private HashMap<UUID, Section> sections;
    private Set<OfficeHours> officeHours;

    //Constructor
    public Course(
        String code,
        String name,
        String instructor,
        String description,
        String term,
        String offered,
        float credits
    )
    {
        this.id = UUID.randomUUID();
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = description;
        this.term = term;
        this.offered = offered;
        this.credits = credits;
        this.requisites = new HashSet<UUID>();
        this.sections = new HashMap<UUID, Section>();
        this.officeHours = new HashSet<OfficeHours>();
    }

    public Course(
        Course other
    )
    {
        this.id = other.id;
        this.code = other.code;
        this.name = other.name;
        this.instructor = other.instructor;
        this.description = other.description;
        this.term = other.term;
        this.offered = other.offered;
        this.credits = other.credits;
        this.requisites = other.requisites;
        this.sections = other.sections;
        this.officeHours = other.officeHours;
    }

    //======== Add Methods ========
    public boolean addRequisite(UUID courseId)
    {
        requisites.add(courseId);
        return true;
    }
    
    public boolean addSection(Section section)
    {
        sections.put(section.getId(), section);
        return true;
    }

    public boolean addOfficeHours(LocalDateTime startTime, LocalDateTime endTime)
    {
        if(startTime != null && endTime != null)
        {
            officeHours.add(new OfficeHours(startTime, endTime));
            return true;
        }
        else
        {
            return false;
        }
    }

    // ======== Remove Methods ========
    public boolean removeRequisite(UUID courseId)
    {
        if(requisites.contains(courseId))
        {
            requisites.remove(courseId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeSection(Section section)
    {
        if(sections.containsKey(section.getId()))
        {
            sections.remove(section.getId());
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeOfficeHours(LocalDateTime startTime, LocalDateTime endTime)
    {
        if(endTime.isAfter(startTime))
        {
            officeHours.remove(new OfficeHours(startTime, endTime));
            return true;
        }
        else
        {
            return false;
        }
    }

    //======== Getters ========
    public String getCode()
    {
        return code;
    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public float getCredits()
    {
        return credits;
    }


    public String getTerm()
    {
        return term;
    }

    public Set<UUID> getRequisites()
    {
        return requisites;
    }

    public String getOffered()
    {
        return offered;
    }

    public Set<OfficeHours> getOfficeHours()
    {
        return officeHours;
    }

    public String getInstructor() //new
    {
        return instructor;
    }

    public Section getSection(UUID sectionId) //new
    {
        if(sections.containsKey(sectionId))
        {
            return sections.get(sectionId);
        }
        return null;
    }

    public ArrayList<Section> getAllSections() //new
    {
        return new ArrayList<Section>(sections.values());
    }

    //======== Setters ========
    public boolean setCode(String code)
    {
        if(!code.isBlank())
        {
            this.code = code;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setName(String name)
    {
        if(!name.isBlank())
        {
            this.name = name;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setDescription(String description)
    {
        if(!description.isBlank())
        {
            this.description = description;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setCredits(float credits)
    {
        if(credits > 0.0f)
        {
            this.credits = credits;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setTerm(String term)
    {
        if(!term.isBlank())
        {
            this.term = term;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setOffered(String offered)
    {
        if(!offered.isBlank())
        {
            this.offered = offered;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setInstructor(String instructor)
    {
        if(!instructor.isEmpty())
        {
            this.instructor = instructor;
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
        "==== Course ====\n" +
        "ID: " + id + "\n" +
        "Code: " + code + "\n" +
        "Name: " + name + "\n" +
        "Instructor: " + instructor + "\n" +
        "Description: " + description + "\n" +
        "Term: " + term + "\n" +
        "Offered: " + offered + "\n" +
        "Credits: " + String.format("%.02f", credits) + "\n" +
        "Requisites: " + requisites + "\n" +
        "Sections: " + sections.values() + "\n" +
        "Office Hours: " + officeHours + "\n\n";
    }

}

