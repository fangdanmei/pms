package cn.cebest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import cn.cebest.framework.util.AppContextUtil;

/**
 *  springboot启动类
  * @author maming  
  * @date 2018年8月30日
 */

@MapperScan("cn.cebest.dao")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		// 设置spring上下文
		AppContextUtil.setApplicationContext(applicationContext);
	}
	
	
	
}
