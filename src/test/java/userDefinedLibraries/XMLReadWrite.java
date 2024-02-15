package userDefinedLibraries;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLReadWrite {
	public static Element element;
	public static XPath xpath;
	public static String recipientName, recipientNumber, recipientEmail,senderName,senderNumber,senderEmail;
	public static Document doc;

	public static void readxml() {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\dataTables\\inputFile.xml";
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("formDetails");
			int tLength = nodeList.getLength();

			for (int i = 0; i < tLength; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					recipientName = element.getElementsByTagName("RecipientName").item(0).getTextContent();
					recipientNumber = element.getElementsByTagName("RecipientNumber").item(0).getTextContent();
					recipientEmail = element.getElementsByTagName("RecipientEmail").item(0).getTextContent();
//					senderName = element.getElementsByTagName("SenderName").item(0).getTextContent();
//					senderNumber = element.getElementsByTagName("SenderNumber").item(0).getTextContent();
//					senderEmail = element.getElementsByTagName("SenderEmail").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getRecipientName() {
		System.out.println(recipientName);
		return recipientName;
	}

	public static String getRecipientNumber() {
		return recipientNumber;
	}

	public static String getRecipientEmail() {
		return recipientEmail;
	}
	
//	public static String getSenderName() {
//		return senderName;
//	}
//
//	public static String getSenderNumber() {
//		return senderNumber;
//	}
//
//	public static String getSenderEmail() {
//		return senderEmail;
//	}
}
