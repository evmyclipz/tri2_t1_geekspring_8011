package com.nighthawk.spring_portfolio.mvc.Calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    private Calculator calc=new Calculator("100+200");

    /*@PostMapping("/{expression}")
    public ResponseEntity<Object> postCalc(@RequestParam("expression") String expression ) 
    {
        calc = new Calculator(expression);

        if(calc.isBalanced())
        {
            //return calc.toString();
            return new ResponseEntity<>("Expression is balanced",HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>("Try again",HttpStatus.BAD_REQUEST);
        }
    }*/

    @GetMapping("/")
    public String getCalc() {
        
        return calc.toString();
    }


    @PostMapping( "/create")
    public ResponseEntity<Object> postCalc(@RequestParam("exp") String exp)
    {
        calc = new Calculator(exp);
        System.out.println(exp);
    
        return new ResponseEntity<>(calc.toString(), HttpStatus.CREATED);
    }

   
}
