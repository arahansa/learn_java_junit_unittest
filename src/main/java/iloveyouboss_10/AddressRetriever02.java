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
import org.json.simple.*;
import org.json.simple.parser.*;


public class AddressRetriever02 {
   private Http http;

   public AddressRetriever02(Http http) {
      this.http = http;
   }

   public Address retrieve(double latitude, double longitude)
         throws IOException, ParseException {
      String parms = String.format("lat=%.6flon=%.6f", latitude, longitude);
      String response = http.get(
         "http://open.mapquestapi.com/nominatim/v1/reverse?format=json&"
         + parms);

      JSONObject obj = (JSONObject)new JSONParser().parse(response);
      // ...

      JSONObject address = (JSONObject)obj.get("address");
      String country = (String)address.get("country_code");
      if (!country.equals("us"))
         throw new UnsupportedOperationException(
               "cannot support non-US addresses at this time");

      String houseNumber = (String)address.get("house_number");
      String road = (String)address.get("road");
      String city = (String)address.get("city");
      String state = (String)address.get("state");
      String zip = (String)address.get("postcode");
      return new Address(houseNumber, road, city, state, zip);
   }
}
