package hotel.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Hotels")
public class Hotel {
    @Id
    private String id;
    @NotEmpty(message = "The name cannot be empty")
    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    @NotNull
    @Positive
    private int pricePerNight;
    @NotNull
    @Positive
    private int star;
    @Valid
    @NotNull
    private Location location;
    private List<Review> reviews;

    protected Hotel() {
        this.reviews = new ArrayList<>();
    }

    public Hotel(@NotEmpty String name, @NotNull @Positive int pricePerNight, @NotNull @Positive int star, @Valid @NotNull Location location, List<Review> reviews) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.star = star;
        this.location = location;
        this.reviews = reviews;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getStar() {
        return star;
    }

    public Location getLocation() {
        return location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

}
