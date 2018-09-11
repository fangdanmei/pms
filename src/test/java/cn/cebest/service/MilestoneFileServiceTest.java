package cn.cebest.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.cebest.entity.MilestoneFileEntity;
import cn.cebest.entity.ProjectEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MilestoneFileServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MilestoneFileService milestoneFileService;

    @Test
    public void insert() {
    	MilestoneFileEntity entity = new MilestoneFileEntity();
    	entity.setProjectId(1L);
    	entity.setFileName("file1");
    	milestoneFileService.insertOrUpdate(entity);
    	
    	MilestoneFileEntity entity1 = milestoneFileService.selectById(entity.getId());
    	assertNotNull(entity1);
    }
    
    @Test
    public void selectByProjectId() {
    	MilestoneFileEntity entity = new MilestoneFileEntity();
    	long projectId = 1L;
		entity.setProjectId(projectId);
    	entity.setFileName("file1");
    	milestoneFileService.insertOrUpdate(entity);
    	List<MilestoneFileEntity> list = milestoneFileService.selectByProjectId(projectId);
    	assertTrue(null != list && list.size() == 1);
    }

}
