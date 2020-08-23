//package declaration
package ch.nolix.system.SQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;
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
	public LinkedList<IColumnAdapter> getColumnAdapters() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
