package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import java.util.List;

public interface MilestoneService {
    void createMilestone(RegisterMilestone registerMilestone);

    List<GetMilestone> getMilestones(Long projectId);
}