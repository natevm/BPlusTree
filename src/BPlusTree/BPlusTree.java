/**
 * BPlusTree.java
 * A B+ tree is a weight-balanced tree, meaning it adjusts based on a few conditions
 * to result in a consistent O(log(n)) search time.
 */
package BPlusTree;
import java.util.Stack;
import java.util.ArrayList;
import BPlusTreeDisplay.*;
import java.util.Collections;
import java.io.*;
import java.util.*;
public class BPlusTree {
    /*-Attributes-*/
    public Node root = new Node();
    int maxKeys;
    int underflowCondition;
    int treeHeight = 0;
    int totalNodes = 1;
    int totalDeletes = 0;
    int totalInserts = 0;
    
    ArrayList<Long> numberStack = new ArrayList<>();
  
    PrintWriter printWriter;

    /*-Constructor-*/
    /**
     * Create a tree of the given order.
     * @param order The order of the tree.
     */
    public BPlusTree(int order) {
        this.maxKeys = order -1;
        underflowCondition = maxKeys/2;
    }

    /*-Methods-*/
    /**
     * Looks for and returns a key in the tree given that key to look for. 
     * @param key
     * @return 
     */
    public long findRecord(long key){
        Node current = root;
        while (current.isNotALeaf())
            current = getNextNode(current, key);
        if(current.keyRing.contains(key))
            return key;
        return -1;
    }
    
    /**
     * Finds the node containing the given key
     * @param key
     * @return 
     */
    public Node findNode(long key){
        Node current = root;
        while (current.isNotALeaf())
            current = getNextNode(current, key);
        return current;
    }
    
    /**
     * Retrieves a list of keys within a range.
     * @param leftKey a key for the left boundary of the range.
     * @param rightKey a key for the right boundary of the range.
     * @return 
     */
    public ArrayList<Long> findRange(long leftKey, long rightKey){
        ArrayList<Long> keyList = new ArrayList<>();
        Node searchNode = findNode(leftKey);
        
        //start i at startkey's index.
        int i = searchNode.keyRing.indexOf(leftKey);
        //if startKey doesn't exist, make startkey the lowest possible key.
        if (i == -1) i = 0;
        
        while (searchNode != null && leftKey <= rightKey)
        {
            //get the key at i.
            leftKey = searchNode.getKey(i);
            keyList.add(leftKey);
            i++;
            //handle jump to next leaf
            if (leftKey == searchNode.getLastKey()) {
                searchNode = searchNode.rightSibling;
                i = 0;}
        }
        return keyList;
    }
    
    /**
     * Traverses one node down to help find a key in the B+ tree.
     * @param node the current search node with children.
     * @param key The key being hunted.
     * @return The next node that leads to a particular key.
     */
    private Node getNextNode(Node node, long key){
        if (node.isNotALeaf())
        {
            int index = 0;
            while (index < node.keyRing.size() && node.keyRing.get(index)<=key)
                index++;
            return node.getChild(index);
        }
        return null;
    }
    

    /**
     * Insert into the BPlus tree.
     * @param key The key to identify a collection of data.
     * @param dataCollection The DataCollection to add to the tree. Can be NULL.
     */
    public void insert(long key, DataCollection dataCollection){
        insert(key, dataCollection, root);  
        totalInserts++;
    }  
    private void insert(long key, DataCollection dataCollection, Node searchNode){
        Stack<Node> nodeStack = new Stack<>();      
        createSearchStack(nodeStack, searchNode, key); //build a trail to the leaf. 
        Node temp = null;
        Node newNode = null;
        boolean finished = false;
        while (finished == false)
        {
            searchNode = nodeStack.pop();
            //case 1: key exists
            if (searchNode.keyRing.contains(key))
                finished = true;
            //case 2: key fits in node.
            else if (searchNode.keyRing.size() < maxKeys){ 
                searchNode.addEntry(key, null);
                searchNode.addNode(newNode); 
                finished = true;} 
            //case 3: split root.
            else if (nodeStack.isEmpty()){
                splitRoot(searchNode, newNode, temp, key, dataCollection);
                finished = true;}
            //case 4: split node.
            else {
                temp = createTempForSplit(searchNode, key, dataCollection);
                temp.addNode(newNode);
                newNode = createNewNode(searchNode); 
                splitTempInTwo(searchNode, newNode, temp);
                Node leftMost = newNode;
                while (leftMost.isNotALeaf())
                    leftMost = leftMost.getFirstChild();
                key = leftMost.getFirstKey();}  
        }
    }
    
