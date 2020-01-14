package hotel.application;

import hotel.domain.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<Hotel> findAll();

    Optional<Hotel> findById(String id);

    List<Hotel> findByPricePerNight(int maxPrice);

    List<Hotel> findByStar(int minStar);

    List<Hotel> findByReviews(int minRating);

    List<Hotel> findByCity(String city);

    Hotel insertHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel);

    String deleteHotel(String id);

    List<Hotel> findByCountry(String country);

    List<Hotel> findByRecommend();

    List<Hotel> findByPurpose(String purpose);

}
