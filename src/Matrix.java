import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.abs;

public class Matrix {

    private int rowNumber;
    private int columnNumber;
    private Float[][] matrix;

    public Matrix(int n, int m) throws ArrayIndexOutOfBoundsException {
       if (n < 0 || m < 0){ throw new ArrayIndexOutOfBoundsException("Matrix size < 0!");}
        rowNumber = n;
        columnNumber = m;
        matrix = new Float [rowNumber][columnNumber];
        for (Float[] row : matrix)
            for (Float element: row)
                element = 0.f;
    }

    public Matrix (Matrix matrix2) throws IOException{
        rowNumber = matrix2.getMatrixSize().get(0);
        columnNumber = matrix2.getMatrixSize().get(1);
        Float[][] matrix = new Float[rowNumber][columnNumber];
        Float[][] fMatrix2 = matrix2.get();
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++)
                matrix[i][j] = fMatrix2[i][j];
    }


    public Matrix (){
        matrix = new Float [2][2];
        rowNumber = 2;
        columnNumber = 2;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0.f;
            }
        }
    }

   public Matrix (Float[][] matrix){
       this.rowNumber = matrix.length;
       this.columnNumber = (rowNumber == 0)?0:matrix[0].length;
       this.matrix = Arrays.copyOf(matrix, rowNumber);
   }

    public Float[][] get(){
        Float[][] safe_matrix = new Float[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++)
                safe_matrix[i][j] = matrix[i][j];
        return safe_matrix;
    }

    public void fillWithRandomValues(){
        Random randNumber = new Random();
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                matrix[i][j] = randNumber.nextFloat();
            }
        }
    }

    public List<Integer> getMatrixSize(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(rowNumber);
        list.add(columnNumber);
        return list;
    }

    public final void getMatrixInDecimalFormat(){
        final String pattern = "%";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        final String pattern2 = "¤";
        DecimalFormat myFormatter2 = new DecimalFormat(pattern2);
        final String pattern3 = "";
        DecimalFormat myFormatter3 = new DecimalFormat(pattern3);
        for (int i = 0; i < rowNumber; i++) {
            System.out.print("[");
            for (int j = 0; j < columnNumber; j++) {
                switch (i){
                    case(0):
                        String output = myFormatter.format(matrix[i][j] );
                        System.out.print(output + "\t");
                        break;
                    case(1):
                        String output2 = myFormatter2.format(matrix[i][j]);
                        System.out.print(output2 + "\t");
                        break;
                    default:
                        String output3 = myFormatter3.format(matrix[i][j]);
                        System.out.print(output3 + "\t");
                }
            }
            System.out.println("]");
        }
    }

    public final void out(){
        for (Float[] row: matrix){
            System.out.print("[ ");
            for (Float element: row){
                System.out.printf("%5f ",element);
            }
            System.out.println("]");
        }
    }

    public void minElementInTheLowerRightAngle() {
        // Searching for minimum element
        Float min = Float.MAX_VALUE;
        int minRowIndex = 0;
        int minColumnIndex = 0;
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minRowIndex = i;
                    minColumnIndex = j;
                }
    }
        System.out.println("Minimum element: " + min);
        System.out.println("Row index: " + minRowIndex);
        System.out.println("Column index: " + minRowIndex);

        // Swap lines and columns
        Float[] temp = matrix[rowNumber - 1];//[j];
        matrix[rowNumber - 1] = matrix[minRowIndex];//[j];
        matrix[minRowIndex] = temp;

        for (int k = 0; k < rowNumber; k++){
            Float tempElement = matrix[k][columnNumber - 1];
            matrix[k][columnNumber - 1] = matrix[k][minColumnIndex];
            matrix[k][minColumnIndex] = tempElement;
        }
    }

    public void rowCopy(int rowNumber, int from, int to){
       Float[] array = Arrays.copyOfRange(matrix[rowNumber], from, to);
       System.out.println(Arrays.toString(array));
    }

    public void partialSort(int from, int to) throws ArrayIndexOutOfBoundsException {
        if (to < 1 || to > rowNumber) throw new ArrayIndexOutOfBoundsException("'to' index out of Bounds!!!");
        if (from < 0 || from > rowNumber) throw new ArrayIndexOutOfBoundsException("'from' index out of bounds!!!");
        if (to < from) throw new ArrayIndexOutOfBoundsException("'to' index < 'from'  index!!!");
        Arrays.sort(matrix[0], from, to + 1, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return -o1.compareTo(o2);
            }
        });
    }

    public List<Integer> find(Float x){
        /**
         *Return a list containing row and column indexes of element*/
        Float[] foundElements = new Float[rowNumber];
        Integer[] foundColumnIndexes = new Integer[rowNumber];
        for (int rowIndex = 0; rowIndex < rowNumber; rowIndex++){
            int foundColIndex = Arrays.binarySearch(matrix[rowIndex], x);
            if (foundColIndex < 0)
                foundColIndex = abs(foundColIndex) - 2;
            foundColumnIndexes[rowIndex] = abs(foundColIndex);
            foundElements[rowIndex] = matrix[rowIndex][abs(foundColIndex)];
        }

        int foundRowIndex = Arrays.binarySearch(foundElements, x);
        if (foundRowIndex < 0)
            foundRowIndex = abs(foundRowIndex) - 2;
        List<Integer> indexesList = new ArrayList<Integer>();
        indexesList.add(abs(foundRowIndex));
        indexesList.add(foundColumnIndexes[abs(foundRowIndex)]);
        return indexesList;
    }

    public void sort(){
        // temporary matrix of size n^2
        Float temp[] = new Float[rowNumber * columnNumber];
        int k = 0;

        // copy the elements of matrix
        // one by one into temp[]
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++)
                temp[k++] = matrix[i][j];

        // sort temp[]
        Arrays.sort(temp);

        // copy the elements of temp[]
        // one by one in mat[][]
        k = 0;
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++)
                matrix[i][j] = temp[k++];
    }
}

