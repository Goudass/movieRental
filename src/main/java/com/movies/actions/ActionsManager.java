package com.movies.actions;

import com.movies.enums.Options;
import com.movies.service.Actions;
import com.movies.service.impl.MovieActions;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class ActionsManager {
    Actions actions = new MovieActions();
   public void loadData() throws IOException {
       actions.loadData();
   }

    public void manageOption(int opt) throws IOException, ParseException {
        Options first = Arrays.stream(Options.values()).filter(f -> f.getVal() == opt).findFirst().get();
        switch (first) {
            case PRINT:
                actions.printaData();
                break;
            case EDIT:
                actions.editData();
                break;
            case DELETE:
                actions.deleteData();
                break;
            case SEARCH:   
                actions.searchData();
                break;
            case ADD:
                actions.addData();
                break;
            case IMPORT_FILE:
                actions.importFile();
                break;
            case SORT:
                actions.sortData();
                break;
            case EXIT:
                actions.exitApp();
                break;
            default:
                System.out.println("Action not present !!!");
                break;
        }
    }
}