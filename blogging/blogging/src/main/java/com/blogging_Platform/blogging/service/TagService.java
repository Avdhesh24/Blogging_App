package com.blogging_Platform.blogging.service;

import com.blogging_Platform.blogging.models.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag createtag(Tag tag);

    List<Tag> getAllTag();

    Optional<Tag> updateTag(int tagId, Tag updatedTag);

    boolean deleteTag(int tagId);
}
