import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * DataWriter class: 
 * Reads a row data file, manupulates data, and write as a new file
 */
public class DataWriter {
    /**
     * main method
     * initialize dataWriter and read data from source
     * @param args[0] src file
     * @param args[1] destination file 
     * @param args[2] # of records in src file
     */
    public static void main(String[] args) {
        String src = "./SmallAreaIncomePovertyEstData.txt";
        String dst = "./SmallAreaIncomePovertyEstData_output.txt";
        int numRecords = 13486;

        if (args.length != 0) {
            src = "./" + args[0];
            dst = "./" + args[1];
            numRecords = Integer.parseInt(args[2].trim());
        }

        DataWriter dataWriter = new DataWriter();

        Row[] data = dataWriter.readData(numRecords, src);
        dataWriter.manupulateAndWriteData(dst, data);

    }

    /**
     * Row[] readData(int numRecords, String src) 
     * @param numRecords: int, number of records 
     * @param src: String, src file name with a path
     * @return Row[]
     */
    public Row[] readData(int numRecords, String src) {

        // init array of Row object with the number of records
        Row[] data = new Row[numRecords];

        // use FileReader to read a src file by character, 
        // and store into a char array representing a row object input
        try {
            FileReader inFile = new FileReader(src);
            int charDec;
            int charIndex = 0;
            int rowIndex = 0;
            char[] rowInput = new char[132];

            while ((charDec = inFile.read()) != -1) {
                rowInput[charIndex] = (char) charDec;
                charIndex++;
                if (charDec == 10) {
                    data[rowIndex] = new Row(rowInput);
                    rowIndex++;
                    charIndex = 0;
                }
            }
        } catch (Exception e) {
            System.out.println("readData Error: " + e);
        } finally {
            return data;
        }

    }

    /**
     * void manupulateAndWriteData(String dst, Row[] data)
     * aggregate row records by stateId, and write the output into a dst file
     * 
     * @param dst: dst file name with path
     * @param data: array of Row data
     */
    public void manupulateAndWriteData(String dst, Row[] data) {

        // Initialize BufferedWriter and aggregate pop, childPop, childPovPop, %ChildPovPop
        // Write into outFile with append.
        try {
            BufferedWriter outFile = new BufferedWriter(new FileWriter(dst));

            int stateId = 1;
            int population = 0;
            int childPopulation = 0;
            int childPovertyPopulation = 0;
            double percentageChildPovertyPopulation = 0;

            for (int i = 0; i < data.length; i++) {
                if (data[i].stateId == stateId) {
                    population += data[i].population;
                    childPopulation += data[i].childPopulation;
                    childPovertyPopulation += data[i].childPovertyPopulation;
                } else {
                    if (childPopulation == 0) {
                        percentageChildPovertyPopulation = 0;
                    } else {
                        percentageChildPovertyPopulation = (double) childPovertyPopulation / childPopulation * 100;
                    }

                    outFile.append(stateId + "\t" + population + "\t" + childPopulation + "\t" + childPovertyPopulation
                            + "\t" + percentageChildPovertyPopulation + "\n");

                    population = 0;
                    childPopulation = 0;
                    childPovertyPopulation = 0;
                    stateId++;
                    i--;

                }
            }
            if (childPopulation == 0) {
                percentageChildPovertyPopulation = 0;
            } else {
                percentageChildPovertyPopulation = (double) childPovertyPopulation / childPopulation * 100;
            }

            outFile.append(stateId + "\t" + population + "\t" + childPopulation + "\t" + childPovertyPopulation + "\t"
                    + percentageChildPovertyPopulation + "\n");

            outFile.close();

        } catch (

        Exception e) {
            System.out.println("manupulateData Error: " + e);
        }
    }

    /**
     * Row class: 
     * an object represents a records read from an input file
     * with stateId, population, childPop, childPovPop numbers are parsed
     */
    public class Row {
        private char[] content;
        private int stateId;
        private int population;
        private int childPopulation;
        private int childPovertyPopulation;

        Row(char[] input) {
            this.content = input;
            char[] stateIdChar = Arrays.copyOfRange(input, 0, 2);
            this.stateId = Integer.parseInt(new String(stateIdChar).trim());
            char[] populationChar = Arrays.copyOfRange(input, 82, 90);
            this.population = Integer.parseInt(new String(populationChar).trim());
            char[] childPopulationChar = Arrays.copyOfRange(input, 91, 99);
            this.childPopulation = Integer.parseInt(new String(childPopulationChar).trim());
            char[] childPovertyPopulationChar = Arrays.copyOfRange(input, 100, 108);
            this.childPovertyPopulation = Integer.parseInt(new String(childPovertyPopulationChar).trim());
        }
    }

}
