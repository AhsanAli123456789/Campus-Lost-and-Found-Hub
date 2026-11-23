import java.time.LocalDate;

public class FoundItem extends Item {

    public FoundItem(String id, String title,
                    String description, String location,
                    String reportedBy, LocalDate date) {

        super(id, title, description, location,
            reportedBy, date, ItemStatus.FOUND);
    }

    @Override
    public String getType() {
        return "Found Item";
    }
}