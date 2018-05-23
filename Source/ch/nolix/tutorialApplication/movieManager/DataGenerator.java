//package declaration
package ch.nolix.tutorialApplication.movieManager;

//own import
import ch.nolix.core.databaseAdapter.DatabaseAdapter;

//class
public final class DataGenerator {

	//method
	public void generateAndSaveSampleData(final DatabaseAdapter movieManagerDatabaseAdapter) {
		
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
		
		final var battleRoyaleMovie = new Movie();
		battleRoyaleMovie.Title.setValue("Battle Royale");
		battleRoyaleMovie.Genre.set(
			movieManagerDatabaseAdapter
			.getRefEntitySet(Genre.class)
			.getRefEntities()
			.getRefFirst(g -> g.Name.getValue().equals("Drama")));
		
		final var hobbitMovie = new Movie();
		hobbitMovie.Title.setValue("Hobbit");
		hobbitMovie.Genre.set(
			movieManagerDatabaseAdapter
			.getRefEntitySet(Genre.class)
			.getRefEntities()
			.getRefFirst(g -> g.Name.getValue().equals("Fantasy")));
		
		movieManagerDatabaseAdapter
		.getRefEntitySet(Movie.class)
		.addEntity(
			battleRoyaleMovie,
			hobbitMovie
		);
		
		movieManagerDatabaseAdapter.saveChanges();
	}
}
