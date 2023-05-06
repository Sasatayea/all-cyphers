
import java.awt.Color;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CS402Lab1 extends javax.swing.JFrame {

    String mode = "enc";

    public CS402Lab1() {
        initComponents();
    }

    public static String Caesar_encrypt(String p, String key) {
         String c = "";
        String k = p.toLowerCase();
        String s = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < k.length(); i++) {
            int index =  s.indexOf(k.charAt(i));
            if(s.contains(k.charAt(i)+"")){
            int x = (Integer.parseInt(key) + index )%26 ;
            c +=  s.charAt(x);
            }else{
                c+=p.charAt(i);
            }
        }
        return c.toUpperCase();
    }

    public static String Caesar_decrypt(String c, String key) {
        String s = "abcdefghijklmnopqrstuvwxyz";
        c = c.toLowerCase();
        String p = "";
        for (int i = 0; i < c.length(); i++) {
            int index2 = s.indexOf(c.charAt(i));
            if (s.contains(c.charAt(i) + "")) {
                int f = ((index2 - (Integer.parseInt(key))) % 26);
                if (f < 0) {
                    f += 26;
                }
                char a = s.charAt(f);
                p += a;
            } else {
                p+=c.charAt(i);
            }
        }
        p=p.toLowerCase();
        return p;
    }
    
    public static String Playfair_encrypt(String p, String key) {
        String c = "";
        
        PlayfairCipher2 opj = new PlayfairCipher2(p , key );
        
        return opj.oout;
    }
    
    public static String Playfair_decrypt(String c, String key) {
     int f =0;
       PlayfairCipher2 opj = new PlayfairCipher2(key , c ,f);
        
        return opj.rrr;
    }
    
    public static String Vigenère_encrypt(String text, String key) {
        String s = "abcdefghijklmnopqrstuvwxyz";
        String res = "";
        String k = text.toLowerCase();
        String key2 =Vigenère_generateKey(text ,key);
        for (int i = 0; i < text.length(); i++)
        {
            int index1 = s.indexOf(k.charAt(i));
            int index2 = s.indexOf(key2.charAt(i));
            int f = (index1+index2)%26;
            res+= s.charAt(f);
        }
        return res;
    }
    
    public static String Vigenère_decrypt(String text, String key) {
        String s = "abcdefghijklmnopqrstuvwxyz";

        String res = "";
        String k = text.toLowerCase();
        String key2 =Vigenère_generateKey(text ,key);
        for (int i = 0; i < text.length(); i++)
        {
            int index1 = s.indexOf(k.charAt(i));
            int index2 = s.indexOf(key2.charAt(i));
            int f = Math.abs(index1-index2 )%26;
            res+= s.charAt(f);
        }
        return res;
    }
    
    static String Vigenère_generateKey(String str, String key) {
        int x = str.length();
        for (int i = 0;; i++) {
            if (x == i) {
                i = 0;
            }
            if (key.length() == str.length()) {
                break;
            }
            key += (key.charAt(i));
        }
        return key;
    }
    
    public static  String alphabet = "";
    public static String Autokey_encrypt(String text, String key) {
        
        String newKey = key.concat(text);
        alphabet = newKey ;
        String s = "abcdefghijklmnopqrstuvwxyz";

         String res = "";
         String k = text.toLowerCase();
        for (int i = 0; i < text.length(); i++)
        {
            int index1 = s.indexOf(k.charAt(i));
            int index2 = s.indexOf(newKey.charAt(i));
            int f = (index1+index2)%26;
            res+= s.charAt(f);
        }
        return res;
    }
    
    public static String Autokey_decrypt(String text, String key) {
        String s = "abcdefghijklmnopqrstuvwxyz";
         String res = "";
         String k = text.toLowerCase();
        for (int i = 0; i < text.length(); i++)
        {
            int index1 = s.indexOf(k.charAt(i));
            int index2 = s.indexOf(alphabet.charAt(i));
            int f = Math.abs(index1-index2)%26;
            res+= s.charAt(f);
        }
        return res;
    }
    
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static String Monoalphabetic_encrypt(String plaintext,String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            int index = ALPHABET.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char substitution = key.charAt(index);
                if (Character.isUpperCase(c)) {
                    substitution = Character.toUpperCase(substitution);
                }
                ciphertext.append(substitution);
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
     
    public static String Monoalphabetic_decrypt(String ciphertext,String key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            int index = key.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char substitution = ALPHABET.charAt(index);
                if (Character.isUpperCase(c)) {
                    substitution = Character.toUpperCase(substitution);
                }
                plaintext.append(substitution);
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }
    
    public String ROW_encrypt(String plaintext, String k) {
        int[] permutation = new int[k.length()];
        for (int i = 0; i < k.length(); i++) {
            permutation[i] = Integer.parseInt(String.valueOf(k.charAt(i))) - 1;
            System.out.print(permutation[i] + " ");
        }
        System.out.println("");
        char[] keyChars = k.toCharArray();
        Arrays.sort(keyChars);
        for (int i = 0; i < k.length(); i++) {
            for (int j = 0; j < k.length(); j++) {
                if (keyChars[i] == k.charAt(j)) {
                    permutation[j] = i;
                    break;
                }
            }
        }
        int numColumns = k.length();
        int numRows = (int) Math.ceil((double) plaintext.length() / numColumns);
        int paddingLength = numColumns * numRows - plaintext.length();
        StringBuilder paddedPlaintext = new StringBuilder(plaintext);
        for (int i = 0; i < paddingLength; i++) {
            paddedPlaintext.append('X');
        }
        char[][] matrix = new char[numRows][numColumns];
        int charIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = paddedPlaintext.charAt(charIndex++);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        char[] ciphertextChars = new char[numRows * numColumns];
        int ciphertextIndex = 0;
        for (int j = 0; j < numColumns; j++) {
            int col = 0;
            for (int kkk = 0; kkk < permutation.length; kkk++) {
                col = permutation[j];
                if (j == permutation[kkk]) {
                    col = kkk;
                    for (int i = 0; i < numRows; i++) {
                        ciphertextChars[ciphertextIndex++] = matrix[i][col];

                    }
                }
            }

            System.out.print((col) + " ");
        }
        return new String(ciphertextChars);
    }

    public static String ROW_decrypt(String ciphertext, String key) {
        int[] keyOrder = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyOrder[i] = Character.getNumericValue(key.charAt(i));
        }

        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length());
        char[][] grid = new char[numRows][key.length()];
        int charIndex = 0;

        for (int i = 1; i <= key.length(); i++) {
            int col = indexOf(keyOrder, i);
            for (int row = 0; row < numRows; row++) {
                if (charIndex < ciphertext.length()) {
                    grid[row][col] = ciphertext.charAt(charIndex);
                    charIndex++;
                }
            }
        }

        StringBuilder plaintextBuilder = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length(); col++) {
                plaintextBuilder.append(grid[row][col]);
            }
        }

        return plaintextBuilder.toString().replaceAll("\\s+$", "");
    }

    private static int indexOf(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
    
    public static String RailFenceCipher_encrypt(String text, String key) {

        int key2 =Integer.parseInt(key) ;
        char[][] rail = new char[2][text.length()];
        for (int i = 0; i < key2; i++)
            Arrays.fill(rail[i], '\n');
 
        boolean dirDown = false;
        int row = 0, col = 0;
 
        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key2 - 1)
                dirDown = !dirDown;
 
            rail[row][col++] = text.charAt(i);
            if (dirDown)
                row++;
            else
                row--;
        }
 
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key2; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);
 
        return result.toString();
    }
     
    public static String RailFenceCipher_decrypt(String cipher, String key) {
        int key2 =Integer.parseInt(key) ;
        char[][] rail = new char[key2][cipher.length()];
        
 
        // filling the rail matrix to distinguish filled
        // spaces from blank ones
        for (int i = 0; i < key2; i++)
            Arrays.fill(rail[i], '\n');
 
        // to find the direction
        boolean dirDown = true;
 
        int row = 0, col = 0;
 
        // mark the places with '*'
        for (int i = 0; i < cipher.length(); i++) {
            // check the direction of flow
            if (row == 0)
                dirDown = true;
            if (row == key2 - 1)
                dirDown = false;
 
            // place the marker
            rail[row][col++] = '*';
 
            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }
 
        int index = 0;
        for (int i = 0; i < key2; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*'
                    && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);
 
        StringBuilder result = new StringBuilder();
 
        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                dirDown = true;
            if (row == key2 - 1)
                dirDown = false;
 
            // place the marker
            if (rail[row][col] != '*')
                result.append(rail[row][col++]);
 
            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }
        return result.toString();
    }
    
    // one time pad
    static String keyone="";
    public static String generateKey_one(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }

        return sb.toString();
    }

    public static String encrypt_one(String plaintext, String key) {
       keyone = generateKey_one(plaintext.length());
        byte[] plainBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = keyone.getBytes(StandardCharsets.UTF_8);
        byte[] cipherBytes = new byte[plainBytes.length];

        for (int i = 0; i < plainBytes.length; i++) {
            cipherBytes[i] = (byte) (plainBytes[i] ^ keyBytes[i]);
        }

        return new String(cipherBytes, StandardCharsets.UTF_8);
    }

    public static String decrypt_one(String ciphertext, String key) {
        byte[] cipherBytes = ciphertext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = keyone.getBytes(StandardCharsets.UTF_8);
        byte[] plainBytes = new byte[cipherBytes.length];

        for (int i = 0; i < cipherBytes.length; i++) {
            plainBytes[i] = (byte) (cipherBytes[i] ^ keyBytes[i]);
        }

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
    
        public static String encrypt_Vernam(String plaintext, String key) {
        String key2 = Vigenère_generateKey(plaintext,key);
        byte[] plainBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key2.getBytes(StandardCharsets.UTF_8);
        byte[] cipherBytes = new byte[plainBytes.length];

        for (int i = 0; i < plainBytes.length; i++) {
            cipherBytes[i] = (byte) (plainBytes[i] ^ keyBytes[i]);
        }

        return new String(cipherBytes, StandardCharsets.UTF_8);
    }
        
        public static String decrypt_Vernam(String ciphertext, String key) {
        
        String key2 = Vigenère_generateKey(ciphertext,key);
        byte[] cipherBytes = ciphertext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key2.getBytes(StandardCharsets.UTF_8);
        byte[] plainBytes = new byte[cipherBytes.length];

        for (int i = 0; i < cipherBytes.length; i++) {
            plainBytes[i] = (byte) (cipherBytes[i] ^ keyBytes[i]);
        }

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
        
        
        // hill 
    public static String Hill_encrypt(String p, String key) {
        key = key.toLowerCase();
        int[][] matrix = new int[2][2];
        int x = 0;
        for (int i = 0; i < 2; i++) {
            matrix[i][0] = key.charAt(x) - 'a';
            x++;
            matrix[i][1] = key.charAt(x) - 'a';
            x++;
        }
        String ans = "";
        int[][] mat2 = new int[2][1];
        for (int i = 0; i < p.length(); i += 2) {
            mat2[0][0] = p.charAt(i) - 'a';
            mat2[1][0] = p.charAt(i + 1) - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        if (p.length() % 2 != 0) {
            mat2[0][0] = p.charAt(p.length() - 1) - 'a';
            mat2[1][0] = 'x' - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        return ans;
    }

    public static String Hill_decrypt(String c, String key) {
        key = key.toLowerCase();
        int[][] matrix = new int[2][2];

        matrix[0][0] = key.charAt(0) - 'a';
        matrix[0][1] = (key.charAt(1) - 'a') * -1;
        matrix[1][0] = (key.charAt(2) - 'a') * -1;
        matrix[1][1] = (key.charAt(3) - 'a');
        int tmp = matrix[0][0];
        matrix[0][0] = matrix[1][1];
        matrix[1][1] = tmp;
        int determint = (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        determint = make_num_pos(determint);
        if (determint == 0) {
            return "ERROR . . . . . .!";
        }
        int inverse = Inverse_of_num(determint);
        if (inverse == -1) {
            return "ERROR . . . . . .!";
        }

        for (int i = 0; i < 2; i++) {
            matrix[i][0] = make_num_pos(matrix[i][0] * inverse) % 26;
            matrix[i][1] = make_num_pos(matrix[i][1] * inverse) % 26;
        }
        String ans = "";
        int[][] mat2 = new int[2][1];
        for (int i = 0; i < c.length(); i += 2) {
            mat2[0][0] = c.charAt(i) - 'a';
            mat2[1][0] = c.charAt(i + 1) - 'a';
            ans += multi(matrix, mat2, 2, 2, 2, 1);
        }
        if (ans.charAt(ans.length() - 1) == 'x') {
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

    public static String multi(int mat1[][], int mat2[][], int r1, int c1, int r2, int c2) {
        if (c1 != r2) {
            System.out.println("# of columns in Mat1 must = # of row in mat 2");
            return "";
        }
        int[][] rslt = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                rslt[i][j] = 0;
                for (int k = 0; k < r2; k++) {
                    rslt[i][j] += (mat1[i][k] * mat2[k][j]) % 26;
                    rslt[i][j] %= 26;
                }
            }
        }
        String ans = "";
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                ans += (char) (rslt[i][j] + 'a');
            }
        }
        return ans;
    }

    private static int Inverse_of_num(int val) {

        for (int i = 1; i < 26; i++) {
            if (((val * i) % 26) == 1)
                return i;
        }
        return -1;
    }

    private static int make_num_pos(int val) {
        while (val < 0) {
            val += 26;
        }
        return val;
    }
/*
    DES
00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001      
M = 0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111
*/
    public static String DES_encrypt(String text, String kkey) {
        String res = "";
        Scanner in = new Scanner(System.in);
        
        int [][]mat ={
          {57 ,49 ,41 ,33, 25, 17, 9},
          {1 ,58 ,50, 42, 34, 26, 18},
          {10, 2, 59, 51, 43, 35, 27},
          {19, 11, 3, 60, 52, 44, 36},
          {63, 55, 47, 39, 31, 23, 15},
          {7, 62, 54, 46 ,38 ,30, 22},
          {14, 6 ,61, 53, 45 ,37 ,29},
          {21, 13, 5 ,28, 20, 12, 4}};
          
        //kkey = "00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001";
        String FKey = kkey;
        String Key =FKey.replaceAll("\\s+","") ;
        
        String newKey = "";
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                newKey += Key.charAt(mat[i][j] -1) ;
            }
        }
        
        String C0 = newKey.substring(0,28);
        String D0 = newKey.substring(28,newKey.length());
        
        int [] arr = {0,1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2 ,2 ,1};
        
        String [] Keyy = new String [34];
        Keyy[0]=C0 ;  Keyy[1]=D0;
        
        for (int i = 2 , k=1; i < Keyy.length; i=i+2 ,k++) {
            Keyy[i]=Leftshifts(Keyy[i-2] ,arr[k]);   //Cn
            Keyy[i+1]=Leftshifts(Keyy[i-1] ,arr[k]); //Dn
        }
        int l = 0 ;

        int [][] Finalmat = {
            {14 ,17 ,11, 24, 1, 5},
            {3 ,28 ,15, 6, 21 ,10},
            {23 ,19, 12, 4 ,26, 8},
            {16, 7 ,27, 20, 13, 2},
            {41, 52, 31, 37, 47, 55},
            {30, 40, 51, 45, 33 ,48},
            {44 ,49, 39, 56, 34, 53},
            {46, 42, 50, 36, 29, 32} } ;

        int d = 16 ;
        int r = 1 ;
        int e = 2;
        int index = 0 ;
        String [] arrofkeys =new String [16];
        while(d>0){
            //System.out.println(Keyy[e]+Keyy[e+1]);
            String g =Keyy[e]+Keyy[e+1] ;
            String filalllKey = "";
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 6; j++) {
                    filalllKey += g.charAt(Finalmat[i][j] -1) ;
                }
            }
            arrofkeys[index] =filalllKey ;
            System.out.println("K"+r+" = "+filalllKey );
            d--; r++; e+=2 ; index++;
        }
        System.out.println("--------------------------------------");
        
        int [][] IP = {
            {58, 50, 42, 34 ,26 ,18, 10, 2},
            {60 ,52, 44, 36 ,28 ,20 ,12 ,4},
            {62, 54, 46, 38, 30, 22, 14, 6},
            {64, 56, 48, 40, 32, 24, 16, 8},
            {57, 49, 41, 33, 25, 17, 9, 1},
            {59, 51, 43, 35, 27, 19 ,11, 3},
            {61, 53, 45, 37, 29, 21, 13, 5},
            {63, 55, 47, 39, 31, 23, 15, 7}
        };
        
        //text = "0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111";
        String f_text = text;
        String last_text2 = f_text.replaceAll("\\s+","") ;
        
        String new_text = "";
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                new_text += last_text2.charAt(IP[i][j] -1) ;
            }
        }
        
        //System.out.println(new_text);
        
        String L0 = new_text.substring(0,32);
        String R0 = new_text.substring(32,new_text.length());
        
        //System.out.println(L0);
        //System.out.println(R0);
        
        int [][] BITSELECTION ={
        {32, 1, 2, 3, 4, 5},
        {4, 5, 6, 7, 8, 9},
        {8, 9, 10, 11, 12, 13},
        {12, 13, 14, 15, 16, 17},
        {16, 17, 18, 19, 20, 21},
        {20, 21, 22, 23, 24, 25},
        {24, 25, 26, 27 ,28 ,29},
        {28, 29, 30, 31 ,32 ,1}
    };
        int [][] S1 ={
            { 14, 4, 13, 1, 2, 15, 11 ,8 ,3 ,10,6 ,12 ,5, 9, 0,7},
            { 0, 15, 7 ,4, 14, 2, 13 ,1 ,10, 6, 12, 11, 9 ,5, 3,8},
            {4, 1, 14, 8, 13, 6 ,2 ,11, 15, 12, 9 ,7 ,3 ,10 ,5,0},
            { 15, 12 ,8 ,2 ,4 ,9, 1 ,7 ,5 ,11, 3 ,14, 10, 0, 6,13}    };
        int [][] S2 ={
            {15 ,1, 8, 14, 6, 11, 3 ,4 ,9, 7, 2, 13 ,12 ,0 ,5,10},
            {3, 13, 4 ,7, 15, 2 ,8 ,14, 12 ,0 ,1 ,10, 6 ,9 ,11,5},
            {0 ,14, 7 ,11, 10, 4 ,13, 1, 5, 8, 12 ,6, 9, 3 ,2,15},
            {13, 8, 10 ,1 ,3 ,15, 4 ,2 ,11, 6 ,7 ,12 ,0 ,5 ,14,9}     };
        
        int [][] S3 ={
            
            { 10, 0 ,9 ,14, 6 ,3 ,15 ,5 ,1, 13, 12, 7, 11 ,4, 2,8},
            { 13 ,7 ,0 ,9 ,3, 4, 6, 10, 2, 8 ,5 ,14, 12, 11, 15,1},
            { 13 ,6 ,4, 9 ,8, 15 ,3 ,0 ,11 ,1 ,2, 12 ,5 ,10, 14,7},
            { 1 ,10 ,13, 0 ,6 ,9, 8 ,7, 4 ,15 ,14 ,3, 11 ,5 ,2,12}
        };
         int [][] S4 ={
             { 7, 13, 14, 3, 0 ,6 ,9 ,10, 1 ,2 ,8, 5, 11, 12 ,4,15},
             {13 ,8 ,11, 5, 6 ,15, 0, 3, 4 ,7, 2 ,12, 1, 10, 14,9},
             {10 ,6, 9 ,0, 12, 11 ,7 ,13 ,15, 1, 3, 14,5 ,2, 8,4},
             {3 ,15 ,0, 6 ,10,1, 13, 8, 9, 4, 5, 11, 12 ,7 ,2,14}
         };
         
         int [][] S5 ={           
             {2 ,12 ,4 ,1, 7 ,10, 11 ,6, 8, 5, 3 ,15, 13, 0, 14,9},
             {14 ,11 ,2 ,12 ,4 ,7 ,13 ,1 ,5 ,0 ,15 ,10, 3 ,9, 8,6},
             {4, 2 ,1 ,11 ,10, 13 ,7, 8 ,15, 9, 12, 5 ,6 ,3 ,0,14},
             {11, 8 ,12, 7 ,1 ,14 ,2, 13, 6 ,15, 0 ,9, 10, 4, 5,3}
         };
         int [][] S6 ={           
             {12, 1, 10, 15 ,9 ,2 ,6 ,8 ,0 ,13 ,3 ,4 ,14, 7 ,5,11 },
             { 10, 15 ,4 ,2 ,7 ,12, 9, 5 ,6 ,1 ,13 ,14, 0 ,11 ,3,8},
             { 9, 14 ,15 ,5 ,2, 8 ,12 ,3, 7, 0 ,4, 10, 1, 13, 11,6},
             {4, 3 ,2, 12 ,9 ,5, 15, 10, 11 ,14 ,1 ,7, 6, 0 ,8,13 }
         };
         
         int [][] S7 ={           
             {4, 11 ,2 ,14, 15 ,0 ,8 ,13 ,3, 12, 9 ,7 ,5 ,10, 6,1},
             {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5 ,12, 2 ,15, 8,6},
             { 1, 4 ,11, 13, 12, 3 ,7 ,14, 10, 15, 6 ,8 ,0, 5, 9 ,2},
             { 6 ,11 ,13 ,8, 1 ,4 ,10, 7 ,9, 5, 0 ,15 ,14,2 ,3 ,12}
         };
         
         int [][] S8 ={           
             {13 ,2 ,8, 4, 6, 15 ,11, 1 ,10 ,9 ,3, 14, 5, 0, 12,7},
             {1, 15, 13, 8 ,10 ,3 ,7, 4, 12, 5, 6 ,11, 0 ,14, 9,2},
             { 7, 11 ,4 ,1, 9, 12, 14, 2 ,0 ,6 ,10, 13, 15 ,3, 5,8},
             { 2, 1, 14, 7 ,4 ,10 ,8, 13, 15, 12 ,9 ,0 ,3, 5, 6,11}
         };
         int [][] pi ={
             {16, 7, 20, 21},
             {29 ,12, 28, 17},
             {1, 15, 23, 26},
             {5, 18, 31, 10},
             {2, 8, 24, 14},
             {32, 27, 3 ,9},
             {19, 13, 30, 6},
             {22, 11, 4, 25} };
         int [][] ip_1 ={
             {40 ,8, 48 ,16 ,56, 24, 64, 32},
             {39 ,7 ,47, 15, 55, 23, 63, 31},
             {38 ,6 ,46, 14, 54, 22, 62, 30},
             {37, 5 ,45, 13, 53, 21, 61, 29},
             {36, 4 ,44 ,12 ,52, 20, 60, 28},
             {35, 3 ,43, 11, 51, 19, 59, 27},
             {34, 2 ,42, 10, 50, 18, 58, 26},
             {33, 1 ,41, 9, 49, 17, 57, 25},
         };
        
        String [] LR_arr = new String [34];
        LR_arr[0]=L0;
        LR_arr[1]=R0;
        // lr lr lr
        int kk = 0 ;
        for (int i = 2 ; i <LR_arr.length; i+=2 ) {
            LR_arr[i] = LR_arr[i-1];
            
            String new_Rn ="";
            for (int k = 0; k < 8; k++) {
            for (int j = 0; j < 6; j++) {
                new_Rn += LR_arr[i-1].charAt(BITSELECTION[k][j] -1) ;
            }
        }
            String xor_res1 = first_xor(new_Rn ,arrofkeys[kk]);
            //System.out.println("K1+E(R0)"+xor_res1);
            
            String okkkk = "";
            
                String rt1 = xor_res1.substring(0, 6);
                okkkk+=Sbox(rt1 ,S1);
            String rt2 = xor_res1.substring(6, 12);
                okkkk+=Sbox(rt2 ,S2);
            String rt3 = xor_res1.substring(12, 18);
                okkkk+=Sbox(rt3 ,S3);    
            String rt4 = xor_res1.substring(18, 24);
                okkkk+=Sbox(rt4 ,S4);
            String rt5 = xor_res1.substring(24, 30);
                okkkk+=Sbox(rt5 ,S5);
            String rt6 = xor_res1.substring(30, 36);
                okkkk+=Sbox(rt6 ,S6);    
             String rt7 = xor_res1.substring(36, 42);
                okkkk+=Sbox(rt7 ,S7);   
             String rt8 = xor_res1.substring(42, 48);
                okkkk+=Sbox(rt8 ,S8);
             //System.out.println("SBook out put ->  "+okkkk);
             
             String new_ ="";
            for (int k = 0; k < 8; k++) {
            for (int h = 0; h < 4; h++) {
                new_ += okkkk.charAt(pi[k][h] -1) ;
            }
        }
           // System.out.println("function"+kk+ "out = >"+new_);
            kk++;
            
            LR_arr[i+1] = first_xor(new_,LR_arr[i-2]);
           // System.out.println("New R"+kk+ " >>  " +LR_arr[i+1]);
        }
        // lr lr lr

        String LastLR = LR_arr[33] +LR_arr[32];
        System.out.println("R16L16 = "+LastLR);
        
        String u ="";
            for (int k = 0; k < 8; k++) {
            for (int j = 0; j < 8; j++) {
                u += LastLR.charAt(ip_1[k][j] -1) ;
            }
        }
            
            System.out.println("IP = "+u);
        return u;
    }
    public static String Sbox (String s1 , int [][] s){
        String out ="";
        String d = s1.substring(0 ,1)+ s1.charAt(5);
        String midell = s1.substring(1, 5);
        int decimal_row=Integer.parseInt(d,2);  
        int decimal_col=Integer.parseInt(midell,2);  
        
        //System.out.println("decimal_row =>"+decimal_row);
        //System.out.println("decimal_col =>"+decimal_col);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 16; j++) {
                String f = Integer.toBinaryString(s[i][j]);
                while(f.length()<4){
                    f=0+f;
                }
                    if(i==decimal_row &&j ==decimal_col)
                    out = f ;
                
            }
        }
        
        return out ;
    }
    public static String first_xor(String s1 ,String s2){
        String d ="";
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(i)){
                d+=0;
            }else{
                d+=1;
            }
        }
        return d ;
    }
    public static String Leftshifts (String s , int i){
        while(i>0){
            s = s.substring(1 ,s.length())+s.charAt(0);
            i--;
        }
        return s ;
    }
    public static String DES_decrypt(String text, String kkey) {
        String res = "abcdefghijklmnopqrstuvwxyz";
        int [][]mat ={
          {57 ,49 ,41 ,33, 25, 17, 9},
          {1 ,58 ,50, 42, 34, 26, 18},
          {10, 2, 59, 51, 43, 35, 27},
          {19, 11, 3, 60, 52, 44, 36},
          {63, 55, 47, 39, 31, 23, 15},
          {7, 62, 54, 46 ,38 ,30, 22},
          {14, 6 ,61, 53, 45 ,37 ,29},
          {21, 13, 5 ,28, 20, 12, 4}};
          
        //kkey = "00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001";
        String FKey = kkey;
        String Key =FKey.replaceAll("\\s+","") ;
        
        String newKey = "";
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                newKey += Key.charAt(mat[i][j] -1) ;
            }
        }
        
        String C0 = newKey.substring(0,28);
        String D0 = newKey.substring(28,newKey.length());
        
        int [] arr = {0,1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2 ,2 ,1};
        
        String [] Keyy = new String [34];
        Keyy[0]=C0 ;  Keyy[1]=D0;
        
        for (int i = 2 , k=1; i < Keyy.length; i=i+2 ,k++) {
            Keyy[i]=Leftshifts(Keyy[i-2] ,arr[k]);   //Cn
            Keyy[i+1]=Leftshifts(Keyy[i-1] ,arr[k]); //Dn
        }
        int l = 0 ;

        int [][] Finalmat = {
            {14 ,17 ,11, 24, 1, 5},
            {3 ,28 ,15, 6, 21 ,10},
            {23 ,19, 12, 4 ,26, 8},
            {16, 7 ,27, 20, 13, 2},
            {41, 52, 31, 37, 47, 55},
            {30, 40, 51, 45, 33 ,48},
            {44 ,49, 39, 56, 34, 53},
            {46, 42, 50, 36, 29, 32} } ;

        int d = 16 ;
        int r = 1 ;
        int e = 2;
        int index = 0 ;
        String [] arrofkeys =new String [16];
        while(d>0){
            //System.out.println(Keyy[e]+Keyy[e+1]);
            String g =Keyy[e]+Keyy[e+1] ;
            String filalllKey = "";
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 6; j++) {
                    filalllKey += g.charAt(Finalmat[i][j] -1) ;
                }
            }
            arrofkeys[index] =filalllKey ;
            System.out.println("K"+r+" = "+filalllKey );
            d--; r++; e+=2 ; index++;
        }
        System.out.println("--------------------------------------");
        
        int [][] IP = {
            {58, 50, 42, 34 ,26 ,18, 10, 2},
            {60 ,52, 44, 36 ,28 ,20 ,12 ,4},
            {62, 54, 46, 38, 30, 22, 14, 6},
            {64, 56, 48, 40, 32, 24, 16, 8},
            {57, 49, 41, 33, 25, 17, 9, 1},
            {59, 51, 43, 35, 27, 19 ,11, 3},
            {61, 53, 45, 37, 29, 21, 13, 5},
            {63, 55, 47, 39, 31, 23, 15, 7}
        };
        
        //text = "0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111";
        String f_text = text;
        String last_text2 = f_text.replaceAll("\\s+","") ;
        
        String new_text = "";
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                new_text += last_text2.charAt(IP[i][j] -1) ;
            }
        }
        //System.out.println(new_text);
        String L0 = new_text.substring(0,32);
        String R0 = new_text.substring(32,new_text.length());
        
        //System.out.println(L0);
        //System.out.println(R0);
        
        int [][] BITSELECTION ={
        {32, 1, 2, 3, 4, 5},
        {4, 5, 6, 7, 8, 9},
        {8, 9, 10, 11, 12, 13},
        {12, 13, 14, 15, 16, 17},
        {16, 17, 18, 19, 20, 21},
        {20, 21, 22, 23, 24, 25},
        {24, 25, 26, 27 ,28 ,29},
        {28, 29, 30, 31 ,32 ,1}
    };
        int [][] S1 ={
            { 14, 4, 13, 1, 2, 15, 11 ,8 ,3 ,10,6 ,12 ,5, 9, 0,7},
            { 0, 15, 7 ,4, 14, 2, 13 ,1 ,10, 6, 12, 11, 9 ,5, 3,8},
            {4, 1, 14, 8, 13, 6 ,2 ,11, 15, 12, 9 ,7 ,3 ,10 ,5,0},
            { 15, 12 ,8 ,2 ,4 ,9, 1 ,7 ,5 ,11, 3 ,14, 10, 0, 6,13}    };
        int [][] S2 ={
            {15 ,1, 8, 14, 6, 11, 3 ,4 ,9, 7, 2, 13 ,12 ,0 ,5,10},
            {3, 13, 4 ,7, 15, 2 ,8 ,14, 12 ,0 ,1 ,10, 6 ,9 ,11,5},
            {0 ,14, 7 ,11, 10, 4 ,13, 1, 5, 8, 12 ,6, 9, 3 ,2,15},
            {13, 8, 10 ,1 ,3 ,15, 4 ,2 ,11, 6 ,7 ,12 ,0 ,5 ,14,9}     };
        
        int [][] S3 ={
            
            { 10, 0 ,9 ,14, 6 ,3 ,15 ,5 ,1, 13, 12, 7, 11 ,4, 2,8},
            { 13 ,7 ,0 ,9 ,3, 4, 6, 10, 2, 8 ,5 ,14, 12, 11, 15,1},
            { 13 ,6 ,4, 9 ,8, 15 ,3 ,0 ,11 ,1 ,2, 12 ,5 ,10, 14,7},
            { 1 ,10 ,13, 0 ,6 ,9, 8 ,7, 4 ,15 ,14 ,3, 11 ,5 ,2,12}
        };
         int [][] S4 ={
             { 7, 13, 14, 3, 0 ,6 ,9 ,10, 1 ,2 ,8, 5, 11, 12 ,4,15},
             {13 ,8 ,11, 5, 6 ,15, 0, 3, 4 ,7, 2 ,12, 1, 10, 14,9},
             {10 ,6, 9 ,0, 12, 11 ,7 ,13 ,15, 1, 3, 14,5 ,2, 8,4},
             {3 ,15 ,0, 6 ,10,1, 13, 8, 9, 4, 5, 11, 12 ,7 ,2,14}
         };
         
         int [][] S5 ={           
             {2 ,12 ,4 ,1, 7 ,10, 11 ,6, 8, 5, 3 ,15, 13, 0, 14,9},
             {14 ,11 ,2 ,12 ,4 ,7 ,13 ,1 ,5 ,0 ,15 ,10, 3 ,9, 8,6},
             {4, 2 ,1 ,11 ,10, 13 ,7, 8 ,15, 9, 12, 5 ,6 ,3 ,0,14},
             {11, 8 ,12, 7 ,1 ,14 ,2, 13, 6 ,15, 0 ,9, 10, 4, 5,3}
         };
         int [][] S6 ={           
             {12, 1, 10, 15 ,9 ,2 ,6 ,8 ,0 ,13 ,3 ,4 ,14, 7 ,5,11 },
             { 10, 15 ,4 ,2 ,7 ,12, 9, 5 ,6 ,1 ,13 ,14, 0 ,11 ,3,8},
             { 9, 14 ,15 ,5 ,2, 8 ,12 ,3, 7, 0 ,4, 10, 1, 13, 11,6},
             {4, 3 ,2, 12 ,9 ,5, 15, 10, 11 ,14 ,1 ,7, 6, 0 ,8,13 }
         };
         
         int [][] S7 ={           
             {4, 11 ,2 ,14, 15 ,0 ,8 ,13 ,3, 12, 9 ,7 ,5 ,10, 6,1},
             {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5 ,12, 2 ,15, 8,6},
             { 1, 4 ,11, 13, 12, 3 ,7 ,14, 10, 15, 6 ,8 ,0, 5, 9 ,2},
             { 6 ,11 ,13 ,8, 1 ,4 ,10, 7 ,9, 5, 0 ,15 ,14,2 ,3 ,12}
         };
         
         int [][] S8 ={           
             {13 ,2 ,8, 4, 6, 15 ,11, 1 ,10 ,9 ,3, 14, 5, 0, 12,7},
             {1, 15, 13, 8 ,10 ,3 ,7, 4, 12, 5, 6 ,11, 0 ,14, 9,2},
             { 7, 11 ,4 ,1, 9, 12, 14, 2 ,0 ,6 ,10, 13, 15 ,3, 5,8},
             { 2, 1, 14, 7 ,4 ,10 ,8, 13, 15, 12 ,9 ,0 ,3, 5, 6,11}
         };
         int [][] pi ={
             {16, 7, 20, 21},
             {29 ,12, 28, 17},
             {1, 15, 23, 26},
             {5, 18, 31, 10},
             {2, 8, 24, 14},
             {32, 27, 3 ,9},
             {19, 13, 30, 6},
             {22, 11, 4, 25} };
         int [][] ip_1 ={
             {40 ,8, 48 ,16 ,56, 24, 64, 32},
             {39 ,7 ,47, 15, 55, 23, 63, 31},
             {38 ,6 ,46, 14, 54, 22, 62, 30},
             {37, 5 ,45, 13, 53, 21, 61, 29},
             {36, 4 ,44 ,12 ,52, 20, 60, 28},
             {35, 3 ,43, 11, 51, 19, 59, 27},
             {34, 2 ,42, 10, 50, 18, 58, 26},
             {33, 1 ,41, 9, 49, 17, 57, 25},
         };
        
        String [] LR_arr = new String [34];
        LR_arr[0]=L0;
        LR_arr[1]=R0;
        // lr lr lr
        int kk = 15 ;
        for (int i = 2 ; i <LR_arr.length; i+=2 ) {
            LR_arr[i] = LR_arr[i-1];
            
            String new_Rn ="";
            for (int k = 0; k < 8; k++) {
            for (int j = 0; j < 6; j++) {
                new_Rn += LR_arr[i-1].charAt(BITSELECTION[k][j] -1) ;
            }
        }
            String xor_res1 = first_xor(new_Rn ,arrofkeys[kk]);
            //System.out.println("K1+E(R0)"+xor_res1);
            
            String okkkk = "";
            
                String rt1 = xor_res1.substring(0, 6);
                okkkk+=Sbox(rt1 ,S1);
            String rt2 = xor_res1.substring(6, 12);
                okkkk+=Sbox(rt2 ,S2);
            String rt3 = xor_res1.substring(12, 18);
                okkkk+=Sbox(rt3 ,S3);    
            String rt4 = xor_res1.substring(18, 24);
                okkkk+=Sbox(rt4 ,S4);
            String rt5 = xor_res1.substring(24, 30);
                okkkk+=Sbox(rt5 ,S5);
            String rt6 = xor_res1.substring(30, 36);
                okkkk+=Sbox(rt6 ,S6);    
             String rt7 = xor_res1.substring(36, 42);
                okkkk+=Sbox(rt7 ,S7);   
             String rt8 = xor_res1.substring(42, 48);
                okkkk+=Sbox(rt8 ,S8);
             //System.out.println("SBook out put ->  "+okkkk);
             
             String new_ ="";
            for (int k = 0; k < 8; k++) {
            for (int h = 0; h < 4; h++) {
                new_ += okkkk.charAt(pi[k][h] -1) ;
            }
        }
           // System.out.println("function"+kk+ "out = >"+new_);
            kk--;
            
            LR_arr[i+1] = first_xor(new_,LR_arr[i-2]);
           // System.out.println("New R"+kk+ " >>  " +LR_arr[i+1]);
        }
        // lr lr lr

        String LastLR = LR_arr[33] +LR_arr[32];
        System.out.println("R16L16 = "+LastLR);
        
        String u ="";
            for (int k = 0; k < 8; k++) {
            for (int j = 0; j < 8; j++) {
                u += LastLR.charAt(ip_1[k][j] -1) ;
            }
        }
            
            System.out.println("IP = "+u);
        
        
        return u;
    }

    // RSA
    public static String RSA_encrypt(String m, String key) {
        String ccc = "";
        boolean c = false;
        long p = 0;
        while (c != true) {
            p = (long) ((Math.random() * 100000000) + 500000);
            long kk = (long) Math.log(p);
            boolean prime2 = isPrime(p, (int) kk);
            if (prime2) {
                System.out.println("p = " + p);
                break;
            }
        }
        boolean cc = false;
        long Q = 0;
        while (cc != true) {
            Q = (long) ((Math.random() * 100000000) + 500000);
            long kk = (long) Math.log(Q);
            boolean prime2 = isPrime(Q, (int) kk);
            if (prime2) {
                System.out.println("q = " + Q);
                break;
            }
        }
        BigInteger N = BigInteger.valueOf(p * Q);
        System.out.println("n = " + N);
        BigInteger Phi = BigInteger.valueOf((p - 1) * (Q - 1));
        System.out.println("Phi = " + Phi);
        boolean flag = false;
        long E = 0;

        while (cc != true) {
            E = (long) ((Math.random() * 100000000) + 30000);
            long kk = (long) Math.log(E);

            boolean prime2 = isPrime(E, (int) kk);
            BigInteger sa = BigInteger.valueOf(E);
            //BigInteger eee =BigInteger.valueOf(sa).compareTo(Phi);
            if (prime2 && E != Q && E != p) {
                System.out.println("e = " + E);
                break;
            }
        }
        BigInteger d = BigInteger.valueOf(E).modInverse(Phi);
        System.out.println("d = " + d);
        int[] arr = new int[m.length()];

        for (int i = 0; i < m.length(); i++) {
            int ascii = (int) m.charAt(i);
            String ss = String.valueOf(ascii);
            while (ss.length() < 3) {
                ss = "0" + ss;
            }
            int newascii = Integer.parseInt(ss);
            arr[i] = newascii;
//            AAA += ss;
            System.out.println("Ascii of " + m.charAt(i) + " = " + ss);
        }
        //enc
        BigInteger[] array = new BigInteger[m.length()];

        for (int i = 0; i < arr.length; i++) {
            BigInteger EE = BigInteger.valueOf(E);

            BigInteger ci = BigInteger.valueOf(arr[i]).modPow(EE, N);
            array[i] = ci;
            ccc = ccc + ci + " ";
            System.out.println("C " + i + " = " + ci);
        }
        //dec
        BigInteger[] arri = new BigInteger[m.length()];
        for (int i = 0; i < arr.length; i++) {
//            BigInteger mas = array[i].pow(d.intValue()).mod(N);
            BigInteger mas = array[i].modPow(d, N);
            arri[i] = mas;
            System.out.println("M " + i + " = " + mas + " = " + m.charAt(i));
        }

        return ccc;
    }
    public static boolean isPrime(long n, int iteration) {
        if (n == 0 || n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        long s = n - 1;
        while (s % 2 == 0) {
            s /= 2;
        }

        Random rand = new Random();
        for (int i = 0; i < iteration; i++) {
            long r = Math.abs(rand.nextLong());
            long a = r % (n - 1) + 1, temp = s;
            long mod = modPow(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1) {
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0) {
//                System.out.println(" is composite");
                return false;
            }
        }
//        System.out.println(" May be prime");
        return true;
    }
    public static long modPow(long a, long b, long c) {
        long res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }
    public static long mulMod(long a, long b, long mod) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }
    // millar test
    
    public static boolean Test(BigInteger q, BigInteger n) {
        BigInteger maxLimit = new BigInteger(n.subtract(BigInteger.valueOf(4))+"");
        BigInteger minLimit = new BigInteger("2"); 
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        
        BigInteger a = new BigInteger(len, randNum);
        if (a.compareTo(minLimit) < 0)
         a = a.add(minLimit);
      if (a.compareTo(bigInteger) >= 0)
         a = a.mod(bigInteger).add(minLimit);
     
        // Compute a^d % n
        BigInteger x;
        
        x = a.modPow(q, n);
            
        if ((x.equals(BigInteger.ONE)) || x.equals(n.subtract(BigInteger.ONE)))
            
                return true;
    
        // Keep squaring x if q is still not equal to (n-1) or x not euqal to 1 or n-1
 
        while (!n.subtract(BigInteger.ONE).equals(new BigInteger(q+""))) {
           
            x.equals(x.multiply(x).mod(n));
      
            q.equals(q.multiply(BigInteger.valueOf(2)));
 
            //if 1 then composite 
            if (x.equals(BigInteger.ONE))
                return false;
            if (x .equals(n.subtract(BigInteger.ONE)))
                return true;
        }
     
        // Return composite
        return false;
    }
    
    public static boolean isPrime(BigInteger n, int t){
        if (n.equals(2))
            return true;
        if (n.equals(0) || n.equals(1))
            return false;
       //if even then false
        if (n.mod(BigInteger.valueOf(2)).equals(0))
            return false;
      /*Find an odd number o such that n-1 can be written as o*(2^r). 
         Note that since n is odd, (n-1) must be even and r must be 
         greater than 0*/
        BigInteger o = new BigInteger(n.subtract(BigInteger.ONE)+"");
        while (o.mod(BigInteger.valueOf(2)).equals(0)){
            o = o.divide(BigInteger.valueOf(2));
        }
 
          for (int i = 0; i < t; i++)
              if (!Test(o, n))
                return false;
     
        return true;
          
    }
    
    public static String MRT( String p ,String Key){
        
        int sss = Integer.parseInt(Key);
        BigInteger re = new BigInteger(p);
        if (isPrime(re , sss))
           return " is Prime";
        else
            return " is Composite";

    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Cipher");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Time");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Enter Text");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Import from File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setText("Encrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 51));
        jButton3.setText("Decrypt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Result");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 51));
        jButton4.setText("Export to File");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Generate and Plot");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Key");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caesar", "Monoalphabetic", "RailFenceCipher", "RowTranspositionCiphers", "Playfair", "Vigenère", "Autokey", "DES", "RSA", "OneTimePad", "Vernam", "Hill", "MRT" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField1)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addGap(101, 101, 101)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 // encrypt Button
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String cellect = (String) (jComboBox1.getSelectedItem());
        //System.out.println(cellect);
        
        String p = jTextArea2.getText();
        String key = jTextField1.getText();
        String c = "" ;
        
        long start = System.currentTimeMillis();
        
        if(cellect.equals("Caesar")){
             c = Caesar_encrypt(p, key);
             
        }else if(cellect.equals("RowTranspositionCiphers")){
             c = ROW_encrypt(p, key);
        }else if(cellect.equals("RailFenceCipher")){
             c = RailFenceCipher_encrypt(p, key);
        }else if(cellect.equals("Playfair")){
             c = Playfair_encrypt(p, key);
        }else if(cellect.equals("Vigenère")){
             c = Vigenère_encrypt(p, key);
        }else if(cellect.equals("Monoalphabetic")){
             c = Monoalphabetic_encrypt(p, key);
        }else if(cellect.equals("Autokey")){
             c = Autokey_encrypt(p, key);
        }else if(cellect.equals("DES")){
             c = DES_encrypt(p, key);
        }else if(cellect.equals("RSA")){
             c = RSA_encrypt(p, key);
        }else if(cellect.equals("OneTimePad")){
             c = encrypt_one(p, key); 
        }else if(cellect.equals("Vernam")){
             c = encrypt_Vernam(p, key); 
        }else if(cellect.equals("Hill")){
             c = Hill_encrypt(p, key); 
        }else if(cellect.equals("MRT")){
             c = MRT(p, key); 
        }
        
        long end = System.currentTimeMillis();

        jTextArea1.setText(c);
        jLabel5.setText((end - start) + " ms");

        mode = "enc";

    }//GEN-LAST:event_jButton2ActionPerformed
