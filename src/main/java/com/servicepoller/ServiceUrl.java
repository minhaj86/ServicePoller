package com.servicepoller;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceUrl implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
		

	@Column
	String name;
	
	@Column
	String url;
	
	@Column
	Date create_time;
	
	@Column
	String status;
	
	public ServiceUrl() {
//		this.id=0;
		this.name="";
		this.url="";
		this.create_time=new Date(); 
		
		this.status="";
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
