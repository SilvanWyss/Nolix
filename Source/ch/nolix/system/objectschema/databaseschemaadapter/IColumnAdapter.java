//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;

//interface
public interface IColumnAdapter extends Named {
	
	//method declaration
	ParametrizedPropertyType<?> getDataType();
	
	//method
	default Column toColumn() {
		return new Column(getName(), getDataType());
	}
}
