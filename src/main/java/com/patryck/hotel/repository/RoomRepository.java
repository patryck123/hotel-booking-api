package com.patryck.hotel.repository;
import com.patryck.hotel.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
    List<Room> findByType(RoomType type);
    List<Room> findByAvailableTrueAndType(boolean available, RoomType type);
}
