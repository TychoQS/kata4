package software.ulpgc.kata4.architecture.model;

public class AnswerValidator {
    public static boolean validate(String answer, Title title) {
        return answer.equals(String.valueOf(title.year()));
    }
}
