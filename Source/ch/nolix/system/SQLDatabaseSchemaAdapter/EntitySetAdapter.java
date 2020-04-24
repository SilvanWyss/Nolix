//package declaration
package ch.nolix.system.SQLDatabaseSchemaAdapter;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IEntitySetAdapter;

//class
public final class EntitySetAdapter implements IEntitySetAdapter {
	
	//attribute
	private final String name;
	
	//constructor
	public EntitySetAdapter(final String name) {
		
		Validator
		.assertThat(name)
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
