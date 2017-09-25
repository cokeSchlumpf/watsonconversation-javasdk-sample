package model;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import util.BaseObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType()
public class ConversationContext extends BaseObject {
	
	private User user;
	
	private String conversation_id;
	
	private HashMap<String, Object> system;
	
	private ConversationContext() {
		
	}
	
	public ConversationContext(User user) {
		this();
		this.user = user;
	}
	
	public String getConversationId() {
		return conversation_id;
	}
	
	public HashMap<String, Object> getSystem() {
		return system;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setConversationId(String conversationId) {
		this.conversation_id = conversationId;
	}
	
	public void setSystem(HashMap<String, Object> systemContext) {
		this.system = systemContext;
	}

}
