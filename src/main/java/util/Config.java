package util;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Config {

    /**
     * Max count for calculating "Cyclomatic Complexity"
     */
    public static final int MAX_CYCLOMATIC_COMPLEXITY = 50;

    /**
     * Max count for calculating "Weighted Method Count (WMC)"
     */
    public static final int MAX_WEIGHTED_COUNT = 50;

    /**
     * Maximum length of class file
     */
    public static final int MAX_CLASS_LENGTH = 1000;

    /**
     * Maximum number of lines per method
     */
    public static final int MAX_BODY_LENGTH = 50;

    /**
     * Maximum length per method name
     */
    public static final int MAX_METHOD_NAME_LENGTH = 50;

    /**
     * Maximum number of parameters per method
     */
    public static final int MAX_PARAM_COUNT = 8;

    /**
     * Maximum number of variables declared per class
     */
    public static final int MAX_VARIABLE_COUNT = 15;

    /**
     * Maximum number of methods per class
     */
    public static final int MAX_METHODS_COUNT = 25;
}
