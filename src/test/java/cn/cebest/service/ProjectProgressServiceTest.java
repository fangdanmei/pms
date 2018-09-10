package cn.cebest.service;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.DateUtil;
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
    	projectProgress.setStartTime(DateUtil.yesterday());
    	projectProgressService.insertOrUpdate(projectProgress);
    	ProjectProgressEntity projectProgress2 = new ProjectProgressEntity();
    	projectProgress2.setName("项目2");
    	projectProgress2.setManager(manager);
    	projectProgress2.setArchived(true);
    	projectProgress2.setCertWorkDays(10);
    	projectProgress2.setStartTime(DateUtil.tomorrow());
    	projectProgressService.insertOrUpdate(projectProgress2);
        Map<String, Object> param = new HashMap<>();
        param.put("name", "项目");
        param.put("pageNum", 1);
        param.put("pageSize", 1);
        param.put("orderBy", "start_time");
        param.put("isAsc", false);
        PageResult result = projectProgressService.queryPage(param);
        assertNotNull(result);
    }

}
