package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import java.util.List;

public interface TagService {

    void registerTag(RegisterTag tag);

    void modifyTag(Long tagId, ModifyTag tag);

    void deleteTag(Long tagId);

    List<GetTag> getTags(Long projectId);

    GetTag getTag(Long tagId);

    List<GetTag> getTagsByTaskId(Long taskId);
}