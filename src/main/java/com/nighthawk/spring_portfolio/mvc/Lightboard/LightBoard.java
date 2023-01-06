package com.nighthawk.spring_portfolio.mvc.Lightboard;

import lombok.Data;

@Data  // Annotations to simplify writing code (ie constructors, setters)
public class LightBoard {
    private Light[][] lights;

    /* Initialize LightBoard and Lights */
    public LightBoard(int numRows, int numCols,int diffcolors) {
        this.lights = new Light[numRows][numCols];
        int rc=0;
        // 2D array nested loops, used for initialization
        for (int row = 0; row < numRows; row++) {
            if(diffcolors==1)
                rc++;
            for (int col = 0; col < numCols; col++) {
                if(diffcolors==0)
                    lights[row][col] = new Light();  // each cell needs to be constructed
                else
                    lights[row][col] = new Light(rc);
            }
        }
        this.buildBoard();
    }

    
    public boolean evaluateLight(int row, int col)
    {
        int onCount = 0;
        if(lights[row][col].isOn())
        {
        for(int i = 0; i < lights.length ; i++)
            {
                if(lights[i][col].isOn())
                {
                    onCount++;
                }
            }
        if(onCount % 2 == 0)
        {
            return false;
        }
        }
        else
        {
            onCount = 0;
            for(int i = 0; i < lights.length ; i++)
            {
                if(lights[i][col].isOn())
                {
                    onCount++;
                }
            }
            if(onCount % 3 == 0)
            {
                return true;
            }
        }

        return lights[row][col].isOn();
    }

    public void buildBoard()
    {
        /**
         * Use of nested loops to iterate through lights array to assign on or off for each light
        */
        for(int i=0;i<lights.length;i++) 
        {
            for(int j=0;j<lights[i].length;j++)
            {
                Double x = Math.random();
                if(x>=0&&x<=0.4)
                {
                    lights[i][j].setOn(true);
                }
                else
                {
                    lights[i][j].setOn(false);
                }
            }
        }
    }

    public String evaluatetoString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" +
                "\"Evaluate Light\": " + evaluateLight(row, col) + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                // extract toString data
                "}," +
                // newline
                "\n" ;
                
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }


    /* Output is intended for API key/values */
    public String toString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" +
                "\"Light on\": " + lights[row][col].isOn() + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                "\"light\": " + lights[row][col] +   // extract toString data
                "}," +
                // newline
                "\n" ;
                
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }

    /* Output is intended for Terminal, effects added to output */
    public String toTerminal() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // reset
                "\033[m" +
                
                // color
                "\033[38;2;" + 
                lights[row][col].getRed() + ";" +  // set color using getters
                lights[row][col].getGreen() + ";" +
                lights[row][col].getBlue() + ";" +
                lights[row][col].getEffect() + "m" +
                // data, extract custom getters
                "{" +
                "\"" + "RGB\": " + "\"" + lights[row][col].getRGB() + "\"" +
                "," +
                "\"" + "Effect\": " + "\"" + lights[row][col].getEffectTitle() + "\"" +
                "}," +
                // newline
                "\n" ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 2) + "\033[m" + "]";
		return outString;
    }

    /* Output is intended for Terminal, draws color palette */
    public String toColorPalette() {
        // block sizes
        final int ROWS = 5;
        final int COLS = 10;

        // Build large string for entire color palette
        String outString = "";
        // find each row
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < ROWS; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < COLS; j++) {
                        // print single character, except at midpoint print color code
                        String c = (i == (int) (ROWS / 2) && j == (int) (COLS / 2) ) 
                            ? lights[row][col].getRGB()
                            : (j == (int) (COLS / 2))  // nested ternary
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        outString += 
                        // reset
                        "\033[m" +
                        
                        // color
                        "\033[38;2;" + 
                        lights[row][col].getRed() + ";" +
                        lights[row][col].getGreen() + ";" +
                        lights[row][col].getBlue() + ";" +
                        "7m" +

                        // color code or blank character
                        c +

                        // reset
                        "\033[m";
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }

    public String getLightAtRowColumn(int row,int col)
    {
        return lights[row][col].toString();
    }
    
    static public void main(String[] args) {
        // create and display LightBoard
        LightBoard lightBoard = new LightBoard(5, 5, 1);
        System.out.println(lightBoard);  // use toString() method
        System.out.println(lightBoard.toTerminal());
        System.out.println(lightBoard.toColorPalette());
    }
}
