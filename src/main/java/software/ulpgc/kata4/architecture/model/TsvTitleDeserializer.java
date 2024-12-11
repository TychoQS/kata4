package software.ulpgc.kata4.architecture.model;

public class TsvTitleDeserializer implements TitleDeserializer{
    @Override
    public Title deserialize(String line) {
        return deserialize(line.split("\t"));
    }

    private Title deserialize(String[] split) {
        return new Title(split[0], split[3], toInt(split[5]), toInt(split[7]), Title.TitleType.valueOf(split[1].toUpperCase()));
    }

    private int toInt(String value) {
        if (value.equals("\\N")) return 0;
        return Integer.parseInt(value);
    }
}
