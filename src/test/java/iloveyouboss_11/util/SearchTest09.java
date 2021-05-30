/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_11.util;

// implicit meaning
// meaningful correlation
// 2nd test was hitting live URL, changed to use string string
// no more throws clause on 2nd test

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

public class SearchTest09 {
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
      stream = streamOn("rest of text here"
            + "1234567890search term1234567890"
            + "more rest of text");
      Search search = new Search(stream, "search term", A_TITLE);
      search.setSurroundingCharacterCount(10);

      search.execute();

      assertThat(search.getMatches(), containsMatches(new Match[]
            { new Match(A_TITLE, "search term", "1234567890search term1234567890") }));
   }

   @Test
   public void noMatchesReturnedWhenSearchStringNotInContent() {
      stream = streamOn("any text");
      Search search = new Search(stream, "text that doesn't match", A_TITLE);

      search.execute();

      assertTrue(search.getMatches().isEmpty());
   }

   @Test
   public void returnsErroredWhenUnableToReadStream() {
      stream = createStreamThrowingErrorWhenRead();
      Search search = new Search(stream, "", "");

      search.execute();
      
      assertTrue(search.errored());
   }

   private InputStream createStreamThrowingErrorWhenRead() {
      return new InputStream() {
         @Override
         public int read() throws IOException {
            throw new IOException();
         }
      };
   }
   
   @Test
   public void erroredReturnsFalseWhenReadSucceeds() {
      stream = streamOn("");
      Search search = new Search(stream, "", "");
      
      search.execute();
      
      assertFalse(search.errored());
   }

   private InputStream streamOn(String pageContent) {
      return new ByteArrayInputStream(pageContent.getBytes());
   }
}
