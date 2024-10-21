package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name="SP_ROOM")
public class RoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private int floor;
    private String name;

    @ManyToOne
    private SensorEntity currentTemperature;

    private Double currentTemperatureValue;
    private Double targetTemperature;

    @OneToMany(mappedBy = "room")
    private Set<WindowEntity> windows =Set.of();

    public RoomEntity(){
    }

    public RoomEntity (Long id, int floor, String name)
    {
        this.id = id;
        this.floor = floor;
        this.name = name;
    }

    public RoomEntity (String name, SensorEntity currentTemperature, int floor)
    {
        this.floor = floor;
        this.currentTemperature = currentTemperature;
        this.name = name;
    }

    public RoomEntity(Long id, String name, int floor, Double currentTemperatureValue, Double targetTemperature,
            Set<WindowEntity> windows)
    {
        this.floor = floor;
        this.currentTemperatureValue = currentTemperatureValue;
        this.targetTemperature=targetTemperature;
        this.name = name;
        this.windows=windows;
    }

    // getteur et setteur for id
    public Long getId()
    {
        return this.id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }

    public int getFloor()
    {
        return this.floor;
    }
    public void setFloor(int floor)
    {
        this.floor=floor;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public Double getTargetTemperature()
    {
        return this.targetTemperature;
    }
    public void setTargetTemperature(Double targetTemperature)
    {
        this.targetTemperature=targetTemperature;
    }

    public SensorEntity getCurrentTemperature()
    {
        return this.currentTemperature;
    }
    public Double getCurrentTemperatureValue()
    {
        return this.currentTemperatureValue;
    }
    public void setCurrentTemperature(SensorEntity currentTemperature) {this.currentTemperature=currentTemperature;}

    public Set<WindowEntity> getWindows() {return this.windows;}
    public void setWindows(Set<WindowEntity> windows) {this.windows=windows;}

}
