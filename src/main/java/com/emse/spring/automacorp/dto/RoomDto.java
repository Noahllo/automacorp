package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.WindowEntity;

import java.util.Set;

public record RoomDto(Long id, String name, int floor, Double currentTemperature, Double targetTemperature,
                      Set<WindowEntity> windows) {}

