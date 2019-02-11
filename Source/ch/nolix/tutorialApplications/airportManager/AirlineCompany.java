//package declaration
package ch.nolix.tutorialApplications.airportManager;

import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

//class
public final class AirlineCompany extends Entity {

	//attribute
	public final Property<String> Name = new Property<String>();
}
