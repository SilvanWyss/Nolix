//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.airportManager;

import ch.nolix.system.databaseAdapter.Schema;

//class
public final class AirportManagerSchema extends Schema {

	//constructor
	public AirportManagerSchema() {
		super(
			AirlineCompany.class,
			Airport.class,
			Flight.class
		);
	}
}
