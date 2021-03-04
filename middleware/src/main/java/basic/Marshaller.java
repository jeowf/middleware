package basic;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import general.Message;

public class Marshaller {
	
	private Gson gson;
	
	public  Marshaller() {
		gson = new Gson();
	}
	
	public String marshal(Message message) {
		return gson.toJson(message);
	}
	
	public Message unmarshal(String json) {
		JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        Message message = gson.fromJson(reader, Message.class);
		return message;
	}
	
	
}
