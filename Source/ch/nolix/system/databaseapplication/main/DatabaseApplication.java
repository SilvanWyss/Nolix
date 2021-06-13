//package declaration
package ch.nolix.system.databaseapplication.main;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.guiclient.BackGUIClient;
import ch.nolix.system.database.databaseadapter.DatabaseAdapter;
import ch.nolix.system.database.databaseadapter.IDatabaseAdapterCreator;
import ch.nolix.system.database.databaseadapter.Schema;

//class
public abstract class DatabaseApplication extends Application<BackGUIClient> {
	
	//attribute
	private boolean isReady;
	
	//optional attribute
	private MutableImage pageLogo;
	
	//constructor
	public DatabaseApplication(final String name, final DatabaseAdapter databaseAdapter) {
		
		super(name,	StartSession.class,	new DatabaseApplicationContext(databaseAdapter));
		
		createInitialDataInBackgroundIfNeeded(databaseAdapter);
	}
	
	//constructor
	public DatabaseApplication(
		final String name,
		final DatabaseAdapter databaseAdapter,
		final Configuration pGUILook
	) {
		
		super(name,	StartSession.class,	new DatabaseApplicationContext(databaseAdapter, pGUILook));
		
		createInitialDataInBackgroundIfNeeded(databaseAdapter);
	}
	
	//constructor
	public DatabaseApplication(
		final String name,
		final IDatabaseAdapterCreator databaseAdapterCreator,
		final Schema schema
	) {
		this(name, databaseAdapterCreator.createDatabaseAdapter(schema));
	}
	
	//constructor
	public DatabaseApplication(
		final String name,
		final IDatabaseAdapterCreator databaseAdapterCreator,
		final Schema schema,
		final Configuration pGUILook
	) {
		this(name, databaseAdapterCreator.createDatabaseAdapter(schema), pGUILook);
	}
	
	//method
	public final MutableImage getPageLogo() {
		
		assertHasPageLogo();
		
		return pageLogo;
	}
	
	//method
	public final boolean hasPageLogo() {
		return (pageLogo != null);
	}
	
	//method
	public final boolean isReady() {
		return isReady;
	}
	
	//method
	public final DatabaseApplication setPageLogo(final MutableImage pageLogo) {
		
		Validator.assertThat(pageLogo).thatIsNamed("page logo").isNotNull();
		
		this.pageLogo = pageLogo;
		
		return this;
	}
	
	//method declaration
	protected abstract void createInitialData(DatabaseAdapter databaseAdapter);
	
	//method
	private void assertHasPageLogo() {
		if (!hasPageLogo()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "page logo");
		}
	}
	
	//method
	private void createInitialDataAndSetState(final DatabaseAdapter databaseAdapter) {
		createInitialData(databaseAdapter.createNewDatabaseAdapter());
		isReady = true;
	}
	
	//method
	private void createInitialDataAndSetStateInBackground(final DatabaseAdapter databaseAdapter) {
		Sequencer.runInBackground(() -> createInitialDataAndSetState(databaseAdapter));
	}
	
	//method
	private void createInitialDataInBackgroundIfNeeded(final DatabaseAdapter databaseAdapter) {
		if (!createInitialDataIsNeeded(databaseAdapter)) {
			isReady = true;
		} else {			
			createInitialDataAndSetStateInBackground(databaseAdapter);
		}
	}
	
	//method
	private boolean createInitialDataIsNeeded(final DatabaseAdapter databaseAdapter) {
		return databaseAdapter.databaseIsEmpty();
	}
}
