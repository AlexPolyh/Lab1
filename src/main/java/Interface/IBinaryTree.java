package Interface;

import com.example.lab1.Node;
import com.example.lab1.NodePoint;

public interface IBinaryTree {
    public void insert(Node node);
    public String display();
    public boolean search(int data);
    public void remove(int data);
    public void balancing();
}
