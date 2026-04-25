package com.compulsory8.lab8.model;

import java.io.Serializable;

public class Cell implements Serializable {
    public int row;
    public int col;
    public boolean top=true;
    public boolean bottom=true;
    public boolean right=true;
    public boolean left=true;
    public Cell(int row, int col){
        this.row=row;
        this.col=col;
    }

}
