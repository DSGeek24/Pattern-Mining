package Project1.apriorialgorithm;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

import Project1.domainobjects.InputDomain;
import Project1.domainobjects.MapSetGet;
import Project1.helperfunctions.ParseInput;
import Project1.helperfunctions.PrepareMaps;
import Project1.helperfunctions.PrettyPrint;

/**
 * 
 * @author deepika
 *
 */
public class FrequentItemsets {

    private int minSup;
    private int kValue;
    private String inputFilePath;
    private String outputFilePath;

    List<List<Integer>> idPerTransaction;

    MapSetGet setGet;

    PrepareMaps pm;
    Convert2Bitstream c2bs;
    Apriori apriori;
    PrettyPrint pp;

    public FrequentItemsets(int minSup, int k, String inputFile, String outputFile) {
        this.minSup = minSup;
        this.kValue = k;
        this.inputFilePath = inputFile;
        this.outputFilePath = outputFile;
    }

    private void genFreqItems() {
        setGet = new MapSetGet();

        pm = new PrepareMaps(idPerTransaction, inputFilePath);
        pm.init();
        pm.readInputFile(setGet);

        c2bs = new Convert2Bitstream(setGet.getIdPerTransaction(), minSup, kValue);
        c2bs.init();
        c2bs.prepareBitStream();
        int l1Size = c2bs.convert2BitStream();

        apriori = new Apriori(Convert2Bitstream.listL1, Convert2Bitstream.transaction2BitsetList,
                Convert2Bitstream.item2BitSetList, l1Size, minSup, kValue);
        apriori.init();
        HashMap<BitSet, Integer> finalItemSets = this.apriori.aprioriAlgorithm();

        pp = new PrettyPrint(outputFilePath, Convert2Bitstream.listL1, setGet.getId2Item());
        pp.print2file(finalItemSets);
    }

    public static void main(String[] args) {
        ParseInput parseInput = new ParseInput();
        InputDomain inputDomain = parseInput.parseInput(args);
        FrequentItemsets fim = new FrequentItemsets(inputDomain.getMinSup(),
                inputDomain.getK(), inputDomain.getInputFilePath(),
                inputDomain.getOutputFilePath());
        fim.genFreqItems();
    }
}
