package com.patryck.hotel.repository;
import com.patryck.hotel.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByGuestEmail(String email);
    List<Booking> findByStatus(BookingStatus status);
    @Query("SELECT b FROM Booking b WHERE b.room.id=:roomId AND b.status<>'CANCELLED' AND NOT (b.checkOut<=:checkIn OR b.checkIn>=:checkOut)")
    List<Booking> findConflicts(@Param("roomId") Long roomId, @Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
}
