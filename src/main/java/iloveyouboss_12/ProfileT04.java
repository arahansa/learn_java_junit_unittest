package iloveyouboss_12;

import java.util.HashMap;
import java.util.Map;

public class ProfileT04 {
    private Map<String, Answer> answers = new HashMap<>();

    private Answer getMatchingProfileAnswer(Criterion criterion){
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public boolean matches(Criterion criterion){
        Answer answer = getMatchingProfileAnswer(criterion);
        return criterion.getAnswer().match(answer);
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        boolean matches = false;
        for (Criterion criterion: criteria)
            if (matches(criterion))
                matches = true;
            else if(criterion.getWeight() == Weight.MustMatch)
                matches = false;
        return matches;
    }
}
