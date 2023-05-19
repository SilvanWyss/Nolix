//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//class
public record ParametrizedPropertyTypeSQLRecord(
	String propertyTypeValue,
	String dataTypeValue,
	String referencedTableIdValue,
	String backReferencedColumnIdValue
) { //NOSONAR: The left curly brace is at the right place.
	
	//method
	public String getBackReferencedColumnIdValue() {
		return backReferencedColumnIdValue;
	}
	
	//method
	public String getDataTypeValue() {
		return dataTypeValue;
	}
	
	//method
	public String getPropertyTypeValue() {
		return propertyTypeValue;
	}
	
	//method
	public String getOrierencedTableIdValue() {
		return referencedTableIdValue;
	}
}
