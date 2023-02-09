package com.example.lab1;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button addBotton, deleteBotton, loadJson, balanceTree, saveJson, searchBotton, visiblTree;

    @FXML
    private ComboBox<String> dataType;

    @FXML
    private TextField fieldData, savePath, downloadPath;

    @FXML
    private TextArea mainText;

    private Boolean isinteger = true;
    private String[] typeDataString = {"Целое", "2D точка"};
    private static final Pattern SPACE = Pattern.compile("\\s+");

    @FXML
    void initialize()  {

        dataType.getItems().addAll(typeDataString);
        dataType.setValue("Целое");
        BinaryTree tree = new BinaryTree();
        BinaryTreePoint treeP = new BinaryTreePoint();
        JSONSaveLoad jsonF = new JSONSaveLoad();
        dataType.setOnAction(actionEvent -> {
            if(dataType.getSelectionModel().getSelectedItem().toString().equals("Целое")) {
                isinteger = true;
                fieldData.setPromptText("Введите число");
            } else {
                isinteger = false;
                fieldData.setPromptText("Введите 2 числа");
            }
        });
        addBotton.setOnAction(actionEvent1 -> {
            if(isinteger) {
                addBottonFun(tree);
            } else {
                addBottonFunPoint(treeP);
            }
        });
            visiblTree.setOnAction(actionEvent -> {
                if(isinteger) {
                    mainText.clear();
                    mainText.appendText(tree.display());
                } else {
                    mainText.clear();
                    mainText.appendText(treeP.displayPoint());
                }
            });
            searchBotton.setOnAction(actionEvent -> {
                if (isinteger){
                    if (fieldData.getText() != null) {
                        int searchNumber = Integer.parseInt(fieldData.getText());
                        mainText.clear();
                        mainText.appendText(tree.search(searchNumber) + " ");
                    }
                } else {
                    searchButtonFun(treeP);
                }
            });
            deleteBotton.setOnAction(actionEvent -> {
                if (isinteger){
                    if (fieldData.getText() != null) {
                        int searchNumber = Integer.parseInt(fieldData.getText());
                        tree.remove(searchNumber);
                    }
                } else {
                    deleteButtonFun(treeP);
                }
            });
            balanceTree.setOnAction(actionEvent -> {
                if(isinteger){
                    tree.balancing();
                } else {
                    treeP.balancingPoint();
                }
            });
            saveJson.setOnAction(actionEvent -> {
                if(isinteger) {
                    saveJson(tree, jsonF);
                } else {
                    saveJsonPoint(treeP, jsonF);
                }
            });
            loadJson.setOnAction(actionEvent -> {
                if(isinteger){
                    loadJson(tree, jsonF);
                } else {
                    loadJsonPoint(treeP, jsonF);
                }
            });
    }

    void addBottonFun(BinaryTree tree){
        if(fieldData.getText() != null) {
            try {
                tree.insert(new Node(Integer.parseInt(fieldData.getText())));
                fieldData.clear();
            }
            catch (Exception e){
                e.getStackTrace();
            }
        } else {
            mainText.clear();
            mainText.appendText("Не правильный формат данных");
        }
    }
    void addBottonFunPoint(BinaryTreePoint treeP){
        if(fieldData.getText() != null) {
            try {
                String[] words = SPACE.split(fieldData.getText().trim());
                treeP.insertPoint(new NodePoint(Float.parseFloat(words[0]), Float.parseFloat(words[1])));
                fieldData.clear();
            }
            catch (Exception e){
                e.getStackTrace();
            }
        } else {
            mainText.clear();
            mainText.appendText("Не правильный формат данных");
        }
    }

    void searchButtonFun(BinaryTreePoint treeP){
        if(fieldData.getText() != null) {
            try {
                String[] words = SPACE.split(fieldData.getText().trim());
                float cordx = Float.parseFloat(words[0]), cordy = Float.parseFloat(words[1]);
                mainText.clear();
                mainText.appendText(treeP.searchPoint((cordx * cordx) + (cordy * cordy)) + " ");
            }
            catch (Exception e){
                e.getStackTrace();
            }
        } else {
            mainText.clear();
            mainText.appendText("Не правильный формат данных");
        }
    }

    void deleteButtonFun(BinaryTreePoint treeP){
        if(fieldData.getText() != null) {
            try {
                String[] words = SPACE.split(fieldData.getText().trim());
                float cordx = Float.parseFloat(words[0]), cordy = Float.parseFloat(words[1]);
                treeP.removePoint((cordx * cordx) + (cordy * cordy));
            }
            catch (Exception e){
                e.getStackTrace();
            }
        } else {
            mainText.clear();
            mainText.appendText("Не правильный формат данных");
        }
    }

    void saveJson(BinaryTree tree, JSONSaveLoad jsonF){
        String urnFile = savePath.getText().trim();
        String[] saveData = SPACE.split(tree.display());
        jsonF.loadObj(saveData);
        jsonF.saveJson(urnFile);
    }

    void saveJsonPoint(BinaryTreePoint treeP, JSONSaveLoad jsonF){
        String urnFile = savePath.getText().trim();
        String[] saveData = SPACE.split(treeP.displayPoint());
        jsonF.loadObjPoint(saveData);
        jsonF.saveJson(urnFile);
    }

    void loadJson(BinaryTree tree, JSONSaveLoad jsonF){
        String urnFile = downloadPath.getText().trim();
        ArrayList<String> downloadData = jsonF.loadJson(urnFile);
        for (String intData:
             downloadData) {
            tree.insert(new Node(Integer.parseInt(intData)));
        }

    }

    void loadJsonPoint(BinaryTreePoint treeP, JSONSaveLoad jsonF){
        String urnFile = downloadPath.getText().trim();
        ArrayList<String> downloadData = jsonF.loadJson(urnFile);
        for (String intData: downloadData) {
            String[] saveData = SPACE.split(intData);
            treeP.insertPoint(new NodePoint(Float.parseFloat(saveData[0]),Float.parseFloat(saveData[1])));
        }

    }
}
