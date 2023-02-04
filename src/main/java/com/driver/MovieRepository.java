package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    // name of movies , MoviesClass

    Map<String, Movie> moviesDatabase = new HashMap<>();

    //name of director,Director obj
    Map<String, Director> directorDatabase = new HashMap<>();

    // this data base for storing the pairs of movie and direct
    //name of director    movies
    // example   Aman -> [kick,B,C,D]
    Map<String, List<String>> pairdirectMovesDB = new HashMap<>();


    public String addMovie(Movie movies) {
        String name = movies.getName();
        moviesDatabase.put(name, movies);

        return "Movie Added Sucessfully";
    }

    public String addDirector(Director director) {
        String name = director.getName();
        directorDatabase.put(name, director);
        return "Director Added Sucessfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        if (moviesDatabase.containsKey(movieName) && directorDatabase.containsKey(directorName)) {

            List<String> movies = new ArrayList<>();
            if (pairdirectMovesDB.containsKey(directorName)) {
                movies = pairdirectMovesDB.get(directorName);
            }
            movies.add(movieName);
            pairdirectMovesDB.put(directorName, movies);
        }
        return "Pair Added Sucessfully";

    }

    public Movie getMovieByName(String movieName) {
        return moviesDatabase.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return directorDatabase.get(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName){
        List<String>movies=new ArrayList<>();
        if(pairdirectMovesDB.containsKey(directorName))movies= pairdirectMovesDB.get(directorName);
        return movies;

    }
    public List<String>  findAllMovies(){
        List<String>allMovies=new ArrayList<>();

        for(String movieName:moviesDatabase.keySet()){
            allMovies.add(movieName);

        }
        return allMovies;
    }

    public String deleteDirectorByName(String directorName){

        List<String>movies=new ArrayList<>();

        if(pairdirectMovesDB.containsKey(directorName)){
            movies=pairdirectMovesDB.get(directorName);

            for(String movie:movies){
                if(moviesDatabase.containsKey(movie)){
                    moviesDatabase.remove(movie);
                }
            }
            pairdirectMovesDB.remove(directorName);
        }
        if(directorDatabase.containsKey(directorName))directorDatabase.remove(directorName);

        return "Delete Sucessfully";
    }

    public String deleteAllDirectors(){
        List<String>movies=new ArrayList<>();
        for(String director:pairdirectMovesDB.keySet()){
           movies=pairdirectMovesDB.get(director);

           for(String movieName:movies){
               if(moviesDatabase.containsKey(movieName))moviesDatabase.remove(movieName);
           }
        }

        for(String name: pairdirectMovesDB.keySet()){
            if(directorDatabase.containsKey(name))directorDatabase.remove(name);

            pairdirectMovesDB.remove(name);
        }
        return "delet Sucessfully";

    }







}
