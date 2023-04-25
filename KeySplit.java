import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class KeySplit {
    static BigInteger[] X = new BigInteger[10];
    static BigInteger[] Y = new BigInteger[10];

    static void Secret_Sharing(String Key) throws FileNotFoundException {
        BigInteger S = new BigInteger(Key,16);
        int N=10;
        int K=4;
        BigInteger[] allX = new BigInteger[N];
        BigInteger[] allY = new BigInteger[N];
        BigInteger[] coeff = new BigInteger[K];
        coeff[0]=S;
        Random rnd = new Random();
        int max=10,min=1;
        for(int i=1;i<K;i++){
            coeff[i]=BigInteger.valueOf(rnd.nextInt(max - min + 1) + min);
        }
        for(int i=0;i<N;i++){
            allX[i] =BigInteger.valueOf(i+1);
            X[i]=BigInteger.valueOf(i+1);
            allY[i] = calculateY(allX[i],coeff);
            Y[i]=allY[i];
        }

//        String[] Xstring = new String[N];
//        String[] Ystring = new String[N];
//
//        for(int i=0;i<N;i++){
//            Xstring[i] = allX[i].toString();
//            Ystring[i] = allY[i].toString(16);  //BigInteger to Hex Conversion
//        }

//        System.out.println("Secret is divided into " + N +" parts : " );
//        for(int i=0;i<N;i++){
//            System.out.println(Xstring[i] + " " + Ystring[i]);
//        }

        for(int i=0;i<N;i++){

            try (PrintWriter writer = new PrintWriter(Encrypt_Window.ParentDirectory+"/Key" + (i+1) +".txt")) {
                writer.println(allX[i].toString() + " " + allY[i].toString());
            }
            catch(IOException e){
                System.out.println(e);
            }
        }

    }

    static BigInteger calculateY(BigInteger x,BigInteger[] coeff){
        BigInteger y= BigInteger.valueOf(0);
        BigInteger temp= BigInteger.valueOf(1);
        BigInteger result;
        for (BigInteger j : coeff) {
            result= j.multiply(temp);
            y=y.add(result);
            result = temp.multiply(x);
            temp=result;
        }
        return y;
    }

    static String Decode_Secret() throws IOException {
        int K = 4;
        String[] Keys = new String[K];

        //Reading Key Text Files
        Keys[0] = new String(Files.readAllBytes(Paths.get(Decrypt_Window.K1)));
        Keys[1] = new String(Files.readAllBytes(Paths.get(Decrypt_Window.K2)));
        Keys[2] = new String(Files.readAllBytes(Paths.get(Decrypt_Window.K3)));
        Keys[3] = new String(Files.readAllBytes(Paths.get(Decrypt_Window.K4)));



        BigInteger[] x = new BigInteger[K];
        BigInteger[] y = new BigInteger[K];

        String[] xString = new String[K];

        String[] yString = new String[K];
        int split;
        int end;
        for(int i=0;i<K;i++){
            split = firstSpace(Keys[i]);
            xString[i]=Keys[i].substring(0,split);
            end=Keys[i].length();
            yString[i]=Keys[i].substring((split+1),end-1);
            //System.out.println("Key : " + yString[i]);
        }

        //Hex to BigInteger
        String temp;
        for(int i=0;i<K;i++){
            x[i] = new BigInteger(xString[i]);
            temp = yString[i];
            y[i] = new BigInteger(temp);
        }

        BigDecimal secret=BigDecimal.valueOf(0);
        BigDecimal result;
        BigInteger mul;
        BigInteger num=BigInteger.valueOf(1);
        BigInteger den=BigInteger.valueOf(1);
        for(int i=0;i<K;i++){

            for(int j=0;j<K;j++){
                if(i!=j){
                    mul=num.multiply(x[j]);
                    num=mul;
                    //num=num*x[j];
                    mul=den.multiply(x[i].subtract(x[j]));
                    den=mul;
                    //den=den*(x[i]-x[j]);
                }
            }
            if(K%2==0){
                mul=num.multiply(BigInteger.valueOf(-1));
                num=mul;
                //num=num*(-1);
            }

            BigDecimal mul1;
            BigDecimal a = new BigDecimal(num);
            BigDecimal b = new BigDecimal(den);
            BigDecimal c = a.divide(b,5, BigDecimal.ROUND_HALF_UP);
            BigDecimal d = new BigDecimal(y[i]);
            mul1=c.multiply(d);
            result=mul1;

            secret=secret.add(result);
            num=BigInteger.valueOf(1);
            den=BigInteger.valueOf(1);
        }

        BigDecimal value = secret.setScale(0, BigDecimal.ROUND_HALF_UP);
        //System.out.println("Secret Message : " + value);
        BigInteger toHex = new BigInteger(value.toString());
        String KeyInHex = toHex.toString(16);
        //System.out.println("Key : " + KeyInHex);
        return KeyInHex;
    }

    static int firstSpace(String str){
        int pos = 1;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                pos=i;
                break;
            }
        }
        return pos;
    }

}

