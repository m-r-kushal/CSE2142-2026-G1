
 // File Name: EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read all employee names from the data file
    public static String[] readEmployeeNames() throws IOException {

        try (BufferedReader employeeReader = new BufferedReader(
                new FileReader(Constants.FILE_PATH))) {

            String employeeData = employeeReader.readLine();

            return employeeData.split(",");
        }
    }

    // Write employee names to the data file
    public static void writeEmployeeNames(String[] employeeNames) throws IOException {

        try (BufferedWriter employeeWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_PATH))) {

            employeeWriter.write(String.join(",", employeeNames));
        }
    }

    // Add a new employee to the end of the data file
    public static void addEmployee(String employeeName) throws IOException {

        try (BufferedWriter employeeWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_PATH, true))) {

            employeeWriter.write(", " + employeeName);
        }
    }

    public static void main(String[] args) {

        // Validate the number of command-line arguments
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument.");
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            return;
        }

        String command = args[0].trim();

        // Check whether the entered command is valid
        if (command.isEmpty() ||
            (!command.equals("l") &&
             !command.equals("s") &&
             !command.equals("c") &&
             !command.startsWith("+") &&
             !command.startsWith("?") &&
             !command.startsWith("u") &&
             !command.startsWith("d"))) {

            System.out.println("Error: Invalid command.");
            System.out.println("Please enter a valid command.");
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            return;
        }

        // Check whether an employee name is provided
        if ((command.startsWith("+") ||
             command.startsWith("?") ||
             command.startsWith("u") ||
             command.startsWith("d")) &&
             command.substring(1).trim().isEmpty()) {

            System.out.println("Error: Employee name is required.");
            return;
        }

        // Display all employee names
        if (command.equals("l")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeNames();

                for (String employeeName : employeeNames) {
                    System.out.println(employeeName.trim());
                }

            } catch (Exception exception) {
                System.out.println("Error loading employee data.");
            }

            System.out.println("Data Loaded.");

        // Display all employees and select a random employee
        } else if (command.equals("s")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeNames();

                System.out.println(String.join(",", employeeNames));

                int randomIndex = new Random().nextInt(employeeNames.length);

                System.out.println(employeeNames[randomIndex]);

            } catch (Exception exception) {
                System.out.println("Error loading employee data.");
            }

            System.out.println("Data Loaded.");

        // Add a new employee
        } else if (command.startsWith("+")) {

            System.out.println("Loading data ...");

            try {
                String employeeName = command.substring(1).trim();

                addEmployee(employeeName);

                System.out.println("Employee added successfully.");

            } catch (Exception exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println("Data Loaded.");

        // Search for an employee by name
        } else if (command.startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeNames();

                String searchName = command.substring(1).trim();

                boolean isEmployeeFound = false;

                for (String employeeName : employeeNames) {

                    if (employeeName.trim().equalsIgnoreCase(searchName)) {

                        System.out.println("Employee found: " + employeeName.trim());

                        isEmployeeFound = true;
                        break;
                    }
                }

                if (!isEmployeeFound) {
                    System.out.println("Employee not found: " + searchName);
                }

            } catch (Exception exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println("Data Loaded.");

        // Count words and characters in employee data
        } else if (command.equals("c")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeNames();

                String employeeData = String.join(",", employeeNames);

                int wordCount = employeeData.trim().split("\\s+").length;

                int characterCount = employeeData.length();

                System.out.println(
                    wordCount + " word(s) found " + characterCount
                );

            } catch (Exception exception) {
                System.out.println("Error counting words.");
            }

            System.out.println("Data Loaded.");

        // Update an existing employee name
        } else if (command.startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeNames();

                String searchName = command.substring(1).trim();

                boolean isEmployeeFound = false;

                for (int index = 0; index < employeeNames.length; index++) {

                    if (employeeNames[index].trim().equalsIgnoreCase(searchName)) {

                        employeeNames[index] = "Updated";

                        isEmployeeFound = true;
                    }
                }

                if (isEmployeeFound) {

                    writeEmployeeNames(employeeNames);

                    System.out.println("Employee updated successfully.");

                } else {
                    System.out.println("Employee not found.");
                }

            } catch (Exception exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println("Data Updated.");

        // Delete an existing employee name
        } else if (command.startsWith("d")) {

            System.out.println("Loading data ...");

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readEmployeeNames())
                );

                String searchName = command.substring(1).trim();

                boolean isEmployeeDeleted = false;

                for (int index = 0; index < employeeList.size(); index++) {

                    if (employeeList.get(index).trim().equalsIgnoreCase(searchName)) {

                        employeeList.remove(index);

                        isEmployeeDeleted = true;
                        break;
                    }
                }

                if (isEmployeeDeleted) {

                    writeEmployeeNames(employeeList.toArray(new String[0]));

                    System.out.println("Employee deleted successfully.");

                } else {
                    System.out.println("Employee not found.");
                }

            } catch (Exception exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println("Data Deleted.");
        }
    }
}
