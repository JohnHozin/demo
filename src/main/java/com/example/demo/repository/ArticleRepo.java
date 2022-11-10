package com.example.demo.repository;

import com.example.demo.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<ArticleEntity, Integer> {
    ArticleEntity findByTitle(String title);
}
