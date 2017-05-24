package pasta;

/**
 * Created by devin on 5/17/17.
 */
public class Assignments {
    private String name;
    private String dueDate;
    private int priority;

    public Assignments()
    {
        this.name = "";
        this.dueDate = "";
        this.priority = 0;
    }

    public Assignments(String name, String dueDate, int priority)
    {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }
}
