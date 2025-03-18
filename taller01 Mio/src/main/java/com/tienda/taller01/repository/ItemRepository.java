package com.tienda.taller01.repository;

import com.tienda.taller01.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
