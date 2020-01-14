package hotel.repository;

import hotel.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {

    Optional<Hotel> findById(String id);

    List<Hotel> findByPricePerNightLessThanEqual(int maxPrice);

    List<Hotel> findByStarGreaterThanEqual(int minStar);

    @Query(value = "{'reviews.rating':?0}")
    List<Hotel> findByRatingIsGreaterThanEqual(int minRating);

    @Query(value = "{'location.city':?0}")
    List<Hotel> findByCity(String city);

    List<Hotel> findAll();

    String deleteHotelById(String id);

}

