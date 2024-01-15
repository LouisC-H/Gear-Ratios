package Model;

import Utils.Part12Utils;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class GearAdjacency {

    private DataHandler dataHandler;

    // Stores index location of gear
    private int row;
    private int col;

    // Stores the row of numbers above, below, and containing the gear
    private char[] adjRowTop;
    private char[] adjRowMid;
    private char[] adjRowBtm;


    // Stores the number of adjacent numbers
    private int adjNumbers;

    // Stores final two numbers to be multiplied if the adjNumbers == 2
    ArrayList<Integer> numberPair = new ArrayList<>();

    // Stores whether those directions are valid (ie not off the edge of the matrix)
    private boolean topRowValid;
    private boolean btmRowValid;
    private boolean leftValid;
    private boolean rightValid;

    // Stores whether those squares are valid (ie not off the edge of the matrix)
    private boolean topLValid = true;
    private boolean topMValid = true;
    private boolean topRValid = true;
    private boolean btmLValid = true;
    private boolean btmMValid = true;
    private boolean btmRValid = true;
    private boolean RowLValid = true;
    private boolean RowRValid = true;

    // Stores whether there's a digit in those squares
    private boolean topLDigit = false;
    private boolean topMDigit = false;
    private boolean topRDigit = false;
    private boolean btmLDigit = false;
    private boolean btmMDigit = false;
    private boolean btmRDigit = false;

    // Stores number of numbers found in that direction, usually 0 or 1 but can be 2 for T/B
    private int topNumber = 0;
    private int btmNumber = 0;
    private int lNumber = 0;
    private int rNumber = 0;

    public GearAdjacency(DataHandler dataHandler, int i, int j) {

        this.dataHandler = dataHandler;

        this.row = i;
        this.col = j;

        initialiseValidity();
        propagateValidity();
        findGearRatio();
    }

    private void initialiseValidity(){
        this.topRowValid = row > 0;
        this.btmRowValid = row < dataHandler.getRowCount()-1;
        this.leftValid = col > 0;
        this.rightValid = col < dataHandler.getColCount()-1;
    }

    private void propagateValidity(){
        if (topRowValid){
            this.adjRowTop = importRows(row-1);
        } else {
            this.topLValid = this.topMValid = this.topRValid = false;
        }
        if (btmRowValid){
            this.adjRowBtm = importRows(row+1);
        } else {
            this.btmLValid = this.btmMValid = this.btmRValid = false;
        }
        this.adjRowMid = importRows(row);
        if (!leftValid){
            this.RowLValid = this.topLValid = this.btmLValid = false;
        }
        if (!rightValid){
            this.RowRValid = this.topRValid = this.btmRValid = false;
        }
    }

    private char[] importRows(int adjRow){
        return dataHandler.dataMatrix[adjRow];
    }

    public void findGearRatio() {
        findAdjNumbers();
        if (adjNumbers == 2){
            findNumberPair();
            dataHandler.increaseRollingSum(numberPair.get(0)*numberPair.get(1));
        }
    }

    private void findAdjNumbers() {
        // Left
        if (RowLValid){
            if (isDigit(dataHandler.dataMatrix[row][col-1])){
                lNumber++;
                this.adjNumbers ++;
            }
        }

        // Right
        if (RowRValid){
            if (isDigit(dataHandler.dataMatrix[row][col+1])){
                rNumber++;
                this.adjNumbers ++;
            }
        }

        // Top
        if (topLValid){
            if (isDigit(dataHandler.dataMatrix[row-1][col-1])){
                topLDigit = true;
            }
        }
        if (topMValid){
            if (isDigit(dataHandler.dataMatrix[row-1][col+0])){
                topMDigit = true;
            }
        }
        if (topRValid){
            if (isDigit(dataHandler.dataMatrix[row-1][col+1])){
                topRDigit = true;
            }
        }

        // there can only be two numbers on one condition: the left and right digits are numbers, but not the middle one
        if (topLDigit || topMDigit || topRDigit){
            topNumber++;
            if (topLDigit && !topMDigit && topRDigit){
                topNumber++;
            }
            this.adjNumbers+= topNumber;
        }

        // Bottom
        if (btmLValid){
            if (isDigit(dataHandler.dataMatrix[row+1][col-1])){
                btmLDigit = true;
            }
        }
        if (btmMValid){
            if (isDigit(dataHandler.dataMatrix[row+1][col+0])){
                btmMDigit = true;
            }
        }
        if (btmRValid){
            if (isDigit(dataHandler.dataMatrix[row+1][col+1])){
                btmRDigit = true;
            }
        }

        // There can only be two numbers on one condition: the left and right digits are numbers, but not the middle one
        if (btmLDigit || btmMDigit || btmRDigit){
            btmNumber++;
            if (btmLDigit && !btmMDigit && btmRDigit){
                btmNumber++;
            }
            this.adjNumbers += btmNumber;
        }
    }

    private void findNumberPair() {
        // Left
        if (lNumber == 1){
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowMid, col-1, true)));
        }
        
        // Right
        if (rNumber == 1){
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowMid, col+1, true)));
        }
        
        // Top
        if (topNumber == 2) {
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowTop, col-1, true)));
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowTop, col+1, true)));
        } else if (topNumber == 1){
            if (topLDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowTop, col-1, true)));
            } else if (topMDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowTop, col, true)));
            } else if (topRDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowTop, col+1, true)));
            }
        }
        
        // Bottom
        if (btmNumber == 2) {
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowBtm, col-1, true)));
            numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowBtm, col+1, true)));
        } else if (btmNumber == 1){
            if (btmLDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowBtm, col-1, true)));
            } else if (btmMDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowBtm, col, true)));
            } else if (btmRDigit){
                numberPair.add(Integer.parseInt(Part12Utils.numberFinder(dataHandler, adjRowBtm, col+1, true)));
            }
        }


    }
}
