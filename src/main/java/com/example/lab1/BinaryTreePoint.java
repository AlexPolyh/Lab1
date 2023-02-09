package com.example.lab1;

import Interface.IBinaryTreePoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class BinaryTreePoint implements IBinaryTreePoint {
    NodePoint rootPoint;
    String displayDataTransfer = "0";
    private static final Pattern SPACE = Pattern.compile("\\s+");
    public void insertPoint(NodePoint nodePoint){
        rootPoint = insertNodePoint(rootPoint, nodePoint);
    }
    private NodePoint insertNodePoint(NodePoint rootPoint, NodePoint nodePoint) {
        if(rootPoint == null){
            rootPoint = nodePoint;
            return rootPoint;
        }
        else if (nodePoint.dataP < rootPoint.dataP){
            rootPoint.leftNodeP = insertNodePoint(rootPoint.leftNodeP, nodePoint);
        } else if (nodePoint.dataP == rootPoint.dataP) {
            System.out.println("Такие данные уже есть");
        } else {
            rootPoint.rightNodeP = insertNodePoint(rootPoint.rightNodeP, nodePoint);
        }
        return rootPoint;
    }
    public String displayPoint(){
        displayDataTransfer = "0";
        return displayNodePoint(rootPoint);
    }
    private String displayNodePoint(NodePoint rootPoint){
        if (rootPoint != null){
            displayNodePoint(rootPoint.leftNodeP);
            displayDataTransfer += rootPoint.cordx + " " + rootPoint.cordy + "\n";
            displayNodePoint(rootPoint.rightNodeP);
        }
        return displayDataTransfer.substring(1);
    }
    public boolean searchPoint(float dataP){
        return searchNodePoint(rootPoint, dataP);
    }
    private boolean searchNodePoint(NodePoint rootPoint, float dataP){
        if(rootPoint == null) {
            return false;
        } else if (rootPoint.dataP == dataP) {
            return true;
        } else if (rootPoint.dataP > dataP) {
            return searchNodePoint(rootPoint.leftNodeP, dataP);
        } else {
            return searchNodePoint(rootPoint.rightNodeP, dataP);
        }
    }
    public void removePoint(float dataP){
        if(searchPoint(dataP)){
            deleteNodePoint(rootPoint, dataP);
        }
        else {
            System.out.println(dataP + "Не найдено");
        }
    }

    private static NodePoint deleteNodePoint(NodePoint rootPoint, float dataP)
    {
        NodePoint parent = null;
        NodePoint curr = rootPoint;
        while (curr != null && curr.dataP != dataP)
        {
            parent = curr;
            if (dataP < curr.dataP) {
                curr = curr.leftNodeP;
            }
            else {
                curr = curr.rightNodeP;
            }
        }
        if (curr == null) {
            return rootPoint;
        }
        if (curr.leftNodeP == null && curr.rightNodeP == null)
        {
            if (curr != rootPoint)
            {
                if (parent.leftNodeP == curr) {
                    parent.leftNodeP = null;
                }
                else {
                    parent.rightNodeP = null;
                }
            }
            else {
                rootPoint = null;
            }
        }
        else if (curr.leftNodeP != null && curr.rightNodeP != null)
        {
            NodePoint successor = getMinimumKey(curr.rightNodeP);
            float val = successor.dataP;
            deleteNodePoint(rootPoint, successor.dataP);
            curr.dataP = val;
        }
        else {
            NodePoint child = (curr.leftNodeP != null)? curr.leftNodeP: curr.rightNodeP;
            if (curr != rootPoint)
            {
                if (curr == parent.leftNodeP) {
                    parent.leftNodeP = child;
                }
                else {
                    parent.rightNodeP = child;
                }
            }
            else {
                rootPoint = child;
            }
        }
        return rootPoint;
    }

    private static NodePoint getMinimumKey(NodePoint curr)
    {
        while (curr.leftNodeP != null) {
            curr = curr.leftNodeP;
        }
        return curr;
    }

    public void balancingPoint(){
        String[] balanceString = SPACE.split(displayPoint());

        ArrayList<Float> balanceFloatData = new ArrayList<Float>();
        ArrayList<Float> balanceFloatCoordx = new ArrayList<Float>();
        ArrayList<Float> balanceFloatCoordY = new ArrayList<Float>();
        for (int i = 0; i < balanceString.length; i++) {
            if(i%2 == 0) {
                balanceFloatCoordx.add(Float.parseFloat(balanceString[i]));
            } else {
                balanceFloatCoordY.add(Float.parseFloat(balanceString[i]));
                balanceFloatData.add(balanceFloatCoordx.get(balanceFloatCoordx.size() - 1) * balanceFloatCoordx.get(balanceFloatCoordx.size() - 1) +
                        balanceFloatCoordY.get(balanceFloatCoordY.size() - 1) * balanceFloatCoordY.get(balanceFloatCoordY.size() - 1));
                removePoint(balanceFloatData.get(balanceFloatData.size() - 1));
            }
        }
        for (Float f:
             balanceFloatCoordx) {
            System.out.println(f);
        }
        System.out.println("ffff");
        for (Float f:
             balanceFloatCoordY) {
            System.out.println(f);
        }
        System.out.println("ffff");
        for (Float f:
             balanceFloatData) {
            System.out.println(f);
        }
        System.out.println("ffff");
        Collections.sort(balanceFloatData);
        System.out.println(balanceFloatData.size());
        if (balanceFloatData.size()%2 == 0 ){
            firstPartArray(balanceFloatData, balanceFloatCoordx, balanceFloatCoordY);
        } else {
            secondPartArray(balanceFloatData, balanceFloatCoordx, balanceFloatCoordY);
        }
    }

    private void firstPartArray(ArrayList<Float> balanceFloatData, ArrayList<Float> balanceFloatCoordx, ArrayList<Float> balanceFloatCoordY ){
        int firstAverageArr = balanceFloatData.size()/2 - 1;
        int secondAverageArr = balanceFloatData.size()/2;
        for (int i = 0; i < secondAverageArr; i++) {
            System.out.println(firstAverageArr - i);
            insertPoint(new NodePoint(balanceFloatCoordx.get(firstAverageArr - i), balanceFloatCoordY.get(firstAverageArr - i)));
            System.out.println(secondAverageArr + i);
            insertPoint(new NodePoint(balanceFloatCoordx.get(secondAverageArr + i), balanceFloatCoordY.get(secondAverageArr + i)));
        }
    }

    private void secondPartArray(ArrayList<Float> balanceFloatData, ArrayList<Float> balanceFloatCoordx, ArrayList<Float> balanceFloatCoordY ){
        int averageArr = balanceFloatData.size()/2;
        System.out.println(averageArr);
        insertPoint(new NodePoint(balanceFloatCoordx.get(averageArr), balanceFloatCoordY.get(averageArr)));
        for (int i = 1; i <= averageArr; i++) {
            System.out.println(averageArr - i);
            insertPoint(new NodePoint(balanceFloatCoordx.get(averageArr - i), balanceFloatCoordY.get(averageArr - i)));
            System.out.println(averageArr + i);
            insertPoint(new NodePoint(balanceFloatCoordx.get(averageArr + i), balanceFloatCoordY.get(averageArr + i)));
        }
    }
}
