package Backend.Model;

public class Flashcard {
    private int id;
    private String question;
    private String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean checkAnswer(String userAnswer) {
        return answer.equals(userAnswer);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void editFlashcard(String newQuestion, String newAnswer) {
        setQuestion(newQuestion);
        setAnswer(newAnswer);
    }
}
