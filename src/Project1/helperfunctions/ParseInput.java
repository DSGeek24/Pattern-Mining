package Project1.helperfunctions;

import Project1.domainobjects.InputDomain;

/**
 * 
 * @author deepika
 *
 */
public class ParseInput {

    public InputDomain parseInput(String[] args) {
        InputDomain domain = new InputDomain();
        if (args.length != 4) {
            System.err.println("Input arguments must be 4\n" +
                    "1. Minimum support\n" +
                    "2. k value\n" +
                    "3. input file path\n" +
                    "4. output file path\n");
        }
        if (args.length == 4) {
            int minSup = Integer.parseInt(args[0]);
            domain.setMinSup(minSup);

            int kValue = Integer.parseInt(args[1]);
            domain.setK(kValue);

            String inputFilePath = args[2];
            domain.setInputFilePath(inputFilePath);

            String outputFilePath = args[3];
            domain.setOutputFilePath(outputFilePath);
        }
        return domain;
    }
}
