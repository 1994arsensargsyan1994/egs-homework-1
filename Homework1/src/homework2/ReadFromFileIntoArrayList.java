package homework2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFileIntoArrayList {
    private static ArrayList<User> users = new ArrayList<>();
    private static final File FILE_PATH = new File("resources/fileArrayList");

    public static void main(String[] args) {
        try {
            readFile();
        } catch (Exception e) {
            System.out.println("Error during read");
        }
        try {
            writeFile();
        } catch (IOException e) {
            System.out.println("Error during  write  or console ");
        }
    }

    private static void readFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            User user = null;
            while ((user = (User) oInput.readObject()) != null) {
                users.add(user);
            }
        } catch (IOException ex) {
            return;
        }
    }

    private static void writeFile() throws IOException {
        System.out.println("\nselect <command(ADD,REMOVE,LIST or EXIT)\n" +
                "for <add> select :ADD space age: \n" +
                "for <remove> select :REMOVE space Index(n): \n" +
                "for <Print> select only: LIST:\n" +
                "for <exit and Save> select only :EXIT:\n" +
                "for <support> select only :HELP:");
        Scanner scanner = new Scanner(System.in);
        String line;
        String[] array = {""};
        while (!array[0].equals("EXIT")) {
            System.out.println("\nInput command");
            line = scanner.nextLine();
            array = line.split(" ");
            switch (array[0].toUpperCase()) {
                case "ADD": {
                    users.add(new User(array[1], Integer.parseInt(array[2])));
                    System.out.println("user added");
                    break;
                }
                case "REMOVE": {
                    if (array.length < Integer.parseInt(array[1])) {
                        throw new IllegalArgumentException("Index can not be small array.length");
                    }
                    users.remove(Integer.parseInt(array[1]));
                    System.out.println("user removed");
                    break;
                }
                case "LIST": {
                    for (User user : users) {
                        System.out.println(user.getName() + " " + user.getEge());
                    }
                    break;
                }
                case "HELP": {
                    System.out.println("\nselect <command(ADD,REMOVE,LIST or EXIT)\n" +
                            "for <add> select :ADD space age: \n" +
                            "for <remove> select :REMOVE space Index(n): \n" +
                            "for <Print> select only: LIST:\n" +
                            "for <exit and Save> select only :EXIT:\n");
                    break;
                }
                case "CLEAR": {
                    users = new ArrayList<>();
                    FILE_PATH.delete();
                    System.out.println("users list has be clear");
                    break;

                }

                default: {
                    System.out.println("Input correct arguments:");
                    break;
                }

            }
            if (line.equals("EXIT")) {
                try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                    for (User user : users) {
                        oOut.writeObject(user);
                    }
                }
            }
        }
    }
}
