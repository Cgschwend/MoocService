package com.example.mooclistService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagEntityRepository extends JpaRepository<TagEntity, String>  {

    List<TagEntity> findAll();
    List<TagEntity> findAllByTagEquals(String tag);

}