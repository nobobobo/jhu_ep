import java.io.FileReader;

public class DataRenderer {
    public static void main(String[] args) {
        String src = "./SmallAreaIncomePovertyEstData_output.txt";
        int numRecords = 56;

        if (args.length != 0) {
            src = "./" + args[0];
            numRecords = Integer.parseInt(args[1].trim());
        }

        System.out.println("File: " + src);
        System.out.println("State\tPopulation\tChild Population\tChild Poverty Population\t% Child Poverty");
        System.out.println("-----\t----------\t----------------\t------------------------\t---------------");

        try {
            FileReader inFile = new FileReader(src);

            String[] arr = { "", "", "", "", "" };
            int arrIndex = 0;
            int charDec;

            while ((charDec = inFile.read()) != -1) {
                if (charDec == 9) {
                    arrIndex++;
                } else if (charDec == 10) {
                    if (Integer.parseInt(arr[1]) != 0) {
                        System.out.printf("%5s\t%10s\t%16s\t%24s\t%15.2f\n", arr[0], arr[1], arr[2], arr[3],
                                Float.parseFloat(arr[4]));
                    }
                    arrIndex = 0;
                    for (int i = 0; i < 5; i++) {
                        arr[i] = "";
                    }

                } else {
                    arr[arrIndex] += (char) charDec;
                }
            }
        } catch (Exception e) {
            System.out.println("Data Renderer Error: " + e);
        }

    }
}