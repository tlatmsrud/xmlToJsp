package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.junit.Test;

import service.XmlParserService;

public class test {

	@Test
	public void test() throws InvalidPropertiesFormatException, IOException{
		XmlParserService xmlParserService = new XmlParserService();
		
		String[] xmlFilePathList = {
				"C:\\Users\\mcnc\\antProject\\test\\src\\main\\resources\\session_management.xml"
				,"C:\\Users\\mcnc\\antProject\\test\\src\\main\\resources\\mandatorConfig.xml"};
		
		File file = new File("classpath:/");
	}
	
	@Test
	public void test2(){
		XmlParserService xmlParserService = new XmlParserService();
		FileOutputStream fos = null;
		try {
			Properties props = new Properties();
			
			//프로퍼티 setting
			props.setProperty("", "");
		
			File file = new File("C:\\Users\\mcnc\\antProject\\test\\src\\test\\java\\test\\test.xml");
			fos = new FileOutputStream(file);
			props.storeToXML(fos, "test");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
