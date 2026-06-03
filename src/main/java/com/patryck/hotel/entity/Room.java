package com.patryck.hotel.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity @Table(name = "rooms") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String roomNumber;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private RoomType type;
    @Column(nullable = false, precision = 8, scale = 2) private BigDecimal pricePerNight;
    @Builder.Default private Boolean available = true;
    private Integer capacity;
    private String description;
}
