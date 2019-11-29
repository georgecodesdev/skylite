package com.example.skylite.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "constellation_table")
public class Constellation {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Id")
    private String Id;
    @ColumnInfo(name = "ObservationSeason")
    private String ObservationSeason;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "Area")
    private float Area;
    @ColumnInfo(name = "Declination")
    private String Declination;
    @ColumnInfo(name = "RightAscension")
    private String RightAscension;
    @ColumnInfo(name = "PrincipalStar")
    private String PrincipalStar;
    @ColumnInfo(name = "CelestialEquatorZone")
    private String CelestialEquatorZone;
    @ColumnInfo(name = "EclipticZone")
    private String EclipticZone;
    @ColumnInfo(name = "Quadrant")
    private String Quadrant;
    @ColumnInfo(name = "NameOrigin")
    private String NameOrigin;
    @ColumnInfo(name = "Meaning")
    private String Meaning;
    @ColumnInfo(name = "Image")
    private String Image;
    @ColumnInfo(name = "Story")
    private String Story;
    @ColumnInfo(name = "FirstAppeared")
    private String FirstAppeared;


    public Constellation(@NonNull String Id, String ObservationSeason, String Name, float Area, String Declination, String RightAscension, String PrincipalStar, String CelestialEquatorZone, String EclipticZone, String Quadrant, String NameOrigin, String Meaning, String Image, String Story, String FirstAppeared) {
        this.Id = Id;
        this.ObservationSeason = ObservationSeason;
        this.Name = Name;
        this.Area = Area;
        this.Declination = Declination;
        this.RightAscension = RightAscension;
        this.PrincipalStar = PrincipalStar;
        this.CelestialEquatorZone = CelestialEquatorZone;
        this.EclipticZone = EclipticZone;
        this.Quadrant = Quadrant;
        this.NameOrigin = NameOrigin;
        this.Meaning = Meaning;
        this.Image = Image;
        this.Story = Story;
        this.FirstAppeared = FirstAppeared;
    }

    @NonNull
    public String getId() {
        return Id;
    }

    public String getObservationSeason() {
        return ObservationSeason;
    }

    public String getName() {
        return Name;
    }

    public float getArea() {
        return Area;
    }

    public String getDeclination() {
        return Declination;
    }

    public String getRightAscension() {
        return RightAscension;
    }

    public String getPrincipalStar() {
        return PrincipalStar;
    }

    public String getCelestialEquatorZone() {
        return CelestialEquatorZone;
    }

    public String getEclipticZone() {
        return EclipticZone;
    }

    public String getQuadrant() {
        return Quadrant;
    }

    public String getNameOrigin() {
        return NameOrigin;
    }

    public String getMeaning() {
        return Meaning;
    }

    public String getImage() {
        return Image;
    }

    public String getStory() {
        return Story;
    }

    public String getFirstAppeared() {
        return FirstAppeared;
    }
}
