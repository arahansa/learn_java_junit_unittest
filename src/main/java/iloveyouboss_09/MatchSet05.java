/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_09;

import iloveyouboss_02.Weight;
import iloveyouboss_08.Criteria03;
import iloveyouboss_08.Criterion03;

public class MatchSet05 {
   private AnswerCollection answers;
   private Criteria03 criteria;

   public MatchSet05(AnswerCollection answers, Criteria03 criteria) {
      this.answers = answers;
      this.criteria = criteria;
   }

   public int getScore() {
      int score = 0;
      for (Criterion03 criterion: criteria)
         if (criterion.matches(answers.answerMatching(criterion)))
            score += criterion.getWeight().getValue();
      return score;
   }
   // ...

   public boolean matches() {
      if (doesNotMeetAnyMustMatchCriterion())
         return false;
      return anyMatches();
   }

   private boolean doesNotMeetAnyMustMatchCriterion() {
      for (Criterion03 criterion: criteria) {
         boolean match = criterion.matches(answers.answerMatching(criterion));
         if (!match && criterion.getWeight() == Weight.MustMatch)
            return true;
      }
      return false;
   }

   private boolean anyMatches() {
      boolean anyMatches = false;
      for (Criterion03 criterion: criteria)
         anyMatches |= criterion.matches(answers.answerMatching(criterion));
      return anyMatches;
   }
}
