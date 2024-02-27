package com.movies.service;

import java.io.IOException;
import java.text.ParseException;

public interface Actions {
    void loadData() throws IOException;
    void printaData();
    void editData() throws ParseException;
    void deleteData();
    void searchData();
    void addData() throws ParseException;
    void importFile() throws IOException;
    void sortData();
    void exitApp() throws IOException;
}
// Metody zorganizowane są w pakiecie service i jest do tego interface do rozbudowy aplikacji 
/// Dostarczając taki interface Actions można zaimplementować te metody
/// Pakiet impl --> jest implementacja tego interfaceu