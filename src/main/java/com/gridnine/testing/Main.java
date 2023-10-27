package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightBuilder;
import com.gridnine.testing.service.FlightFilterImpl;

import java.util.List;

import static com.gridnine.testing.util.Message.*;

public class Main {

    public static void main(String[] args) {

        FlightBuilder flightBuilder = new FlightBuilder();

        List<Flight> flights = flightBuilder.createFlights();

        FlightFilterImpl flightFilter = new FlightFilterImpl();

        System.out.println(MESSAGE_1);
        flightFilter.printAllFlights(flights);

        System.out.println();

        System.out.println(MESSAGE_2);
        flightFilter.findFlightsBeforeCurrentTime(flights);

        System.out.println(MESSAGE_3);
        flightFilter.findAllSegmentsArrivalDateIsBeforeDepartureDate(flights);

        System.out.println(MESSAGE_4);
        flightFilter.findFlightsTimeOnEarthIsOver2Hours(flights);
    }
}

