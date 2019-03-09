package Project1.helperfunctions;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Project1.domainobjects.MapSetGet;

/**
 * 
 * @author deepika
 *
 */
public class PrepareMaps {

    private String inputFilePath;

    private HashMap<String, Integer> item2Id;
    private HashMap<Integer, String> id2Item;
    private List<List<Integer>> idPerTransaction;

    public PrepareMaps(List<List<Integer>> idPerTransaction, String inputFilePath) {
        this.idPerTransaction = idPerTransaction;
        this.inputFilePath = inputFilePath;
    }

    public void init() {
        item2Id = new HashMap<>();
        id2Item = new HashMap<>();
        idPerTransaction = new ArrayList<>();
    }

    public void readInputFile(MapSetGet setGet) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFilePath)))) {
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split(" ");
                List<Integer> itemsId = new ArrayList<>();

                for (String item : tokens) {
                    if (!item2Id.containsKey(item)) {
                    	//giving ids to items
                        item2Id.put(item, item2Id.size() + 1);
                        //map which contains id as key and the item as value
                        id2Item.put(item2Id.size(), item);
                    }
                    //contains list of items(in terms of ids) in every transaction which is later added to idPerTransaction
                    itemsId.add(item2Id.get(item));
                }
                //contains transactions in terms of id's instead of items
                idPerTransaction.add(itemsId);

                line = br.readLine();
            }
            
            
            setGet.setItem2Id(item2Id);
            setGet.setId2Item(id2Item);
            setGet.setIdPerTransaction(idPerTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
