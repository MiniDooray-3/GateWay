package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.domain.tag.TagRequest;
import java.util.List;

public interface TagService {

    void registerTag(RegisterTag tag);

    void modifyTag(Long tagId, TagRequest tag);

    void deleteTag(Long tagId);

    List<TagRequest> getTags(Long projectId);

    TagRequest getTag(Long tagId);
}