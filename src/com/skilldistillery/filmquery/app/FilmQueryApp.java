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
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Look up a film by its id");
			System.out.println("2. Look up a film by a search keyword.");
			System.out.print("Enter your choice: ");
			int choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter film Id: ");
				int id = input.nextInt();
				
				Film film = db.findFilmById(id);
				if(film == null) {
					System.out.println();
					System.out.println("....FILM IS NOT FOUND....\n");
				}else {
				printFilm(film);
				}
				break;

			case 2:
				System.out.println("Enter the keyword: ");
				String keyword = input.next();
				
				Film filmByKeyword = db.findFilmByKeyword(keyword);
				if(filmByKeyword == null) {
					System.out.println();
					System.out.println("....FILM IS NOT FOUND....\n");
				}else {
				printFilm(filmByKeyword);
				}
				
				
				break;

			case 3:
				System.out.println("Thank you for visiting.");
				return;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 to 3: ");
			}

		}
	}

//	  List<Actor> filmList = db.findActorsByFilmId(i);
//	  System.out.println(filmList);

		// do - while loop
		// print menu
		// Get user choice
		// call and get method to get data
		// print it out nicely
		// Display results; if film not found, say so

	
	

	 
	 public void printFilm(Film film) {
		 
		 System.out.println("===========================================");
		 System.out.println("Film ID\t: " + film.getId());
		 System.out.println("Title\t\t: " + film.getTitle());
		 System.out.println("Year\t\t: " + film.getReleaseYear());
		 System.out.println("Rating\t\t: " + film.getRating());
		 System.out.println("Description\t: " + film.getDescription());		 
		 System.out.println("Language\t\t: " + film.getLanguage());		 
		 System.out.println("===========================================");
		 
	 }
	 
	 public void printActorList(Film film) {
		 
	 }

}
