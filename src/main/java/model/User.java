package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "application", "version" })
public class User {

	private String salutation;
	
	private String firstname;
	
	private String lastname;
	
	private User() {
		
	}
	
	public User(String salutation, String firstname, String lastname) {
		this();
		this.salutation = salutation;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getSalutation() {
		return salutation;
	}
	
}
