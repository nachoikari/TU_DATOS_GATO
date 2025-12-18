package tu_gato;

import java.lang.reflect.Array;

/**
 *
 * @author isaac
 */
public class List {
    private Node head;
    private int size;
    public List(int pSize) {
        //Node up, Node down, Node left, Node right, char mark
        head = new Node(null,null,null,null,'7');
        this.size = pSize;
        createRows();
        createColumns();
    }

    private void createRows(){
        System.out.println("Creating rows");
        Node temp = head;
        for(int i = 1; i < this.size; i++){
            Node newNode = new Node(temp, null,null,null,'7');
            temp.setDown(newNode);
            newNode.setUp(temp);
            temp = newNode;
        }
    }
    
    private void createColumns(){
        Node[] nodes; 
        nodes = new Node[this.size];
        Node temp = head;
        for(int i =0; i <this.size; i++){
            nodes[i] = temp;
            temp = temp.getDown();
        }
        
        for(int columns = 1; columns < this.size; columns++) {
            Node[] newNodes = new Node[this.size];
            
            for(int i =0; i < size; i++){
                newNodes[i] = new Node(null, null, null, null, '8'); 
            }
            
            for(int i = 1; i < this.size; i++){
                newNodes[i - 1].setDown(newNodes[i]);
                newNodes[i].setUp(newNodes[i - 1]);
            }
            
            for(int i = 0; i < size; i++){
                nodes[i].setRight(newNodes[i]);
                newNodes[i].setLeft(nodes[i]);
                
                nodes[i] = newNodes[i];
            }
        }
    }
    
    public void printMatrix() {
        Node row = head;

        while (row != null) {
            Node col = row;

            while (col != null) {
                System.out.print(col.getMark() + " ");
                col = col.getRight();
            }

            System.out.println();
            row = row.getDown();
        }
    }

    public void printRows(){
        System.out.println("Printing rows");
        Node temp = head;
        int counter = 0;
        while(temp != null){
            System.out.println(temp.getMark());
            counter++;
            temp = temp.getDown();
        }
        System.out.println("Rows "+ counter);
    }
}
