package com.java.sbz.repository;

import com.java.sbz.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/6/17.
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
