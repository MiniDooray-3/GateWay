package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.MilestoneAdaptor;
import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.ModifyMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MilestoneServiceImpl implements MilestoneService{
    private final MilestoneAdaptor milestoneAdaptor;

    public MilestoneServiceImpl(MilestoneAdaptor milestoneAdaptor) {
        this.milestoneAdaptor = milestoneAdaptor;
    }

    @Override
    public void createMilestone(RegisterMilestone registerMilestone) {
        milestoneAdaptor.createMilestone(registerMilestone);
    }

    @Override
    public List<GetMilestone> getMilestones(Long projectId) {
        return milestoneAdaptor.getMilestones(projectId);
    }

    @Override
    public void modifyMilestone(ModifyMilestone modifyMilestone) {
        milestoneAdaptor.modifyMilestone(modifyMilestone);
    }

    @Override
    public void deleteMilestone(Long milestoneId) {
        milestoneAdaptor.deleteMilestone(milestoneId);
    }
}
