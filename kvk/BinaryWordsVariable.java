package algth;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Clappifieds on 10/3/2016.
 */
public class BinaryWordsVariable {
    private FastMatrixPowering fmPowering;
    private int numberOfZeros;
    private BigInteger[][] baseValuesMatrix;
    private BigInteger[][] magicMatrix;

    public BinaryWordsVariable(int numberOfZeros){
        this.numberOfZeros = numberOfZeros;
        int matrixSize = numberOfZeros + 1;
        fmPowering = new FastMatrixPowering(matrixSize);
        baseValuesMatrix = this.GetBaseValuesMatrix();
        magicMatrix = this.GetMagicMatrix();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.print("Enter required number of zeros between ones: ");
            int numberOfZeros = input.nextInt();
            BinaryWordsVariable binWordsVr = new BinaryWordsVariable(numberOfZeros);
            while(true){
                System.out.print("Enter the number of bits: ");
                BigInteger numberOfBits = input.nextBigInteger();
                System.out.printf("Number of words is: %d \n\n", binWordsVr.GetNumberOfWords(numberOfBits));

                System.out.println("New bit length: L, New number of zeros: N");
                String cmd = input.next();

                if(cmd.equals("L")){
                    continue;
                }else if(cmd.equals("N")){
                    break;
                }else{
                    System.exit(0);
                }
            }


        }
    }

    private BigInteger GetNumberOfWords(BigInteger numberOfBits){
        int numberOfZeros = this.numberOfZeros;

        if(numberOfBits == BigInteger.valueOf(0) || numberOfZeros == 0){
            return BigInteger.valueOf(1);
        }else if(numberOfBits.intValue() <= numberOfZeros){
            return numberOfBits.add(BigInteger.valueOf(1));
        }else{
            BigInteger[][] resultMatrix = fmPowering.MultiplyTwoMatrices(fmPowering.PowerMatrix(this.magicMatrix, numberOfBits), this.baseValuesMatrix);
            return resultMatrix[0][0];
        }
    }

    private BigInteger[][] GetMagicMatrix(){
        int numberOfZeros = this.numberOfZeros;
        int matrixSize = numberOfZeros + 1;
        BigInteger[][] magicMatrix = new BigInteger[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < magicMatrix[row].length; column++) {
                if(column  == row + 1 || (row == magicMatrix.length - 1 && (column == 0 || column == magicMatrix[row].length - 1))){
                    magicMatrix[row][column] = BigInteger.valueOf(1);
                }else{
                    magicMatrix[row][column] = BigInteger.valueOf(0);
                }
            }
        }
        return magicMatrix;
    }

    private BigInteger[][] GetBaseValuesMatrix(){
        int numberOfZeros = this.numberOfZeros;
        int matrixSize = numberOfZeros + 1;
        BigInteger[][] baseValuesMatrix = new BigInteger[matrixSize][1];
        for (int row = 0; row < matrixSize; row++) {
            BigInteger numberOfWords = this.GetNumberOfWords(BigInteger.valueOf(row));
            baseValuesMatrix[row][0] = numberOfWords;
        }
        return baseValuesMatrix;
    }
}