    /*The following methods are private, and serve the insert function.*/
    private void createSearchStack(Stack<Node> nodeStack, Node current, long key){
        while (current.isNotALeaf())
        {   //Essentially search, but with stack implementation.
            nodeStack.push(current);
            if (key < current.getFirstKey())
                current = current.getFirstChild();
            else if (key >= current.getLastKey())
                current = current.getLastChild();
            else for (int i = 1; i < current.keyRing.size(); i++) //changed current...size() -1
                if (((current.getKey(i-1) <= key) && (key < current.getKey(i))) && current.isNotALeaf())
                {    
                    current = current.getChild(i);
                    break;
                }
        }
        nodeStack.push(current);
    }
    private Node createTempForSplit(Node searchNode, long key, DataCollection dataCollection){
        Node temp = new Node();
        searchNode.deepCopyTo(temp);
        temp.addEntry(key, dataCollection);
        return temp;
    }
    private Node createNewNode(Node nowLeftNode){
        Node newNode = new Node();
        newNode.leftSibling = nowLeftNode;
        newNode.rightSibling = nowLeftNode.rightSibling;
        newNode.parent = nowLeftNode.parent; 
        if (nowLeftNode.rightSibling != null)
            nowLeftNode.rightSibling.leftSibling = newNode;
        nowLeftNode.rightSibling = newNode; 
        return newNode;
    }
    private void splitTempInTwo(Node leftNode, Node rightNode, Node oldNode){        
        leftNode.keyRing = new ArrayList<>();
        leftNode.dataCollectionList = new ArrayList<>();
        
        rightNode.keyRing = new ArrayList<>();
        rightNode.dataCollectionList = new ArrayList<>();
        for (int i = 0; i < underflowCondition; i++) {
            leftNode.addKeyToEndOfList(oldNode.getKey(i));
            leftNode.addCollectionToEndOfList(oldNode.getDataCollection(i));}
        for (int i = underflowCondition; i < oldNode.keyRing.size(); i++) {
            rightNode.addKeyToEndOfList(oldNode.getKey(i));
            rightNode.addCollectionToEndOfList(oldNode.getDataCollection(i));}   
        
        if (oldNode.isNotALeaf())
        {   
            leftNode.childList = new ArrayList<>();
            for (int i = 0; i < underflowCondition; i++){
                oldNode.getChild(i).parent = leftNode;
                leftNode.addChildToEndOfList(oldNode.getChild(i));}
            leftNode.addChildToEndOfList(oldNode.getChild(underflowCondition));
            oldNode.getChild(underflowCondition).parent = leftNode;

            for (int i = underflowCondition+1; i < oldNode.keyRing.size(); i++){ 
                rightNode.addChildToEndOfList(oldNode.getChild(i));
                oldNode.getChild(i).parent = rightNode;}
            rightNode.addChildToEndOfList(oldNode.getChild(oldNode.keyRing.size()));
            oldNode.getChild(oldNode.keyRing.size()).parent = rightNode;
            rightNode.keyRing.remove(0);
        }  
        totalNodes++;
    }
    private void splitRoot(Node leftNode, Node rightNode, Node oldNode, long key, DataCollection dataCollection){
        oldNode = createTempForSplit(leftNode, key, dataCollection);
        oldNode.addNode(rightNode);
        rightNode = createNewNode(leftNode);
        splitTempInTwo(leftNode, rightNode, oldNode);
                
        root = new Node();
        root.addChildToEndOfList(leftNode);
        root.addChildToEndOfList(rightNode);
        leftNode.parent = rightNode.parent = root;
        Node leftMost = rightNode;
        while (leftMost.isNotALeaf())
            leftMost = leftMost.getFirstChild();
        root.addKeyToFrontOfList(leftMost.getFirstKey());
        
        treeHeight++; //When we split the root, we have increased the height of the tree.
        totalNodes++;
    }
    
