import java.time.LocalDate;

public class LostItem extends Item {

    public LostItem(String id, String title,
                    String description, String location,
                    String reportedBy, LocalDate date) {

        super(id, title, description, location,
              reportedBy, date, ItemStatus.LOST);
    }

    @Override
    public String getType() {
        return "Lost Item";
    }
}
