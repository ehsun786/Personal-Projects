package com.ehsun.eventsavy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.rest.core.annotation.RestResource;

import com.google.gson.annotations.Expose;

@Entity
public class Category {

	@Id
	@RestResource
	@Expose
	protected String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
