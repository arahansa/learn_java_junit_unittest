package iloveyouboss_12;

import java.util.HashMap;
import java.util.Map;

public class ProfileT03 {
    private Map<String, Answer> answers = new HashMap<>();

    private Answer getMatchingProfileAnswer(Criterion criterion){
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public boolean matches(Criterion criterion){
        Answer answer = getMatchingProfileAnswer(criterion);
        return answer != null && answer.match(criterion.getAnswer());
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        for (Criterion criterion: criteria)
            if (matches(criterion))
                return true;
        return false;
    }
}
