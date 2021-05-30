/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_11.util;
// eliminate irrelevant information
import java.io.*;
import java.net.*;
import org.junit.jupiter.api.Test;

import java.util.logging.*;

import static iloveyouboss_11.util.ContainsMatches.containsMatches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest04 {
   private static final String A_TITLE = "1";

   @Test
   public void testSearch() throws IOException {
      InputStream stream =
            streamOn("There are certain queer times and occasions "
             + "in this strange mixed affair we call life when a man "
             + "takes this whole universe for a vast practical joke, "
             + "though the wit thereof he but dimly discerns, and more "
             + "than suspects that the joke is at nobody's expense but "
             + "his own.");
      // search
      Search search = new Search(stream, "practical joke", A_TITLE);
      // ...
      Search.LOGGER.setLevel(Level.OFF);
      search.setSurroundingCharacterCount(10);
      search.execute();
      assertFalse(search.errored());
      assertThat(search.getMatches(), containsMatches(new Match[]
         { new Match(A_TITLE, "practical joke",
                              "or a vast practical joke, though t") }));
      stream.close();

      // negative
      URLConnection connection =
            new URL("http://bit.ly/15sYPA7").openConnection();
      InputStream inputStream = connection.getInputStream();
      search = new Search(inputStream, "smelt", A_TITLE);
      search.execute();
      assertTrue(search.getMatches().isEmpty());
      stream.close();
   }

   private InputStream streamOn(String pageContent) {
      return new ByteArrayInputStream(pageContent.getBytes());
   }
}
