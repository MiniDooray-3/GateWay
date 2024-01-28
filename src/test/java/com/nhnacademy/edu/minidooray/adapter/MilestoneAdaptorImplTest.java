package com.nhnacademy.edu.minidooray.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MilestoneAdaptorImplTest {

    @MockBean
    private RestTemplate restTemplate;


    @Autowired
    private MilestoneAdaptorImpl milestoneAdaptor;

    @Test
    void testCreateMilestone() {
    }

    @Test
    void testGetMilestones() {
    }

    @Test
    void testModifyMilestone() {
    }

    @Test
    void testDeleteMilestone() {
    }
}