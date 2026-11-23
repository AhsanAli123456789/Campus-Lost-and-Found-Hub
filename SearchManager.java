import java.util.List;

public class SearchManager {

    public static void search(List<Item> items, String keyword) {

        boolean found = false;

        for (Item item : items) {

            if (item.getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())
                || item.getDescription().toLowerCase()
                    .contains(keyword.toLowerCase())
                || item.getLocation().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                item.displayDetails();

                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching items found.");
        }
    }
}