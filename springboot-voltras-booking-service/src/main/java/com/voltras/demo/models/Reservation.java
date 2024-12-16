package com.voltras.demo.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record Reservation(UUID id, Room room, List<Guest> guests, LocalDate checkInDate, LocalDate checkOutDate,
		double totalPrice) {

}
