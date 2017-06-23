package kr.co.bit.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	
	private Map<String, Controller> mappings = null;
	Properties prop = null;

	public HandlerMapping(String configName){
		
		mappings = new HashMap<>();
		prop = new Properties();

		try {
			InputStream is = new FileInputStream(configName);
 			// InputStream is = new FileInputStream("C:/hy/web-workspace/Mission-MVC01/bean.properties");
			prop.load(is);
			
			Set<Object> keys = prop.keySet();
			
			// 1. iterator
			// 2. toArray()
			// 3. 1.5 for¹®
			
			for(Object key : keys){
				String className = prop.getProperty(key.toString());
				Class<?> cl = Class.forName(className);
				mappings.put(key.toString(), (Controller)cl.newInstance());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		mappings = new HashMap<>();
		mappings.put("/board/list.do", new ListController());
		mappings.put("/board/writeForm.do", new WriteFormController());
		mappings.put("/board/write.do", new WriteController());
		mappings.put("/member/loginForm.do", new LoginController());
		mappings.put("/member/login.do", new LoginProcessController());
		mappings.put("/member/logout.do", new LogoutController());*/
	}
	
	public Controller getController(String uri){
		 return mappings.get(uri);
	}
	
	
}
