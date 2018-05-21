//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class DatabaseApplicationContext {

	//attributes
	private final String title;
	private final IElementGetter<DatabaseAdapter> databaseAdapterFactory;
	
	//optional attribute
	private final StandardConfiguration GUILook;
	
	//constructor
	public DatabaseApplicationContext(
		final String title,
		final IElementGetter<DatabaseAdapter> databaseAdapterFactory
	) {
		
		Validator
		.suppose(title)
		.thatIsNamed(VariableNameCatalogue.TITLE)
		.isNotEmpty();
		
		Validator
		.suppose(databaseAdapterFactory)
		.thatIsNamed("database adapter factory")
		.isNotNull();
		
		this.title = title;
		this.databaseAdapterFactory = databaseAdapterFactory;
		this.GUILook = null;
	}
	
	public DatabaseApplicationContext(
			final String title,
			final IElementGetter<DatabaseAdapter> databaseAdapterFactory,
			final StandardConfiguration GUILook
		) {
			
			Validator
			.suppose(title)
			.thatIsNamed(VariableNameCatalogue.TITLE)
			.isNotEmpty();
			
			Validator
			.suppose(databaseAdapterFactory)
			.thatIsNamed("database adapter factory")
			.isNotNull();
			
			Validator
			.suppose(GUILook)
			.thatIsNamed("GUI look")
			.isNotNull();
			
			this.title = title;
			this.databaseAdapterFactory = databaseAdapterFactory;
			this.GUILook = GUILook;
		}
	
	//method
	public DatabaseAdapter createDatabaseAdapter() {
		return databaseAdapterFactory.getOutput();
	}
	
	//method
	public StandardConfiguration getGUILook() {
		
		supposeHasGUILook();
		
		return GUILook;
	}

	//method
	public String getTitle() {
		return title;
	}
	
	//method
	public boolean hasGUILook() {
		return (GUILook != null);
	}
	
	//method
	private void supposeHasGUILook() {
		if (!hasGUILook()) {
			throw new UnexistingAttributeException(this, "GUI look");
		}
	}
}
