package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import java.util.List;

public interface TagAdaptor {

    void registerTag(RegisterTag tag);

    void modifyTag(Long tagId, ModifyTag tag);

    void deleteTag(Long tagId);

    List<GetTag> getTags(Long projectId);

    GetTag getTag(Long tagId);

    List<GetTag> getTagByTaskId(Long taskId);
}