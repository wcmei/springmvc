package com.study.springmvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/*优先级别高于MyDateEditor*/
public class MyDateEditor extends PropertiesEditor {
	
	@Override
	public void setAsText(String source) throws IllegalArgumentException {
		SimpleDateFormat sdf = getDateFormat(source);
		try {
			Date date = sdf.parse(source);
			setValue(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SimpleDateFormat getDateFormat(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)) {
			sdf = new SimpleDateFormat("yyyy/MM/dd");
		}else if(Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)) {
			sdf = new SimpleDateFormat("yyyyMMdd");
		}else {
			/*
			 * 在没有匹配的情况下抛出TypeMismatchException 
			 * 如果第一个参数类型能够转换第二个参数类型则不抛出异常，如果不能进行转换才抛出异常
			 * 这样上面代码就不会抛出ParseException异常，只会抛出TypeMismatchException，
			 * 而我们对TypeMismatchException异常在controller里面进行了处理。
			 */
			throw new TypeMismatchException("", Date.class);
		}
		return sdf;
	}
}
