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
        if (args[0].equals(Constants.ARG_LIST)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                for (String employee : readEmployeesFromFile().split(Constants.COMMA_DELIMITER)) {
                    System.out.println(employee.trim());
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].equals(Constants.ARG_SHOW_RANDOM)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                String line = readEmployeesFromFile();
                System.out.println(line);
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                System.out.println(employees[new Random().nextInt(employees.length)].trim());
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.ARG_ADD)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                writeEmployeesToFile(Constants.COMMA_SPACE_DELIMITER + args[0].substring(1), true);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.ARG_SEARCH)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile().split(Constants.COMMA_DELIMITER);
                int foundIndex = -1;
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].trim().equals(args[0].substring(1).trim())) {
                        foundIndex = index;
                        break;
                    }
                }
                if (foundIndex != -1) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.ARG_COUNT)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                String line = readEmployeesFromFile();
                String[] words = line.trim().split("\\s+");
                System.out.println(words.length + " word(s) found " + line.length());
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.ARG_UPDATE)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile().split(Constants.COMMA_DELIMITER);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].trim().equals(args[0].substring(1).trim())) {
                        employees[index] = Constants.STATUS_UPDATED;
                    }
                }
                writeEmployeesToFile(String.join(Constants.COMMA_DELIMITER, employees), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_UPDATED);
        } else if (args[0].contains(Constants.ARG_DELETE)) {
            System.out.println(Constants.MSG_LOADING);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile().split(Constants.COMMA_DELIMITER)));
                employeeList.remove(args[0].substring(1).trim());
                writeEmployeesToFile(String.join(Constants.COMMA_DELIMITER, employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.MSG_DELETED);
        }
    }

    private static String readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)))) {
            return reader.readLine();
        }
    }

    private static void writeEmployeesToFile(String content, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, append))) {
            writer.write(content);
        }
    }
}