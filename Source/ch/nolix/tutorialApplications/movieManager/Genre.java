//package declaration
package ch.nolix.tutorialApplications.movieManager;

import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

//class
public final class Genre extends Entity {

	//attribute
	public final Property<String> Name = new Property<String>();
}
