/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss_10;

import java.io.*;

import iloveyouboss_10.util.Http;
import org.json.simple.parser.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressRetrieverTest {
   @Test
   public void answersAppropriateAddressForValidCoordinates() 
         throws IOException, ParseException {
      Http http = (String url) ->
         "{\"address\":{"
         + "\"house_number\":\"324\","
         + "\"road\":\"North Tejon Street\","
         + "\"city\":\"Colorado Springs\","
         + "\"state\":\"Colorado\","
         + "\"postcode\":\"80903\","
         + "\"country_code\":\"us\"}"
         + "}";
      AddressRetriever02 retriever = new AddressRetriever02(http);

      Address address = retriever.retrieve(38.0,-104.0);
      
      assertThat(address.houseNumber, equalTo("324"));
      assertThat(address.road, equalTo("North Tejon Street"));
      assertThat(address.city, equalTo("Colorado Springs"));
      assertThat(address.state, equalTo("Colorado"));
      assertThat(address.zip, equalTo("80903"));
   }

   @Test
   public void returnsAppropriateAddressForValidCoordinates() 
         throws IOException, ParseException {
      Http http = new Http() {
         @Override
         public String get(String url) throws IOException {
            return "{\"address\":{"
               + "\"house_number\":\"324\","
               + "\"road\":\"North Tejon Street\","
               // ...
               + "\"city\":\"Colorado Springs\","
               + "\"state\":\"Colorado\","
               + "\"postcode\":\"80903\","
               + "\"country_code\":\"us\"}"
               + "}";
            }};
      AddressRetriever02 retriever = new AddressRetriever02(http);

      Address address = retriever.retrieve(38.0,-104.0);
      
      assertThat(address.houseNumber, equalTo("324"));
      assertThat(address.road, equalTo("North Tejon Street"));
      assertThat(address.city, equalTo("Colorado Springs"));
      assertThat(address.state, equalTo("Colorado"));
      assertThat(address.zip, equalTo("80903"));
   }
}
