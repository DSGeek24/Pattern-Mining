package Project1.domainobjects;

/**
 * 
 * @author deepika
 *
 */
public class InputDomain {
    int minSup;
    int k;
    String inputFilePath;
    String outputFilePath;

    public int getMinSup() {
        return minSup;
    }

    public void setMinSup(int minSup) {
        this.minSup = minSup;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}
