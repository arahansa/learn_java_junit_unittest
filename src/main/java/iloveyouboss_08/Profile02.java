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
import iloveyouboss_02.Criteria;
import iloveyouboss_02.Criterion;
import iloveyouboss_02.Weight;

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
   
   public boolean matches(Criteria criteria) {
      score = 0;
      
      boolean kill = false;
      boolean anyMatches = false; 
      for (Criterion criterion: criteria) {
         Answer answer = answers.get(
               criterion.getAnswer().getQuestionText()); 
         boolean match = matches(criterion, answer);

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

   private boolean matches(Criterion criterion, Answer answer){
      return criterion.getWeight() == Weight.DontCare ||
              answer.match(criterion.getAnswer());
   }

   public int score() {
      return score;
   }
}
