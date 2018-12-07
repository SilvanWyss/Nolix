//package declaration
package ch.nolix.core.SQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.databaseSchemaAdapter.Column;
import ch.nolix.core.databaseSchemaAdapter.IColumnAdapter;
import ch.nolix.core.validator2.Validator;

//class
public final class ColumnAdapter implements IColumnAdapter {
	
	//attribute
	private String header;
	
	//constructor
	public ColumnAdapter(final Column column) {
		this(column.getHeader());
	}
	
	//constructor
	public ColumnAdapter(final String header) {
		
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotBlank();
		
		this.header = header;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
}
