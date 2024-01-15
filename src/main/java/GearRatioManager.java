import Model.DataHandler;
import Model.GearAdjacency;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class GearRatioManager {

    public void calculateGearRatios(DataHandler dataHandler) {
        for (int i = 0; i < dataHandler.getRowCount(); i++) {
            int j = 0;
            while (j < dataHandler.getColCount()) {
                if (dataHandler.dataMatrix[i][j] == '*'){
                    GearAdjacency gearAdjacency = new GearAdjacency(dataHandler, i, j);
                }
                j ++;
            }
        }
    }

    private int topBtmTest(ArrayList<Character> adjacentList){
        int nums = 0;
        if (adjacentList.size() == 3){
            //Annoying edge case we need to consider; only way to get more than one number from a single row
            if (isDigit(adjacentList.get(0)) && !isDigit(adjacentList.get(1)) && isDigit(adjacentList.get(2))){
                return 2;
            }
            else {
                for (Character c: adjacentList) {
                    if (isDigit(c)) {nums = 1;}
                }
            }
        } else {
            for (Character c: adjacentList) {
                if (isDigit(c)) {nums = 1;}
            }
        }
        return  nums;
    }

}
