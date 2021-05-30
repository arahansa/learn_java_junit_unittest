/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_09;

import iloveyouboss_02.Answer;
import iloveyouboss_02.Weight;
import iloveyouboss_08.Criteria03;
import iloveyouboss_08.Criterion03;

import java.util.Map;

public class MatchSet04 {
   private Map<String, Answer> answers;
   private int score = 0;
   private Criteria03 criteria;

   public MatchSet04(Map<String, Answer> answers, Criteria03 criteria) {
      this.answers = answers;
      this.criteria = criteria;
   }

   private Answer answerMatching(Criterion03 criterion) {
      return answers.get(criterion.getAnswer().getQuestionText());
   }

   public int getScore() {
      int score = 0;
      for (Criterion03 criterion: criteria)
         if (criterion.matches(answerMatching(criterion)))
            score += criterion.getWeight().getValue();
      return score;
   }

   public boolean matches() {
      if (doesNotMeetAnyMustMatchCriterion())
         return false;
      return anyMatches();
   }

   private boolean doesNotMeetAnyMustMatchCriterion() {
      for (Criterion03 criterion: criteria) {
         boolean match = criterion.matches(answerMatching(criterion));
         if (!match && criterion.getWeight() == Weight.MustMatch)
            return true;
      }
      return false;
   }

   private boolean anyMatches() {
      boolean anyMatches = false;
      for (Criterion03 criterion: criteria)
         anyMatches |= criterion.matches(answerMatching(criterion));
      return anyMatches;
   }
}
