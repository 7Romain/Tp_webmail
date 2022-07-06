package afpa.romain;

public class Laurent {

    public static boolean isAreaTextFull = false;
    public static boolean isFieldTextFull = false;
    public static boolean isComboBoxFull = false;
    public static boolean isComboBoxTextStatusFull = false;
    public static String taxi = "";

    /**
     * @return String
     */
    public static String getTaxi() {
        return taxi;
    }

    /**
     * @param taxi
     */
    public static void setTaxi(String taxi) {
        Laurent.taxi = taxi;
    }

    /**
     * @return boolean
     */
    public static boolean isComboBoxTextStatusFull() {
        return isComboBoxTextStatusFull;
    }

    /**
     * @param isComboBoxTextStatusFull
     */
    public static void setComboBoxTextStatusFull(boolean isComboBoxTextStatusFull) {
        Laurent.isComboBoxTextStatusFull = isComboBoxTextStatusFull;
    }

    /**
     * @return boolean
     */
    public static boolean isFieldTextFull() {
        return isFieldTextFull;
    }

    /**
     * @param isFieldTextFull
     */
    public static void setFieldTextFull(boolean isFieldTextFull) {
        Laurent.isFieldTextFull = isFieldTextFull;
    }

    /**
     * @return boolean
     */
    public static boolean isComboBoxFull() {
        return isComboBoxFull;
    }

    /**
     * @param isComboBoxFull
     */
    public static void setComboBoxFull(boolean isComboBoxFull) {
        Laurent.isComboBoxFull = isComboBoxFull;
    }

    /**
     * @return boolean
     */
    public static boolean isAreaTextFull() {
        return isAreaTextFull;
    }

    /**
     * @param isAreaTextFull
     */
    public static void setAreaTextFull(boolean isAreaTextFull) {
        Laurent.isAreaTextFull = isAreaTextFull;
    }

}
