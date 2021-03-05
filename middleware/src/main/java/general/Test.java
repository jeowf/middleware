package general;

import basic.client.ClientConfig;
import patterns.Singleton;

public class Test {

	public static void main(String[] args) {
		Class c;
		c = Integer.class;
		System.out.println(c.getName());
		
		try {
			Object x = Class.forName(c.getName()).cast(100);
			Integer i = (Integer) x;
			System.out.println(i);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Singleton<ClientConfig> x = (Singleton<ClientConfig>) Singleton.getInstance();
		
	}
}
