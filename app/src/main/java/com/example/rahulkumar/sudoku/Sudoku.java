package com.example.rahulkumar.sudoku;

import java.util.ArrayList;

/**
 * Created by rahulkumar on 02-08-2017.
 */

public class Sudoku {
    private static Sudoku instance;
    int totalgridsize=81;
    int grid[];


    public static Sudoku getInstance(){
        if (instance == null)
            instance = new Sudoku();
        return instance;
    }


    public int[] newGrid(){
        grid = new int[totalgridsize];

        resetGrid();

        return grid;
    }

    private void resetGrid() {

        //set all grid entries to 0:
        for (int i = 0; i < totalgridsize; i++) {
                grid[i] = 0;

        }
    }



}
