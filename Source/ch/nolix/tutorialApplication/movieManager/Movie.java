//package declaration
package ch.nolix.tutorialApplication.movieManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.ReferenceProperty;

//class
public final class Movie extends Entity {

	//attributes
	public final Property<String> Title = new Property<String>();
	public final ReferenceProperty<Genre> Genre = new ReferenceProperty<Genre>();
}
