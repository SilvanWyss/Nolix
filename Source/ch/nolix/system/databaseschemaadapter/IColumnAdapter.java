//package declaration
package ch.nolix.system.databaseschemaadapter;

import ch.nolix.common.attributeapi.Headered;
import ch.nolix.system.schemadatatype.SchemaDataType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	SchemaDataType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