    /**
     * Delete a given key from the tree.
     * @param key 
     */
    public void delete(long key){
        totalDeletes++;
        Node keyRefNode = null;
        Node searchNode = root;
        //case 1: root is the only node.
        if (root.isALeaf() && (root.keyRing.contains(key)))
            root.keyRing.remove(root.keyRing.indexOf(key));
        //case 2: more nodes than root.
        else
            while (searchNode.isNotALeaf()){
                if (searchNode.keyRing.contains(key))
                    keyRefNode = searchNode; //track the second occurence of the key.
                searchNode = getNextNode(searchNode, key);
            }
        if (searchNode.keyRing.contains(key))
            searchNode.keyRing.remove(searchNode.keyRing.indexOf(key));
        if (keyRefNode != null)
            keyRefNode.keyRing.set(keyRefNode.keyRing.indexOf(key), searchNode.keyRing.get(0));
        if (searchNode.keyRing.size() < maxKeys/2)
            handleUnderflow(searchNode);    
    }
    
    /*The following methods are private, and serve the deleteKey function*/
    private void handleUnderflow(Node node){
//      int underflowCondition = maxKeys/2;
        while (node != null && node.keyRing.size() < underflowCondition){
            if (node.leftSibling != null && node.leftSibling.parent == node.parent 
                    && node.leftSibling.keyRing.size() > underflowCondition) {
                redistributeWithLeftSibling(node, node.leftSibling);
            }
            else if (node.rightSibling != null && node.rightSibling.parent == node.parent 
                    && node.rightSibling.keyRing.size() > underflowCondition) {
                redistributeWithRightSibling(node, node.rightSibling);
            }
            else{
                if (node.leftSibling != null && node.leftSibling.parent == node.parent)
                    mergeNodes(node.leftSibling, node);
                else if (node.rightSibling != null && node.rightSibling.parent == node.parent)
                    mergeNodes(node, node.rightSibling);
                node = node.parent;
            }
        }
    }
    private void redistributeWithLeftSibling(Node underflowNode, Node leftSiblingNode){
        
        if (underflowNode.isALeaf() && leftSiblingNode.isALeaf())
            redistributeLeafWithLeftSibling(underflowNode, leftSiblingNode);
        else if (underflowNode.isNotALeaf() && leftSiblingNode.isNotALeaf())
            redistributeKeyNodeWithLeftSibling(underflowNode, leftSiblingNode);
            
    }
    private void redistributeLeafWithLeftSibling(Node underflowNode, Node leftSiblingNode){
        while (underflowNode.keyRing.size() < leftSiblingNode.keyRing.size())
        {
            underflowNode.addKeyToFrontOfList(leftSiblingNode.popLastKey());
        }
        underflowNode.parent.keyRing.set
        (underflowNode.parent.childList.indexOf(underflowNode) - 1, underflowNode.getFirstKey());
    }
    private void redistributeKeyNodeWithLeftSibling(Node underflowNode, Node leftSiblingNode){
        if (underflowNode.keyRing.size() < underflowCondition)
        {
            underflowNode.addKeyToFrontOfList(underflowNode.leftSibling.popLastKey());
            underflowNode.addChildToFrontOfList(underflowNode.leftSibling.popLastChild());
            underflowNode.getFirstChild().parent = underflowNode;
            Node temp = underflowNode.getChild(1);
            
            while (temp.isNotALeaf())
                temp = temp.getFirstChild();
            
            underflowNode.replaceKey(0, temp.getFirstKey());
            
            temp = underflowNode;
            while (temp.isNotALeaf())
                temp = temp.getFirstChild();
            
            underflowNode.parent.replaceKey(underflowNode.parent.childList.indexOf(underflowNode)-1, temp.getFirstKey());
        }
    }
    private void redistributeWithRightSibling(Node underflowNode, Node rightSiblingNode){
        if (underflowNode.isALeaf() && rightSiblingNode.isALeaf())
            redistributeLeafWithRightSibling(underflowNode, rightSiblingNode);
        else if (underflowNode.isNotALeaf() && rightSiblingNode.isNotALeaf())
            redistributeKeyNodeWithRightSibling(underflowNode, rightSiblingNode);
    }
    private void redistributeLeafWithRightSibling(Node underflowNode, Node rightSiblingNode){
        while (underflowNode.keyRing.size() < rightSiblingNode.keyRing.size())
            underflowNode.addKeyToEndOfList(rightSiblingNode.popFirstKey());
        rightSiblingNode.parent.keyRing.set(rightSiblingNode.parent.childList.indexOf(rightSiblingNode)-1, rightSiblingNode.keyRing.get(0));  
    }
    private void redistributeKeyNodeWithRightSibling(Node underflowNode, Node rightSiblingNode){
        if (underflowNode.keyRing.size() < underflowCondition)
        {
            underflowNode.keyRing.add(underflowNode.parent.keyRing.get(underflowNode.parent.childList.indexOf(rightSiblingNode)-1));
            underflowNode.parent.replaceKey(rightSiblingNode.parent.childList.indexOf(rightSiblingNode) -1, rightSiblingNode.popFirstKey());
            underflowNode.childList.add(underflowNode.childList.size(), rightSiblingNode.popFirstChild());
            underflowNode.childList.get(underflowNode.childList.size()-1).parent = underflowNode;
        }
    }
    private void mergeNodes(Node leftNode, Node rightNode){
        if (leftNode.isALeaf() && rightNode.isALeaf())
            mergeLeafNodes(leftNode, rightNode);
        else if (leftNode.isNotALeaf() && rightNode.isNotALeaf())
            mergeKeyNodes(leftNode, rightNode);
        totalNodes--;
        if (leftNode.parent != null && rightNode.parent != null)
            if ((leftNode == leftNode.parent.getFirstChild()) && (rightNode == leftNode.parent.getLastChild()))
                treeHeight--;
    }
    private void mergeLeafNodes(Node leftNode, Node rightNode){
        Boolean isRoot = false;
        if (rightNode.parent == root && root.childList.size() == 2)  //Check if the parent is the root
            isRoot = true;
        while (!leftNode.keyRing.isEmpty()) { //Merge nodes
            rightNode.keyRing.add(0, leftNode.keyRing.get(leftNode.keyRing.size() - 1));
            leftNode.keyRing.remove(leftNode.keyRing.size() - 1);
        }
        rightNode.leftSibling = leftNode.leftSibling; //Handle pointers
        if (leftNode.leftSibling != null) {
            leftNode.leftSibling.rightSibling = rightNode;
        }
        else
            rightNode.leftSibling = null;
        
        leftNode.parent.keyRing.remove(leftNode.parent.childList.indexOf(leftNode));
        rightNode.parent.childList.remove(leftNode);
        leftNode = null;
        
        if (isRoot) { //Handle root pointers if the parent is the root
            root = rightNode;
            root.leftSibling = null;
            root.rightSibling = null;
            root.parent = null;
        }
    }
    private void mergeKeyNodes(Node leftNode, Node rightNode){
        Boolean isRoot = false;
        if (rightNode.parent == root && root.childList.size() == 2) //Check if the parent is the root
            isRoot = true;
        rightNode.keyRing.add(0, rightNode.parent.keyRing.get(rightNode.parent.childList.indexOf(rightNode) - 1)); //Merge nodes
        rightNode.parent.keyRing.remove(rightNode.parent.childList.indexOf(leftNode));
        rightNode.parent.childList.remove(leftNode);
        while (!leftNode.keyRing.isEmpty()) { //Move keys
            rightNode.keyRing.add(0, leftNode.keyRing.get(leftNode.keyRing.size() - 1));
            leftNode.keyRing.remove(leftNode.keyRing.size() - 1);
        }
        while (!leftNode.childList.isEmpty()) { //Move children
            rightNode.childList.add(0, leftNode.childList.get(leftNode.childList.size() - 1));
            rightNode.childList.get(0).parent = rightNode;
            leftNode.childList.remove(leftNode.childList.size() - 1);
        }
        rightNode.leftSibling = leftNode.leftSibling; //Handle pointers
        if (leftNode.leftSibling != null) {
            leftNode.leftSibling.rightSibling = rightNode;
        }
        else
            rightNode.leftSibling = null;
        leftNode = null;
        if (isRoot) { //Handle root pointers if the parent is the root
            root = rightNode;
            root.leftSibling = null;
            root.rightSibling = null;
            root.parent = null;
        }
    }
    
