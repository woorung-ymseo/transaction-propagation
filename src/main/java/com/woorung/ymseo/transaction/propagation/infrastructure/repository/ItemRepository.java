package com.woorung.ymseo.transaction.propagation.infrastructure.repository;

import com.woorung.ymseo.transaction.propagation.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
