package com.alok.service.facesImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.alok.service.faces.Writable;
import com.alok.utilities.MyConstants;

/**
 * core functionality of writing content into a file
 * @author Alok
 *
 */
public class Writer implements Writable {
	
	
	public Boolean textWriter(String fileNameWithPath, String dateTime,
			String logLevel, String className, String content) {

		String finalcontent = dateTime + MyConstants.DELIMITER + logLevel
				+ MyConstants.DELIMITER + className + MyConstants.DELIMITER
				+ content + MyConstants.NEWLINE;
		System.out.println("In textWriter");
		Boolean writeStatus = false;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(fileNameWithPath, true);
			bw = new BufferedWriter(fw);
			bw.write(finalcontent);
			writeStatus = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {

				bw.close();
			} catch (Exception ex) {
				System.out.println("Error occured while closing the writer"
						+ ex.getMessage());
				ex.printStackTrace();
			}
		}

		return writeStatus;

	}

	
	@Override
	public Boolean xmlWriter(String fileNameWithPath, String dateTime,
			String logLevel, String className, String content) {
		Boolean writeStatus = false;
		try {
			Node rootElement=null;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
					
			rootElement = doc
					.createElement(MyConstants.XML_ROOT_ELEMENT);
			doc.appendChild(rootElement);

			// dateTime element
			Element dateTimeNode = doc
					.createElement(MyConstants.XML_CHILD_DATE_TIME_ELEMENT);
			dateTimeNode.appendChild(doc.createTextNode(dateTime));
			rootElement.appendChild(dateTimeNode);

			// logLevel element
			Element logLevelNode = doc
					.createElement(MyConstants.XML_CHILD_LOG_LEVEL_ELEMENT);
			logLevelNode.appendChild(doc.createTextNode(logLevel));
			rootElement.appendChild(logLevelNode);

			// className elements
			Element classNameNode = doc
					.createElement(MyConstants.XML_CHILD_CLASS_NAME_ELEMENT);
			classNameNode.appendChild(doc.createTextNode(className));
			rootElement.appendChild(classNameNode);

			// content elements
			Element contentNode = doc
					.createElement(MyConstants.XML_CHILD_MESSAGE_ELEMENT);
			contentNode.appendChild(doc.createTextNode(content));
			rootElement.appendChild(contentNode);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter(fileNameWithPath,true));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub
		return writeStatus;
	}

	/**
	 * 
	 */
	@Override
	public Boolean jSONWriter(String fileNameWithPath, String dateTime,
			String logLevel, String className, String content) {
		// TODO Auto-generated method stub
		return null;
	}

}
