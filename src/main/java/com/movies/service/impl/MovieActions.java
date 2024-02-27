package com.movies.service.impl;

import com.movies.enums.Columns;
import com.movies.model.GeneralMovie;
import com.movies.service.Actions;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieActions implements Actions {
    public static final String DEFAULT_FILENAME = "data.txt";
    private final String DATA_SPLITTER = ",";
    private final List<GeneralMovie> moviesList = new ArrayList<>();
    Scanner in = new Scanner(System.in);

   @Override
    public void loadData() throws IOException {
        File f= new File(DEFAULT_FILENAME);
        loadFromFile(f);
   }

    private void loadFromFile(File f, boolean...calculateId) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(f);
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(fileInputStream))) {        
            String line;
            int line0 = 0;
            while ((line = br.readLine()) != null) {
                if (line0 != 0)
                    analyzeLine(line, calculateId);
                line0++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void analyzeLine(String line, boolean...calculateId) throws ParseException {
        List<String> strings = Arrays.asList(line.split(DATA_SPLITTER));
       Long id;
       if (calculateId.length!=0 && calculateId[0]==true)
           id=getNextId();
        else
            id=Long.valueOf(strings.get(0));
        moviesList.add(new GeneralMovie(
                id, strings.get(1), strings.get(2),
                strings.get(3), strings.get(4)));
    }


    public void exitApp() throws IOException {
        String header = "Id,name,prDate,director,type";
        BufferedWriter writer = new BufferedWriter(new FileWriter("data1.txt", false));

        writer.append(header);                                                                          
        writer.newLine();
        moviesList.stream().forEach(f-> {
            try {
                writer.append(f.getId()+","+f.getTitle()+","+f.getDate()+","+f.getDirector()+","+f.getType());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }
    @Override
    public void editData() throws ParseException {
        System.out.println("Which movie id you want to edit?");
        long l = in.nextLong();
        GeneralMovie movie = findMovieById(l);
        System.out.println("Add new title");
        String title = in.next();
        movie.setTitle(title);
        System.out.println("Add new premier date (format: dd-mm-yyyy)");
        String date = in.next();
        movie.setDate(date);
        System.out.println("Add new director");
        String director = in.next();
        movie.setDirector(director);
        System.out.println("Add new type");
        String type = in.next();
        movie.setType(type);
    }
    @Override
    public void deleteData() {
        System.out.println("Delete one {id} or [all]");

        String next = in.next();
        if (next.equals("all")) {
            moviesList.clear();
        } else {
            try {
                Long i = Long.parseLong(next);

                GeneralMovie first = findMovieById(i);
                moviesList.remove(first);

            } catch (NumberFormatException e) {
                System.out.println("Given value is not a number");
            }
        }
    }

    private GeneralMovie findMovieById(Long i) {
        return moviesList.stream().filter(m -> m.getId() == i).findFirst().get();
    }
    @Override
    public void searchData() {
        System.out.println("By which column you would like to search?");
        Stream.of(Columns.values()).forEach(o-> System.out.println(o));
        String s = in.next();
        System.out.println("Enter phrase to search");
        String phrase = in.next();
        switch (s){
            case "id":
                moviesList.stream().filter(m->m.getId().toString().contains(phrase)).forEach(m->System.out.println(m));
                break;
            case "premiereDate":
                moviesList.stream().filter(m->m.getDate().toString().contains(phrase)).forEach(m->System.out.println(m));
                break;
            case "director":
                moviesList.stream().filter(m->m.getDirector().contains(phrase)).forEach(m->System.out.println(m));
                break;
            case "type":
                moviesList.stream().filter(m->m.getType().contains(phrase)).forEach(m->System.out.println(m));
                break;
            case "title":
                moviesList.stream().filter(m->m.getTitle().contains(phrase)).forEach(m->System.out.println(m));
                break;
            default:
                System.out.println("Given column doesn't exist");
                break;
        }
    }
    @Override
    public void addData() throws ParseException {
        System.out.println("Add new title");
        String title = in.next();
        System.out.println("Add new director");
        String director = in.next();
        System.out.println("Add new type");
        String type = in.next();
        System.out.println("Add ne date");
        String date = in.next();
        Long id = getNextId();
        GeneralMovie movie = new GeneralMovie(id,title,date,director,type);
        moviesList.add(movie);

    }

    private long getNextId(){
        List<GeneralMovie> collect = moviesList.stream().sorted(Comparator.comparingLong(GeneralMovie::getId)
                        .reversed()).
                collect(Collectors.toList());
        return collect.get(0).getId()+1;
    }
    @Override
    public void importFile() throws IOException {
        System.out.println("Add path to file");
        String dir = in.next();
        loadFromFile(new File(dir),true);
    }
    @Override
    public void sortData() {
        System.out.println("By which column you would like to sort?");
        Stream.of(Columns.values()).forEach(o-> System.out.println(o));
        String s = in.next();
        switch (s){
            case "id":
                moviesList.stream().sorted(Comparator.comparingLong(GeneralMovie::getId)).forEach(m->System.out.println(m));
                break;
            case "premiereDate":
                moviesList.stream().sorted(Comparator.comparing(GeneralMovie::getDate)).forEach(m->System.out.println(m));
                break;
            case "director":
                moviesList.stream().sorted(Comparator.comparing(GeneralMovie::getDirector)).forEach(m->System.out.println(m));
                break;
            case "type":
                moviesList.stream().sorted(Comparator.comparing(GeneralMovie::getType)).forEach(m->System.out.println(m));
                break;
            case "title":
                moviesList.stream().sorted(Comparator.comparing(GeneralMovie::getTitle)).forEach(m->System.out.println(m));
                break;
            default:
                System.out.println("Given column doesn't exist");
                break;
        }
    }
    @Override
    public void printaData() {
        moviesList.stream().forEach(m -> System.out.println(m));
    }
}
