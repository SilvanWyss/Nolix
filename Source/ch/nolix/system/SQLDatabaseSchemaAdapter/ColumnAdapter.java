//package declaration
package ch.nolix.system.SQLDatabaseSchemaAdapter;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;

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
		.assertThat(header)
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
