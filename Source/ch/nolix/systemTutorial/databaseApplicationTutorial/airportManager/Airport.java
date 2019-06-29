//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.airportManager;

import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

//class
public final class Airport extends Entity {

	//attributes
	public final Property<String> Name = new Property<>();
	public final Property<String> AirportCode = new Property<>();
}
