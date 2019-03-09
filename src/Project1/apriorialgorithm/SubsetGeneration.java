package Project1.apriorialgorithm;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author deepika
 *
 */
public class SubsetGeneration {

    protected HashSet<BitSet> formSubsets(BitSet bitSet) {
        HashSet<BitSet> subsets = new HashSet<>();
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                BitSet newBitSet = (BitSet) bitSet.clone();
                newBitSet.flip(i);
                subsets.add(newBitSet);
            }
        }
        return subsets;
    }

    protected Set<BitSet> formSubsetsOfSize2(BitSet bitSet, int l1Size) {
        Set<BitSet> subsets = new HashSet<>();
        
        for (int bitSet1 = 0; bitSet1 < bitSet.length(); ) {
        	bitSet1 = bitSet.nextSetBit(bitSet1);

            for (int bitSet2 = bitSet1 + 1; bitSet2 < bitSet.length(); ) {
            	bitSet2 = bitSet.nextSetBit(bitSet2);
                BitSet b = new BitSet(l1Size);
                b.set(bitSet1, true);
                b.set(bitSet2, true);
                subsets.add(b);
                bitSet2++;
            }
            bitSet1++;
        }
        return subsets;
    }
}
