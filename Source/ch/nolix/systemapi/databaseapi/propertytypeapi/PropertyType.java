//package declaration
package ch.nolix.systemapi.databaseapi.propertytypeapi;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;

//enum
public enum PropertyType {
	VALUE(BasePropertyType.BASE_VALUE),
	OPTIONAL_VALUE(BasePropertyType.BASE_VALUE),
	MULTI_VALUE(BasePropertyType.BASE_VALUE),
	REFERENCE(BasePropertyType.BASE_REFERENCE),
	OPTIONAL_REFERENCE(BasePropertyType.BASE_REFERENCE),
	MULTI_REFERENCE(BasePropertyType.BASE_REFERENCE),
	BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE),
	OPTIONAL_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE),
	MULTI_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE);
	
	//static method
	public static PropertyType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
	
	//attribute
	private final BasePropertyType baseType;
	
	//constructor
	PropertyType(final BasePropertyType baseType) {
		
		Validator.assertThat(baseType).thatIsNamed(LowerCaseCatalogue.BASE_TYPE).isNotNull();
		
		this.baseType = baseType;
	}
	
	//method
	public final BasePropertyType getBaseType() {
		return baseType;
	}
}
