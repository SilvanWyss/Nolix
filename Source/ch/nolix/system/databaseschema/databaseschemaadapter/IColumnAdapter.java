//package declaration
package ch.nolix.system.databaseschema.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.system.databaseschema.parametrizedschemadatatype.ParametrizedSchemaDataType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	ParametrizedSchemaDataType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
