//package declaration
package ch.nolix.core.SQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.databaseSchemaAdapter.Column;
import ch.nolix.core.databaseSchemaAdapter.IEntitySetAdapter;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitySetAdapter implements IEntitySetAdapter {
	
	//attribute
	private final String name;
	
	//constructor
	public EntitySetAdapter(final String name) {
		
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public ColumnAdapter getColumnAdapter(final Column column) {
		return new ColumnAdapter(column);
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
