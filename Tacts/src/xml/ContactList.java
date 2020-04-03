package xml;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*; 
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
public class ContactList {

    public static Document document;  

    public ContactList() {
        openContactList();
    }

    public static void openContactList() {
        // Obtains an instance of a factory that gives a DocumentBuilder
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            // Make factory return a validating parser (valid XML)
            dFactory.setValidating(true);
            // Make factory return a parser that supports namespaces(0)
            dFactory.setNamespaceAware(true);
            // Use DocumentBuilder to parse a XML file
            document = builder.parse(new File("../contactList.xml"));
        } catch (SAXException sxe) { // doesn't throw a SAXParseException
            sxe.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void saveContactList() {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(
                new FileWriter("../contactList.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void addContact(String n, String e, String p, String a, String g) {
        Element newContact = (Element)document.createElement("contact");
        newContact.setAttribute("name", n);
        newContact.setAttribute("email", e);
        newContact.setAttribute("phone", p);
        newContact.setAttribute("address", a);
        
        boolean makeGroup = true;
        NodeList groupNodes = document.getElementsByTagName("group");
        for (int gn = 0; gn < groupNodes.getLength() && makeGroup; gn++) {
            Element groupElem = (Element)groupNodes.item(gn);
            if (groupElem.getAttribute("name").equals(g)) {
                document.getDocumentElement().getElementsByTagName("group").item(gn).appendChild(newContact);
                makeGroup = false;
            }
        }
        if (makeGroup) {
            addGroup(g);
            document.getDocumentElement().getLastChild().appendChild(newContact);
        }    
    }

    public static void addGroup(String n) {
        Element newGroup = (Element)document.createElement("group");
        newGroup.setAttribute("name", n);      
        document.getDocumentElement().appendChild(newGroup);
    }

    public static TactsElement search(String s) {
        Element found = document.createElement(new String("search"));

        NodeList groups = document.getElementsByTagName("group");
        for (int g = 0; g < groups.getLength(); g++) {
            Element groupElem = (Element)groups.item(g);

            NodeList contacts = groupElem.getElementsByTagName("contact");
            for (int c = 0; c < contacts.getLength(); c++) {
                Element tactElem = (Element)contacts.item(c);
                if (tactElem.getAttribute("name").indexOf(s) != -1
                || tactElem.getAttribute("email").indexOf(s) != -1
                || tactElem.getAttribute("phone").indexOf(s) != -1
                || tactElem.getAttribute("address").indexOf(s) != -1) {
                    // Appending node straight from dom removes it, so clone it.
                    found.appendChild(tactElem.cloneNode(true));
                }
            }
        }
        return new TactsElement(found);
    }
}
