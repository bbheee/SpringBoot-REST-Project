package hotel.config;

import hotel.domain.Hotel;
import hotel.domain.Location;
import hotel.domain.Review;
import hotel.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DBConfig implements CommandLineRunner {
    private HotelRepository hotelRepository;

    public DBConfig(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Hotel grand = new Hotel(
                "Grand Hotel",
                5000,
                5,
                new Location("Stockholm", "Sweden"),
                Arrays.asList(
                        new Review("hello", 10, "business", true),
                        new Review("secret", 8, "leisure", true)
                )
        );

        Hotel hilton = new Hotel(
                "Hilton Hotel",
                2000,
                4,
                new Location("Tokyo", "Japan"),
                Arrays.asList(
                        new Review("john", 9, "business", false),
                        new Review("malin", 8, "business", true)
                )
        );

        Hotel kimpton = new Hotel(
                "Kimpton Hotel",
                3000,
                4,
                new Location("Los Angeles", "USA"),
                Arrays.asList(
                        new Review("julia", 8, "business", false),
                        new Review("tom", 7, "adventure", true)
                )
        );

        Hotel story = new Hotel(
                "Story Hotel",
                1000,
                3,
                new Location("Stockholm", "Sweden"),
                new ArrayList<>()
        );

        this.hotelRepository.deleteAll();
        List<Hotel> hotels = Arrays.asList(grand, hilton, kimpton, story);
        hotelRepository.saveAll(hotels);
    }
}
