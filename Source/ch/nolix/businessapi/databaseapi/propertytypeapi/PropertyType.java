//package declaration
package ch.nolix.businessapi.databaseapi.propertytypeapi;

//own import
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//enum
public enum PropertyType {
	ID,
	VALUE,
	OPTIONAL_VALUE,
	MULTI_VALUE,
	REFERENCE,
	OPTIONAL_REFERENCE,
	MULTI_REFERENCE,
	BACK_REFERENCE,
	OPTIONAL_BACK_REFERENCE,
	MULTI_BACK_REFERENCE;
	
	//method
	public final BasePropertyType getBaseDataType() {
		switch (this) {
			case ID:
				return BasePropertyType.BASE_CONTROL_TYPE;
			case VALUE:
			case OPTIONAL_VALUE:
			case MULTI_VALUE:
				return BasePropertyType.BASE_VALUE;
			case REFERENCE:
			case OPTIONAL_REFERENCE:
			case MULTI_REFERENCE:
				return BasePropertyType.BASE_REFERENCE;
			case BACK_REFERENCE:
			case OPTIONAL_BACK_REFERENCE:
			case MULTI_BACK_REFERENCE:
				return BasePropertyType.BASE_BACK_REFERENCE;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
