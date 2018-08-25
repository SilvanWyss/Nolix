//package declaration
package ch.nolix.tutorialApplications.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.databaseApplication.DatabaseApplication;
import ch.nolix.system.databaseApplication.DatabaseApplicationContext;

//class
public final class AirportManager extends DatabaseApplication {

	//constant
	public static final String TITLE = "Airport Manager";
	
	//constant
	private static final String NAME = "AirportManager";
	
	//constructor	
	public AirportManager(
		final IElementGetter<DatabaseAdapter> databaseAdapterFactory
	) {
		super(
			NAME,
			new DatabaseApplicationContext(
				TITLE,
				databaseAdapterFactory
			)
		);
	}
	
	//constructor	
	public AirportManager(
		final IElementGetter<DatabaseAdapter> databaseAdapterFactory,
		final StandardConfiguration GUILook
	) {
		super(
			NAME,
			new DatabaseApplicationContext(
				TITLE,
				databaseAdapterFactory,
				GUILook
			)
		);
	}
}
