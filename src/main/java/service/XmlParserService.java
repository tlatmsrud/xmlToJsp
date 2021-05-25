package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.stereotype.Service;

import dto.XmlDataDto;
import dto.XmlResponseDto;

@Service
public class XmlParserService {
	
	private static final String mainFilePath = "C:\\Users\\mcnc\\antProject\\test\\src\\main\\resources";
	
	/**
	 * xmlFileName에 대한 properties 데이터를 추출하는 메서드
	 * @param xmlFileName
	 * @return xmlResponseDto
	 */
	public XmlResponseDto getXmlData(String xmlFileName){
		XmlResponseDto 		xmlResponseDto 	= new XmlResponseDto();
		Map<String,String> 	fileData		= new HashMap<String,String>();
		Properties 			props 			= new Properties();
	
		//파일 경로 서치
		String xmlFilePath = getFilePathToFileName(mainFilePath, xmlFileName);
		
		//파일 객체 생성
		File xmlFile = new File(xmlFilePath);
		
		FileInputStream fis = null;
		try {
			//xmlFile에 대한 inputStream 생성
			fis = new FileInputStream(xmlFile);
			
			//inputStream을 통해 property 데이터를 읽음
			props.loadFromXML(fis);
			
			//일긍ㄴ 데이터를 response 객체에 putting
			for( Entry<Object, Object> entry : props.entrySet()){
				fileData.put(entry.getKey().toString(),entry.getValue().toString());
			}
			
			xmlResponseDto.setFileName(xmlFile.getName());
			xmlResponseDto.setFileData(fileData);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return xmlResponseDto;
	}

	/**
	 * 파일명으로 파일 경로를 검색 찾아내는 메서드
	 * @param filePath
	 * @param fileName
	 * @return filePath
	 */
	public String getFilePathToFileName(String filePath, String fileName){
		File dir = new File(filePath);
		File files[] = dir.listFiles();
		
		for(int i =0; i< files.length; i++){
			File file = files[i];
			
			if(file.isDirectory()){
				filePath = getFilePathToFileName(file.getPath(), fileName);
			}else{
				if(file.getName().equals(fileName)){
					System.out.println(file.getPath());
					return file.getPath();
				}
			}
		}
		return filePath;
	}
	
	/**
	 * Xml Properties 파일 형식으로 저장하는 메서드
	 * @param xmlResponseDto
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void saveXmlProperties(String xmlFileName, List<XmlDataDto> xmlData) throws IOException{

		FileOutputStream fos = null;
		try {
			Properties props = new Properties();
			
			//프로퍼티 setting
			for(XmlDataDto data : xmlData){
				props.setProperty(data.getPropKey(), data.getPropValue());
			}

			//저장할 파일 경로 검색
			String xmlFilePath = getFilePathToFileName(mainFilePath, xmlFileName);
			System.out.println("실제 저장될 xml 파일 경로  :"+xmlFilePath);
			File file = new File(xmlFilePath);
			fos = new FileOutputStream(file);
			
			props.storeToXML(fos, "test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			fos.close();
		} finally{
			fos.close();
		}
	}
}
