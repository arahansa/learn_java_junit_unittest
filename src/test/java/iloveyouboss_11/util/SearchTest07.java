/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_11.util;

// misleading organization
import java.io.*;
import java.net.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.*;

import static iloveyouboss_11.util.ContainsMatches.containsMatches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest07 {
   private static final String A_TITLE = "1";
   private InputStream stream;
   
   @BeforeEach
   public void turnOffLogging() {
      Search.LOGGER.setLevel(Level.OFF);
   }
   
   @AfterEach
   public void closeResources() throws IOException {
      stream.close();
   }

   @Test
   public void returnsMatchesShowingContextWhenSearchStringInContent() {
      stream = streamOn("There are certain queer times and occasions "
            + "in this strange mixed affair we call life when a man "
            + "takes this whole universe for a vast practical joke, "
            + "though the wit thereof he but dimly discerns, and more "
            + "than suspects that the joke is at nobody's expense but "
            + "his own.");
      Search search = new Search(stream, "practical joke", A_TITLE);
      search.setSurroundingCharacterCount(10);

      search.execute();

      assertThat(search.getMatches(), containsMatches(new Match[]
         { new Match(A_TITLE, "practical joke", 
                              "or a vast practical joke, though t") }));
   }

   @Test
   public void noMatchesReturnedWhenSearchStringNotInContent() 
         throws MalformedURLException, IOException {
      URLConnection connection = 
            new URL("http://bit.ly/15sYPA7").openConnection();
      stream = connection.getInputStream();
      Search search = new Search(stream, "smelt", A_TITLE);

      search.execute();

      assertTrue(search.getMatches().isEmpty());
   }

   private InputStream streamOn(String pageContent) {
      return new ByteArrayInputStream(pageContent.getBytes());
   }
}
