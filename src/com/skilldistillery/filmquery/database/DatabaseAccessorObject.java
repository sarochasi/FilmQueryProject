package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, title, description, IFNULL(release_year,''), language_id, language.name, rental_duration, rental_rate, ";
			sql += "IFNULL(length,''), replacement_cost, rating, special_features FROM film"
					+ " JOIN language ON film.language_id = language.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));
				film.setDescription(filmResult.getString(3));
				film.setReleaseYear(filmResult.getInt(4));
				film.setLanguageId(filmResult.getInt(5));
				film.setLanguage(filmResult.getString(6));
				film.setRentalDuration(filmResult.getInt(7));
				film.setRentalRate(filmResult.getInt(8));
				film.setLength(filmResult.getInt(9));
				film.setReplacementCost(filmResult.getInt(10));
				film.setRating(filmResult.getString(11));
				film.setSpecialFeatures(filmResult.getString(12));
				film.setActors(findActorsByFilmId(filmId));

			}
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}


	@Override
	public Film findFilmByKeyword(String keyword) {
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, title, description, IFNULL(release_year,''), language_id, language.name, rental_duration, rental_rate, ";
			sql += " IFNULL(length,''), replacement_cost, rating, special_features FROM film" + 
			" JOIN language ON film.language_id = language.id WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			keyword = "%" + keyword + "%";
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				film = new Film(); // Create the object

				film.setId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));
				film.setDescription(filmResult.getString(3));
				film.setReleaseYear(filmResult.getInt(4));
				film.setLanguageId(filmResult.getInt(5));
				film.setLanguage(filmResult.getString(6));
				film.setRentalDuration(filmResult.getInt(7));
				film.setRentalRate(filmResult.getInt(8));
				film.setLength(filmResult.getInt(9));
				film.setReplacementCost(filmResult.getInt(10));
				film.setRating(filmResult.getString(11));
				film.setSpecialFeatures(filmResult.getString(12));
				film.setActors(findActorsByFilmId(film.getId()));

			}
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
		
	}

	
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				
			}
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name"
					+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return actors;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load MySQL driver");
			e.printStackTrace();
		}
	}

}
