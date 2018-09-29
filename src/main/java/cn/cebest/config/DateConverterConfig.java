package cn.cebest.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import lombok.extern.slf4j.Slf4j;

/**
 * 日期转换配置
 * @author maming
 * @date 2018年9月29日
 */

@Slf4j
@Configuration
public class DateConverterConfig {

	@Bean
	public Converter<String, Date> dateConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (Exception e) {
					log.error("日期转换异常", e);
				}
				return date;
			}
		};
	}

}
