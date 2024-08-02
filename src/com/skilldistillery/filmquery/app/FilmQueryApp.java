package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
//    Actor actor = db.findActorById(1);
    System.out.println(film);
//    System.out.println(actor);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
//    System.out.println("Enter Id: ");
//    int i = input.nextInt();
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  System.out.println("Enter Id: ");
	  int i = input.nextInt();
	  Film film = db.findFilmById(i);
	  System.out.println(film);
	  
//	  List<Actor> filmList = db.findActorsByFilmId(i);
//	  System.out.println(filmList);
	  
	  //do - while loop
	  //print menu
	  //Get user choice
	  //call and get method to get data
	  //print it out nicely
	  //Display results; if film not found, say so
	  
  }

}
