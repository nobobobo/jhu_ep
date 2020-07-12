import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab2 {
    /**
     * main method, read the input file, print the matrix, then compute and print determinant of each matrices
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File("./Lab2RequiredInput.txt");
        Scanner sc = new Scanner(text);

        int matCnt = 0;
        while (sc.hasNextLine()){
            matCnt++;
            int order = sc.nextInt();
            sc.nextLine();
            int[][] mat = new int[order][order];
            System.out.println("Matrix "+ matCnt+ ": ");
            for (int i =0; i< order; i++){
                for (int j =0; j<order; j++){
                    mat[i][j] = sc.nextInt();
                    System.out.print(mat[i][j]+ " ");
                }
                System.out.println();
                sc.nextLine();
            }

            System.out.println("det(mat): " +calcDeterminant(mat));
            System.out.println();
        }


        // int[][] mat = { { 2, 4, 5, 6 }, { 0, 3, 6,9 }, { 0,0, 9, 8 },{0,0,0,5} };

        // System.out.println(calcDeterminant(mat));

    }

    /**
     * calcDeterminant(int[][] mat)
     * 
     * recursive function that take a 2-d matrix as an input, for each element on the first row, create a sub matrix and pass it as an argument to self. 
     * The base case is mat.length == 1, which means only one element in the input matrix and returns that element as a determinant 1x1 matrix.
     * @param mat
     * @return
     */
    public static int calcDeterminant(int[][] mat) {
        if (mat.length == 1)
            return mat[0][0];

        int order = mat.length;
        int sign = 1;
        int det = 0;
        for (int i = 0; i < order; i++) {

            int[][] newMat = new int[order - 1][order - 1];
            for (int j = 0; j < order - 1; j++) {
                int newMatX = 0;
                for (int k = 0; k < order; k++) {
                    if (k == i) 
                        continue;
                    newMat[j][newMatX] = mat[j + 1][k];
                    newMatX++;
                }

            }
            det += (sign * mat[0][i] * calcDeterminant(newMat));
            sign *= -1;
        }
        return det;
    }

}