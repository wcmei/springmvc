package com.study.springmvc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Student {
	@NotNull(message = "姓名不能为空")
	@Size(min = 3, max = 6, message = "姓名长度应在{min}-{max}个字符")
	private String name;
	@NotNull(message = "分数不能为空")
	@Min(value = 0, message = "成绩不能小于{value}")
	@Max(value = 100, message = "成绩不能大于{value}")
	private double score;
	@NotNull(message = "电话不能为空")
	@Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号格式不正确")
	private String mobile;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, double score, String mobile) {
		super();
		this.name = name;
		this.score = score;
		this.mobile = mobile;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final double getScore() {
		return score;
	}

	public final void setScore(double score) {
		this.score = score;
	}

	public final String getMobile() {
		return mobile;
	}

	public final void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + ", mobile=" + mobile + "]";
	}

}