    /**
     * Determine the average width of all leaf nodes. 
     * This method is used to gage the width based balance of a B+ tree.
     * @return 
     */
    public double checkAverageWidth()
    {
        int totalLeaves = 0;
        int totalKeys = 0;
        Node searchNode = root;
        //find the leftmost leaf.
        while (searchNode.isNotALeaf())
            searchNode = searchNode.getFirstChild();
        //run through the leaves
        do{ totalLeaves++;
            totalKeys += searchNode.keyRing.size();
            searchNode = searchNode.rightSibling;
        }while (searchNode != null);
        return totalKeys/totalLeaves;
    }
    
    /**
     * Runs a Monte Carlo Simulation of the B+ tree. 
     * WARNING: This WILL erase any data contained within the tree.
     * @param textPane A JTextPane to be updated periodically.
     * @return 
     */
    public void runMonteCarloSimulation(javax.swing.JTextPane textPane, int numberOfSimulations, int numberToInsert, long maxNumber, int deleteIndicator, int numberToDelete)
    {   
        for (int k = 0; k < numberOfSimulations; k++)
        {
        root = new Node();
        
        long currentKey = 0;
        ArrayList<Long> numberHistory = new ArrayList<>();
        
        for (int i = 0; i < numberToInsert; i++)
        {
                if (i%(numberToInsert/1000.0 + 1) == 0){    
                    textPane.setText("Killing the B+ tree to death with Monte Carlo. Thank you for your patience.\n" 
                            + Math.round(i/(double)numberToInsert*100) + "%\n" 
                            + "Current Tree Height: " + treeHeight + "\n"
                            + "Total Nodes: " + totalNodes + "\n"
                            + "Total Inserts: " + totalInserts + "\n"
                            + "Total Deletes: " + totalDeletes + "\n");
                    textPane.update(textPane.getGraphics());
                }
        
            //generate a new number
            do{currentKey =  (long)(Math.random() * maxNumber);}
            while (numberHistory.contains(currentKey));
            
            //Add that number to the tree.
            numberHistory.add(currentKey);
            insert(currentKey, null);
            
            //Trigger for deletion event
            if ((i%deleteIndicator ==  0) && i != 0){
                for (int j = 0; j < numberToDelete; j++){
                    //generate a number that exists in the tree
                    do{currentKey =  (long)(Math.random() * maxNumber);}
                    while (!numberHistory.contains(currentKey));       
                    delete(currentKey);
                }
            }
        }
        textPane.setText("Finished!\n" + this.toString());
        textPane.update(textPane.getGraphics());     
        }
    }
    
