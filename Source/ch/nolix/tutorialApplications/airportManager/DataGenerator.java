//package declaration
package ch.nolix.tutorialApplications.airportManager;

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
		
		//Creates flights.
			final var departureAirport =
			airportManagerDatabaseAdapter.getRefEntitySet(Airport.class).getRefEntityById(1);
			
			for (var i = 1; i <= 10; i++) {
				
				final var flight = new Flight();
				flight.Code.setValue("F" + i);
				flight.DepartureAirport.set(departureAirport);
				flight.DestinationAirport.set(
					airportManagerDatabaseAdapter.getRefEntitySet(Airport.class).getRefEntityById(i)
				);
				
				airportManagerDatabaseAdapter
				.getRefEntitySet(Flight.class)
				.addEntity(flight);
			}
		
		airportManagerDatabaseAdapter.saveChanges();
	}
}
