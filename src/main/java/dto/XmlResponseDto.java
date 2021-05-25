package dto;

import java.util.Map;

public class XmlResponseDto {

	private String fileName;
	private Map<String,String> fileData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Map<String, String> getFileData() {
		return fileData;
	}
	public void setFileData(Map<String, String> fileData) {
		this.fileData = fileData;
	}
	
}
