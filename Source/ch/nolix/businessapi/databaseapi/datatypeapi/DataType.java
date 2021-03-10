//package declaration
package ch.nolix.businessapi.databaseapi.datatypeapi;

//own import
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//enum
public enum DataType {
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
	public final BaseDataType getBaseDataType() {
		switch (this) {
			case ID:
				return BaseDataType.BASE_TECNICAL_TYPE;
			case VALUE:
			case OPTIONAL_VALUE:
			case MULTI_VALUE:
				return BaseDataType.BASE_VALUE;
			case REFERENCE:
			case OPTIONAL_REFERENCE:
			case MULTI_REFERENCE:
				return BaseDataType.BASE_REFERENCE;
			case BACK_REFERENCE:
			case OPTIONAL_BACK_REFERENCE:
			case MULTI_BACK_REFERENCE:
				return BaseDataType.BASE_BACK_REFERENCE;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
