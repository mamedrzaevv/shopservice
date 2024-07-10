package com.service.shop.repo;

import com.service.shop.models.items;
import org.springframework.data.repository.CrudRepository;

public interface itemsRepository extends CrudRepository<items, Integer> {
}
