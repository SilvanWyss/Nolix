//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.movieManager;

import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;

//class
public final class Movie extends Entity {

	//attributes
	public final Property<String> Title = new Property<>();
	public final Reference<Genre> Genre = new Reference<>();
}
