package hotel.application;

import com.querydsl.core.types.dsl.BooleanExpression;
import hotel.domain.Hotel;
import hotel.domain.QHotel;
import hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    public HotelRepository hotelRepository;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> findByPricePerNight(int maxPrice) {
        return hotelRepository.findByPricePerNightLessThanEqual(maxPrice);
    }

    @Override
    public List<Hotel> findByStar(int minStar) {
        return hotelRepository.findByStarGreaterThanEqual(minStar);
    }

    @Override
    public List<Hotel> findByReviews(int minRating) {
        return hotelRepository.findByRatingIsGreaterThanEqual(minRating);
    }

    @Override
    public List<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    @Override
    public Hotel insertHotel(Hotel hotel) {
        return hotelRepository.insert(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public String deleteHotel(String id) {
        return hotelRepository.deleteHotelById(id);
    }

    @Override
    public List<Hotel> findByCountry(String country) {
        //create a query class QHotel
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByCountry = qHotel.location.country.eq(country);
        return (List<Hotel>) hotelRepository.findAll(filterByCountry);
    }

    @Override
    public List<Hotel> findByRecommend() {
        final int maxPrice = 3000;
        final int minRating = 7;

        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);
        return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
    }

    @Override
    public List<Hotel> findByPurpose(String purpose) {
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByPurpose = qHotel.reviews.any().purpose.eq(purpose);
        return (List<Hotel>) hotelRepository.findAll(filterByPurpose);

    }

}
