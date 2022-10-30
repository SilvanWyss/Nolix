//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//class
public record ParametrizedPropertyTypeRecord(
	String propertyTypeValue,
	String dataTypeValue,
	String referencedTableIdValue,
	String backReferencedColumnIdValue
) {
	
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
	public String getReferencedTableIdValue() {
		return referencedTableIdValue;
	}
}
