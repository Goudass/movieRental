package com.movies.enums;

public enum Options {
    PRINT(1),
    EDIT(2),
    DELETE(3),
    ADD(4),
    SEARCH(5),
    IMPORT_FILE(6),
    SORT(7),
    EXIT(99);

    int val;
    Options(int val){
        this.val=val;
    }

    public int getVal() {
        return val;
    }
}