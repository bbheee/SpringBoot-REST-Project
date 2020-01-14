package hotel.presentation;

import hotel.application.HotelService;
import hotel.domain.Hotel;
import hotel.presentation.error.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> hotels = this.hotelService.findAll();
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getById(@Valid @PathVariable("id") String id) {
        Optional<Hotel> hotel = this.hotelService.findById(id);
        if (hotel.isPresent()) {
            return new ResponseEntity(hotel.get(), HttpStatus.OK);
        }
        throw new RecordNotFoundException("Invalid hotel id " + id);
    }

    @GetMapping("/price/{maxPrice}")
    public ResponseEntity<List<Hotel>> getByPricePerNight(@Valid @PathVariable("maxPrice") int maxPrice) {
        List<Hotel> hotels = this.hotelService.findByPricePerNight(maxPrice);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No match with max price " + maxPrice);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("star/{minStar}")
    public ResponseEntity<List<Hotel>> getByStar(@Valid @PathVariable("minStar") int minStar) {
        List<Hotel> hotels = this.hotelService.findByStar(minStar);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel found with min star " + minStar);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<List<Hotel>> getByCity(@Valid @PathVariable("city") String city) {
        List<Hotel> hotels = this.hotelService.findByCity(city);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel found in city " + city);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("rating/{minRating}")
    public ResponseEntity<List<Hotel>> getByRating(@Valid @PathVariable("minRating") int minRating) {
        List<Hotel> hotels = this.hotelService.findByReviews(minRating);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel found with min rating " + minRating);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Hotel>> getByCountry(@Valid @PathVariable("country") String country) {
        List<Hotel> hotels = this.hotelService.findByCountry(country);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel in the country " + country);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<Hotel>> getRecommended() {
        List<Hotel> hotels = this.hotelService.findByRecommend();
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel with recommendation found!");
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @GetMapping("/purpose/{purpose}")
    public ResponseEntity<List<Hotel>> getByPurpose(@Valid @PathVariable("purpose") String purpose) {
        List<Hotel> hotels = this.hotelService.findByPurpose(purpose);
        if (hotels.isEmpty()) {
            throw new RecordNotFoundException("No hotel with purpose " + purpose);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Hotel> insert(@Valid @RequestBody Hotel hotel) {
        this.hotelService.insertHotel(hotel);
        return new ResponseEntity(hotel, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Hotel> update(@Valid @RequestBody Hotel hotel) {
        this.hotelService.updateHotel(hotel);
        return new ResponseEntity(hotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable("id") String id) {
        String hotel = this.hotelService.deleteHotel(id);
        if (!id.contains(hotel)) {
            throw new RecordNotFoundException("Cannot delete the hotel with id " + id);
        }
        return new ResponseEntity("Hotel with id " + id + " is now deleted successfully!", HttpStatus.ACCEPTED);
    }

}

