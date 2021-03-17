package basic;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import application.User;
import basic.client.ClientProxy;

public class InterfaceDescriptor {

	public static void main(String[] args) {
		Class proxyClass = ClientProxy.class;
		Class remoteErrorClass = RemoteError.class;
		
		Class[] classes = new Class[]{User.class};
		
		for (Class c : classes) {
			String[] gambs = c.getName().split("\\.");
			String className = gambs[gambs.length-1];
			
			//Nome da classe
			String str = "public class " + className + "Proxy" + 
						 " extends " + proxyClass.getName() +  "{\n"; 
			
			//Atributos
			
			//Construtor
			str += "\t" + "public " + className+"Proxy(){\n"; 	
				str += "\t\t" + "super(" + className + ".class);\n";
			str += "\t}\n\n";
			
			//RequestAOR
			str += "\t" + "public void requestAOR(String objType) throws java.io.IOException {\n";
				str += "\t\t" + "try{\n";
					str += "\t\t\t" + "Long id = (long) Math.floor((Double) requestor.invoke(-2, objType , null, null))" + ";\n";
					str += "\t\t\t" + "this.realID = id;\n";
				str += "\t\t" + "} catch (" + remoteErrorClass.getName() + " e){\n";
					str += "\t\t\t" + "throw new IOException(\"Erro ao obter o ID do objeto requisitado\");\n";
				str += "\t\t" + "}\n";
			str += "\t}\n\n";
			
			//Metodos
			Method[] methods = c.getDeclaredMethods();
			for (Method m : methods) {
				//Assinatura do metodo
				str += "\t" + 
						Modifier.toString(m.getModifiers()) + " " + 
						m.getReturnType().getName() + " " +  
						m.getName() + "";
				
				//Parametros
				str += "(";
				Parameter[] parameters = m.getParameters();
				
				String prList = "";
				String tpList = "";
				
				if (parameters.length == 0) {
					prList = "null";
					tpList = "null";
				} else {
					prList = "new Object[]{";
					tpList = "new String[]{";
				}
				
				for(int i = 0; i < parameters.length; i++) {
					str += parameters[i].getType().getName() + " " + 
							parameters[i].getName() ;
					
					prList += parameters[i].getName();
					tpList += parameters[i].getName() + ".getClass().getName()";
					
					if (i < parameters.length - 1) {
						str += ", ";
						prList += ", ";
						tpList += ", ";
					}
				}
				
				if (parameters.length > 0) {
					prList += "}";
					tpList += "}";
				}
				
				//Corpo do metodo
				str += "){\n";
				
					str += "\t\t" + "try{\n";
						str += "\t\t\t";
					if (!m.getReturnType().getName().equals("void"))
						str += "return (" + m.getReturnType().getName() + ") ";
						str += "requestor.invoke(realID,\""+m.getName()+"\",";
						str += prList + ",";
						str += tpList;
						str += ");\n";
					str += "\t\t" + "} catch (" + remoteErrorClass.getName() + " e){\n";
						str += "\t\t\t" + "e.printStackTrace();\n";
					str += "\t\t" + "}\n";
				
				str += "\t}\n\n";
			}
			
			str += "}";
			
			System.out.println(str);
			
			
		}
	}
	
}
