package BPlusTreeDisplay;
import BPlusTree.*;
import java.util.*;
/**
 * NodeBox.java
 * 
 * The following class creates boxes that emulate a b+ tree node.
 * 
 * example:
 * +-----+
 * |1 |2 |
 * +-----+
 * 
 * @author nathan
 */
public class NodeBox {
    /* Attributes:
        A nodeBox has an x & y coordinate, a width, and a height as well as a 
        character array emulating a b+ tree node.
    */
    int x;
    int y;
    int width;
    int height;
    char[][] box;
    public boolean nullBox = false;
    
    /**
     * @param representedNode A NodeBox needs a b+ tree node.
     */
    public NodeBox(Node representedNode)
    {
        /*
        all you need to know is that I insert a border around the character box and
        add keys in the character box. boxes currently only support keys of length 2, 
        but I could easily change it.
        */
        int j;
        box = new char[3][(representedNode.keyRing.size()*3) + 1];
        
        for (char[] row: box)
           Arrays.fill(row, ' ');
         
        for (int i = 0; i < box[0].length; i++){
            j = i%3;
            if (j == 0){
                box[0][i] = '+';
                box[1][i] = '|';
                box[2][i] = '+';
            }else{
                box[0][i] = box[2][i] = '-';
                if (j == 1){   //should be 1..2..n, getting the length either 1 or 2
                    for (int k = 0; k < String.valueOf(representedNode.getKey((i-1)/3)).length(); k++){
                        if (k == 2)
                            break; //safe guard for numbers like 100. 
                        //should insert number characters. like | 1 0 | for 10.
                        //i is the current location, k is the offset. 
                        box[1][i + k] = String.valueOf(representedNode.getKey((i-1)/3)).charAt(k);
                    }
                }
            }
        }
        height = 3;
        width = box[0].length;
    }
    
    /**
     * This is a constructor of a null NodeBox.
     * A "Null Box" is used for spacing, and it acts like an indicator between 
     * sets of node boxes containing different parents.
     * There's probably a better way to do this, but a nullBox was an on-the-fly
     * solution that seems to work.
     * @param width the width of the empty box.
     * @param height the width of the empty box.
     */
    public NodeBox(int width, int height)       
    {
        nullBox = true;
        this.width = width;
        this.height = height;
        
        box = new char[height][width];
        
        for (char[] row: box)
           Arrays.fill(row, ' ');
    }
}
