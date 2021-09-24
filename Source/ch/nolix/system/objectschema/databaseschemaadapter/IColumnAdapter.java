//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;

//interface
public interface IColumnAdapter extends Headered {
	
	//method declaration
	ParametrizedPropertyType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
