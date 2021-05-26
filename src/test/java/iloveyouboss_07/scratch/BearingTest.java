/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_07.scratch;

import iloveyouboss_07.BearingOutOfRangeException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BearingTest {
   @Test
   public void throwsOnNegativeNumber() {
      assertThrows(BearingOutOfRangeException.class,()-> new Bearing(-1));
   }

   @Test
   public void throwsWhenBearingTooLarge() {
      assertThrows(BearingOutOfRangeException.class, ()-> new Bearing(Bearing.MAX + 1));
   }
   
   @Test
   public void answersValidBearing() {
      assertThat(new Bearing(Bearing.MAX).value(), equalTo(Bearing.MAX));
   }
   
   @Test
   public void answersAngleBetweenItAndAnotherBearing() {
      assertThat(new Bearing(15).angleBetween(new Bearing(12)), equalTo(3));
   }
   
   @Test
   public void angleBetweenIsNegativeWhenThisBearingSmaller() {
      assertThat(new Bearing(12).angleBetween(new Bearing(15)), equalTo(-3));
   }
}
