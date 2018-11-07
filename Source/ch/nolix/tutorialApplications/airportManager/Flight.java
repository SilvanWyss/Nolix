//package declaration
package ch.nolix.tutorialApplications.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.Reference;

//class
public final class Flight extends Entity {

	//attributes
	public final Property<String> Code = new Property<String>();
	public final Reference<Airport> DepartureAirport = new Reference<Airport>();
	public final Reference<Airport> DestinationAirport = new Reference<Airport>();
}
