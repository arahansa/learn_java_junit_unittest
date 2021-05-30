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
import iloveyouboss_08.Criteria03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Profile04 {
   private AnswerCollection answers = new AnswerCollection();
   private int score;
   private String name;

   public Profile04(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void add(Answer answer) { 
      answers.add(answer);
   }
   public MatchSet05 getMatchSet(Criteria03 criteria) {
      return new MatchSet05(answers, criteria);
   }


}
