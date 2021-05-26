/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_08;

import iloveyouboss_02.Answer;
import iloveyouboss_02.Weight;

import java.util.HashMap;
import java.util.Map;

public class Profile04 {
   private Map<String, Answer> answers = new HashMap<>();
   private int score;
   private String name;

   public Profile04(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void add(Answer answer) { 
      answers.put(answer.getQuestionText(), answer);
   }
   
   public boolean matches(Criteria03 criteria) {
      score = 0;
      
      boolean kill = false;
      boolean anyMatches = false; 
      for (Criterion03 criterion: criteria) {
         Answer answer = answerMatching(criterion);
         boolean match = criterion.matches(answer);

         if (!match && criterion.getWeight() == Weight.MustMatch) {  
            kill = true;
         }
         if (match) {         
            score += criterion.getWeight().getValue();
         }
         anyMatches |= match;  
      }
      if (kill)       
         return false;
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
