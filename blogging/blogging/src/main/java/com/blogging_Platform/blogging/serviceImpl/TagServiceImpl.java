package com.blogging_Platform.blogging.serviceImpl;

import com.blogging_Platform.blogging.models.Tag;
import com.blogging_Platform.blogging.repository.TagRepository;
import com.blogging_Platform.blogging.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createtag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> updateTag(int tagId, Tag updatedTag) {
        return tagRepository.findById(tagId).map(existingTag -> {
            existingTag.setName(updatedTag.getName());
            return tagRepository.save(existingTag);
        });
    }

    @Override
    public boolean deleteTag(int tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            return true;
        }).orElse(false);
    }
}
