package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.ModifyMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import java.util.List;

public interface MilestoneAdaptor {
    void createMilestone(RegisterMilestone registerMilestone);

    List<GetMilestone> getMilestones(Long projectId);

    void modifyMilestone(Long milestoneId, ModifyMilestone modifyMilestone);

    void deleteMilestone(Long milestoneId);
}
