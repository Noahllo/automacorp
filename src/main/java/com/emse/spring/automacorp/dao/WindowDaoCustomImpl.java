package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Objects;

public class WindowDaoCustomImpl implements WindowDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WindowEntity> findRoomsWithOpenWindows(Long id) {
        String jpql = "select w from WindowEntity w inner join w.windowStatus s " +
                "where w.room.id = :id and s.value > 0.0 order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<WindowEntity> findWindowsByRoomName(String name) {
        String jpql = "select w from WindowEntity w " +
                "where w.room.name = :name order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    // Pas faisable facilement car il faudrait changer le statut du sensor or,
    // pour cela on devrait avoir une jointure entre WindowEntity et SensorEntity
    // Ce qui n'est pas le cas, on devrait alors modifier tout notre code
    /* @Override
     public void openOrCloseAllWindowsInRoom(Long id,String endStatus) {
        if (Objects.equals(endStatus, "open"))
        {
            String jpql = "update s from SensorEntity s inner join w.windowStatus s " +
                    "where w.room.id = :id and s.value = 0.0 order by w.name";
            em.createQuery(jpql, WindowEntity.class)
                    .setParameter("id",id)
                    .executeUpdate();
        }
        if (Objects.equals(endStatus, "close"))
        {
            String jpql = "update w from WindowEntity w inner join w.windowStatus s " +
                    "where w.room.id = :id and s.value > 0.0 order by w.name";
            em.createQuery(jpql, WindowEntity.class)
                    .setParameter("id", id)
                    .executeUpdate();
        }
    } */

}