//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.movieManager;

import ch.nolix.system.databaseAdapter.Schema;

//class
public final class MovieManagerSchema extends Schema {

	//constructor
	public MovieManagerSchema() {
		super(
			Movie.class,
			Genre.class
		);
	}
}