// decrypt Button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String cellect = (String) (jComboBox1.getSelectedItem());

        String c = jTextArea2.getText();
        String key = jTextField1.getText();

        long start = System.currentTimeMillis();
        
        String cc ="";
        
        if(cellect.equals("Caesar")){
             cc = Caesar_decrypt(c, key);
        }else if(cellect.equals("RowTranspositionCiphers")){
             cc = ROW_decrypt(c, key);
        }else if(cellect.equals("RailFenceCipher")){
            cc = RailFenceCipher_decrypt(c, key);
        }else if(cellect.equals("Playfair")){
            cc = Playfair_decrypt(c, key);
        }else if(cellect.equals("Vigenère")){
            cc = Vigenère_decrypt(c, key);
        }else if(cellect.equals("Monoalphabetic")){
            cc = Monoalphabetic_decrypt(c, key);
        }else if(cellect.equals("Autokey")){  
            cc = Autokey_decrypt(c, key);
        }else if(cellect.equals("DES")){  
            cc = DES_decrypt(c, key);
        }else if(cellect.equals("RSA")){  
            cc = "no decrypt ";
        }else if(cellect.equals("OneTimePad")){  
           cc = decrypt_one(c, key); 
        }else if(cellect.equals("Vernam")){  
           cc = decrypt_Vernam(c, key); 
        }else if(cellect.equals("Hill")){  
           cc = Hill_decrypt(c, key);  
        }
        
        long end = System.currentTimeMillis();

        jTextArea1.setText(cc);
        jLabel5.setText((end - start) + " ms");

        mode = "dec";

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String s = "";
        try {
            JFileChooser jFileChooser1 = new JFileChooser();
            int choice = jFileChooser1.showOpenDialog(this);
            if (choice == JFileChooser.APPROVE_OPTION) {
                java.io.File file = jFileChooser1.getSelectedFile();
                java.util.Scanner scan = new java.util.Scanner(file);
               


                while (scan.hasNext()) {
                    s += scan.nextLine() + "\n";
                }

                jTextArea2.setText(s.substring(0, s.length() - 1));

                scan.close();

            }


        } catch (Exception e) {
        }
        System.out.println(s);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {

            if (mode.equals("enc")) {
                FileWriter fileWriter = new FileWriter("EncRes.txt", false); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(jTextArea1.getText());
                printWriter.close();
                
            } else {
                FileWriter fileWriter = new FileWriter("DecRes.txt", false); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(jTextArea1.getText());
                printWriter.close();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        XYSeries series = new XYSeries("XYGraph");

        for (int i = 1; i <= 50; i++) {
            int size = i * 10;
            String p = "";
            for (int j = 0; j < size; j++) {
                p += (char) (47 + (int) (Math.random() * 80));
            }

            long start = 0, end = 0;

            start = System.currentTimeMillis();
            //encrypt(p, "123");
            end = System.currentTimeMillis();

            long time = end - start;
            series.add(size, time);
        }

        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Encryption", // Title
                "Size", // x-axis Label
                "Time", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        JFrame plotFrame = new JFrame("Plot");
        plotFrame.setContentPane(chartPanel);
        plotFrame.setSize(500, 500);
        plotFrame.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        final CS402Lab1 myForm = new CS402Lab1();
        ImageIcon img = new ImageIcon("Icon.png");
        myForm.setIconImage(img.getImage());
        myForm.getContentPane().setBackground(new Color(255, 255, 255));
        myForm.setLocationRelativeTo(null);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                myForm.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
