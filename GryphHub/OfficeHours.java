import java.time.*;

public class OfficeHours 
{
    private LocalDateTime start;
    private LocalDateTime end;

    public OfficeHours(
        LocalDateTime start, 
        LocalDateTime end)
    {
        this.start = start;
        this.end = end;
    }

    public OfficeHours(
        OfficeHours other
    )
    {
        this.start = other.start;
        this.end = other.end;
    }

    //======== Getters ========
    public LocalDateTime getStart()
    {
        return start;
    }

    public LocalDateTime getEnd()
    {
        return end;
    }

    //======== Setters ========
    public boolean setStart(LocalDateTime start)
    {
        if(start.isBefore(end))
        {
            this.start = start;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setEnd(LocalDateTime end)
    {
        if(end.isAfter(start))
        {
            this.end = end;
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
        return start + " - " + end;
    }
}

