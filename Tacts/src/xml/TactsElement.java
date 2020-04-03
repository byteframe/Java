




package xml;

import org.w3c.dom.*;

public class TactsElement { //make it extend element?
    Element domNode;
    boolean compress = true;
    
    public TactsElement(Element n) {
        domNode = n;
    }
    public TactsElement(Object n) {
        domNode = (Element)n;    
    }

    public String toString() {
        if (treeElement(domNode.getNodeName())) {
            return domNode.getAttribute("name");  	
        } else {
            return domNode.getNodeName();
        }
    }

    /*
    * Return children, index, and count values
    */
    public int index(TactsElement child) {
        int count = childCount();
        for (int i = 0; i < count; i++) {
            TactsElement n = this.child(i);
            if (child.domNode == n.domNode) {
                return i;
            }
        }
        return -1; // Should never get here.
    }

    public TactsElement child(int searchIndex) {
        //Note: JTree index is zero-based. 
        Node node = domNode.getChildNodes().item(searchIndex);
        if (compress) {
            // Return Nth displayable node
            int elementNodeIndex = 0;
            for (int i = 0; i < domNode.getChildNodes().getLength(); i++) {
                node = domNode.getChildNodes().item(i);
                if (node.getNodeType() == 1 & treeElement( node.getNodeName() )
                  && elementNodeIndex++ == searchIndex) {
                    break; 
                }
            }
        }
        return new TactsElement((Element)node); 
    }

    public int childCount() {
        if (!compress) {
            return domNode.getChildNodes().getLength();  
        } 

        int count = 0;
        for (int i = 0; i < domNode.getChildNodes().getLength(); i++) {
            Node node = domNode.getChildNodes().item(i); 
            if (node.getNodeType() == 1 && treeElement(node.getNodeName())) {
                // Note: 
                //   Have to check for proper type. 
                //   The DOCTYPE element also has the right name
                ++count;
            }
        }
            
        return count;
    }
    
    public String getNodeName() {
        return domNode.getNodeName();
    }

    public String getAttributeValue(int search) {
        switch (search) {
            case 0: 
                return domNode.getAttribute("name");
            case 1: 
                return domNode.getAttribute("email");
            case 2: 
                return domNode.getAttribute("phone");
            case 3: 
                return domNode.getAttribute("address");
        }
        return "gAV";
    }

    // The list of elements to display in the tree
    static String[] treeElementNames = {
        "group",
        "contact",
    };
    
    boolean treeElement(String elementName) {
        for (int i = 0; i<treeElementNames.length; i++) {
            if ( elementName.equals(treeElementNames[i]) ) {
                return true;
            }
        }
        return false;
    }    
}
