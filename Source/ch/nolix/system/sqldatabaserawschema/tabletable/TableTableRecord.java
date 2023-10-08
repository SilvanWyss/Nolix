//package declaration
package ch.nolix.system.sqldatabaserawschema.tabletable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
public record TableTableRecord(String idValue, String nameValue) {
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public TableTableRecord( //NOSONAR: This constructor does more than the default one.
		final String idValue,
		final String nameValue
	) {
		
		if (idValue == null) {
			throw ArgumentIsNullException.forArgumentName("id value");
		}
		
		if (nameValue == null) {
			throw ArgumentIsNullException.forArgumentName("name value");
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
