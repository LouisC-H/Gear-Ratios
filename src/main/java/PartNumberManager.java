import Model.DataHandler;
import Utils.Part12Utils;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class PartNumberManager {

    public void calculatePartNumbers(DataHandler dataHandler) {
        for (int i = 0; i < dataHandler.getRowCount(); i++) {
            int j = 0;
            while (j < dataHandler.getColCount()) {
                if (isDigit(dataHandler.dataMatrix[i][j])){
                    String number = Part12Utils.numberFinder(dataHandler, dataHandler.dataMatrix[i], j, true);
                    // analyseAdjacency returns that number of digits in the number so that the loop can skip those and not double count
                    j += analyseAdjacency(dataHandler, i, j, number);
                } else {
                    j++;
                }
            }
        }
    }

    private int analyseAdjacency(DataHandler dataHandler, int i, int j, String number) {
        ArrayList<Character> adjacentList = new ArrayList<>();
        int numLen = number.length();

        adjacentList = Part12Utils.returnIfValid(dataHandler, i-1, j-1, adjacentList);
        adjacentList = Part12Utils.returnIfValid(dataHandler, i+0, j-1, adjacentList);
        adjacentList = Part12Utils.returnIfValid(dataHandler, i+1, j-1, adjacentList);
        for (int digit = 0; digit < numLen; digit++) {
            adjacentList = Part12Utils.returnIfValid(dataHandler, i-1, j+digit, adjacentList);
            adjacentList = Part12Utils.returnIfValid(dataHandler, i+1, j+digit, adjacentList);
        }
        adjacentList = Part12Utils.returnIfValid(dataHandler, i-1, j+numLen, adjacentList);
        adjacentList = Part12Utils.returnIfValid(dataHandler, i+0, j+numLen, adjacentList);
        adjacentList = Part12Utils.returnIfValid(dataHandler, i+1, j+numLen, adjacentList);

        isAdjToSymbol(dataHandler,  i, j, number, adjacentList);

        return numLen;
    }

    private void isAdjToSymbol(DataHandler dataHandler, int i, int j, String number, ArrayList<Character> adjacentList){
        for (Character adjacentChar: adjacentList) {
            if (adjacentChar != '.' && !isDigit(adjacentChar)){
                dataHandler.increaseRollingSum(Integer.parseInt(number));
                break;
            }
        }
    }


}
