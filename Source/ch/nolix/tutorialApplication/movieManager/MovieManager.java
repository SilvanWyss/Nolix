package ch.nolix.tutorialApplication.movieManager;

import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.databaseApplication.DatabaseApplication;
import ch.nolix.system.databaseApplication.DatabaseApplicationContext;

public final class MovieManager extends DatabaseApplication {

	public static final String TITLE = "Movie Manager";

	private static final String NAME = "MovieManager";
	
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
