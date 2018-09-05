package cn.cebest.dao;

import static org.junit.Assert.assertNotNull;

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

import cn.cebest.entity.ProjectEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectMapper projectDao;

    @Test
    public void insert() {
    	ProjectEntity project = new ProjectEntity();
    	project.setDescription(" ");
    	project.setDevLang(" ");
    	project.setName(" ");
    	project.setPlace(" ");
    	project.setProductionMode(" ");
    	project.setTimeLimit(" ");
    	projectDao.insert(project);
    	
    	ProjectEntity entity = projectDao.selectById(project.getId());
    	assertNotNull(entity);
    }
    
    @Test
    public void findByName() {
    	ProjectEntity project = new ProjectEntity();
    	project.setDescription(" ");
    	project.setDevLang(" ");
    	project.setName("vvv");
    	project.setPlace(" ");
    	project.setProductionMode(" ");
    	project.setTimeLimit(" ");
    	projectDao.insert(project);
    	Map map = new HashMap();
    	map.put("name", "vvv");
    	List<ProjectEntity> list = projectDao.selectByMap(map);
    	assertNotNull(list);
    }

}
