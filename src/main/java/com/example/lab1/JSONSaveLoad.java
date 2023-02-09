package com.example.lab1;


import Interface.IJSONSaveLoad;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class JSONSaveLoad implements IJSONSaveLoad {
    JSONObject obj = new JSONObject();
    private static FileWriter file;
    public void saveJson(String savePath) {
        try {
            file = new FileWriter(savePath + "\\laboratory1.json");
            file.write(obj.toString());
            System.out.println("\nJSON объект: " + obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadObj (String[] saveDataArray){
        int numNode = 1;
        for (String dataNode: saveDataArray) {
            obj.put("Node " + numNode, dataNode);
            numNode++;
        }
    }
    public void loadObjPoint (String[] saveDataArray){
        int numNode = 1;
        for (int i = 0; i < saveDataArray.length - 1; i += 2) {
            obj.put("Node " + numNode, saveDataArray[i] + " " +  saveDataArray[i + 1]);
            numNode++;
        }
    }

    public ArrayList<String> loadJson(String loadPath){
        ArrayList<String> downloadData = new ArrayList<String>();
        try {
            file = new FileWriter(loadPath);
            file.write(obj.toString());
            System.out.println(obj.length());
            System.out.println(obj.get("Node 3"));
            int numload = 1;
            for (int i = 0; i < obj.length() - 1; i++) {
                downloadData.add(obj.get("Node " + numload).toString());
                System.out.println(obj.get("Node " + numload).toString());
                numload++;
            }
        } catch (IOException e){
            e.getStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadData;
    }
}
