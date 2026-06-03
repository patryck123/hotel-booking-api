package com.patryck.hotel.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Entity @Table(name = "bookings") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "room_id") private Room room;
    @Column(nullable = false) private String guestName;
    @Column(nullable = false) private String guestEmail;
    @Column(nullable = false) private String guestCpf;
    @Column(nullable = false) private LocalDate checkIn;
    @Column(nullable = false) private LocalDate checkOut;
    @Column(precision = 10, scale = 2) private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING) @Builder.Default private BookingStatus status = BookingStatus.CONFIRMED;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (room != null && checkIn != null && checkOut != null)
            totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(checkIn, checkOut)));
    }
}
