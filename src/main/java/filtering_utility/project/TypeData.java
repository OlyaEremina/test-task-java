package filtering_utility.project;

public class TypeData {
    public static Type findoutType(String line) {

        try {
            Long.parseLong(line);
            return Type.INT;

        } catch (NumberFormatException e1) {
            try {
                Float.parseFloat(line);
                return Type.FLOAT;

            } catch (NumberFormatException e2) {
                return Type.STRING;
            }
        }
    }

}
