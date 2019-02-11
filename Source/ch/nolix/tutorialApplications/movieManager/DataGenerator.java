//package declaration
package ch.nolix.tutorialApplications.movieManager;

import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public final class DataGenerator {

	//method
	public void generateAndSaveSampleData(final DatabaseAdapter movieManagerDatabaseAdapter) {
		
		//Creates and saves genres.
			final var actionGenre = new Genre();
			actionGenre.Name.setValue("Action");
			
			final var dramaGenre = new Genre();
			dramaGenre.Name.setValue("Drama");
			
			final var fantasyGenre = new Genre();
			fantasyGenre.Name.setValue("Fantasy");
			
			final var horrorGenre = new Genre();
			horrorGenre.Name.setValue("Horror");
			
			movieManagerDatabaseAdapter
			.getRefEntitySet(Genre.class)
			.addEntity(
				actionGenre,
				dramaGenre,
				fantasyGenre,
				horrorGenre
			);
			
			movieManagerDatabaseAdapter.saveChanges();
		
		//Creates and saves movies.
			final var battleRoyaleMovie = new Movie();
			battleRoyaleMovie.Title.setValue("Battle Royale");
			battleRoyaleMovie.Genre.set(
				movieManagerDatabaseAdapter
				.getRefEntitySet(Genre.class)
				.getRefEntities()
				.getRefFirst(g -> g.Name.hasEqualValue("Drama")));
			
			final var hobbitMovie = new Movie();
			hobbitMovie.Title.setValue("Hobbit");
			hobbitMovie.Genre.set(
				movieManagerDatabaseAdapter
				.getRefEntitySet(Genre.class)
				.getRefEntities()
				.getRefFirst(g -> g.Name.hasEqualValue("Fantasy")));
			
			movieManagerDatabaseAdapter
			.getRefEntitySet(Movie.class)
			.addEntity(
				battleRoyaleMovie,
				hobbitMovie
			);
			
			movieManagerDatabaseAdapter.saveChanges();
	}
}
