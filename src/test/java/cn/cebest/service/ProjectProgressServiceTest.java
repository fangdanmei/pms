package cn.cebest.service;

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

import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.util.PageResult;
import cn.cebest.utils.PageUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectProgressServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectProgressService projectProgressService;

    @Test
    public void insert() {
    	ProjectProgressEntity projectProgress = new ProjectProgressEntity();
    	projectProgress.setArchived(true);
    	projectProgress.setCertWorkDays(10);
    	projectProgressService.insertOrUpdate(projectProgress);
    	
    	ProjectProgressEntity entity = projectProgressService.selectById(projectProgress.getId());
    	assertNotNull(entity);
    }
    
    @Test
    public void findByArchived() {
    	ProjectProgressEntity projectProgress = new ProjectProgressEntity();
    	projectProgress.setArchived(true);
    	projectProgress.setName("一二三四五六");
    	projectProgress.setCertWorkDays(10);
    	projectProgressService.insert(projectProgress);
    	Map map = new HashMap();
    	map.put("name", "一二三四五六");
    	List<ProjectProgressEntity> list = projectProgressService.selectByMap(map);
    	assertNotNull(list);
    }
    
    @Test
    public void queryPage() {
    	String name = "测试项目名称";
    	String manager = "张立新";
    	String risk = "高";
    	ProjectProgressEntity projectProgress = new ProjectProgressEntity();
    	projectProgress.setName(name);
    	projectProgress.setManager(manager);
    	projectProgress.setArchived(true);
    	projectProgress.setCertWorkDays(10);
    	projectProgressService.insertOrUpdate(projectProgress);
        Map<String, Object> param = new HashMap<>();
        param.put("name", "测");
//        param.put("manager", manager);
        param.put("page", "1");
        param.put("limit", "1");
        PageResult result = projectProgressService.queryPage(null);
        assertNotNull(result);
    }

}
