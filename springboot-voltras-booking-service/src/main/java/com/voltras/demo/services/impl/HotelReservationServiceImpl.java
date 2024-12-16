package com.voltras.demo.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.voltras.demo.models.Guest;
import com.voltras.demo.models.Reservation;
import com.voltras.demo.models.Room;
import com.voltras.demo.services.HotelReservationService;
import com.voltras.voltrasspring.rpc.services.RpcBasicService;
import com.voltras.voltrasspring.security.Publish;

@Service("HotelReservationService")
public class HotelReservationServiceImpl implements HotelReservationService, RpcBasicService {
	
	private final List<Room> availableRooms = List.of(
			Room.builder().id(UUID.randomUUID()).type("Standard").pricePerNight(100000.0).build(),
			Room.builder().id(UUID.randomUUID()).type("Deluxe").pricePerNight(200000.0).build(),
			Room.builder().id(UUID.randomUUID()).type("Suite").pricePerNight(300000.0).build());
	
	@Override
	@Publish(allowAll = true)
	public Reservation bookRoom(String roomType, List<Guest> guests, LocalDate checkInDate, LocalDate checkOutDate) {
		// TODO Auto-generated method stub
		if (checkInDate.isAfter(checkOutDate)) {
			throw new IllegalArgumentException("Check-in date must be before check-out date.");
		}

		Room room = availableRooms.stream().filter(r -> r.type().equalsIgnoreCase(roomType)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Room type not available."));

		long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		double totalPrice = room.pricePerNight() * nights;

		return Reservation.builder().id(UUID.randomUUID()).room(room).guests(guests).checkInDate(checkInDate)
				.checkOutDate(checkOutDate).totalPrice(totalPrice).build();
	}
}
