package com.study.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex",ex);
		mv.setViewName("error"); //默认异常页面
		if(ex instanceof MyException) {	
			//进行相应的操作
			mv.setViewName("myError"); //跳转到相应异常页面
		}
		return mv;
	}

}
