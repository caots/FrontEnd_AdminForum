package com.bksoftware.repository.news;

import com.bksoftware.entities.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    List<News> findByStatus(boolean status);

    @Query("SELECT n FROM News n WHERE n.smallCategory.id = :id and  n.status= true")
    Page<News> findBySmallCategoryId(@Param("id") int id, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.smallCategory.id = :id and  n.status= true")
    List<News> findBySmallCategoryIdSize(@Param("id") int id);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.id = :id and n.status= true")
    Page<News> findByBigCategoryId(@Param("id") int id, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.id = :id and n.status= true")
    List<News> findByBigCategoryIdSize(@Param("id") int id);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.menu.id = :id and n.status= true")
    Page<News> findByMenuId(@Param("id") int id, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.menu.id = :id and n.status= true")
    List<News> findByMenuIdSize(@Param("id") int id);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.menu.name = :name and n.status= true")
    Page<News> findByMenuName(@Param("name") String name, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.menu.name = :name and n.status= true")
    List<News> findByMenuNameSize(@Param("name") String name);

    @Query("SELECT n FROM News n WHERE n.smallCategory.bigCategory.name = :name and n.status= true")
    Page<News> findByBigCategoryName(@Param("name") String name, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.smallCategory.name = :name and n.status= true")
    Page<News> findBySmallCategoryName(@Param("name") String name, Pageable pageable);

    //===================================================

    @Query("select n from News n where n.status=true ")
    Page<News> findAllPage(Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.title LIKE CONCAT('%',:title,'%') and n.status= true")
    Page<News> findByNamePage(@Param("title") String title, Pageable pageable);

    News findById(int id);

    @Query(value = " SELECT n from News n  where n.status=true order by (n.time) desc ")
    Page<News> findNewsByTime(Pageable pageable);

    @Query(value = " SELECT n from News n  where n.status=true order by (n.like) desc ")
    Page<News> findNewsByLike(Pageable pageable);
}
