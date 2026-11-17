// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Manages employee records stored in a text file via command-line arguments.
 * Supports listing, random selection, adding, searching, counting, updating, and deleting employees.
 */
public class EmployeeManager {

    /**
     * Entry point of the application. Validates and processes command-line arguments.
     *
     * @param args Command-line arguments passed by the user.
     */
    public static void main(String[] args) {
        // Validate that exactly one command-line argument is provided
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument (e.g., l, s, +Name, ?Name, c, uName, dName).");
            return;
        }

        String command = args[0];

        if (command.equals(Constants.ARG_LIST)) {
            // List all employees from the data file
            System.out.println(Constants.MSG_LOADING);
            try {
                for (String employee : readEmployeeData().split(Constants.COMMA_DELIMITER)) {
                    System.out.println(employee.trim());
                }
            } catch (Exception exception) {
                System.out.println("Error reading employee data: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_LOADED);

        } else if (command.equals(Constants.ARG_SHOW_RANDOM)) {
            // Display all employee data and print one randomly selected employee
            System.out.println(Constants.MSG_LOADING);
            try {
                String employeeData = readEmployeeData();
                System.out.println(employeeData);
                String[] employees = employeeData.split(Constants.COMMA_DELIMITER);
                System.out.println(employees[new Random().nextInt(employees.length)].trim());
            } catch (Exception exception) {
                System.out.println("Error selecting random employee: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_LOADED);

        } else if (command.startsWith(Constants.ARG_ADD)) {
            // Add a new employee record to the end of the file
            String newEmployeeName = command.substring(1).trim();
            if (newEmployeeName.isEmpty()) {
                System.out.println("Error: Please provide an employee name to add (e.g., \"+John Doe\").");
                return;
            }
            System.out.println(Constants.MSG_LOADING);
            try {
                writeEmployeeData(Constants.COMMA_SPACE_DELIMITER + newEmployeeName, true);
            } catch (Exception exception) {
                System.out.println("Error adding employee: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_LOADED);

        } else if (command.startsWith(Constants.ARG_SEARCH)) {
            // Search for a specific employee by name
            String targetEmployeeName = command.substring(1).trim();
            if (targetEmployeeName.isEmpty()) {
                System.out.println("Error: Please provide an employee name to search (e.g., \"?John Doe\").");
                return;
            }
            System.out.println(Constants.MSG_LOADING);
            try {
                String[] employees = readEmployeeData().split(Constants.COMMA_DELIMITER);
                int foundIndex = -1;
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].trim().equals(targetEmployeeName)) {
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
                System.out.println("Error searching employee: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_LOADED);

        } else if (command.equals(Constants.ARG_COUNT)) {
            // Count total words and characters in the employee data file
            System.out.println(Constants.MSG_LOADING);
            try {
                String employeeData = readEmployeeData();
                String[] wordArray = employeeData.trim().split("\\s+");
                System.out.println(wordArray.length + " word(s) found " + employeeData.length());
            } catch (Exception exception) {
                System.out.println("Error counting words: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_LOADED);

        } else if (command.startsWith(Constants.ARG_UPDATE)) {
            // Update a matching employee record to "Updated"
            String targetEmployeeName = command.substring(1).trim();
            if (targetEmployeeName.isEmpty()) {
                System.out.println("Error: Please provide an employee name to update (e.g., \"uJohn Doe\").");
                return;
            }
            System.out.println(Constants.MSG_LOADING);
            try {
                String[] employees = readEmployeeData().split(Constants.COMMA_DELIMITER);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].trim().equals(targetEmployeeName)) {
                        employees[index] = Constants.STATUS_UPDATED;
                    }
                }
                writeEmployeeData(String.join(Constants.COMMA_DELIMITER, employees), false);
            } catch (Exception exception) {
                System.out.println("Error updating employee: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_UPDATED);

        } else if (command.startsWith(Constants.ARG_DELETE)) {
            // Remove a matching employee record from the file
            String targetEmployeeName = command.substring(1).trim();
            if (targetEmployeeName.isEmpty()) {
                System.out.println("Error: Please provide an employee name to delete (e.g., \"dJohn Doe\").");
                return;
            }
            System.out.println(Constants.MSG_LOADING);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeData().split(Constants.COMMA_DELIMITER)));
                employeeList.removeIf(employee -> employee.trim().equals(targetEmployeeName));
                writeEmployeeData(String.join(Constants.COMMA_DELIMITER, employeeList), false);
            } catch (Exception exception) {
                System.out.println("Error deleting employee: " + exception.getMessage());
            }
            System.out.println(Constants.MSG_DELETED);

        } else {
            // Handle unrecognized command-line arguments
            System.out.println("Error: Invalid argument '" + command + "'. Valid options are: l, s, +Name, ?Name, c, uName, dName.");
        }
    }

    /**
     * Reads the first line of employee records from the data file.
     *
     * @return A comma-separated string of employee names.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private static String readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)))) {
            return reader.readLine();
        }
    }

    /**
     * Writes or appends employee data to the data file.
     *
     * @param content The string content to write to the file.
     * @param append  True to append to the existing file, false to overwrite it.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeEmployeeData(String content, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, append))) {
            writer.write(content);
        }
    }
}