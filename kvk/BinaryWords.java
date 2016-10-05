package algth;

/**
 * Created by Clappifieds on 10/3/2016.
 */

import java.math.BigInteger;
import java.util.*;
public class BinaryWords {
    private FastMatrixPowering fmPowering;
    private BigInteger[][] baseValues = {{BigInteger.valueOf(1)}, {BigInteger.valueOf(2)}, {BigInteger.valueOf(3)}};
    private BigInteger[][] baseMatrix = {
            {BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(0)},
            {BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(1)},
            {BigInteger.valueOf(1), BigInteger.valueOf(0), BigInteger.valueOf(1)}
    };

    public BinaryWords(){
        int matrixSize = 3;
        fmPowering = new FastMatrixPowering(matrixSize);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinaryWords binWords = new BinaryWords();
        while (true){
            System.out.print("Enter the number of bits: ");
            BigInteger n = input.nextBigInteger();
            System.out.printf("Number of words is: %d \n\n", binWords.GetNumberOfWords(n));
        }
    }

    private BigInteger GetNumberOfWords(BigInteger numberOfBits){
        if(numberOfBits == BigInteger.valueOf(0)){
            return BigInteger.valueOf(1);
        }else if(numberOfBits == BigInteger.valueOf(1)){
            return BigInteger.valueOf(2);
        }else if(numberOfBits == BigInteger.valueOf(2)){
            return BigInteger.valueOf(3);
        }else{
            BigInteger[][] resultMatrix = fmPowering.MultiplyTwoMatrices(fmPowering.PowerMatrix(baseMatrix, numberOfBits), baseValues);
            return resultMatrix[0][0];
        }
    }
}
