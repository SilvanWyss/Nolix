//package declaration
package ch.nolix.tutorialApplications.movieManager;

//own import
import ch.nolix.core.databaseAdapter.Schema;

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
