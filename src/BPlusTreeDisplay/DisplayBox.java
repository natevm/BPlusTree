package BPlusTreeDisplay;
import BPlusTree.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
/**
 * DisplayBox.java
 * 
 * This creates a 2D character array that prints a given B+ tree. 
 * 
 * @author nathan
 */
public class DisplayBox {
    /*Attributes:
        A DisplayBox is a 2D character array with a width, hight, and a reference to
        a bPlusTree.
    */
    int width;
    int height;
    char[][] display;
    BPlusTree bPlusTree;
    
    /**
     * DisplayBox constructor.
     * @param bPlusTree a reference to your BPlusTree class.
     * @param width This display class doesn't currently calculate width. One must be provided.
     * @param height This display class doesn't currently calculate height. One must be provided.
     */
    public DisplayBox(BPlusTree bPlusTree, int width, int height)
    {
        this.bPlusTree = bPlusTree;
        display = new char[width][height];
        for (char[] row: display)
            Arrays.fill(row, ' '); //initialize the array with spaces.
    }

    /**
     * Creates a string emulating an entire b+ tree.
     * this REALLY needs to be broken up into multiple methods.
     * @return A string that contains the emulated b+ tree.
     */
    public String getDisplay()
    {
        /**
         * we need a list of NodeBoxes, a widthQueue that is used to calculate 
         * parent offsets, and a current node and a node stack for a 
         * recursive-like technique.
         */
        ArrayList<NodeBox> boxList = new ArrayList<>();
        Stack<Node> nodeStack = new Stack<>();
        Queue<Integer> widthQueue = new LinkedList<>();
        Node current = bPlusTree.root;
        nodeStack.push(current);//push the root.
        
        int currentDepth = 0;
        
        //get the left-most leaf
        while (current.isNotALeaf())      
        {  
            current = current.getFirstChild();
            nodeStack.push(current);//push the current.
            currentDepth++;
        }
        int bottom = currentDepth; //keep track of the bottom.
        while (!nodeStack.isEmpty()) //while there are still layers to handle...
        {
            //pop the next layer to handle.
            current = nodeStack.pop();
            
            //start with a new boxList.
            boxList = new ArrayList<>();
            Node oldParent, newParent;
            oldParent = newParent = current.parent;
            //add all layer siblings to the box list.
            while (current != null)
            {
                if ((oldParent != newParent) && (newParent != null))             
                    boxList.add(new NodeBox(3,3));  //A nodebox will be added between nodes with different parents.
                
                //add the current node to the list.
                boxList.add(new NodeBox(current));
                
                //shift current to the right. 
                oldParent = current.parent;
                current = current.rightSibling;
                
                if (current != null)
                    newParent = current.parent;
            }
            int totalWidth = 0;
            Boolean recordNext = true;
            //assign x and y coordinates. 
            for (int i = 0; i < boxList.size(); i++)
            {
                int childInfluenceOnSpacing = 0;
                int beginning = 0;
                int ending = 0;
                
                if ((currentDepth != bottom) && (widthQueue.size() != 0) 
                        && (widthQueue.size() != 1) && !boxList.get(i).nullBox)
                {
                    beginning = widthQueue.remove();    
                    ending = widthQueue.remove();
                    childInfluenceOnSpacing = ((ending - beginning )/2); 
                }
                //Height doesn't matter. it's consistently the height * depth. 
                boxList.get(i).y = currentDepth * ( boxList.get(i).height + 1);
                
                if (currentDepth != bottom)
                {   //if parent,
                    if (!boxList.get(i).nullBox)
                        totalWidth = beginning;
                    boxList.get(i).x = totalWidth + childInfluenceOnSpacing; 
                    boxList.get(i).x -= (boxList.get(i).width/2);           
                    totalWidth++;
                    if (boxList.get(i).x < 0)
                        boxList.get(i).x = 0;
                }
                else
                {   //if child
                    boxList.get(i).x += totalWidth;
                    totalWidth += boxList.get(i).width + 1;
                }              
                
                if (recordNext)
                {
                    widthQueue.add(boxList.get(i).x);
                    recordNext = false;
                }
                if ((i == boxList.size() - 1) || boxList.get(i).nullBox)
                {
                    if (boxList.get(i).nullBox)
                    {
                        widthQueue.add(boxList.get(i-1).x + (boxList.get(i-1).width) + 1);
                    }
                    else
                        widthQueue.add(boxList.get(i).x + boxList.get(i).width);
                    recordNext = true;
                }
            }
            //apply boxes to display.
            for (NodeBox boxList1 : boxList) {
                //apply one box
                for (int j = 0; j < boxList1.width; j++) {
                    display[boxList1.x + j][boxList1.y] = boxList1.box[0][j];
                    display[boxList1.x + j][boxList1.y + 1] = boxList1.box[1][j];
                    display[boxList1.x + j][boxList1.y + 2] = boxList1.box[2][j];
                }
            }
            currentDepth--;
        }
        
        //convert the display into a string
        String displayString = "";
        //for each x
        for (int j = 0; j < display[0].length; j++)
        {   
            for (int i = 0; i < display.length; i++) //for each y
            {//add the character to the string
                displayString += display[i][j];
            }
            displayString += "\n";
        }
        
        //return the display string.
        return displayString;
    }
}
