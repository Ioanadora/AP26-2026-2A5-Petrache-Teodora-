package com.compulsory8.lab8.game;

import java.util.Objects;

public class Position {
    public int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Position)) return false;
        Position position = (Position) object;
        return row == position.row && col == position.col;
    }
    @Override
    public int hashCode() {return Objects.hash(row, col);}
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
