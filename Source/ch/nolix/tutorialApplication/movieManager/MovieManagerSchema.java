package ch.nolix.tutorialApplication.movieManager;

import ch.nolix.core.databaseAdapter.Schema;

public class MovieManagerSchema extends Schema {

	public MovieManagerSchema() {
		
		super(
			Movie.class,
			Genre.class
		);
	}
}
