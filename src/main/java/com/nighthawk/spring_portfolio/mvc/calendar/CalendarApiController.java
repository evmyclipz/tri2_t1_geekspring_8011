package com.nighthawk.spring_portfolio.mvc.calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Calendar API
 * Calendar Endpoint: /api/calendar/isLeapYear/2022, Returns: {"year":2020,"isLeapYear":false}
 */
@RestController
@RequestMapping("/api/calendar")
public class CalendarApiController {

    /** GET isLeapYear endpoint
     * ObjectMapper throws exceptions on bad JSON
     *  @throws JsonProcessingException
     *  @throws JsonMappingException
     */
    @GetMapping("/isLeapYear/{year}")
    public ResponseEntity<JsonNode> getIsLeapYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(year);  // evaluates Leap Year
      System.out.println(year);
      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.isLeapYearToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    /** 
     * GET firstDayOfWeek is an endpoint
     * 
     */
    @GetMapping("/firstDayOfYear/{year}")
    public ResponseEntity<JsonNode> getfirstDayOfYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
      //Backend year object 2 for different year
      Year year_obj2 = new Year();

      year_obj2.setYear(year); //evaluating the year
      System.out.println("My year:"+year);
      //Turn Year object into JSON
      ObjectMapper mapper = new ObjectMapper();
      JsonNode json = mapper.readTree(year_obj2.firstDayOfYearToString());

      return ResponseEntity.ok(json);
    }

    /**
     * GET dayOfYear is an endpoint
     */
    @GetMapping("/dayOfYear/{month}/{day}/{year}")
    public ResponseEntity<JsonNode> getdayOfYear(@PathVariable int year, @PathVariable int month, @PathVariable int day) throws JsonMappingException, JsonProcessingException {
      //Backend year object 2 for different year
      Year year_obj3 = new Year();

      year_obj3.dayOfYear(month,day,year); //evaluating the year
      System.out.println(month+" "+day+" "+year+" "+year_obj3.dayOfYearToString());
      //Turn Year object into JSON
      ObjectMapper mapper = new ObjectMapper();
      JsonNode json = mapper.readTree(year_obj3.dayOfYearToString());

      return ResponseEntity.ok(json);
    }

    /**
     * GET numberOfLeapYears is an endpoint
     */
    @GetMapping("/numberOfLeapYears/{year1}/{year2}")
    public ResponseEntity<JsonNode> getnumberOfLeapYears(@PathVariable int year1, @PathVariable int year2) throws JsonMappingException, JsonProcessingException {
      //Backend year object 2 for different year
      Year year_obj4 = new Year();

      year_obj4.numberOfLeapYears(year1, year2); //evaluating the year

      //Turn Year object into JSON
      ObjectMapper mapper = new ObjectMapper();
      JsonNode json = mapper.readTree(year_obj4.numberOfLeapYearsToString());

      return ResponseEntity.ok(json);
    }

    /*
     * GET dayOfWeek is an endpoint
     */
    @GetMapping("/dayOfWeek/{month}/{day}/{year}")
    public ResponseEntity<JsonNode> getdayOfWeek(@PathVariable int year, @PathVariable int month, @PathVariable int day) throws JsonMappingException, JsonProcessingException {
      //Backend year object 2 for different year
      Year year_obj5 = new Year();

      year_obj5.dayOfWeek(month,day,year); //evaluating the year
    
      //Turn Year object into JSON
      ObjectMapper mapper = new ObjectMapper();
      JsonNode json = mapper.readTree(year_obj5.dayOfWeekToString());

      return ResponseEntity.ok(json);
    }


    // add other methods
}