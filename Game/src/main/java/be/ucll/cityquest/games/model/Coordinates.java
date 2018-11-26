package be.ucll.cityquest.games.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Coordinates {

    private double lat;
    private double lon;

    private Coordinates() {
        //for JSON Deserialization
    }

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
