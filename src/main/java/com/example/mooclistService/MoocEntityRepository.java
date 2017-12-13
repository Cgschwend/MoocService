package com.example.mooclistService;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Collection;
import java.util.Set;


public interface MoocEntityRepository extends JpaRepository<MoocEntity, String> {

    List<MoocEntity> findAll();
    List<MoocEntity> findAllByOrderByTitle();
    List<MoocEntity> findAllByProviderOrderByTitle(String provider);
    List<MoocEntity> findAllBySubjectTagsLikeOrderByTitle(String subjectTags);

    MoocEntity findDistinctById(String id);
    MoocEntity findByIdOrderByTitle(Integer id);
    List<MoocEntity> findDistinctById(List<TagEntity> tagEntities);
    List<MoocEntity> findBySubjectTagsIn(List<String> tags);
}
