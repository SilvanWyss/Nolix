//package declaration
package ch.nolix.system.databaseschema.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	ParametrizedPropertyType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