    //These methods were used for debugging
    private void runInsertionHistory(){
        try
        {
            File myFile = new File("inserting.txt");
            Scanner myScanner = new Scanner(myFile);
            
            while (myScanner.hasNextInt())
            {
                insert(myScanner.nextLong(), null);
            }
            
        }
        catch(Exception e)
        {
            System.out.println("File not found in insertion history.");
        }
    }
    private void checkForErrors(){
        Node searchNode = root;
        while (searchNode.isNotALeaf())
            searchNode = searchNode.getFirstChild();
        searchNode = searchNode.parent;
        
        while (searchNode != null)
        {
            ArrayList<Integer> currentKeyRing = new ArrayList<>();
            for (int i = 0; i < searchNode.keyRing.size(); i++)
            {
                if (searchNode.getChild(i + 1).getFirstKey() != searchNode.keyRing.get(i))
                    System.out.print("ERROR");
            }
            for (int i = 0; i < searchNode.childList.size(); i++)
            {
                if (searchNode != searchNode.getChild(i).parent)
                    System.out.print("ERROR with parent");             
            }
            searchNode = searchNode.rightSibling;
        }
    }
    
    /**
     * Create and return a string representing the b+ tree.
     * @return 
     */
    @Override public String toString()
    {
        try
        {
            if (!root.keyRing.isEmpty())
            {
                DisplayBox displayBox = new DisplayBox(this, 600,30);
                return "Current Tree Height: " + treeHeight + "\n" 
                        + "Total Nodes: " + totalNodes 
                        + "Total Nodes: " + totalNodes + "\n"
                        + "Total Inserts: " + totalInserts + "\n"
                        + "Total Deletes: " + totalDeletes + "\n"
                        + "\n" + displayBox.getDisplay();
            }
            else
                return "Empty B+ tree.";
        }
        catch (Exception e)
        {
            return "Current Tree Height: " + treeHeight + "\n" + "B+ tree too large to display!";
        }  
    }
}
