import Model.DataHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Main {

    public static void main(String[] args)
    {
        Integer rollingSum = 0;
        File inputFile = new File("src/main/resources/input.txt");

        DataHandler dataHandler = initialiseDataHandler(inputFile);
        populateDataHandler(inputFile, dataHandler);

//        PartNumberManager partNumberManager = new PartNumberManager();
//        partNumberManager.calculatePartNumbers(dataHandler);

        GearRatioManager gearRatioManager = new GearRatioManager();
        gearRatioManager.calculateGearRatios(dataHandler);

        System.out.println(dataHandler.getRollingSum());
    }

    private static DataHandler initialiseDataHandler(File inputFile){

        int rowCount = 0, charCount = 0;

        try {
            Scanner lineCounter = new Scanner(inputFile);

            charCount = lineCounter.nextLine().length();
            rowCount ++;

            while (lineCounter.hasNextLine()) {
                rowCount++;
                lineCounter.nextLine();
            }
            lineCounter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new DataHandler(rowCount, charCount);
    }

    private static void populateDataHandler(File inputFile, DataHandler dataHandler){
        try {
            Scanner myReader = new Scanner(inputFile);
            int rowCount = 0;

            while (myReader.hasNextLine()) {
                String charString = myReader.nextLine();
                dataHandler.populateMatrix(charString, rowCount);
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}


