package tu_gato;

/**
 *
 * @author isaac
 */
public class Node {
    private Node up;
    private Node down;
    private Node left;
    private Node right;
    private char mark;

    public Node(Node up, Node down, Node left, Node right, char mark) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.mark = mark;
    }
    
    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }
    
    
}
