package algth;
/**
 * Created by KEO4ABT on 30.09.2016.
 */
import java.math.BigInteger;
import java.util.*;
public class FastMatrixPowering {


    private BigInteger[][] matrix;
    private int rows;

    public FastMatrixPowering(int rows){
        this.rows = rows;
        this.matrix = new BigInteger[rows][rows];

    }


    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Enter the number of rows/columns: ");
            int rows = input.nextInt();
            FastMatrixPowering fmPowering = new FastMatrixPowering(rows);
            System.out.println("Enter elements of the matrix:");
            fmPowering.GetElements();
            while(true){
                System.out.print("Enter the power:");
                BigInteger power = input.nextBigInteger();
                fmPowering.PrintResult(power);

                System.out.println("New power: P, New matrix: N");
                String cmd = input.next();

                if(cmd.equals("P")){
                    continue;
                }else if(cmd.equals("N")){
                    break;
                }else{
                    System.exit(0);
                }
            }
        }
    }

    private void GetElements(){
        int columns = this.rows;
        Scanner input = new Scanner(System.in);
        int k, i = 0;
        while (i < rows){
            k = 0;
            while (k < columns){
                BigInteger element = input.nextBigInteger();
                this.matrix[i][k] = element;
                PrintMatrix(this.matrix);
                k++;
            }
            System.out.println();
            i++;
        }
    }

    public BigInteger[][] PowerMatrix(BigInteger[][] matrix, BigInteger power){
        if(power == BigInteger.valueOf(0)){
            return  this.GetIdentityMatrix();
        }else if(power == BigInteger.valueOf(1) ){
            return matrix;
        }else{
            if(power.mod(BigInteger.valueOf(2)) == BigInteger.valueOf(0)){
                return PowerMatrix(SquareMatrix(matrix), power.divide(BigInteger.valueOf(2)));
            }else{
                return MultiplyTwoMatrices( PowerMatrix(SquareMatrix(matrix), (power.subtract(BigInteger.valueOf(1))).divide(BigInteger.valueOf(2))), matrix );
            }
        }
    }

    private void PrintResult(BigInteger power){
        BigInteger[][] result = PowerMatrix(this.matrix, power);
        PrintMatrix(this.matrix);
        PrintMatrix(result);
    }

    private BigInteger[][] GetIdentityMatrix(){
        BigInteger[][] identityMatrix = new BigInteger[this.rows][this.rows];
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.rows; column++) {
                if(row  == column){
                    identityMatrix[row][column] = BigInteger.valueOf(1);
                }else{
                    identityMatrix[row][column] = BigInteger.valueOf(0);
                }
            }
        }
        return identityMatrix;
    }

    public void PrintMatrix(BigInteger[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if(matrix[row][column] != null){
                    System.out.printf("%50d",  matrix[row][column]);
                }else{
                    System.out.printf("%50s",  "--");
                }
            }
            System.out.printf("\n\n");
        }
    }

    private BigInteger[][] SquareMatrix(BigInteger[][] matrix){
        return this.MultiplyTwoMatrices(matrix, matrix);
    }

    public BigInteger[][] MultiplyTwoMatrices(BigInteger[][] m1, BigInteger[][] m2) {
        int m1rows = m1.length;
        int m1cols = m1[0].length;
        int m2rows = m2.length;
        int m2cols = m2[0].length;
        BigInteger[][] result = new BigInteger[m1rows][m2cols];
        BigInteger partialProduct;
        BigInteger partialSum = new BigInteger("0");
        for (int i = 0; i < m1rows; i++) {
            for (int j = 0; j < m2cols; j++) {
                for (int k = 0; k < m1cols; k++) {
                    partialProduct = m1[i][k].multiply(m2[k][j]);
                    partialSum = partialSum.add(partialProduct);
                    result[i][j] = partialSum;
                }
                partialSum = new BigInteger("0");
            }
        }
        return result;
    }
}
