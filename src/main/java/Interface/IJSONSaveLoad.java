package Interface;

import java.util.ArrayList;

public interface IJSONSaveLoad {
    public void saveJson(String savePath);
    public void loadObj (String[] saveDataArray);
    public void loadObjPoint (String[] saveDataArray);
    public ArrayList<String> loadJson(String loadPath);
}
