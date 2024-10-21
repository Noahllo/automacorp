package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dto.RoomDto;
import com.emse.spring.automacorp.mapper.RoomMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController //
@RequestMapping("/api/rooms") //
@Transactional //
public class RoomController {
    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping //
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .sorted(Comparator.comparing(RoomDto::name))
                .collect(Collectors.toList());  //
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null); //
    }

    @PostMapping //
    public ResponseEntity<RoomDto> create(@RequestBody RoomCommand room) { //
        RoomEntity entity = new RoomEntity(room.name(), room.currentTemperature(), room.floor());
        entity.setCurrentTemperature(room.currentTemperature());
        RoomEntity saved = roomDao.save(entity);
        return ResponseEntity.ok(RoomMapper.of(saved));
    }

    @PutMapping(path = "/{id}") //
    public ResponseEntity<RoomDto> update(@PathVariable Long id, @RequestBody RoomCommand room) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setCurrentTemperature(room.currentTemperature());
        entity.setName(room.name());
        entity.setFloor(room.floor());
        // (11)
        return ResponseEntity.ok(RoomMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }
}