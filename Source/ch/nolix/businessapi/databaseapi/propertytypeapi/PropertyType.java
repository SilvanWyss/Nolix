//package declaration
package ch.nolix.businessapi.databaseapi.propertytypeapi;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum PropertyType {
	ID(BasePropertyType.BASE_CONTROL_TYPE),
	VALUE(BasePropertyType.BASE_VALUE),
	OPTIONAL_VALUE(BasePropertyType.BASE_VALUE),
	MULTI_VALUE(BasePropertyType.BASE_VALUE),
	REFERENCE(BasePropertyType.BASE_REFERENCE),
	OPTIONAL_REFERENCE(BasePropertyType.BASE_REFERENCE),
	MULTI_REFERENCE(BasePropertyType.BASE_REFERENCE),
	BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE),
	OPTIONAL_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE),
	MULTI_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE);
	
	//attribute
	private final BasePropertyType baseType;
	
	//constructor
	PropertyType(final BasePropertyType baseType) {
		
		Validator.assertThat(baseType).thatIsNamed("base type").isNotNull();
		
		this.baseType = baseType;
	}
	
	//method
	public final BasePropertyType getBaseType() {
		return baseType;
	}
}
