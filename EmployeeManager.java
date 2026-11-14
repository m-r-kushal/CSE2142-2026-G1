import java.io.*;
import java.util.*;

private static String readEmployeeLine() throws IOException { ... }`
- `private static void appendToEmployeeFile(String text) throws IOException { ... }`
public class EmployeeManager {
public static void main(String[] args) {
    public static void main(String[] args) {
// Ensure exactly one argument provided
if (args == null || args.length != 1) {
System.err.println("Usage: java EmployeeManager <command>");
System.err.println("Commands: l, s, +Name, ?Name, c, uName, dName");
System.exit(1);
}
// Check arguments
        if (args[0].equals("l")) {
if (args[0].equals("l")) {
System.out.println("Loading data ...");
try {
BufferedReader reader = new BufferedReader(
new InputStreamReader(
new FileInputStream("employees.txt")));
String line = reader.readLine();
String employeesArray[] = line.split(",");
for (String emp : employeesArray) {
System.out.println(emp);
}
} catch (Exception employeesArray) {
}
System.out.println("Data Loaded.");
} else if (args[0].equals("searchParam")) {
System.out.println("Loading data ...");
try {
BufferedReader reader = new BufferedReader(
new InputStreamReader(
new FileInputStream("employees.txt")));
String line = reader.readLine();
System.out.println(line);
String employeesArray[] = line.split(",");
Random rand = new Random();
int randomindex = rand.nextInt(e.length);
System.out.println(e[randomindex]);
} catch (Exception employeesArray) {
}
System.out.println("Data Loaded.");
} else if (args[0].contains("+")) {
System.out.println("Loading data ...");
try {
BufferedWriter w = new BufferedWriter(
new FileWriter("employees.txt", true));
String nameParam = args[0].substring(1);
w.write(", " + nameParam);
w.close();
} catch (Exception employeesArray) {
}
System.out.println("Data Loaded.");
} else if (args[0].contains("?")) {
System.out.println("Loading data ...");
try {
BufferedReader reader = new BufferedReader(
new InputStreamReader(
new FileInputStream("employees.txt")));
String line = reader.readLine();
String employeesArray[] = l.split(",");
boolean found = false;
String searchParam = args[0].substring(1);
for (int i = 0; i < e.length && !found; i++) {
if (e[i].equals(s)) {
System.out.println("Employee found!");
found = true;
}
}
} catch (Exception employeesArray) {
}
System.out.println("Data Loaded.");
} else if (args[0].contains("c")) {
System.out.println("Loading data ...");
try {
BufferedReader reader = new BufferedReader(
}
