package Project1.apriorialgorithm;

import java.util.*;

/**
 * 
 * @author deepika
 *
 */
public class Apriori {

	private List<Map.Entry<Integer, Integer>> listL1;
	private List<BitSet> transaction2BitsetList;
	private HashMap<BitSet, Integer> item2BitSetList;

	int l1Size;
	int minSup;
	int kValue;

	SubsetGeneration subsetGen;

	public Apriori(List<Map.Entry<Integer, Integer>> listL1, List<BitSet> transaction2BitsetList,
			HashMap<BitSet, Integer> item2BitSetList, int l1Size, int minSup, int kValue) {
		this.listL1 = listL1;
		this.l1Size = l1Size;
		this.minSup = minSup;
		this.kValue = kValue;
		this.transaction2BitsetList = transaction2BitsetList;
		this.item2BitSetList = item2BitSetList;
	}

	protected void init() {
		subsetGen = new SubsetGeneration();
	}

	protected HashMap<BitSet, Integer> aprioriAlgorithm() {
		HashMap<BitSet, Integer> frequentItemSetsBits = new HashMap<>();
		HashMap<BitSet, Integer> allFreqItemSets = new HashMap<>();

		for (int i = 0; i < listL1.size(); i++) {
			BitSet bitSet = new BitSet(l1Size);
			bitSet.set(i, true);
			frequentItemSetsBits.put(bitSet, listL1.get(i).getValue());
		}

		int presentKValue = 1;

		while (!frequentItemSetsBits.isEmpty()) {
			if (presentKValue > 1) {
				HashMap<BitSet, Integer> frequentItemAsBits = (HashMap) frequentItemSetsBits.clone();
				frequentItemSetsBits.clear();
				frequentItemSetsBits = candidateGeneration(frequentItemAsBits, presentKValue);
			}

			if (presentKValue >= kValue) {
				for (Map.Entry<BitSet, Integer> entry : frequentItemSetsBits.entrySet()) {
					allFreqItemSets.put(entry.getKey(), entry.getValue());
				}
			}
			presentKValue++;
		}
		return allFreqItemSets;
	}

	private void performJoin(int j, BitSet bitset1, ArrayList<BitSet> frequentItems,
			HashMap<BitSet, Integer> frequentItemAsBits, int currentK, Map<BitSet, Integer> primaryCandidateSet) {
		bitset1.or(frequentItems.get(j));
		if (bitset1.cardinality() == currentK) {
			HashSet<BitSet> subsets = subsetGen.formSubsets(bitset1);
			boolean areSubsetsFrequent = true;
			for (BitSet subset : subsets) {
				if (frequentItemAsBits.getOrDefault(subset, -1) == -1) {
					areSubsetsFrequent = false;
					break;
				}
			}
			if (areSubsetsFrequent) {
				int supValue = 0;
				for (BitSet entry : transaction2BitsetList) {
					if (entry.cardinality() < currentK)
						continue;
					BitSet transaction1 = (BitSet) entry.clone();
					transaction1.and(bitset1);
					if (transaction1.equals(bitset1))
						supValue++;
				}
				if (supValue >= minSup) {
					primaryCandidateSet.put(bitset1, supValue);
				}
			}
		}
	}

	private void joinStep(ArrayList<BitSet> frequentItems, int presentKvalue, Map<BitSet, Integer> primaryCandidateSet,
			HashMap<BitSet, Integer> frequentItemAsBits) {
		for (int i = 0; i < frequentItems.size() - 1; i++) {
			for (int j = i + 1; j < frequentItems.size(); j++) {
				BitSet bitSet1 = (BitSet) frequentItems.get(i).clone();
				BitSet bitSet2 = (BitSet) frequentItems.get(j).clone();
				boolean isJoinable = true;
				for (int bitIndex = 0; bitIndex < presentKvalue - 2; bitIndex++) {
					isJoinable = bitSet1.nextSetBit(bitIndex) == bitSet2.nextSetBit(bitIndex);
					if (!isJoinable)
						break;
				}
				if (isJoinable) {
					performJoin(j, bitSet1, frequentItems, frequentItemAsBits, presentKvalue, primaryCandidateSet);
				}
			}
		}
	}

	protected HashMap<BitSet, Integer> candidateGeneration(HashMap<BitSet, Integer> frequentItemAsBits, int presentKvalue) {
		HashMap<BitSet, Integer> primaryCandidateSet = new HashMap<>();
		ArrayList<BitSet> frequentItems = new ArrayList<>();
		frequentItems.addAll(frequentItemAsBits.keySet());
		if (presentKvalue == 2) { 
			primaryCandidateSet = (HashMap<BitSet, Integer>) item2BitSetList.clone();
		} else {
			joinStep(frequentItems, presentKvalue, primaryCandidateSet, frequentItemAsBits);
		}
		return primaryCandidateSet;
	}
}
