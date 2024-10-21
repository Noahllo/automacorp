package com.emse.spring.automacorp.api;


import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController //
@RequestMapping("/api/windows") //
@Transactional //
public class WindowController {
    private final WindowDao windowDao;

    public WindowController(WindowDao roomDao) {
        this.windowDao = roomDao;
    }

    @GetMapping //
    public List<WindowDto> findAll() {
        return windowDao.findAll()
                .stream()
                .map(WindowMapper::of)
                .sorted(Comparator.comparing(WindowDto::name))
                .collect(Collectors.toList());  //
    }

    @GetMapping(path = "/{id}")
    public WindowDto findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowMapper::of).orElse(null); //
    }

    @PostMapping //
    public ResponseEntity<WindowDto> create(@RequestBody WindowCommand window) { //
        WindowEntity entity = new WindowEntity(window.name(), window.sensor(), window.room());
        entity.setWindowStatus(window.sensor());
        WindowEntity saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowMapper.of(saved));
    }

    @PutMapping(path = "/{id}") //
    public ResponseEntity<WindowDto> update(@PathVariable Long id, @RequestBody WindowCommand window) {
        WindowEntity entity = windowDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setWindowStatus(window.sensor());
        entity.setName(window.name());
        entity.setRoom(window.room());
        // (11)
        return ResponseEntity.ok(WindowMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}