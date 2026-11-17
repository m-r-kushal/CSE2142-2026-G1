// File Name: Constants.java

/**
 * Stores constant values used across the EmployeeManager application,
 * including file paths, delimiters, command-line arguments, and status messages.
 */
public class Constants {
    // File and formatting constants
    public static final String FILE_NAME = "employees.txt";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String COMMA_DELIMITER = ",";
    public static final String COMMA_SPACE_DELIMITER = ", ";

    // Command-line argument flags
    public static final String ARG_LIST = "l";
    public static final String ARG_SHOW_RANDOM = "s";
    public static final String ARG_ADD = "+";
    public static final String ARG_SEARCH = "?";
    public static final String ARG_COUNT = "c";
    public static final String ARG_UPDATE = "u";
    public static final String ARG_DELETE = "d";

    // User feedback and status messages
    public static final String MSG_LOADING = "Loading data ...";
    public static final String MSG_LOADED = "Data Loaded.";
    public static final String MSG_UPDATED = "Data Updated.";
    public static final String MSG_DELETED = "Data Deleted.";
    public static final String STATUS_UPDATED = "Updated";
}