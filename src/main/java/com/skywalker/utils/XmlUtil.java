package com.skywalker.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {
	
	public static JSONObject xml2JSONObject(File xmlFile){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		JSONObject result=new JSONObject();
		try{
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			Element element = doc.getDocumentElement();
			result.put(element.getTagName(), listAllChildNodes(element));
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	private static JSONObject listAllChildNodes(Node node){
		NodeList nodeList=node.getChildNodes();
		JSONObject result=new JSONObject();
		
		for(int i=0;i<nodeList.getLength();i++){
			Node tempnode = nodeList.item(i);
			if(tempnode.getNodeType() == Node.ELEMENT_NODE){
				if(tempnode.hasChildNodes()){
					NodeList tempnodeList = tempnode.getChildNodes();
					for(int k=0;k<tempnodeList.getLength();k++){
						if(tempnodeList.item(k).getNodeType() ==Node.TEXT_NODE &&
								(!tempnodeList.item(k).getTextContent().matches("\\s+"))){
							result.put(tempnode.getNodeName(), tempnodeList.item(k).getTextContent());
							break;
						}else if(tempnodeList.item(k).getNodeType()==Node.ELEMENT_NODE){
							result.put(tempnode.getNodeName(), listAllChildNodes(tempnode));
							break;
						}
					}
				}
			}
			
		}
		return result;
	}
	
}
