//package declaration
package ch.nolix.system.sqldatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseschemaadapter.Column;
import ch.nolix.system.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.databaseschemaadapter.IEntitySetAdapter;

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
