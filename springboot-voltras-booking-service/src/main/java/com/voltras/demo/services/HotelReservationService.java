package com.voltras.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.voltras.demo.models.Guest;
import com.voltras.demo.models.Reservation;

public interface HotelReservationService {
	public Reservation bookRoom(String roomType, List<Guest> guests, LocalDate checkInDate, LocalDate checkOutDate);
}
