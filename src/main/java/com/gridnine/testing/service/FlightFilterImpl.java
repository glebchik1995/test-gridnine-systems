package com.gridnine.testing.service;


import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gridnine.testing.util.Constant.currentDateTime;
import static com.gridnine.testing.util.Constant.formatter;

public class FlightFilterImpl implements FlightFilter {

    @Override
    public void printAllFlights(List<Flight> flights) {
        flights.stream()
                .flatMap((Flight x) -> x.getSegments().stream())
                .forEach(System.out::println);
    }

    @Override
    public List<Segment> findAllSegments(List<Flight> flights) {
        return flights.stream()
                .flatMap((Flight x) -> x.getSegments().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> findFlightsBeforeCurrentTime(List<Flight> flights) {

        List<Flight> tempFlights = new ArrayList<>();

        List<Segment> segs = flights.stream()
                .flatMap((Flight flight) -> flight.getSegments().stream())
                .filter((Segment segment) -> segment.getDepartureDate().isBefore(currentDateTime))
                .collect(Collectors.toList());

        filterPrintInfo(segs);

        tempFlights.add(new Flight(segs));

        return tempFlights;
    }

    @Override
    public List<Flight> findAllSegmentsArrivalDateIsBeforeDepartureDate(List<Flight> flights) {

        List<Flight> tempFlights = new ArrayList<>();

        List<Segment> segs = flights.stream()
                .flatMap((Flight flight) -> flight.getSegments().stream())
                .filter((Segment segment) -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
                .collect(Collectors.toList());

        filterPrintInfo(segs);


        tempFlights.add(new Flight(segs));

        return tempFlights;
    }

    @Override
    public List<Flight> findFlightsTimeOnEarthIsOver2Hours(List<Flight> flights) {

        List<Flight> tempFlights = new ArrayList<>();

        List<Segment> segs = flights.stream()
                .flatMap((Flight flight) -> flight.getSegments().stream())
                .filter((Segment segment) -> segment.getArrivalDate().plusHours(2).isBefore(segment.getDepartureDate()))
                .collect(Collectors.toList());

        filterPrintInfo(segs);

        tempFlights.add(new Flight(segs));

        return tempFlights;
    }

    private void filterPrintInfo(List<Segment> segmentList) {
        segmentList.forEach((Segment segment) -> {
            LocalDateTime departure = segment.getDepartureDate();
            LocalDateTime arrival = segment.getArrivalDate();
            printInfo(departure, arrival);
        });
    }

    private void printInfo(LocalDateTime departure, LocalDateTime arrival) {
        System.out.println("Вылет: " + formatter.format(departure) + "\n"
                + "Прилет: " + formatter.format(arrival) + "\n");
    }
}