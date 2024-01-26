package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.tag.TagRequest;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import java.util.List;

public interface TagAdaptor {

    void registerTag(RegisterTag tag);

    void modifyTag(Long tagId, TagRequest tag);

    void deleteTag(Long tagId);

    List<TagRequest> getTags(Long projectId);

    TagRequest getTag(Long tagId);
}