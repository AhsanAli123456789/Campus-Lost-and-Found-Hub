import java.io.Serializable;
import java.time.LocalDate;

public class Claim implements Serializable {

    private String claimId;
    private String itemId;
    private String claimantName;
    private String claimantEmail;
    private String reason;

    private LocalDate date;

    private ClaimStatus status;

    public Claim(String claimId, String itemId,
                String claimantName, String claimantEmail,
                String reason) {

        this.claimId = claimId;
        this.itemId = itemId;
        this.claimantName = claimantName;
        this.claimantEmail = claimantEmail;
        this.reason = reason;

        this.date = LocalDate.now();
        this.status = ClaimStatus.PENDING;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getItemId() {
        return itemId;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void displayClaim() {

        System.out.println("\n----------------------------");

        System.out.println("Claim ID: " + claimId);
        System.out.println("Item ID: " + itemId);
        System.out.println("Claimant: " + claimantName);
        System.out.println("Email: " + claimantEmail);
        System.out.println("Reason: " + reason);
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);

        System.out.println("----------------------------");
    }
}