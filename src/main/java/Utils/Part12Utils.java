package Utils;

import Model.DataHandler;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class Part12Utils {

    public static ArrayList<Character> returnIfValid(DataHandler dataHandler, int col, int row, ArrayList<Character> adjacentList){
        if(col >= 0 && row>= 0 && col < dataHandler.getRowCount() && row < dataHandler.getColCount()) {
            adjacentList.add(dataHandler.dataMatrix[col][row]);
        }
        return adjacentList;
    }

    public static String numberFinder(DataHandler dataHandler, char[] charList, int j, boolean allowReversing){
        // Reverses to the first digit in number
        if (j > 0 && allowReversing){
            if (isDigit(charList[j-1])){
                return numberFinder(dataHandler, charList, j-1, true);
            }
        }
        // Then goes down the row, adding to the arrayList
        String digits = String.valueOf(charList[j]);
        if (j+1 < dataHandler.getColCount()){
            if (isDigit(charList[j+1])){
                digits += numberFinder(dataHandler, charList, j+1, false);
            }
        }
        return digits;
    }
}
