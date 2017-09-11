package com.java.sbz.repository;

import com.java.sbz.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sirko on 9/6/17.
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {


    @Query("SELECT a FROM Article as a join a.category as c WHERE" +
            "(:id is null or a.id = :id) AND " +
            "(:name is null or UPPER(a.name) like CONCAT('%',UPPER(:name),'%') ) AND " +
            "(:min is null or a.price > :min) AND " +
            "(:max is null or a.price < :max) AND " +
            "(:category is null or c.id = :category)   ")
    List<Article> search(@Param("id") Long id, @Param("name") String name,@Param("min") Double min,@Param("max") Double max,@Param("category") Long category);


    List<Article> findAllByStatus(Boolean status);

}
