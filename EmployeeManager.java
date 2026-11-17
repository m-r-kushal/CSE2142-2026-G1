// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Manages employee records stored in a text file via command-line arguments.
 */
public class EmployeeManager {

    /**
     * Reads the single line of comma-separated employee names from the data file.
     */
    public static String readEmployeeDataFromFile() throws Exception {
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String employeeDataLine = fileReader.readLine();
        fileReader.close();
        return employeeDataLine;
    }

    /**
     * Writes or appends updated employee data back to the data file.
     */
    public static void writeEmployeeDataToFile(String content, boolean shouldAppend) throws Exception {
        BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, shouldAppend));
        fileWriter.write(content);
        fileWriter.close();
    }

    public static void main(String[] args) {
        // Validate that exactly one command-line argument is provided
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARG_COUNT_MESSAGE);
            return;
        }

        String commandArgument = args[0];

        // Option 1: List all employees ('l')
        if (commandArgument.equals(Constants.LIST_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                for (String employeeName : readEmployeeDataFromFile().split(Constants.DELIMITER)) {
                    System.out.println(employeeName.trim());
                }
            } catch (Exception exception) {
                System.out.println("Error reading employee list.");
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Option 2: Display a random employee ('s')
        } else if (commandArgument.equals(Constants.SHOW_RANDOM_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String employeeDataLine = readEmployeeDataFromFile();
                System.out.println(employeeDataLine);
                String[] employeeList = employeeDataLine.split(Constants.DELIMITER);
                System.out.println(employeeList[new Random().nextInt(employeeList.length)].trim());
            } catch (Exception exception) {
                System.out.println("Error selecting random employee.");
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Option 3: Add a new employee ('+<name>')
        } else if (commandArgument.startsWith(Constants.ADD_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String newEmployeeName = commandArgument.substring(1);
                writeEmployeeDataToFile(Constants.DELIMITER + " " + newEmployeeName, true);
            } catch (Exception exception) {
                System.out.println("Error adding new employee.");
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Option 4: Search for an existing employee ('?<name>')
        } else if (commandArgument.startsWith(Constants.SEARCH_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employeeList = readEmployeeDataFromFile().split(Constants.DELIMITER);
                String targetEmployeeName = commandArgument.substring(1);
                for (String employeeName : employeeList) {
                    if (employeeName.trim().equals(targetEmployeeName)) {
                        System.out.println(Constants.FOUND_MESSAGE);
                        System.out.println(Constants.LOADED_MESSAGE);
                        return;
                    }
                }
                System.out.println(Constants.NOT_FOUND_MESSAGE);
            } catch (Exception exception) {
                System.out.println("Error searching for employee.");
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Option 5: Count total words and characters ('c')
        } else if (commandArgument.equals(Constants.COUNT_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String employeeDataLine = readEmployeeDataFromFile();
                String[] words = employeeDataLine.trim().split("\\s+");
                System.out.println(words.length + " word(s) found " + employeeDataLine.length());
            } catch (Exception exception) {
                System.out.println("Error counting words.");
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Option 6: Update an employee's name to "Updated" ('u<name>')
        } else if (commandArgument.startsWith(Constants.UPDATE_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employeeList = readEmployeeDataFromFile().split(Constants.DELIMITER);
                String targetEmployeeName = commandArgument.substring(1);
                for (int i = 0; i < employeeList.length; i++) {
                    if (employeeList[i].trim().equals(targetEmployeeName)) {
                        employeeList[i] = " " + Constants.UPDATED_TEXT;
                    }
                }
                writeEmployeeDataToFile(String.join(Constants.DELIMITER, employeeList), false);
            } catch (Exception exception) {
                System.out.println("Error updating employee.");
            }
            System.out.println(Constants.UPDATED_MESSAGE);

        // Option 7: Delete an employee ('d<name>')
        } else if (commandArgument.startsWith(Constants.DELETE_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employeeList = readEmployeeDataFromFile().split(Constants.DELIMITER);
                String targetEmployeeName = commandArgument.substring(1);
                List<String> updatedEmployeeList = new ArrayList<>();
                for (String employeeName : employeeList) {
                    if (!employeeName.trim().equals(targetEmployeeName)) {
                        updatedEmployeeList.add(employeeName);
                    }
                }
                writeEmployeeDataToFile(String.join(Constants.DELIMITER, updatedEmployeeList), false);
            } catch (Exception exception) {
                System.out.println("Error deleting employee.");
            }
            System.out.println(Constants.DELETED_MESSAGE);

        // Fallback: Handle unrecognized command-line arguments
        } else {
            System.out.println(Constants.INVALID_ARG_MESSAGE);
        }
    }
}