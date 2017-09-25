package wcs;

import org.junit.Test;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.CreateExampleOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.IntentCollection;
import com.ibm.watson.developer_cloud.conversation.v1.model.ListIntentsOptions;
import com.ibm.watson.developer_cloud.http.ServiceCall;

import util.Configuration;

public class ConfigureWCSTest {

	private final Conversation service;

	{
		service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setEndPoint(Configuration.WCS_WORKSPACE_URL);
		service.setUsernameAndPassword(Configuration.WCS_USERNAME, Configuration.WCS_PASSWORD);
	}

	@Test
	public void insertValues() {
		ServiceCall<IntentCollection> result = service.listIntents(new ListIntentsOptions.Builder(Configuration.WCS_WORKSPACE_ID).build());
		System.out.println(result.execute().getIntents());
		service.createExample(new CreateExampleOptions.Builder(Configuration.WCS_WORKSPACE_ID, "AddressChange", "Test Test").build()).execute();
	}

}
