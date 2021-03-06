//package declaration
package ch.nolix.system.database.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
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
