package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.util.List;

public interface FlightFilter {

    void printAllFlights(List<Flight> flights);

    List<Segment> findAllSegments(List<Flight> flights);

    List<Flight> findFlightsBeforeCurrentTime(List<Flight> flights);

    List<Flight> findAllSegmentsArrivalDateIsBeforeDepartureDate(List<Flight> flights);

    List<Flight> findFlightsTimeOnEarthIsOver2Hours(List<Flight> flights);
}
