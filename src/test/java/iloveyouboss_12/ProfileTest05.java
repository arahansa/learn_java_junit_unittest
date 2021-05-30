package iloveyouboss_12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest05 {

    private ProfileT02 profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;

    private BooleanQuestion questionReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    @BeforeEach
    public void createProfile(){
        profile = new ProfileT02();
    }

    @BeforeEach
    public void createQuestion(){
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);
    }

    @Test
    public void matchesNothingWhenProfileEmpty(){
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.DontCare);
        boolean result = profile.matches(criterion);
        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchAnswer(){
        profile.add(answerThereIsRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
        boolean result = profile.matches(criterion);
        assertTrue(result);
    }

    @Test
    public void doestNotMatchWhenNoMatchingAnswer(){
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenContainsMultipleAnswers(){
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

}
