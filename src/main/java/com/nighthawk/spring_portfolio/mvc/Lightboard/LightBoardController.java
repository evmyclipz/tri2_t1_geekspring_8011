package com.nighthawk.spring_portfolio.mvc.lightboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/LightBoard")
public class LightBoardController {
    

    //private LightBoard lightBoard = new LightBoard(5, 7);
    private LightBoard lightBoard; 

    /*
    GET List of People
     */
    @GetMapping("/")
    public String getLightBoard() {
        
        return lightBoard.toString();
    }

    @GetMapping("/check/{row}/{col}")
    public String getLightAtRowAndColumn(@PathVariable int row,@PathVariable int col) {
        //lightBoard = new LightBoard(5, 7);
        return lightBoard.getLightAtRowColumn(row, col);
    }

    @GetMapping("/evaluate")
    public String evalateLight() {
        //lightBoard = new LightBoard(5, 7);
        return lightBoard.evaluatetoString();
    }

    @PostMapping( "/makeBoard")
    public ResponseEntity<Object> postLightBoard(@RequestParam("rows") int rows, @RequestParam("columms") int columms,@RequestParam("diffcolor")int diffcolor) 
    {
        lightBoard  = new LightBoard(rows, columms, diffcolor);
        
        return new ResponseEntity<>("LightBoard is created successfully", HttpStatus.CREATED);
    }



}
