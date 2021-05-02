//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class SchemaIdType extends BaseParametrizedSchemaControlType<Long> {
	
	//constructor
	public SchemaIdType() {
		super(Long.class);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.ID;
	}
}
