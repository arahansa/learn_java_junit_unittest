package iloveyouboss_09;

import iloveyouboss_02.Answer;
import iloveyouboss_02.Weight;
import iloveyouboss_08.Criteria03;
import iloveyouboss_08.Criterion03;

import java.util.Map;

public class MatchSet02 {

    private Map<String, Answer> answers;
    private int score = 0;
    private Criteria03 criteria;

    public MatchSet02(Map<String, Answer> answers, Criteria03 criteria) {
        this.answers = answers;
        this.criteria = criteria;
        calculateScore(criteria);
    }

    public boolean matches() {
        if (doesNotMeetAnyMustMatchCriterion(criteria))
            return false;
        return anyMatches(criteria);
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



    private boolean doesNotMeetAnyMustMatchCriterion(Criteria03 criteria) {
        // ...
        for (Criterion03 criterion: criteria) {
            boolean match = criterion.matches(answerMatching(criterion));
            if (!match && criterion.getWeight() == Weight.MustMatch)
                return true;
        }
        return false;
    }

    private boolean anyMatches(Criteria03 criteria) {
        // ...
        boolean anyMatches = false;
        for (Criterion03 criterion: criteria)
            anyMatches |= criterion.matches(answerMatching(criterion));
        return anyMatches;
    }
}
