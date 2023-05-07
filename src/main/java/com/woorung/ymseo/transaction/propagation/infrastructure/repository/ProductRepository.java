package com.woorung.ymseo.transaction.propagation.infrastructure.repository;

import com.woorung.ymseo.transaction.propagation.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
