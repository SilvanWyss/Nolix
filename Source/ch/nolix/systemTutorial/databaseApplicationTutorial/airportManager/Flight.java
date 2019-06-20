//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.airportManager;

import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;

//class
public final class Flight extends Entity {

	//attributes
	public final Property<String> Code = new Property<String>();
	public final Reference<Airport> DepartureAirport = new Reference<Airport>();
	public final Reference<Airport> DestinationAirport = new Reference<Airport>();
}
