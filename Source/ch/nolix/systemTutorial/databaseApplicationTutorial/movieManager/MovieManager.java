//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.movieManager;

import ch.nolix.common.functionAPI.IElementGetter;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseApplication.DatabaseApplication;
import ch.nolix.system.databaseApplication.DatabaseApplicationContext;

//class
public final class MovieManager extends DatabaseApplication {

	//constant
	public static final String TITLE = "Movie Manager";

	//constant
	private static final String NAME = "MovieManager";
	
	//constructor
	public MovieManager(
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
	public MovieManager(
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
