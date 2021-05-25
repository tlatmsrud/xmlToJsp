package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.XmlDataDto;
import dto.XmlResponseDto;
import service.XmlParserService;

@Controller
public class testController {

	@Autowired
	private XmlParserService xmlParserService;
	
	@GetMapping("/properties")
	public ModelAndView propertiesJsp(HttpServletRequest request) throws InvalidPropertiesFormatException, IOException{
		
		String[] fileNames = {"mandatorConfig.xml","session_management.xml"};
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("properties");
		mav.addObject("fileNameList", fileNames);
		
		return mav;
	}
	
	@PostMapping("/properties")
	@ResponseBody
	public XmlResponseDto getXmlProperties(String fileName) throws InvalidPropertiesFormatException, IOException{

		XmlResponseDto resDto = xmlParserService.getXmlData(fileName);
		
		return resDto;
	}
	
	@PostMapping("/saveProperties")
	@ResponseBody
	public void saveXmlProperties(@RequestParam Map<String,Object> data) throws IOException {
		String xmlFileName	= data.get("fileName").toString();
		String json 		= data.get("fileData").toString();
		
		ObjectMapper mapper = new ObjectMapper();
		List<XmlDataDto> xmlData = mapper.readValue(json, new TypeReference<ArrayList<XmlDataDto>>(){});
		
		xmlParserService.saveXmlProperties(xmlFileName, xmlData);
	}
}
