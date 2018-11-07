//package declaration
package ch.nolix.tutorialApplications.movieManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.Reference;

//class
public final class Movie extends Entity {

	//attributes
	public final Property<String> Title = new Property<String>();
	public final Reference<Genre> Genre = new Reference<Genre>();
}
