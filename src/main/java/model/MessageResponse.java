package model;

import util.BaseObject;

public class MessageResponse extends BaseObject {

	private String[] output;
	
	private ConversationContext context;
	
	private String[] actions;
	
	private MessageResponse() {
		
	}
	
	public MessageResponse(ConversationContext context, String ...output) {
		this();
		this.context = context;
		this.output = output;
	}
	
	public MessageResponse(ConversationContext context, String[] output, String[] actions) {
		this();
		this.actions = actions;
		this.context = context;
		this.output = output;
	}
	
	public String[] getActions() {
		return actions;
	}
	
	public ConversationContext getContext() {
		return context;
	}
	
	public String[] getOutput() {
		return output;
	}
	
}
