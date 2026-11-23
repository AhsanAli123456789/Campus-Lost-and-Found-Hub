import java.io.Serializable;
import java.time.LocalDate;

public abstract class Item implements Serializable {

    private String id;
    private String title;
    private String description;
    private String location;
    private String reportedBy;

    private LocalDate date;
    private ItemStatus status;

    public Item(String id, String title,
                String description, String location,
                String reportedBy, LocalDate date,
                ItemStatus status) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.reportedBy = reportedBy;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public abstract String getType();

    public void displayDetails() {

        System.out.println("\n----------------------------");

        System.out.println("Item ID: " + id);
        System.out.println("Type: " + getType());
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Reported By: " + reportedBy);
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);

        System.out.println("----------------------------");
    }
}
