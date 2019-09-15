package com.study.springmvc;

import java.util.Arrays;

public class User {
	private String name;
	private int age;
	private String[] interests;
	private School school;

	public User() {
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public User(String name, int age, String[] interests, School school) {
		super();
		this.name = name;
		this.age = age;
		this.interests = interests;
		this.school = school;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getAge() {
		return age;
	}

	public final void setAge(int age) {
		this.age = age;
	}

	public final String[] getInterests() {
		return interests;
	}

	public final void setInterests(String[] interests) {
		this.interests = interests;
	}

	public final School getSchool() {
		return school;
	}

	public final void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", interests=" + Arrays.toString(interests) + ", school="
				+ school + "]";
	}

}
