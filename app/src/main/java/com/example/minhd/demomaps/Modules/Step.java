package com.example.minhd.demomaps.Modules;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by minhd on 18/02/05.
 */

public class Step {

    private Distance distance ;
    private Duration duration ;
    private LatLng endLocation ;
    private String html_instructions ;
    private String maneuver ;
    private Polyline polyline;
    private LatLng startLocation ;
    private String travel_mode ;


    public Step(Distance distance, Duration duration, LatLng endLocation, String html_instructions, String maneuver, Polyline polyline, LatLng startLocation, String travel_mode) {
        this.distance = distance;
        this.duration = duration;
        this.endLocation = endLocation;
        this.html_instructions = html_instructions;
        this.maneuver = maneuver;
        this.polyline = polyline;
        this.startLocation = startLocation;
        this.travel_mode = travel_mode;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }
}
