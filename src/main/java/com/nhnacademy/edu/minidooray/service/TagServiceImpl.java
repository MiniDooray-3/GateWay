package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.TagAdaptor;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.domain.tag.TagRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagAdaptor tagAdaptor;

    public TagServiceImpl(TagAdaptor tagAdaptor) {
        this.tagAdaptor = tagAdaptor;
    }

    @Override
    public void registerTag(RegisterTag tag) {
        tagAdaptor.registerTag(tag);
    }

    @Override
    public void modifyTag(Long tagId, TagRequest tag) {
        tagAdaptor.modifyTag(tagId, tag);
    }

    @Override
    public void deleteTag(Long tagId) {
        tagAdaptor.deleteTag(tagId);
    }

    @Override
    public List<TagRequest> getTags(Long projectId) {
        return tagAdaptor.getTags(projectId);
    }

    @Override
    public TagRequest getTag(Long tagId) {
        return tagAdaptor.getTag(tagId);
    }
}
