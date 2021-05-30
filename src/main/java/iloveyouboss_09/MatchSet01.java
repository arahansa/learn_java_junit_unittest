package iloveyouboss_09;

import iloveyouboss_02.Answer;
import iloveyouboss_08.Criteria03;
import iloveyouboss_08.Criterion03;

import java.util.Map;

public class MatchSet01 {

    private Map<String, Answer> answers;
    private int score = 0;

    public MatchSet01(Map<String, Answer> answers, Criteria03 criteria) {
        this.answers = answers;
        calculateScore(criteria);
    }

    private void calculateScore(Criteria03 criteria) {
        for (Criterion03 criterion: criteria)
            if (criterion.matches(answerMatching(criterion)))
                score += criterion.getWeight().getValue();
    }

    private Answer answerMatching(Criterion03 criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        return score;
    }
}
