//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public final class DatabaseApplicationContext {
	
	//attribute
	private final DatabaseAdapter databaseAdapter;
	
	//optional attribute
	private final StandardConfiguration mGUILook;
	
	//constructor
	public DatabaseApplicationContext(final DatabaseAdapter databaseAdapter) {
			
		Validator.assertThat(databaseAdapter).thatIsNamed(DatabaseAdapter.class).isNotNull();
		
		this.databaseAdapter = databaseAdapter;
		mGUILook = null;
	}
	
	//constructor
	public DatabaseApplicationContext(
		final DatabaseAdapter databaseAdapter,
		final StandardConfiguration pGUILook
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
	public StandardConfiguration getGUILook() {
		
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
