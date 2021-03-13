package general;

import java.lang.reflect.Method;

import basic.client.ClientConfig;
import patterns.Singleton;

public class Test {

	public static void main(String[] args) {
		/*Class c;
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
		*/
		
		String s = "pão";
		Method method;
		
		try {
			method = s.getClass().getMethod("Equals", java.lang.String.class);
			//method = s.getClass().getDeclaredMethod("charAt", Integer.class);
		} catch (SecurityException e) { 
			System.out.println("security");
		}
	    catch (NoSuchMethodException e) { 
	    	System.out.println("no such method");
	    	e.printStackTrace();
	    }
		
		//Singleton<ClientConfig> x = (Singleton<ClientConfig>) Singleton.getInstance();
		
	}
}
