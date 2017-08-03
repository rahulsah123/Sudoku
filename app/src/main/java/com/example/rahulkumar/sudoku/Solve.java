package com.example.rahulkumar.sudoku;

import android.content.Context;

/**
 * Created by rahulkumar on 03-08-2017.
 */

public class Solve {
    public Solve(){
    }
    public int[][] solution(int[][] gr){
       if(solveSudoku(gr))
        return  gr;
        else
            return gr;
    }
    int r1=0;int c1=0;
    public boolean solveSudoku(int[][] grid){
        int row=0,column=0;
        if(!isTherenNoPalceToFill(grid))
            return true;
        row=r1;column=c1;
        for(int i=1;i<=9;i++)
        {
            if(canPlace(grid,row,column,i)){
                grid[row][column]=i;
                if(solveSudoku(grid))
                    return true;
                grid[row][column]=0;
            }
        }
        return false;
    }
    boolean isTherenNoPalceToFill(int[][] grid){
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                if(grid[i][j]==0)
                {r1=i;c1=j;return true;}
        return false;
    }

    boolean isInRow(int[][] grid,int r,int num){
        for(int i=0;i<9;i++){
            if(grid[r][i]==num)
                return true;
        }
        return false;
    }


    boolean isInCol(int[][] grid,int c,int num){
        for(int i=0;i<9;i++){
            if(grid[i][c]==num)
                return true;
        }
        return false;
    }

    boolean isInBox(int[][] grid,int r,int c,int num){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(grid[i+r][j+c]==num)
                    return true;
            }
        }
        return false;
    }


    boolean canPlace(int[][] grid,int row,int col,int num){
        if(!isInRow(grid,row,num)&&!isInCol(grid,col,num)&&!isInBox(grid,row-row%3,col-col%3, num))
            return true;
        return false;
    }


}
