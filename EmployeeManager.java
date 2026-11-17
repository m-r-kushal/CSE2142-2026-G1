// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Check if the number of arguments is valid
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument (e.g., l, s, +Name, ?Name, c, uName, dName).");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                System.out.println(line);
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = args[0].substring(1);
                writeEmployeesToFile(", " + newEmployee, true);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);
                for (int index = 0; index < employees.length && !found; index++) {
                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(",");
                String employeeName = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeName)) {
                        employees[index] = "Updated";
                    }
                }
                writeEmployeesToFile(String.join(",", employees), false);
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(",");
                String employeeName = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeName);
                writeEmployeesToFile(String.join(",", employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")))) {
            return reader.readLine();
        }
    }

    private static void writeEmployeesToFile(String content, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt", append))) {
            writer.write(content);
        }
    }
}