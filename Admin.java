public class Admin extends User {

    public Admin(String id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }

    public void approveClaim(Claim claim) {
        claim.setStatus(ClaimStatus.APPROVED);
    }

    public void rejectClaim(Claim claim) {
        claim.setStatus(ClaimStatus.REJECTED);
    }
}