package hotel.domain;

import javax.validation.constraints.NotEmpty;

public class Location {
    @NotEmpty(message = "The location cannot be empty")
    private String city;
    @NotEmpty(message = "The location cannot be empty")
    private String country;

    protected Location() {

    }

    public Location(@NotEmpty String city, @NotEmpty String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
