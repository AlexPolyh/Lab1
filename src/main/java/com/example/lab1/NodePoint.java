package com.example.lab1;

public class NodePoint  {
    public float dataP;
    public float cordx;
    public float cordy;
    public NodePoint leftNodeP;
    public NodePoint rightNodeP;

    public NodePoint(float cordx, float cordy){
        this.cordx = cordx;
        this.cordy = cordy;
        this.dataP = (cordx * cordx) + (cordy * cordy);
    }

}
