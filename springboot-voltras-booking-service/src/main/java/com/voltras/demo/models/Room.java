package com.voltras.demo.models;

import java.util.UUID;

import lombok.Builder;

@Builder
public record Room(UUID id, String type, double pricePerNight) {

}
