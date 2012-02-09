package Server.src.persistance.src;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDB implements Persistable {

    private static InMemoryDB instance = null;
    private ConcurrentHashMap<String, Object> persistor;

    protected InMemoryDB() {
        persistor = new ConcurrentHashMap<String, Object>();
    }

    public static Persistable getInstance() {
        if(instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }
    
    public void write(String table, Object data) {
        persistor.put(table, data);
    }
    
    public Object read(String table) {
        return persistor.get(table);
    }
}
