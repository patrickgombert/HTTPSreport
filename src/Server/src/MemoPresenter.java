package Server.src;

import java.util.Set;

public class MemoPresenter {
    
    Memo memo;
    
    public MemoPresenter(Memo _memo) {
        memo = _memo;
    }
    
    public String getOutMemo() {
        String returnMemo = "";
        returnMemo += memo.getField("Version");
        returnMemo += " " + memo.getField("Status") + "\r\n";
        Set<String> keys = memo.getFields();
        keys.remove("Verb");
        keys.remove("Status");
        keys.remove("Path");
        keys.remove("Version");
        for(String key : keys) {
            if(!key.equals("Content")) {
                returnMemo += key + ": " + memo.getField(key) + "\r\n";
            }
        }
        if (keys.contains("Content")) {
            returnMemo += "\r\n" + memo.getField("Content");
        }
        return returnMemo;
    }
}
