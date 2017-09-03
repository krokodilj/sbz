package com.java.sbz.repository;

import com.java.sbz.models.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/3/17.
 */
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Long> {

    ArticleCategory findOneByName(String name);

}
