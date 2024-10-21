package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomDao extends JpaRepository<RoomEntity, Long> {

    @Query("select c from RoomEntity c where c.name=:name")  //
    RoomEntity findByName(@Param("name") String name);

    @Modifying //
    @Query("delete from RoomEntity c where c.name = ?1")
    void deleteByName(String name);

}
