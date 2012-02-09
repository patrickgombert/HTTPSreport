package Server.src.persistance.src;

public interface Persistable {

    public void write(String table, Object data);

    public Object read(String table);

}
