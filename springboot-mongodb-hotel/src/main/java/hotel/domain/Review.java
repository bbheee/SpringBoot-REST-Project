package hotel.domain;

public class Review {
    private String username;
    private int rating;
    private String purpose;
    private boolean approved;

    protected Review() {

    }

    public Review(String username, int rating, String purpose, boolean approved) {
        this.username = username;
        this.rating = rating;
        this.purpose = purpose;
        this.approved = approved;
    }

    public String getUsername() {

        return username;
    }

    public int getRating() {

        return rating;
    }

    public String getPurpose() {

        return purpose;
    }

    public boolean getApproved() {

        return approved;
    }

}
