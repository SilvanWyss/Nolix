//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Property;

//class
public final class DataTypeHelper {
	
	//static method
	@SuppressWarnings("unchecked")
	public static <C, E extends Entity> ParametrizedDataType<C> createDatatypeFor(final Property<C> property) {
		switch (property.getPropertyKind()) {
			case VALUE:
				return new ValueType<>(property.getValueClass());
			case OPTIONAL_VALUE:
				return new OptionalValueType<>(property.getValueClass());
			case MULTI_VALUE:
				return new MultiValueType<>(property.getValueClass());
			case REFERENCE:
				return (ParametrizedDataType<C>)new ReferenceType<>((Class<E>)(property.getValueClass()));
			case OPTIONAL_REFERENCE:
				return (ParametrizedDataType<C>)new OptionalReferenceType<>((Class<E>)(property.getValueClass()));
			case MULTI_REFERENCE:
				return (ParametrizedDataType<C>)new MultiReferenceType<>((Class<E>)(property.getValueClass()));
			case BACK_REFERENCE:
				return (ParametrizedDataType<C>)new BackReferenceType<>((Class<E>)(property.getValueClass()));
			case OPTIONAL_BACK_REFERENCE:
				return (ParametrizedDataType<C>)new OptionalBackReferenceType<>((Class<E>)(property.getValueClass()));
			case MULTI_BACK_REFERENCE:
				return (ParametrizedDataType<C>)new MultiBackReferenceType<>((Class<E>)(property.getValueClass()));
			default:
				throw new InvalidArgumentException(property);
		}
	}
	
	//visibility-reduced constructor
	private DataTypeHelper() {}
}
