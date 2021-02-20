//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.common.attributeapi.Headered;
import ch.nolix.system.database.schemadatatype.SchemaDataType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	SchemaDataType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
