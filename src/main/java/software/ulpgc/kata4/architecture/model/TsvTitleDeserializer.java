package software.ulpgc.kata4.architecture.model;

public class TsvTitleDeserializer implements TitleDeserializer{
    private static final int ID = 0;
    private static final int TITLETYPE = 1;
    private static final int TITLE = 3;
    private static final int YEAR = 5;
    private static final int DURATION = 7;

    @Override
    public Title deserialize(String line) {
        return deserialize(line.split("\t"));
    }

    private Title deserialize(String[] fields) {
        return new Title(fields[ID], getTitleType(fields[TITLETYPE]), fields[TITLE], toInteger(fields[YEAR]), toInteger(fields[DURATION]));
    }

    private int toInteger(String field) {
        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Title.TitleType getTitleType(String field) {
        return Title.TitleType.valueOf(field.toUpperCase());
    }
}
