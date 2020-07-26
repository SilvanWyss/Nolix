//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public final class DatabaseApplicationContext {
	
	//attribute
	private final DatabaseAdapter databaseAdapter;
	
	//optional attribute
	private final Configuration mGUILook;
	
	//constructor
	public DatabaseApplicationContext(final DatabaseAdapter databaseAdapter) {
			
		Validator.assertThat(databaseAdapter).thatIsNamed(DatabaseAdapter.class).isNotNull();
		
		this.databaseAdapter = databaseAdapter;
		mGUILook = null;
	}
	
	//constructor
	public DatabaseApplicationContext(
		final DatabaseAdapter databaseAdapter,
		final Configuration pGUILook
	) {
			
		Validator.assertThat(databaseAdapter).thatIsNamed(DatabaseAdapter.class).isNotNull();
		Validator.assertThat(pGUILook).thatIsNamed("GUI look").isNotNull();
		
		this.databaseAdapter = databaseAdapter;
		this.mGUILook = pGUILook;
	}
	
	//method
	public DatabaseAdapter createDatabaseAdapter() {
		return databaseAdapter.createNewDatabaseAdapter();
	}
	
	//method
	public Configuration getGUILook() {
		
		supposeHasGUILook();
		
		return mGUILook;
	}
	
	//method
	public boolean hasGUILook() {
		return (mGUILook != null);
	}
	
	//method
	private void supposeHasGUILook() {
		if (!hasGUILook()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "GUI look");
		}
	}
}
