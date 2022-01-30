//package declaration
package ch.nolix.system.objectschema.sqldatabaseschemaadapter;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.databaseschemaadapter.Column;
import ch.nolix.system.objectschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.objectschema.databaseschemaadapter.IEntitySetAdapter;

//class
public final class EntitySetAdapter implements IEntitySetAdapter {
	
	//attribute
	private final String name;
	
	//constructor
	public EntitySetAdapter(final String name) {
		
		Validator
		.assertThat(name)
		.thatIsNamed(LowerCaseCatalogue.NAME)
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
		return new LinkedList<>();
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
