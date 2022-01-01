//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//class
public final class ParametrizedPropertyTypeRecord {
	
	//attributes
	private final String propertyTypeValue;
	private final String dataTypeValue;
	private final String referencedTableValue;
	private final String backReferencedTableValue;
	private final String backReferencedColumnValue;
	
	//constructor
	public ParametrizedPropertyTypeRecord(
		final String propertyTypeValue,
		final String dataTypeValue,
		final String referencedTableValue,
		final String backReferencedTableValue,
		final String backReferencedColumnValue	
	) {
		this.propertyTypeValue = propertyTypeValue;
		this.dataTypeValue = dataTypeValue;
		this.referencedTableValue = referencedTableValue;
		this.backReferencedTableValue = backReferencedTableValue;
		this.backReferencedColumnValue = backReferencedColumnValue;
	}
	
	//method
	public String getBackReferencedColumnValue() {
		return backReferencedColumnValue;
	}
	
	//method
	public String getBackReferencedTableValue() {
		return backReferencedTableValue;
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
	public String getReferencedTableValue() {
		return referencedTableValue;
	}
}
