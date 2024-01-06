package com.example.catalogservice.service;

import com.example.catalogservice.jpa.CatalogEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
