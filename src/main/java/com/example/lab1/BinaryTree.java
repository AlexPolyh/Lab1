package com.example.lab1;

import Interface.IBinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class BinaryTree implements IBinaryTree {
    Node root;
    private static final Pattern SPACE = Pattern.compile("\\s+");

    String displayDataTransfer = "0";
    public void insert(Node node){
        root = insertNode(root, node);
    }
    private Node insertNode(Node root, Node node) {
        if(root == null){
            root = node;
            return root;
        } else if (node.data < root.data){
            root.leftNode = insertNode(root.leftNode, node);
        } else if (node.data == root.data) {
            System.out.println("такие данные уже есть");
        } else {
            root.rightNode = insertNode(root.rightNode, node);
        }
        return root;
    }
    public String display(){
        displayDataTransfer = "0";
       return displayNode(root);
    }
    private String displayNode(Node root){
        if (root != null){
            displayNode(root.leftNode);
            displayDataTransfer += root.data + " ";
            displayNode(root.rightNode);
        }
        return displayDataTransfer.substring(1);
    }
    public boolean search(int data){
        return searchNode(root, data);
    }
    private boolean searchNode(Node root, int data){
        if(root == null) {
            return false;
        } else if (root.data == data) {
            return true;
        } else if (root.data > data) {
            return searchNode(root.leftNode, data);
        } else {
            return searchNode(root.rightNode, data);
        }
    }
    public void remove(int data){
        if(search(data)){
            deleteNode(root, data);
        }
        else {
            System.out.println(data + "Не найдено");
        }
    }

    private static Node deleteNode(Node root, int data)
    {
        Node parent = null;
        Node curr = root;
        while (curr != null && curr.data != data)
        {
            parent = curr;
            if (data < curr.data) {
                curr = curr.leftNode;
            }
            else {
                curr = curr.rightNode;
            }
        }
        if (curr == null) {
            return root;
        }
        if (curr.leftNode == null && curr.rightNode == null)
        {
            if (curr != root)
            {
                if (parent.leftNode == curr) {
                    parent.leftNode = null;
                }
                else {
                    parent.rightNode = null;
                }
            }
            else {
                root = null;
            }
        }
        else if (curr.leftNode != null && curr.rightNode != null)
        {
            Node successor = getMinimumKey(curr.rightNode);
            int val = successor.data;
            deleteNode(root, successor.data);
            curr.data = val;
        }
        else {
            Node child = (curr.leftNode != null)? curr.leftNode: curr.rightNode;
            if (curr != root)
            {
                if (curr == parent.leftNode) {
                    parent.leftNode = child;
                }
                else {
                    parent.rightNode = child;
                }
            }
            else {
                root = child;
            }
        }
        return root;
    }

    private static Node getMinimumKey(Node curr)
    {
        while (curr.leftNode != null) {
            curr = curr.leftNode;
        }
        return curr;
    }
    public void balancing(){
        String[] balanceString = SPACE.split(display());
        ArrayList<Integer> balanceInt = new ArrayList<Integer>();
        System.out.println(balanceString);
        for (String deleteElement : balanceString) {
            System.out.println(Integer.parseInt(deleteElement));
            remove(Integer.parseInt(deleteElement));
            balanceInt.add(Integer.parseInt(deleteElement));
        }
        display();
        Collections.sort(balanceInt);
        System.out.println(balanceInt.size());
        if (balanceInt.size()%2 == 0 ){
            firstPartArray(balanceInt);
        } else {
            secondPartArray(balanceInt);
        }
    }

    private void firstPartArray(ArrayList<Integer> balanceInt){
        int firstAverageArr = balanceInt.size()/2 - 1;
        int secondAverageArr = balanceInt.size()/2;
        for (int i = 0; i < secondAverageArr; i++) {
            System.out.println(firstAverageArr - i);
            insert(new Node(balanceInt.get(firstAverageArr - i)));
            System.out.println(secondAverageArr + i);
            insert(new Node(balanceInt.get(secondAverageArr + i)));
        }
    }

    private void secondPartArray(ArrayList<Integer> balanceInt){
        int averageArr = balanceInt.size()/2;
        System.out.println(balanceInt.size());
        insert(new Node(balanceInt.get(averageArr)));
        for (int i = 1; i <= averageArr; i++) {
            System.out.println(averageArr - i);
            insert(new Node(balanceInt.get(averageArr - i)));
            System.out.println(averageArr + i);
            insert(new Node(balanceInt.get(averageArr + i)));
        }
    }
}
