package com.servicepoller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface BaseEntity {
	public int getId();
	public void setId(int id);

}
