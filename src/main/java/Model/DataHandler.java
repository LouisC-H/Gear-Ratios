package Model;

public class DataHandler {

    private Integer rollingSum = 0;

    private int rowCount;
    private int colCount;
    public char[][] dataMatrix;

    public DataHandler(int rowCount, int colCount){
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.dataMatrix = new char[rowCount][colCount];
    }
    
    public void populateMatrix(String charRow, int rowIndex){
        for (int i = 0; i < charRow.length(); i++) {
            dataMatrix[rowIndex][i] = charRow.charAt(i);
        }
    }

    public int getRollingSum() {
        return rollingSum;
    }

    public void increaseRollingSum(Integer increase) {
        this.rollingSum += increase;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }
}
