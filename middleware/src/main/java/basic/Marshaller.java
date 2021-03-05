package basic;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import general.Message;
import general.RequestorMessage;

public class Marshaller {
	
	private Gson gson;
	
	public  Marshaller() {
		gson = new Gson();
	}
	
	public String marshal(Message message) {
		return gson.toJson(message);
	}
	
	public Message unmarshal(String json, Class cls) {
		JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        //Message message = gson.fromJson(reader, Message.class);
        Message message = gson.fromJson(reader, cls);
		return message;
	}
	
	
}
