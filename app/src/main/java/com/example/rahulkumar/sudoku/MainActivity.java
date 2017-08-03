package com.example.rahulkumar.sudoku;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    CustomAdapter gridAdapter;
    public GridView gridView;
    private AlertDialog.Builder illegalMovePopup,winPopup;
    public Button restartGameButton, button_1, button_2,
            button_3, button_4, button_5, button_6, button_7,
            button_8, button_9,autosolve;
    public String buttonValue = "";
    public String[] gridItems;
    public int[][] checkValid=new int[9][9];
    private Sudoku sudoku = Sudoku.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillArray();
        initComponents();

        //set the first game on startup:
        newGame();

        //set button on click listeners:
        setButtonOnClickListeners();
        //Initialize the popup views:
        buildIllegalMovePopupView();
        buildWinPopupView();
    }
   public void fillArray(){
        checkValid=new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                checkValid[i][j]=0;
            }
        }
    }

    private void initComponents(){
        gridView = (GridView) this.findViewById(R.id.myGridView);
        restartGameButton = (Button) this.findViewById(R.id.new_game_button);
        button_1 = (Button) this.findViewById(R.id.button_1);
        button_2 = (Button) this.findViewById(R.id.button_2);
        button_3 = (Button) this.findViewById(R.id.button_3);
        button_4 = (Button) this.findViewById(R.id.button_4);
        button_5 = (Button) this.findViewById(R.id.button_5);
        button_6 = (Button) this.findViewById(R.id.button_6);
        button_7 = (Button) this.findViewById(R.id.button_7);
        button_8 = (Button) this.findViewById(R.id.button_8);
        button_9 = (Button) this.findViewById(R.id.button_9);
        autosolve=(Button)findViewById(R.id.auto_solve);
    }



    private void setButtonOnClickListeners(){
        restartGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                newGame();
            }
        });
        button_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_1.getText().toString();
                selectGridItem(view);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_2.getText().toString();
                selectGridItem(view);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_3.getText().toString();
                selectGridItem(view);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_4.getText().toString();
                selectGridItem(view);
            }
        });
        button_5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_5.getText().toString();
                selectGridItem(view);
            }
        });
        button_6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_6.getText().toString();
                selectGridItem(view);
            }
        });
        button_7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_7.getText().toString();
                selectGridItem(view);
            }
        });
        button_8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_8.getText().toString();
                selectGridItem(view);
            }
        });
        button_9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                buttonValue = button_9.getText().toString();
                selectGridItem(view);
            }
        });

        autosolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            checkValid=new Solve().solution(checkValid);
                int k=0;
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++){
                        if(k<81)
                        gridItems[k++]=Integer.toString(checkValid[i][j]);
                    }
                }
                CustomAdapter gridAdapter = new CustomAdapter(MainActivity.this, gridItems);
                gridView.setAdapter(gridAdapter);
            }
        });
    }



    public void newGame(){
        fillArray();
        int[] grid = sudoku.newGrid();
        gridItems = new String[81];
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == 0){
                gridItems[i] = "";
            } else {
                gridItems[i] = Integer.toString(grid[i]);
            }
        }
        gridAdapter = new CustomAdapter(MainActivity.this, gridItems);
        gridView.setAdapter(gridAdapter);
      //  winPopupView(true);
    }


    public void selectGridItem(View view){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int moveNumericValue = Integer.parseInt(buttonValue);

                //perform move validation:
             if (validateMove(moveNumericValue,i)){
                    //the move is legal:
                    gridItems[i] = buttonValue;
                    if(checkwinState()){
                        //winPopupView(true);
                        winPopupView(checkAllGridItems());
                    }
             }
                else {
                    //the move is illegal:
                    illegalMovePopupView();
                }

                CustomAdapter gridAdapter = new CustomAdapter(MainActivity.this, gridItems);
                gridView.setAdapter(gridAdapter);
            }
        });
    }
    public boolean validateMove(int value,int pos){
        int row=0,col=0;
        row=pos/9;
        if(pos==81){
            row=8;col=8;
        }
        for(int j=0;j<9;j++){
            if(checkValid[row][j]==value){
                return false;
            }
        }
        col=pos%9;
        for(int j=0;j<9;j++){
            if(checkValid[j][col]==value){
                return false;
            }
        }
        checkValid[row][col]=value;
      return true;
    }

    private void illegalMovePopupView(){
        AlertDialog alert11 = illegalMovePopup.create();
        alert11.show();
    }


    private void buildIllegalMovePopupView(){
        illegalMovePopup = new AlertDialog.Builder(MainActivity.this);
        illegalMovePopup.setTitle("Oops!");
        illegalMovePopup.setMessage("This move is illegal. Please try again :)");
        illegalMovePopup.setCancelable(true);

        illegalMovePopup.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
    }

    private void buildWinPopupView(){
        winPopup = new AlertDialog.Builder(MainActivity.this);
        winPopup.setCancelable(true);

        winPopup.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                newGame();
            }
        });
    }

     private void winPopupView(boolean wonGame){
        if (wonGame){
            winPopup.setTitle("Congratulations!");
            winPopup.setMessage("You have successfully solved the Sudoku puzzle.");
        } else {
            winPopup.setTitle("Game Over.");
            winPopup.setMessage("Please try again!");
        }
        AlertDialog alert11 = winPopup.create();
        alert11.show();
    }

  private boolean checkAllGridItems(){
        for (int i = 0; i < gridItems.length; i++) {
            if (gridItems[i] == ""){
                return false;
            }
        }
        return true;
    }

    private  boolean checkwinState(){
  boolean flag=true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(checkValid[i][j]!=0){flag= true;}
                else {
                    flag = false;
                }
            }
            if(!flag)return false;
        }
        if(flag)return  true;
        return  false;
    }

}
