package com.study.springmvc;

public class School {
	private String sname;
	private String saddress;

	public School() {
		// TODO Auto-generated constructor stub
	}

	public School(String sname, String saddress) {
		super();
		this.sname = sname;
		this.saddress = saddress;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	@Override
	public String toString() {
		return "School [sname=" + sname + ", saddress=" + saddress + "]";
	}

}
