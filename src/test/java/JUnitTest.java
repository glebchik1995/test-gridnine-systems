import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightBuilder;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.FlightFilterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gridnine.testing.util.Constant.currentDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    private FlightBuilder flightBuilder;
    private List<Flight> flights;
    private FlightFilter flightFilter;
    private List<Segment> segments;


    @BeforeEach
    public void beforeEach() {

        flightBuilder = new FlightBuilder();

        flights = new ArrayList<>();

        flightFilter = new FlightFilterImpl();

        segments = new ArrayList<>();
    }

    @Test
    public void shouldThrowExceptionIfNumberDatesNotEven() {

        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> flightBuilder.createFlight(currentDateTime.plusDays(3)));

        assertEquals("Вы должны передать четное количество дат", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfFightIsEmpty() {

        final NullPointerException exception =
                assertThrows(NullPointerException.class,
                        () -> flightBuilder.createFlight());

        assertEquals("Рейс не должен быть пустым", exception.getMessage());
    }

    @Test
    public void shouldCreateCommonFlightDuration2Hours() {

        segments.add(new Segment(LocalDateTime.now(), currentDateTime.plusHours(2)));

        Flight flight = new Flight(segments);

        Flight flightTest = flightBuilder.createFlight(currentDateTime, currentDateTime.plusHours(2));

        assertFalse(segments.isEmpty());

        assertEquals(flight.toString(), flightTest.toString());
    }

    @Test
    public void shouldFindFlightsBeforeCurrentTime() {

        segments.add(new Segment(currentDateTime.minusHours(5), currentDateTime));

        flights.add(new Flight(segments));

        List<Flight> filteredFlights = flightFilter.findFlightsBeforeCurrentTime(flights);

        List<Flight> flightsTest = new ArrayList<>();

        flightsTest.add(flightBuilder.createFlight(currentDateTime.minusHours(5), currentDateTime));

        assertEquals(filteredFlights.toString(), flightsTest.toString());
    }


    @Test
    public void shouldFindAllSegmentsArrivalDateIsBeforeDepartureDate() {

        segments.add(new Segment(currentDateTime, currentDateTime.minusHours(1)));

        flights.add(new Flight(segments));

        List<Flight> filteredFlights = flightFilter.findAllSegmentsArrivalDateIsBeforeDepartureDate(flights);

        List<Flight> flightsTest = new ArrayList<>();

        flightsTest.add(flightBuilder.createFlight(currentDateTime, currentDateTime.minusHours(1)));

        assertEquals(filteredFlights.toString(), flightsTest.toString());
    }

    @Test
    public void shouldFindFlightsTimeOnEarthIsOver2Hours() {

        segments.add(new Segment(currentDateTime.plusHours(2).plusMinutes(1), currentDateTime));

        flights.add(new Flight(segments));

        List<Flight> filteredFlights = flightFilter.findFlightsTimeOnEarthIsOver2Hours(flights);

        List<Flight> flightsTest = new ArrayList<>();

        flightsTest.add(flightBuilder.createFlight(currentDateTime.plusHours(2).plusMinutes(1), currentDateTime));

        assertEquals(filteredFlights.toString(), flightsTest.toString());
    }

    @Test
    public void shouldFindAllSegments() {

        List<Segment> segmentsTest = flightBuilder.createFlights().stream()
                .flatMap((Flight x) -> x.getSegments().stream())
                .collect(Collectors.toList());

        List<Segment> segmentsTest1 = flightFilter.findAllSegments(flightBuilder.createFlights());

        assertEquals(segmentsTest.toString(), segmentsTest1.toString());
        assertNotNull(segmentsTest1);
    }
}
