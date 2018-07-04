package com.textrazor;

import java.util.List;
import java.util.Map;

public class UserBean {
	
	private String userid, name, email, course, stream, batch;
	private List<List<EntityBean>> elist;
	private Map<String, Integer> term_holder;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public List<List<EntityBean>> getElist() {
		return elist;
	}
	public void setElist(List<List<EntityBean>> super_en) {
		this.elist = super_en;
	}
	public Map<String, Integer> getTerm_holder() {
		return term_holder;
	}
	public void setTerm_holder(Map<String, Integer> term_holder) {
		this.term_holder = term_holder;
	}
	

}
