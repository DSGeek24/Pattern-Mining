package Project1.apriorialgorithm;

import java.util.*;

/**
 * 
 * @author deepika
 *
 */
public class Convert2Bitstream {

    private List<List<Integer>> idPerTransaction;
    private int minSup;
    private int kValue;

    private HashMap<Integer, Integer> item2List;
    protected static HashMap<BitSet, Integer> item2BitSetList;
    protected static List<Map.Entry<Integer, Integer>> listL1;
    private HashMap<Integer, Integer> itemPositions;

    private int l1Size;

    protected static ArrayList<BitSet> transaction2BitsetList;

    SubsetGeneration subsetGen;

    public Convert2Bitstream(List<List<Integer>> idPerTransaction, int minSup, int k) {
        this.idPerTransaction = idPerTransaction;
        this.minSup = minSup;
        this.kValue = k;
    }

    protected void init() {
        item2List = new HashMap<>();
        itemPositions = new HashMap<>();

        transaction2BitsetList = new ArrayList<>();
        item2BitSetList = new HashMap<>();

        subsetGen = new SubsetGeneration();
    }

    protected void prepareBitStream() {
        item2List = new HashMap<>();

        //counting the number of times each item has occurred by checking all transactions
        for (List<Integer> listOfIds : idPerTransaction) {
            if (listOfIds.size() >= kValue) {
                for (Integer listOfId : listOfIds) {
                    item2List.put(listOfId, item2List.getOrDefault(listOfId, 0) + 1);
                }
            }
        }

        // Remove the elements in listL1 which have less than minimum support.
        item2List.values().removeIf(value -> value < minSup);

        // Sort the map in the decreasing order of support of the items.
        Set<Map.Entry<Integer, Integer>> set = item2List.entrySet();
        listL1 = new ArrayList<>(set);
        listL1.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // For each item in the remaining set, map it to a unique id.
        //every item getting unique id and stored in itemIndex with item as key and value as unique id
        for (Map.Entry<Integer, Integer> entry : listL1) {
        	itemPositions.put(entry.getKey(), itemPositions.size());
        }

        l1Size = listL1.size();
    }

    protected int convert2BitStream() {
        for (List<Integer> presentTransaction : idPerTransaction) {
            if (presentTransaction.size() >= kValue) {
                int freqItem = 0;
                BitSet bitset = new BitSet(l1Size);
                for (Integer presentItem : presentTransaction) {
                    if (itemPositions.containsKey(presentItem)) {
                        Integer integer = itemPositions.get(presentItem);
                        bitset.set(integer, true);
                        freqItem += 1;
                    }
                }
                if (freqItem >= kValue) {
                    transaction2BitsetList.add(bitset);
                    Set<BitSet> subsetsOfLen2 = subsetGen.formSubsetsOfSize2(bitset, l1Size);
                    for (BitSet subset : subsetsOfLen2) {
                        item2BitSetList.put(subset, item2BitSetList.getOrDefault(subset, 0) + 1);
                    }
                }
            }
        }
        item2BitSetList.values().removeIf(value -> value < minSup);
        return l1Size;
    }
}
