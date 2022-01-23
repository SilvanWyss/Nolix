//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//class
public final class ParametrizedPropertyTypeRecord {
	
	//attributes
	private final String propertyTypeValue;
	private final String dataTypeValue;
	private final String referencedTableIdValue;
	private final String backReferencedColumnIdValue;
	
	//constructor
	public ParametrizedPropertyTypeRecord(
		final String propertyTypeValue,
		final String dataTypeValue,
		final String referencedTableIdValue,
		final String backReferencedColumnIdValue	
	) {
		this.propertyTypeValue = propertyTypeValue;
		this.dataTypeValue = dataTypeValue;
		this.referencedTableIdValue = referencedTableIdValue;
		this.backReferencedColumnIdValue = backReferencedColumnIdValue;
	}
	
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
