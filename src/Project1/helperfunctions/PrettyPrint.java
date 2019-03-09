package Project1.helperfunctions;

import java.io.File;
import java.io.FileWriter;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author deepika
 *
 */
public class PrettyPrint {

    private String outputFile;
    private List<Map.Entry<Integer, Integer>> listL1;
    private HashMap<Integer, String> id2Item;

    public PrettyPrint(String outputFile, List<Map.Entry<Integer, Integer>> listL1, HashMap<Integer, String> id2Item) {
        this.outputFile = outputFile;
        this.listL1 = listL1;
        this.id2Item = id2Item;
    }

    public void print2file(HashMap<BitSet, Integer> finalItemSets) {
        try (FileWriter fw = new FileWriter(new File(outputFile))) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<BitSet, Integer> bitSetIntegerEntry : finalItemSets.entrySet()) {
                BitSet item = bitSetIntegerEntry.getKey();
                Integer support = bitSetIntegerEntry.getValue();

                for (int i = 0; i < item.length(); i++) {
                    if (item.get(i)) {
                        sb.append(id2Item.get(listL1.get(i).getKey()));
                        sb.append(" ");
                    }
                }
                String supportPlaceHolder = "(" + support + ")";
                sb.append(supportPlaceHolder);
                sb.append(System.lineSeparator());
            }
            fw.write(String.valueOf(sb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
