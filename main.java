import java.util.*;
import java.time.LocalDate;

public class main {

    static Scanner sc = new Scanner(System.in);

    static List<Item> items =
        FileManager.loadList("data/items.dat");

    static List<Claim> claims =
        FileManager.loadList("data/claims.dat");

    static Admin admin =
        new Admin("A001", "System Admin",
                "admin@campus.edu");

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println(" CAMPUS LOST & FOUND HUB ");
            System.out.println("================================");

            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. View All Items");
            System.out.println("4. Search Items");
            System.out.println("5. Submit Ownership Claim");
            System.out.println("6. Admin Panel");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            choice = readChoice();

            switch (choice) {

                case 1:
                    reportLostItem();
                    break;

                case 2:
                    reportFoundItem();
                    break;

                case 3:
                    viewItems();
                    break;

                case 4:
                    searchItems();
                    break;

                case 5:
                    submitClaim();
                    break;

                case 6:
                    adminPanel();
                    break;

                case 0:
                    saveData();
                    System.out.println("Thank you for using CLFH!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }

    static int readChoice() {

        try {
            return Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {

            return -1;
        }
    }

    static String readText(String message) {

        String value;

        do {

            System.out.print(message);
            value = sc.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("This field cannot be empty.");
            }

        } while (value.isEmpty());

        return value;
    }

    static String generateId(String prefix) {

        return prefix + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }

    static Student readStudent() {

        System.out.println("\nEnter Student Information");

        String id = generateId("S");
        String name = readText("Student Name: ");
        String email = readText("Student Email: ");

        return new Student(id, name, email);
    }

    static void reportLostItem() {

        System.out.println("\n--- REPORT LOST ITEM ---");

        Student student = readStudent();

        String title = readText("Item Name: ");
        String description = readText("Description: ");
        String location = readText("Last Seen Location: ");

        LostItem item = new LostItem(
            generateId("I"),
            title,
            description,
            location,
            student.getName(),
            LocalDate.now()
        );

        items.add(item);

        saveData();

        System.out.println("\nLost item reported successfully!");
        System.out.println("Your Item ID: " + item.getId());
    }

    static void reportFoundItem() {

        System.out.println("\n--- REPORT FOUND ITEM ---");

        Student student = readStudent();

        String title = readText("Item Name: ");
        String description = readText("Description: ");
        String location = readText("Found Location: ");

        FoundItem item = new FoundItem(
            generateId("I"),
            title,
            description,
            location,
            student.getName(),
            LocalDate.now()
        );

        items.add(item);

        saveData();

        System.out.println("\nFound item reported successfully!");
        System.out.println("Your Item ID: " + item.getId());
    }

    static void viewItems() {

        System.out.println("\n--- ALL REPORTED ITEMS ---");

        if (items.isEmpty()) {

            System.out.println("No items available.");
            return;
        }

        for (Item item : items) {

            item.displayDetails();
        }
    }

    static void searchItems() {

        System.out.println("\n--- SEARCH ITEMS ---");

        String keyword = readText("Enter keyword: ");

        SearchManager.search(items, keyword);
    }

    static void submitClaim() {

        System.out.println("\n--- SUBMIT OWNERSHIP CLAIM ---");

        String itemId = readText("Enter Item ID: ");

        Item selectedItem = null;

        for (Item item : items) {

            if (item.getId().equalsIgnoreCase(itemId)
                    && item.getStatus() == ItemStatus.FOUND) {

                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {

            System.out.println("Found item not available for claims.");
            return;
        }

        Student student = readStudent();

        String reason = readText(
            "Explain why this item belongs to you: ");

        Claim claim = new Claim(
            generateId("C"),
            itemId,
            student.getName(),
            student.getEmail(),
            reason
        );

        claims.add(claim);

        saveData();

        System.out.println("\nClaim submitted successfully!");
        System.out.println("Claim ID: " + claim.getClaimId());
    }

    static void adminPanel() {

        System.out.println("\n--- ADMIN PANEL ---");

        admin.displayInfo();

        boolean hasPending = false;

        for (Claim claim : claims) {

            if (claim.getStatus() == ClaimStatus.PENDING) {

                claim.displayClaim();
                hasPending = true;
            }
        }

        if (!hasPending) {

            System.out.println("No pending claims.");
            return;
        }

        String claimId = readText(
            "\nEnter Claim ID to review (0 to cancel): ");

        if (claimId.equals("0")) {
            return;
        }

        Claim selectedClaim = null;

        for (Claim claim : claims) {

            if (claim.getClaimId().equalsIgnoreCase(claimId)
                    && claim.getStatus() == ClaimStatus.PENDING) {

                selectedClaim = claim;
                break;
            }
        }

        if (selectedClaim == null) {

            System.out.println("Pending claim not found.");
            return;
        }

        System.out.println("1. Approve Claim");
        System.out.println("2. Reject Claim");

        int choice = readChoice();

        if (choice == 1) {

            admin.approveClaim(selectedClaim);

            for (Item item : items) {

                if (item.getId().equals(selectedClaim.getItemId())) {

                    item.setStatus(ItemStatus.RETURNED);
                    break;
                }
            }

            System.out.println("Claim approved successfully!");

        } else if (choice == 2) {

            admin.rejectClaim(selectedClaim);

            System.out.println("Claim rejected.");

        } else {

            System.out.println("Invalid choice.");
            return;
        }

        saveData();
    }

    static void saveData() {

        FileManager.saveList("data/items.dat", items);

        FileManager.saveList("data/claims.dat", claims);
    }
}
