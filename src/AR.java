import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class AR {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter your file path: ");
        Scanner scanner1 = new Scanner(System.in);
        String filePath = scanner1.next();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String pName = scanner.nextLine();
        if (programCheck(pName)){
            System.out.println(scanner.next());
        }
    }

    public static Boolean programCheck(String pName) {
        char[] process = {'p', 'r', 'o', 'c', 'e', 's', 's'};
        boolean processCheck = true;
        char check;
        StringBuilder programName = new StringBuilder();
        int i;
        for ( i = 0; i < 7; i++) {
            if (pName.charAt(i) != process[i]) {
                processCheck = false;
                break;
            }
        }
        if (processCheck) {
            i = 8;
            int size = pName.length();
            check = pName.charAt(i);
            if (Character.isAlphabetic(check)) {
                while (check != ';') {
                    check = pName.charAt(i);
                    if (!Character.isWhitespace(check)) {
                        if (check == ';') {
                            break;
                        } else if (!Character.isAlphabetic(check) && !Character.isDigit(check)) {
                            throw new ArithmeticException("Unexpected token on index " + i + " of input letter = " + pName.charAt(i));
                        }
                    }
                    programName.append(pName.charAt(i));
                    i++;
                    if (i == size && check != ';') {
                        throw new ArithmeticException("Excepted ; after " + check);
                    }
                }
            } else {
                throw new ArithmeticException("Unexpected token for program name");
            }
            System.out.println("Name of your program: " + programName);
        } else {
            throw new ArithmeticException("Unexpected main token, excepted letter {" + process[i] +
                    "} instead of {" + pName.charAt(i) + "} at index " + i);
        }
        //System.out.println("Name of your program: " + programCheck(pName));
        return true;
    }

    /*public static String programName(String pName) {
        int i = 8;
        char check = pName.charAt(i);
        StringBuilder programName = new StringBuilder();
        if (programCheck(pName)) {
            if (Character.isAlphabetic(check)) {
                while (check != ';') {
                    System.out.println(programName);
                    check = pName.charAt(i);
                    if (!Character.isAlphabetic(check) && !Character.isDigit(check)) {
                        throw new ArithmeticException("Unexpected token on index " + i + " of input letter= " + pName.charAt(i));
                    }
                    programName.append(pName.charAt(i));
                    i++;
                }
            } else {
                throw new ArithmeticException("Unexpected token on first Program Name letter");
            }
        }
        return String.valueOf(programName);
    }*/
}