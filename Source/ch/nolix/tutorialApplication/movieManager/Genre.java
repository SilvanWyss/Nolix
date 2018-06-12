//package declaration
package ch.nolix.tutorialApplication.movieManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;

//class
public final class Genre extends Entity {

	//attribute
	public final Property<String> Name = new Property<String>();
}
