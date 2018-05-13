//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.primitive.validator2.Validator;

//class
public final class DatabaseApplicationContext {

	//attributes
	private final String title;
	private final IElementGetter<DatabaseAdapter> databaseAdapterFactory;
	
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
	}
	
	//method
	public DatabaseAdapter createDatabaseAdapter() {
		return databaseAdapterFactory.getOutput();
	}
	
	//method
	public String getTitle() {
		return title;
	}
}
