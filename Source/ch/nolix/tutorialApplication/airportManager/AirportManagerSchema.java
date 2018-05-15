//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own import
import ch.nolix.core.databaseAdapter.Schema;

//class
public final class AirportManagerSchema extends Schema {

	//constructor
	public AirportManagerSchema() {
		super(
			AirlineCompany.class,
			Airport.class
		);
	}
}
