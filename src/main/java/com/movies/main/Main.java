package com.movies.main;


import com.movies.actions.ActionsManager;
import com.movies.enums.Options;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        int opt = 0;
        ActionsManager am = new ActionsManager();
        am.loadData();
        System.out.println("Movie rental app");
        System.out.println("Dafault source file: data");
        do {
            System.out.println("Choose option:");
            Stream.of(Options.values()).forEach(
                    f -> System.out.println(String.valueOf(f.getVal()) + ". " + f));
            opt = in.nextInt();
            am.manageOption(opt);
        }
        while (opt != Options.EXIT.getVal());
    }
}
