package edu.gac.polisci.shared;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
		 
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Professor implements Serializable {
	private static final long serialVersionUID = -4071960164916630827L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name = "no name";
	
	public Professor() {}

	public Professor(String string) {
		name = string;
	}
	
	public Long getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String string) {
		name = string;
	}
}