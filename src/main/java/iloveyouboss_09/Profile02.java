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

import java.util.HashMap;
import java.util.Map;

public class Profile02 {
   private Map<String, Answer> answers = new HashMap<>();
   private int score;
   private String name;

   public Profile02(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void add(Answer answer) { 
      answers.put(answer.getQuestionText(), answer);
   }
   
   public boolean matches(Criteria03 criteria) {
      MatchSet02 matchSet = new MatchSet02(answers, criteria);
      score = matchSet.getScore();
      return matchSet.matches();
   }

   private boolean doesNotMeetAnyMustMatchCriterion(Criteria03 criteria){
      for (Criterion03 criterion: criteria) {
         boolean match = criterion.matches(answerMatching(criterion));
         if (!match && criterion.getWeight() == Weight.MustMatch) {
            return true;
         }
      }
      return false;
   }

   private void calculateScore(Criteria03 criteria) {
      score = 0;
      for (Criterion03 criterion: criteria)
         if (criterion.matches(answerMatching(criterion)))
            score += criterion.getWeight().getValue();
   }

   private boolean anyMatches(Criteria03 criteria) {
      boolean anyMatches = false;
      for (Criterion03 criterion: criteria)
         anyMatches = criterion.matches(answerMatching(criterion));
      return anyMatches;
   }

   private Answer answerMatching(Criterion03 criterion){
      return answers.get(
              criterion.getAnswer().getQuestionText());
   }

   public int score() {
      return score;
   }
}
