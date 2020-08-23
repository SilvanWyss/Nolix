//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.system.schemaDataType.SchemaDataType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	public abstract SchemaDataType<?> getDataType();
	
	//method
	public default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
