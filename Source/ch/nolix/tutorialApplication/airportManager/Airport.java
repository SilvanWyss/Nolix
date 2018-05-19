//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;

//class
public final class Airport extends Entity {

	//attributes
	public final Property<String> Name = new Property<String>();
	public final Property<String> AirportCode = new Property<String>();
}
