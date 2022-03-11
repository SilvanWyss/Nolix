//package declaration
package ch.nolix.systemapi.databaseapi.propertytypeapi;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.databaseapi.cardinalityapi.Cardinality;

//enum
public enum PropertyType {
	VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_ONE),
	OPTIONAL_VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_ONE_OR_NONE),
	MULTI_VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_MANY),
	REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_ONE),
	OPTIONAL_REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_ONE_OR_NONE),
	MULTI_REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_MANY),
	BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_ONE),
	OPTIONAL_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
	MULTI_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_MANY);
	
	//static method
	public static PropertyType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
	
	//attribute
	private final BasePropertyType baseType;
	
	//attribute
	private final Cardinality cardinality;
	
	//constructor
	PropertyType(final BasePropertyType baseType, final Cardinality cardinality) {
		
		Validator.assertThat(baseType).thatIsNamed(LowerCaseCatalogue.BASE_TYPE).isNotNull();
		Validator.assertThat(cardinality).thatIsNamed(Cardinality.class).isNotNull();
		
		this.baseType = baseType;
		this.cardinality = cardinality;
	}
	
	//method
	public final BasePropertyType getBaseType() {
		return baseType;
	}
	
	//method
	public final Cardinality getCardinality() {
		return cardinality;
	}
}
