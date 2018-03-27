package io.egen.clearsky.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wind {

	@Id
	private String id;
	
	public Wind(){
		this.id = UUID.randomUUID().toString();

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private double speed;
	private int degree;
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	@Override
	public String toString() {
		return "Wind [id=" + id + ", speed=" + speed + ", degree=" + degree
				+ "]";
	}
	
}
