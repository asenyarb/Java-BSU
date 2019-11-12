import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a line:");
        String line = scan.nextLine();
        System.out.println("Enter delimiters:");
        String delimiters = scan.nextLine();
        System.out.println("Enter a number to find in first line:");
        Integer P = scan.nextInt();
        StringTokenizer st = new StringTokenizer(line, delimiters);

        // Find all the decimals and find P
        Integer [] decimalsTempArray = new Integer[st.countTokens()];
        String [] nonDecimalsArray = new String[st.countTokens()];
        int decimalIndex = 0;
        int nonDecimalIndex = 0;
        int mainIndex = -1;
        Integer pIndex = null;
        Integer decimal;
        while (st.hasMoreTokens()){
            mainIndex++;
            var token = st.nextToken();
            try {
                decimal = Integer.parseInt(token, 10);
            }
            catch(NumberFormatException ex){
                // For non-decimal values
                boolean match = true;
                char firstchar = token.charAt(0);
                for (var ch: token.toCharArray())
                    if (ch != firstchar) {
                        match = false;
                        break;
                    }
                if (match) {
                    nonDecimalsArray[nonDecimalIndex] = token;
                    nonDecimalIndex++;
                }
                continue;
            }
            // For P index
            boolean eq;
            try {
                eq = decimal.equals(P);
            }
            catch(NullPointerException e){
                eq = false;
            }
            if (eq) {
                pIndex = mainIndex + 1;
            }
            // For decimal array
            decimalsTempArray[decimalIndex] = decimal;
            decimalIndex++;
        }

        Integer[] decimalArray = Arrays.copyOf(decimalsTempArray, decimalIndex);
        String[] nonDecimals = Arrays.copyOf(nonDecimalsArray, nonDecimalIndex);

        // Display decimals array
        System.out.println("\nDecimals in the first line:");
        for (Integer dec: decimalArray){
            System.out.print(dec + " ");
        }
        System.out.println("\n");

        // Display the P value
        System.out.print("P-token index = ");
        System.out.println((pIndex==null)?"P isn't found":pIndex);

        // Display non-decimals with same characters array
        System.out.println("\nNon-decimals in first line with same characters:");
        for (String str: nonDecimals){
            System.out.print(str + " ");
        }
        System.out.println();

        StringBuilder strb = new StringBuilder(line);
        // Duplicate P value
        if (decimalArray.length == 0){
            System.out.println("There are no decimals in line. Nothing to duplicate");
        }
        else {
            Integer decimalToDuplicate = decimalArray[0];
            int duplicateDecimalIndexInLine = line.indexOf(decimalToDuplicate.toString()) + decimalToDuplicate.toString().length();
            System.out.println("\nDuplicating number " + decimalToDuplicate + " at the position " + duplicateDecimalIndexInLine);
            strb.insert(duplicateDecimalIndexInLine, delimiters.charAt(0) + decimalToDuplicate.toString());
            line = strb.toString();
            System.out.println("\nNew line after decimal duplicating: " + line);
        }

        // Delete all substrings consisting of punctuation characters
        System.out.println("\nDeleting punctuation characters...");
        String punctuationCharacters = ",;.";
        for (Character ch: punctuationCharacters.toCharArray()){
            if (delimiters.indexOf(ch) != -1){
                System.out.println("Punctuation character " + ch + " belongs to delimiters string.");
                System.out.println("Substrings with this character won't be deleted");
            }
            else
            {
                int charIndex = strb.toString().indexOf(ch);
                while (charIndex != -1){
                    strb.deleteCharAt(charIndex);
                    charIndex = strb.toString().indexOf(ch);
                }
            }
        }
        line = strb.toString();
        System.out.println("\nModificated line: " + line);
    }
}
