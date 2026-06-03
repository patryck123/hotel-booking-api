package com.patryck.hotel.controller;
import com.patryck.hotel.entity.*;
import com.patryck.hotel.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api") @RequiredArgsConstructor
@Tag(name = "Hotel Booking", description = "Reservas e quartos")
public class BookingController {
    private final RoomRepository roomRepo;
    private final BookingRepository bookingRepo;
    @GetMapping("/rooms") public ResponseEntity<List<Room>> getRooms() { return ResponseEntity.ok(roomRepo.findAll()); }
    @GetMapping("/rooms/available") public ResponseEntity<List<Room>> getAvailable() { return ResponseEntity.ok(roomRepo.findByAvailableTrue()); }
    @PostMapping("/rooms") public ResponseEntity<Room> createRoom(@RequestBody Room room) { return ResponseEntity.status(HttpStatus.CREATED).body(roomRepo.save(room)); }
    @PostMapping("/bookings") @Transactional @Operation(summary = "Criar reserva com verificação de conflito de datas")
    public ResponseEntity<?> book(@RequestBody Booking booking) {
        Room room = roomRepo.findById(booking.getRoom().getId()).orElse(null);
        if (room == null || !room.getAvailable()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Quarto indisponível");
        List<Booking> conflicts = bookingRepo.findConflicts(room.getId(), booking.getCheckIn(), booking.getCheckOut());
        if (!conflicts.isEmpty()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Quarto já reservado para o período");
        booking.setRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingRepo.save(booking));
    }
    @GetMapping("/bookings") public ResponseEntity<List<Booking>> getAll() { return ResponseEntity.ok(bookingRepo.findAll()); }
    @GetMapping("/bookings/guest/{email}") public ResponseEntity<List<Booking>> byGuest(@PathVariable String email) { return ResponseEntity.ok(bookingRepo.findByGuestEmail(email)); }
    @PatchMapping("/bookings/{id}/cancel") @Transactional public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        return bookingRepo.findById(id).map(b -> { b.setStatus(BookingStatus.CANCELLED); return ResponseEntity.ok(bookingRepo.save(b)); }).orElse(ResponseEntity.notFound().build());
    }
}
