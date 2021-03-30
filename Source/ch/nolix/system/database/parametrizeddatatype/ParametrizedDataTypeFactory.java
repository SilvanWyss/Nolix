//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Property;

//class
public final class ParametrizedDataTypeFactory {
	
	//static method
	@SuppressWarnings("unchecked")
	public static <C, E extends Entity> ParametrizedDataType<C> createDatatypeFor(final Property<C> property) {
		switch (property.getPropertyKind()) {
			case VALUE:
				return new ParametrizedValueType<>(property.getValueClass());
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(property.getValueClass());
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(property.getValueClass());
			case REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedReferenceType<>((Class<E>)(property.getValueClass()));
			case OPTIONAL_REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedOptionalReferenceType<>((Class<E>)(property.getValueClass()));
			case MULTI_REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedMultiReferenceType<>((Class<E>)(property.getValueClass()));
			case BACK_REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedBackReferenceType<>((Class<E>)(property.getValueClass()));
			case OPTIONAL_BACK_REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedOptionalBackReferenceType<>((Class<E>)(property.getValueClass()));
			case MULTI_BACK_REFERENCE:
				return (ParametrizedDataType<C>)new ParametrizedMultiBackReferenceType<>((Class<E>)(property.getValueClass()));
			default:
				throw new InvalidArgumentException(property);
		}
	}
	
	//constructor
	private ParametrizedDataTypeFactory() {}
}
