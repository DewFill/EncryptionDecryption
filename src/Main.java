import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String mode = "enc";
        int key = 1;
        String data = "";
        boolean dataIn = false;
        String fileName = "";
        String algorithm = "shift";



        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    dataIn = true;
                case "-in":
                    if (!dataIn) {
                        File file = new File(args[i + 1]);
                        try (Scanner scFile = new Scanner(file)) {
                            data = scFile.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error");
                        }
                    }
                    break;
                case "-out":
                    fileName = args[i + 1];
                    break;
                case "-alg":
                    algorithm = args[i + 1];
                    break;

            }
        }


        if(mode.equals("dec")) {
            key = -(key);
        }

        if (algorithm.equals("shift")) {
            shift(data, key, fileName);
        } else {
            unicode(data, key, fileName);
        }
    }

    public static void unicode(String message, int key, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {

            char letter = (char) (message.charAt(i) + key);


            stringBuilder.append(letter);

        }
        if (fileName.isEmpty()) {
            System.out.println(stringBuilder);
        } else {
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(stringBuilder.toString());
            } catch (Exception e) {
                System.out.println("Error hi");
            }
        }

    }
    public static void shift(String message, int key, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char letter = message.charAt(i);
            if (Character.isLetter(message.charAt(i))) {
                if (Character.isUpperCase(message.charAt(i))) {
                    letter += key;
                    while (letter > 90 || letter < 65) {
                        if (letter > 90) {
                            letter -= 26;
                        } else {
                            letter += 26;
                        }
                    }
                } else {
                    letter += key;
                    while (letter > 122 || letter < 97) {
                        if (letter > 122) {
                            letter -= 26;
                        } else {
                            letter += 26;
                        }
                    }
                }
            }
            stringBuilder.append(letter);

        }
        if (fileName.isEmpty()) {
            System.out.println(stringBuilder);
        } else {
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(stringBuilder.toString());
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

    }
}
