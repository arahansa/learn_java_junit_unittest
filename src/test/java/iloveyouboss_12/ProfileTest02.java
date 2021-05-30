package iloveyouboss_12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest02 {

    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;

    @BeforeEach
    public void createProfile(){
        profile = new Profile();
    }

    @BeforeEach
    public void createQuestion(){
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
    }

    @Test
    public void matchesNothingWhenProfileEmpty(){
        Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, Bool.TRUE), Weight.DontCare);
        boolean result = profile.matches(criterion);
        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchAnswer(){
        Answer answer = new Answer(questionIsThereRelocation, Bool.TRUE);
        profile.add(answer);
        Criterion criterion = new Criterion(answer, Weight.Important);
        boolean result = profile.matches(criterion);
        assertTrue(result);
    }
}
