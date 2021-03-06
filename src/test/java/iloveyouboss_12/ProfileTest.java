package iloveyouboss_12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {

    @Test
    public void matchesNothingWhenProfileEmpty(){
        Profile profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package?");
        Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);
        boolean result = profile.matches(criterion);
        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchAnswer(){
        Profile profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package?");
        Answer answer = new Answer(question, Bool.TRUE);
        profile.add(answer);
        Criterion criterion = new Criterion(answer, Weight.Important);
        boolean result = profile.matches(criterion);
        assertTrue(result);
    }
}
