//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.ReferenceProperty;

//class
public final class Flight extends Entity {

	//attributes
	public final Property<String> Code = new Property<String>();
	public final ReferenceProperty<Airport> DepartureAirport = new ReferenceProperty<Airport>();
	public final ReferenceProperty<Airport> DestinationAirport = new ReferenceProperty<Airport>();
}
