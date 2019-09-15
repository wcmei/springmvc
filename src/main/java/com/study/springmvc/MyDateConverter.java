package com.study.springmvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.converter.Converter;

/*第一个参数表示源类型，第二个参数表示目标类型*/
public class MyDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		
		SimpleDateFormat sdf = getDateFormat(source);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
