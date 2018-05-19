//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own import
import ch.nolix.core.databaseAdapter.DatabaseAdapter;

//class
public final class DataGenerator {

	//method
	public void generateAndSaveSampleData(final DatabaseAdapter airportManagerDatabaseAdapter) {
		
		airportManagerDatabaseAdapter.reset();
		
		//Creates airports.
		for (var i = 1; i <= 10; i++) {
			
			final var airport = new Airport();
			airport.Name.setValue("Airport" + i);
			airport.AirportCode.setValue("AP" + i);
			
			airportManagerDatabaseAdapter
			.getRefEntitySet(Airport.class)
			.addEntity(airport);
		}
		
		//Creates airline companies.
		for (var i = 1; i <= 10; i++) {
			
			final var airlineCompany = new AirlineCompany();
			airlineCompany.Name.setValue("AirlineCompany" + i);
			
			airportManagerDatabaseAdapter.
			getRefEntitySet(AirlineCompany.class)
			.addEntity(airlineCompany);
		}
		
		airportManagerDatabaseAdapter.saveChanges();
	}
}
