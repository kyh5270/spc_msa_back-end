package eu.dreamix.spc.kafka.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Test {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
    	obj.put("name", "mkyong.com");
    	obj.put("age", new Integer(100));
     
    	JSONArray list = new JSONArray();
    	list.add("msg 1");
    	list.add("msg 2");
    	list.add("msg 3");
     
    	obj.put("messages", list);
     
    	try {
    		FileWriter file = new FileWriter("c:\\test.js");
    		file.write("showTours({\"tours\": [");
    		file.flush();
    		file.close();
    		
    		File f1 = new File("c:\\test.js");
    		FileWriter fw1 = new FileWriter(f1, true);  		
    		fw1.write(obj.toJSONString());
    		fw1.flush();
    		fw1.close();
     
    		File f2 = new File("c:\\test.js");
    		FileWriter fw2 = new FileWriter(f2, true);
    		fw2.write("]});");
    		fw2.flush();
    		fw2.close();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	//showTours({"tours": [
    	//]});
    	//System.out.print(obj);
	}

}
