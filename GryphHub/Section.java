import java.util.*;
import java.time.*;

public class Section 
{
    private UUID id;
    private int capacity;
    private int registered;
    private Set<LocalDateTime> times;
    private Set<String> locations;

    public Section(
        int capacity,
        int registered
    )
    {
        this.id = UUID.randomUUID();
        this.capacity = capacity;
        this.registered = registered;
        this.times = new HashSet<LocalDateTime>();
        this.locations = new HashSet<String>();
    }

    public Section(
        Section other
    )
    {
        this.id = other.id;
        this.capacity = other.capacity;
        this.registered = other.registered;
        this.times = other.times;
        this.locations = other.locations;
    }

    //======== Add Methods ========
    public boolean addTime(LocalDateTime time)
    {
        if(times.contains(time))
        {
            return false;
        }
        else
        {
            times.add(time);
            return true;
        }
    }

    public boolean addLocation(String location)
    {
        if(locations.contains(location))
        {
            return false;
        }
        else
        {
            locations.add(location);
            return false;
        }
    }

    //======== Remove Methods ========
    public boolean removeTime(LocalDateTime time)
    {
        if(times.contains(time))
        {
            times.remove(time);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeLocation(String location)
    {
        if(locations.contains(location))
        {
            locations.remove(location);
            return true;
        }
        else
        {
            return false;
        }
    }

    //======== Getters ========
    public Set<LocalDateTime> getTimes()
    {
        return times;
    }

    public Set<String> getLocations()
    {
        return locations;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public int getRegistered()
    {
        return registered;
    }

    public UUID getId()
    {
        return id;
    }

    //======== Setters ========
    public boolean setCapacity(int capacity)
    {
        if(capacity >= 0)
        {
            this.capacity = capacity;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setRegistered(int registered)
    {
        if(registered >= 0 && registered <= capacity)
        {
            this.registered = registered;
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
        "\n==== Section ====\n" +
        "ID: " + id + "\n" +
        "Capacity: " + capacity + "\n" +
        "Registered: " + registered + "\n" +
        "Times: " + times + "\n" +
        "Locations: " + locations + "\n\n";
    }

}   