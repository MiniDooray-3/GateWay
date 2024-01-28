package com.nhnacademy.edu.minidooray.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.adapter.MilestoneAdaptor;
import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.ModifyMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MilestoneServiceImplTest {

    @MockBean
    private MilestoneAdaptor milestoneAdaptor;

    @Autowired
    private MilestoneService milestoneService;

    @Test
    void testCreateMilestone() {
        RegisterMilestone registerMilestone = new RegisterMilestone("개발", 1L);
        milestoneService.createMilestone(registerMilestone);

        verify(milestoneAdaptor, times(1)).createMilestone(registerMilestone);
    }

    @Test
    void testGetMilestones() {
        GetMilestone getMilestone = new GetMilestone(1L, "개발");
        given(milestoneAdaptor.getMilestones(1L)).willReturn(List.of(getMilestone));

        Assertions.assertEquals(milestoneService.getMilestones(1L), List.of(getMilestone));
        verify(milestoneAdaptor, times(1)).getMilestones(1L);
    }

    @Test
    void testModifyMilestone() {
        ModifyMilestone modifyMilestone = new ModifyMilestone("개발");
        milestoneService.modifyMilestone(1L, modifyMilestone);

        verify(milestoneAdaptor, times(1)).modifyMilestone(1L, modifyMilestone);
    }

    @Test
    void testDeleteMilestone() {
        milestoneService.deleteMilestone(1L);

        verify(milestoneAdaptor, times(1)).deleteMilestone(1L);
    }
}