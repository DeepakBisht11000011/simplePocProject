/**
 * 
 */
package com.example.camera.entity;

/**
 * @author deepakbisht
 *
 */

public class StudentEntity {
	String name;
	String profession;
	public StudentEntity() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public StudentEntity(String name, String profession) {
		super();
		this.name = name;
		this.profession = profession;
	}
	
	
}
