//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
public final class TableTableRecord {
	
	//attribute
	private final String idValue;
	
	//attribute
	private final String nameValue;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public TableTableRecord(final String idValue, final String nameValue) {
		
		if (idValue == null) {
			throw new ArgumentIsNullException("id value");
		}
		
		if (nameValue == null) {
			throw new ArgumentIsNullException("name value");
		}
		
		this.idValue = idValue;
		this.nameValue = nameValue;
	}
	
	//method
	public String getIdValue() {
		return idValue;
	}
	
	//method
	public String getNameValue() {
		return nameValue;
	}
}
