package com.example.skylite.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "constellation_table")
public class Constellation {
    @PrimaryKey
    @ColumnInfo(name = "Index")
    private int index;
    @ColumnInfo(name = "Id")
    private String id;
    @ColumnInfo(name = "Observation")
    private String observation;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Constellation area in square degrees")
    private float constellationAreaSquareDegrees;
    @ColumnInfo(name = "Declination")
    private String declination;
    @ColumnInfo(name = "Right Ascension")
    private String rightAscension;
    @ColumnInfo(name = "Principal star")
    private String principalStar;
    @ColumnInfo(name = "Constellation zone (celestial equator)")
    private String constellationZoneCelestialEquator;
    @ColumnInfo(name = "Constellation zone (ecliptic)")
    private String constellationZoneEcliptic;
    @ColumnInfo(name = "Quadrant")
    private String quadrant;
    @ColumnInfo(name = "Name origin")
    private String nameOrigin;
    @ColumnInfo(name = "Meaning")
    private String meaning;
    @ColumnInfo(name = "Image")
    private String image;
    @ColumnInfo(name = "Story")
    private String story;
    @ColumnInfo(name = "First appeared")
    private String firstAppeared;

    public Constellation(int index,
                         String id,
                         String observation,
                         String name,
                         float constellationAreaSquareDegrees,
                         String declination,
                         String rightAscension,
                         String principalStar,
                         String constellationZoneCelestialEquator,
                         String constellationZoneEcliptic,
                         String quadrant,
                         String nameOrigin,
                         String meaning,
                         String image,
                         String story,
                         String firstAppeared) {
        this.index = index;
        this.id = id;
        this.observation = observation;
        this.name = name;
        this.constellationAreaSquareDegrees = constellationAreaSquareDegrees;
        this.declination = declination;
        this.rightAscension = rightAscension;
        this.principalStar = principalStar;
        this.constellationZoneCelestialEquator = constellationZoneCelestialEquator;
        this.constellationZoneEcliptic = constellationZoneEcliptic;
        this.quadrant = quadrant;
        this.nameOrigin = nameOrigin;
        this.meaning = meaning;
        this.image = image;
        this.story = story;
        this.firstAppeared = firstAppeared;
    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getObservation() {
        return observation;
    }

    public String getName() {
        return name;
    }

    public float getConstellationAreaSquareDegrees() {
        return constellationAreaSquareDegrees;
    }

    public String getDeclination() {
        return declination;
    }

    public String getRightAscension() {
        return rightAscension;
    }

    public String getPrincipalStar() {
        return principalStar;
    }

    public String getConstellationZoneCelestialEquator() {
        return constellationZoneCelestialEquator;
    }

    public String getConstellationZoneEcliptic() {
        return constellationZoneEcliptic;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public String getNameOrigin() {
        return nameOrigin;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getImage() {
        return image;
    }

    public String getStory() {
        return story;
    }

    public String getFirstAppeared() {
        return firstAppeared;
    }
}
