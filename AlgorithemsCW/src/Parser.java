/*
W.A.D Vidam Satnindu De Silva.
20200458
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    private int rows;
    private int columns;
    private int start_A;
    private int start_B;
    private int end_A;
    private int end_B;
    private String[][] block;

    public int getStart_A() {
        return start_A;
    }

    public int getStart_B() {
        return start_B;
    }

    public int getEnd_A() {
        return end_A;
    }

    public int getEnd_B() {
        return end_B;
    }

    public String[][] getBlock(){
        return block;
    }

    //Method to get the number of rows and columns and store them in a 2D array and add data to it
    public void fileReader(){
        try {
            rows = Files.readAllLines(Paths.get("Course Work/examples/maze10_1.txt")).size();
            columns = Files.readAllLines(Paths.get("Course Work/examples/maze10_1.txt")).get(0).length();
        }
        catch (IOException ignored){
            System.out.println("File not Found");
        }

        block = new String[rows][columns];

        try{
            for (int x = 0; x < rows; x++){
                String line = Files.readAllLines(Paths.get("Course Work/examples/maze10_1.txt")).get(x);

                for (int y = 0; y < columns; y++){
                    block[x][y] = String.valueOf(line.charAt(y));

                    if (String.valueOf(line.charAt(y)).equals("S")){      //checking for Starting point (S)
                        start_A = y;
                        start_B = x;
                    }
                    if (String.valueOf(line.charAt(y)).equals("F")){      //checking for finishing point (F)
                        end_A = y;
                        end_B = x;
                    }
                }
            }
        }
        catch (IOException ignored) {
            System.out.println("File not Found");
        }
    }

    public static void main(String[] args) {

        Parser parser = new Parser();
        parser.fileReader();

        //Time calculator for the puzzle solving
        Puzzle IceSlider = new Puzzle();
        long startTime = System.currentTimeMillis();
        IceSlider.solve(parser.getBlock(),parser.getStart_A(), parser.getStart_B(), parser.getEnd_A(), parser.getEnd_B());
        long current_time = System.currentTimeMillis();
        double timeTaken = (current_time - startTime)/1000.0;
        System.out.println("    ");
        System.out.println("Time Taken :- " + timeTaken + "seconds.");
    }
}