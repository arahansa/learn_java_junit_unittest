/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_08;

import iloveyouboss_02.Criterion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Criteria03 implements Iterable<Criterion03> {

   private List<Criterion03> criteria = new ArrayList<>();

   public void add(Criterion03 criterion) {
      criteria.add(criterion);
   }

   @Override
   public Iterator<Criterion03> iterator() {
      return criteria.iterator();
   }
   
   public int arithmeticMean() {
      return 0;
   }

   public double geometricMean(int[] numbers) {
      int totalProduct = Arrays.stream(numbers).reduce(1, (product, number) -> product * number);
      return Math.pow(totalProduct, 1.0 / numbers.length);
   }
}
