package com.study.springmvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
//@RequestMapping("my")
public class MyController {

	/*
	 * 1.最基本的访问方式
	 */
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("test1======");
	}

	/*
	 * 2.@ResponseStatus(value = HttpStatus.OK)：只在后台进行处理
	 */
	@RequestMapping("/test2")
	@ResponseStatus(value = HttpStatus.OK)
	public void test2() {
		System.out.println("test2======");
	}

	/*
	 * 3.接受普通的请求参数
	 */
	@RequestMapping("/test3")
	@ResponseStatus(value = HttpStatus.OK)
	/*
	 * @RequestMapping(value = "/test3",method = RequestMethod.POST, // 请求方式必须为post
	 * params = {"name","!age"}) // 请求中必须携带参数name，不能携带参数age params =
	 * {"name=xiaowan","age=23"} // 请求中必须携带参数name且值要为xiaowan，必须要携带参数age且值要为23 params
	 * = {"name！=xiaowan"} // 请求中必须携带参数name，且值不能为xiaowan
	 */
	/*
	 * @RequestParam(value=””, required=true/false, defaultValue=””)
	 * required：是否必须，默认为true，标示请求参数中必须包含该参数，如果不包含则抛出异常
	 * defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false，如果请求中不包含该参数则使用默认值。
	 */
	public void test3(@RequestParam("name") String name, @RequestParam("age") int age,
			@RequestParam("interests") String[] interests) {
		System.out.println(name);
		System.out.println(age);
		System.out.println(interests);
	}

	/*
	 * 4.接受请求路径中的占位符
	 */
	@RequestMapping("/{name}/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void test4(@PathVariable("name") String name, @PathVariable("id") long id) {
		System.out.println(name);
		System.out.println(id);
	}

	/*
	 * 5.接收servlet的内置对象
	 */
	@RequestMapping("/test5")
	@ResponseStatus(value = HttpStatus.OK)
	public void test5(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		System.out.println("request:" + request);
		System.out.println("response:" + response);
		System.out.println("session:" + session);
		System.out.println("model:" + model);
	}

	/*
	 * 6.直接获取对象
	 */
	@RequestMapping("/test6")
	@ResponseStatus(value = HttpStatus.OK)
	public void test6(User user) {
		System.out.println(user);
	}

	/*
	 * 7.返回Object对象，需要导入Jackson的包
	 */
	@RequestMapping(value="/test7")
	@ResponseBody // 将返回的对象作为json对象返回到相应体中
	public Object test7() {
		// return 12345;
		// return "xiaowan23";
		// return new User();

		// Map<String,User> map = new HashMap<String,User>();
		// map.put("user", new User("小万","23"));
		// return map;

		List<User> list = new ArrayList<User>();
		list.add(new User("小万", 23));

		return list;
	}
	
	/*
	 * 8.请求转发
	 */
	@RequestMapping("test8")
	public String test6(HttpServletRequest request) {
		// 请求转发的时候会自动忽略/my,所以需要手动添加上去 
		// return "forward:/my/test89?id=1&type=forward";
		 
		// 当然也可以跳转到jsp页面
		request.setAttribute("msg", "请求转发");
		return "forward:/jsp/welcome.jsp"; 
	}
	
	/*
	 * 9.重定向
	 */
	@RequestMapping("test9")
	public String test9() {
		return "redirect:test89?id=2&type=redirect";
	}

	@RequestMapping("test89")
	public String test89(Model model, @RequestParam("id") long id, @RequestParam("type") String type) {
		model.addAttribute("msg","转发或重定向"+type);
		return "welcome";
	}
	
	/*
	 * 10.文件上传
	 */
	@RequestMapping("test10")
	private String test10(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
						//@RequestParam("file")MultipartFile[] files 多个文件必须使用矫正参数，然后遍历数组上传即可
		String path = request.getServletContext().getRealPath("/file");//获取本地服务端该项目下的file文件夹的路径
		System.out.println(path); // E:\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\springmvc\file
		File toFile = new File(path,file.getOriginalFilename());
		// file.getSize() > 0;//判断是否有上传文件
		try {
			file.transferTo(toFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "welcome";
	}
	
	/*
	 * 11.第三种异常处理方式，注解。
	 */
	@RequestMapping("/test11")
	@ResponseStatus(value = HttpStatus.OK)
	private void test11() throws MyException{
		throw new MyException("我的异常");
	}
	@ExceptionHandler
	/*
	 * @ExceptionHandler(MyException.class)// 异常映射
	   *    可以创建一个BaseExceptionController，专门用来处理异常，然后其他的类继承它即可。
	   *    再此仅测试在当前类注解异常方法。
	 */	
	public ModelAndView test11ex(Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex",ex);
		mv.setViewName("error"); //默认异常页面
		if(ex instanceof MyException) {	
			//进行相应的操作
			mv.setViewName("myError"); //跳转到相应异常页面
		}
		return mv;
	}
	
	/*
	 * 12.类型转换
	 */
	
	
	/*
	 * 13.数据回显
	 */
	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView test12ex(HttpServletRequest request,Exception ex) {
		/* 发生异常进行数据回显 */
		ModelAndView mv = new ModelAndView();
		String errorMsg = ex.getMessage();
		System.out.println(errorMsg);
		if(errorMsg.contains("int")) {
			mv.addObject("ageErrors", "年龄格式错误");
		}
		if(errorMsg.contains("Date")) {
			mv.addObject("birthErrors", "生日格式错误");
		}
		mv.addObject("age",request.getParameter("age"));
		mv.addObject("birthday",request.getParameter("birthday"));
		mv.setViewName("my");
		return mv;
	}
	
	/*
	 * 初始化参数绑定（类型转换的第二种方式）
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));//true表示嫩能够为空
		
		binder.registerCustomEditor(Date.class, new MyDateEditor());//自定义编辑器
	}
	
	/*
	 * 13.数据验证
	 */
	@RequestMapping("/test13")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView test13(@Validated Student stu, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",stu);
		mv.setViewName("welcome");
		int errCount = br.getErrorCount();
		System.out.println(errCount);
		if(errCount > 0) {
			mv.setViewName("my");
			FieldError nameError = br.getFieldError("name");
			FieldError scoreError = br.getFieldError("score");
			FieldError mobileError = br.getFieldError("mobile");
			if(nameError != null) {
				String nameErrorMSG = nameError.getDefaultMessage();
				mv.addObject("nameErrorMSG",nameErrorMSG);
			}
			if(scoreError != null) {
				String scoreErrorMSG = scoreError.getDefaultMessage();
				mv.addObject("scoreErrorMSG",scoreErrorMSG);
			}
			if(mobileError != null) {
				String mobileErrorMSG = mobileError.getDefaultMessage();
				mv.addObject("mobileErrorMSG",mobileErrorMSG);
				System.out.println(mobileErrorMSG);
			}
			
		}
		return mv;
	}
	
	
}
