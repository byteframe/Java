package xml;

import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

public class TactsTreeModel extends DefaultTreeModel {
    // Uses Document Root
    public TactsTreeModel(TreeNode root) {
        super(root);
    }

    public Object getChild(Object parent, int index) {
        TactsElement node = (TactsElement) parent;
        return node.child(index);
    }
    public int getChildCount(Object parent) {
        TactsElement node = (TactsElement) parent;
        return node.childCount();
    }
    public int getIndexOfChild(Object parent, Object child) {
        TactsElement node = (TactsElement) parent;
        return node.index((TactsElement) child);
    }
    public Object getRoot() {
        return new TactsElement(ContactList.document.getDocumentElement());
    }
    public boolean isLeaf(Object aNode) {
        TactsElement node = (TactsElement) aNode;
        System.out.println();
        if (node.childCount() > 0 || node.getNodeName().equals("group")) {
            return false;
        } else {
            return true;
        }
    }    

    public void valueForPathChanged(TreePath path, Object newValue) {
        // Null. We won't be making changes in the GUI
        // If we did, we would ensure the new value was really new,
        // adjust the model, and then fire a TreeNodesChanged event.
    }
}
