/**
 * Node.java
 * This class is to be used specifically with the B+ tree class. 
 * I have included many methods in this class to make interacting with a node 
 * more user friendly.
 * @author NateV
 */
package BPlusTree;
import java.util.*;
/**
 * Node.
 * 1: A node has a parent, a left sibling, a right sibling, and a list of children.
 * 
 * 2: A node contains a key to identify its data.
 * 
 * 3: A nodes data is saved in a structure called a DataCollection.
 * @author nathan
 */
public class Node {
    public Node parent, leftSibling, rightSibling;
    public ArrayList<Node> childList = new ArrayList<>();
    public ArrayList<Long> keyRing = new ArrayList<>();
    public ArrayList<DataCollection> dataCollectionList = new ArrayList<>();
    
    public Node(){};

    public boolean isALeaf(){ return childList.isEmpty(); } 
    public boolean isNotALeaf(){return !childList.isEmpty(); } 
    
    public DataCollection getDataCollection(long key){
        if (!dataCollectionList.isEmpty())
            return dataCollectionList.get(dataCollectionList.indexOf(key));
        else return null;
    }  
    public DataCollection getLastDataCollection(){
        return dataCollectionList.get(dataCollectionList.size()-1);
    }
    public DataCollection getFirstDataCollection(){
        return dataCollectionList.get(0);
    }  
    public DataCollection popFirstDataCollection(){
        DataCollection firstDataCollection = this.getFirstDataCollection();
        dataCollectionList.remove(firstDataCollection);
        return firstDataCollection;
    }
    public DataCollection popLastDataCollection(){
        DataCollection LastDataCollection = this.getLastDataCollection();
        dataCollectionList.remove(LastDataCollection);
        return LastDataCollection;
    }
    
    public void replaceKey(int index, long newKey){
        keyRing.set(index, newKey);
    }    
    public long getKey(int i) {
        return keyRing.get(i);
    } 
    public long getLastKey(){
        return keyRing.get(keyRing.size()-1);
    }  
    public long getFirstKey(){
        return keyRing.get(0);
    } 
    public long popLastKey(){
        long lastKey = this.getLastKey();
        keyRing.remove(keyRing.indexOf(lastKey));
        return lastKey;
    } 
    public long popFirstKey(){
        long key = this.getFirstKey();
        keyRing.remove(keyRing.indexOf(key));
        return key;
    }  
    public void replaceChild(int i, Node newChild){
        Node oldChild = childList.get(i);
        childList.add(i, newChild);
        childList.remove(childList.indexOf(oldChild));
    }
    
    public Node getChild(int i ){
        return childList.get(i);
    }   
    public Node getLastChild(){
        return childList.get(childList.size()-1);
    }  
    public Node getFirstChild(){
        return childList.get(0);
    }  
    public Node popLastChild(){
        Node lastChild = this.getLastChild();
        childList.remove(lastChild);
        return lastChild;
    }  
    public Node popFirstChild(){
        Node firstChild = this.getFirstChild();
        childList.remove(firstChild);
        return firstChild;
    }   
  
    public void addChildToEndOfList(Node child){
        childList.add(childList.size(), child);
    }   
    public void addChildToFrontOfList(Node child){
        childList.add(0, child);
    }   
    public void addKeyToEndOfList(long key){
        keyRing.add(keyRing.size(), key);
    }   
    public void addKeyToFrontOfList(long key){
        keyRing.add(0, key);
    }   
    public void addCollectionToEndOfList(DataCollection dataCollection){
        if (!dataCollectionList.isEmpty())
            dataCollectionList.add(dataCollectionList.size(), dataCollection);
    }  
    public void addCollectionToFrontOfList(DataCollection dataCollection){
        dataCollectionList.add(dataCollectionList.size(), dataCollection);
    }
    
    /**
     * Adds a key and dataCollection to the node using an insertion sort.
     * @param key The key to be added.
     * @param dataCollection The DataCollection associated with that key.
     */
    public void addEntry(long key, DataCollection dataCollection){
        if (keyRing.isEmpty())
        {
            keyRing.add(0,key);
            if (dataCollection != null)
                dataCollectionList.add(0, dataCollection);
        }
        else{
            if (key < this.getFirstKey()){
                keyRing.add(0,key);
                if (dataCollection != null)
                    dataCollectionList.add(0, dataCollection);}
            else if (key >= this.getLastKey()){
                keyRing.add(keyRing.size(),key);
                if (dataCollection != null)
                    dataCollectionList.add(dataCollectionList.size()-1, dataCollection);}
            else for (int i = 1; i < this.keyRing.size(); i++) //changed keyring.size() -1 to keyRing.size()
                if ((this.getKey(i-1) <= key) && (key < this.getKey(i))){
                    keyRing.add(i,key);
                    if (dataCollection != null)
                        dataCollectionList.add(i, dataCollection);
                    break;} //added break. had infinite loop with keySize end condition changing.
        }
    } 
    /**
     * Adds a node to this node's childList using an insertion sort based on keys.
     * @param child 
     */
    public void addNode(Node child){
        if (child != null)
        {
            if (child.getFirstKey() < this.getFirstKey())
            {
                childList.add(0, child);
            }
            else if (child.getFirstKey() >= this.getLastKey())
            {
                childList.add(childList.size(), child);
            }
            else for (int i = 1; i < this.childList.size(); i++)
                if ((this.getKey(i-1) <= child.getFirstKey()) && (child.getFirstKey() < this.getKey(i)))
                {   
                    childList.add(i, child);
                }
        }
    }
    /**
     * Allows another node to obtain pointers to the data of this object, 
     * but not this object itself.
     * @param otherNode 
     */
    public void deepCopyTo(Node otherNode) {
        otherNode.childList = (ArrayList<Node>) this.childList.clone();
        otherNode.dataCollectionList = (ArrayList<DataCollection>) this.dataCollectionList.clone();
        otherNode.keyRing = (ArrayList<Long>) this.keyRing.clone();
        otherNode.leftSibling = this.leftSibling;
        otherNode.rightSibling = this.rightSibling;
        //otherNode.parent.replaceChild(otherNode.parent.childList.indexOf(otherNode), this);
        otherNode.parent = this.parent;
        
    }
}
