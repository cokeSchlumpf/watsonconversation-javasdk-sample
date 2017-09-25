package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import util.BaseObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "input", "context" })
public class Message extends BaseObject {

	private String input;
	
	private ConversationContext context;
	
	private Message() {
		
	}
	
	public Message(String input, ConversationContext context) {
		this();
		this.input = input;
		this.context = context;
	}
	
	public Message(String input) {
		this(input, null);
	}
	
	public ConversationContext getContext() {
		return context;
	}
	
	public String getInput() {
		return input;
	}
	
}
