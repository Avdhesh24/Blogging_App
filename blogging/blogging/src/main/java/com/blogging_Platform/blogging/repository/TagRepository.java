package com.blogging_Platform.blogging.repository;

import com.blogging_Platform.blogging.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
