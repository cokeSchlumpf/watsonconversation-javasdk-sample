package ws;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import model.ConversationContext;
import model.Message;
import util.AbstractService;
import util.BaseObject;
import util.Configuration;

@Path("message")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageService extends AbstractService {
	
	private final Conversation service;
	
	{
		service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setEndPoint(Configuration.WCS_WORKSPACE_URL);
		service.setUsernameAndPassword(Configuration.WCS_USERNAME,Configuration. WCS_PASSWORD);
	}
	
	@POST
	public model.MessageResponse receiveMessage(Message message) {
		String inputText = message.getInput() != null ? message.getInput() : "";
		InputData input = new InputData.Builder(inputText).build();
		
		MessageOptions options = new MessageOptions.Builder(Configuration.WCS_WORKSPACE_ID) //
				.input(input) //
				.context(convertToWatsonContext(message.getContext())) //
				.build();
		
		MessageResponse response = service.message(options).execute();
		System.out.println(response);
		
		return new model.MessageResponse( //
				BaseObject.fromString(ConversationContext.class, response.getContext().toString()), //
				response.getOutput().getText().toArray(new String[] {}));
	}
	
	@SuppressWarnings("unchecked")
	private Context convertToWatsonContext(ConversationContext context) {
		if (context == null) {
			return null;
		}
		
		Context result = new Context();
		HashMap<Object, Object> values = new ObjectMapper().convertValue(context, HashMap.class);
		
		for (Object key : values.keySet()) {
			result.put(key.toString(), values.get(key));
		}
		
		return result;
	}
	
	@SuppressWarnings("unused")
	private model.MessageResponse createSampleResponse(Message message) {		
		String[] messages = { //
				String.format( //
						"Hallo %s %s", //
						message.getContext().getUser().getSalutation(), //
						message.getContext().getUser().getLastname()), //
				
				"Wie kann ich ihnen heute helfen?" //
		};
		
		String[] actions = {
				"displayMessage"
		};

		return new model.MessageResponse(message.getContext(), messages, actions);
	}

}
