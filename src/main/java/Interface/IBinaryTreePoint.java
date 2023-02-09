package Interface;

import com.example.lab1.NodePoint;

public interface IBinaryTreePoint {
    public void insertPoint(NodePoint nodePoint);
    public String displayPoint();
    public boolean searchPoint(float dataP);
    public void removePoint(float dataP);
    public void balancingPoint();
}
