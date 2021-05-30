package iloveyouboss_12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest03 {

    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;

    @BeforeEach
    public void createProfile(){
        profile = new Profile();
    }

    @BeforeEach
    public void createQuestion(){
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
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
}
