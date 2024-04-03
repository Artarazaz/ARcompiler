import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class AR {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter your file path: ");
        Scanner scanner1 = new Scanner(System.in);
        String filePath = scanner1.next();
        scanner1.close();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String pName = scanner.nextLine();
        if (programCheck(pName, scanner)) {
            scanner = new Scanner(file);
            if (varName(scanner)){
                scanner = new Scanner(file);
                if (func(scanner)) {
                    System.out.println("test passed");
                }
            }
        }
    }

    public static Boolean programCheck(String pName, Scanner scanner) {
        char[] process = {'p', 'r', 'o', 'c', 'e', 's', 's'};
        boolean processCheck = true;
        char check;
        StringBuilder programName = new StringBuilder();
        int i;
        for (i = 0; i < 7; i++) {
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
                            throw new ArithmeticException("Unexpected token on index " + i
                                    + " of input letter = " + pName.charAt(i));
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
            System.out.println("Program name: " + programName);
        } else {
            throw new ArithmeticException("Unexpected main token, excepted letter {" + process[i] +
                    "} instead of {" + pName.charAt(i) + "} at index " + i);
        }
        scanner.reset();
        return true;
    }

    public static Boolean varName(Scanner scanner) {
        boolean flag;
        int lineCounter = 1;
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            int founder = lineCounter;
            if (next.contains("var")) {
                flag = false;
                while (scanner.hasNextLine()) {
                    next = scanner.nextLine();
                    if (next.contains("endVar")) {
                        flag = true;
                        lineCounter++;
                        break;
                    }
                    if (next.contains("var")) {
                        lineCounter++;
                        break;
                    }
                    lineCounter++;
                }
                if (!flag) {
                    throw new ArithmeticException("You missed an endVar for a var found on line " + founder);
                }

            } else if (next.contains("endVar")) {
                throw new ArithmeticException("You missed a var for an endVar that found on line " + lineCounter);
            }
            lineCounter++;
        }
        scanner.reset();
        return true;
    }
    public static Boolean func(Scanner scanner) {
        boolean flag;
        int lineCounter = 1;
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            int founder = lineCounter;
            if (next.contains("func")) {
                flag = false;
                while (scanner.hasNextLine()) {
                    next = scanner.nextLine();
                    if (next.contains("endFunc")) {
                        flag = true;
                        lineCounter++;
                        break;
                    }
                    if (next.contains("func")) {
                        lineCounter++;
                        break;
                    }
                    lineCounter++;
                }
                if (!flag) {
                    throw new ArithmeticException("You missed an endFunc for a func found on line " + founder);
                }

            } else if (next.contains("endFunc")) {
                throw new ArithmeticException("You missed a func for an endFunc that found on line " + lineCounter);
            }
            lineCounter++;
        }
        scanner.reset();
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