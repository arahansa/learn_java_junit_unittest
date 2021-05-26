/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_07.scratch;


import static iloveyouboss_07.scratch.ConstrainsSidesTo.constrainsSidesTo;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import iloveyouboss_03.scratch.ExpectToFail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RectangleTest {
   private Rectangle rectangle;
   
   @AfterEach
   public void ensureInvariant() {
      assertThat(rectangle, constrainsSidesTo(100));
   }

   @Test
   public void answersArea() {
      rectangle = new Rectangle(new Point(5, 5), new Point (15, 10));
      assertThat(rectangle.area(), equalTo(50));
   }
   
   @Disabled
   @ExpectToFail
   @Test
   public void allowsDynamicallyChangingSize() {
      rectangle = new Rectangle(new Point(5, 5));
      rectangle.setOppositeCorner(new Point(130, 130));
      assertThat(rectangle.area(), equalTo(15625));
   }
}
