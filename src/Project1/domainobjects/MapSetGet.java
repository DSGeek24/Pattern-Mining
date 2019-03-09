package Project1.domainobjects;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author deepika
 *
 */
public class MapSetGet {

    private HashMap<String, Integer> item2Id;// = new HashMap<>();
    private HashMap<Integer, String> id2Item;// = new HashMap<>();
    private List<List<Integer>> idPerTransaction;// = new ArrayList<>();

    public HashMap<String, Integer> getItem2Id() {
        return item2Id;
    }

    public void setItem2Id(HashMap<String, Integer> item2Id) {
        this.item2Id = item2Id;
    }

    public HashMap<Integer, String> getId2Item() {
        return id2Item;
    }

    public void setId2Item(HashMap<Integer, String> id2Item) {
        this.id2Item = id2Item;
    }

    public List<List<Integer>> getIdPerTransaction() {
        return idPerTransaction;
    }

    public void setIdPerTransaction(List<List<Integer>> idPerTransaction) {
        this.idPerTransaction = idPerTransaction;
    }
}
