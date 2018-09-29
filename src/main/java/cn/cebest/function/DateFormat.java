package cn.cebest.function;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public String dateFormat (Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

}
