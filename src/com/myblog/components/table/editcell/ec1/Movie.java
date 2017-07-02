package com.myblog.components.table.editcell.ec1;

import java.util.Date;
import java.util.List;

/**
 * 
 Created with IntelliJ IDEA.
 * 
 * User: Edison
 * 
 * Date: 13-8-16
 * 
 * Time: 下午10:30
 * 
 * To change this template use File | Settings | File Templates.
 */
public class Movie {
	private String name;
	private Date premiere;
	private String publisher;
	private String director;
	private List<String> starrings;

	public Movie(String movName, Date movPremiere, String movDirector) {
		this.name = movName;
		this.premiere = movPremiere;
		this.director = movDirector;
	}

	public String getName() {
		return name;
	}

	public Date getPremiere() {
		return premiere;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getDirector() {
		return director;
	}

	public List<String> getStarrings() {
		return starrings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPremiere(Date premiere) {
		this.premiere = premiere;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setStarrings(List<String> starrings) {
		this.starrings = starrings;
	}
}