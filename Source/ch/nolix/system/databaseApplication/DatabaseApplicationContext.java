//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.functionAPI.IElementGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public final class DatabaseApplicationContext {

	//attribute
	private final IElementGetter<DatabaseAdapter> databaseAdapterCreator;
	
	//optional attribute
	private final StandardConfiguration mGUILook;
	
	//constructor
	public DatabaseApplicationContext(final IElementGetter<DatabaseAdapter> databaseAdapterCreator) {
			
		Validator.suppose(databaseAdapterCreator).thatIsNamed("database adapter creator").isNotNull();
		
		this.databaseAdapterCreator = databaseAdapterCreator;
		mGUILook = null;
	}
	
	//constructor
	public DatabaseApplicationContext(
		final IElementGetter<DatabaseAdapter> databaseAdapterCreator,
		final StandardConfiguration pGUILook
	) {
			
		Validator.suppose(databaseAdapterCreator).thatIsNamed("database adapter creator").isNotNull();
		Validator.suppose(pGUILook).thatIsNamed("GUI look").isNotNull();
		
		this.databaseAdapterCreator = databaseAdapterCreator;
		this.mGUILook = pGUILook;
	}
	
	//method
	public DatabaseAdapter createDatabaseAdapter() {
		return databaseAdapterCreator.getOutput();
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
