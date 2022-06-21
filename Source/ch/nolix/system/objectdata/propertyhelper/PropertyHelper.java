//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.GlobalReflectionHelper;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.databaseapi.cardinalityapi.BaseCardinality;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IPropertyHelper;

//class
public class PropertyHelper extends DatabaseObjectHelper implements IPropertyHelper {
	
	//method
	@Override
	public final void assertBelongsToEntity(final IProperty<?> property) {
		if (!property.belongsToEntity()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(property, IEntity.class);
		}
	}
	
	//method
	@Override
	public final void assertDoesNotBelongToEntity(final IProperty<?> property) {
		if (property.belongsToEntity()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(property, property.getParentEntity());
		}
	}
	
	//method
	@Override
	public final void assertIsNotEmpty(final IProperty<?> property) {
		if (property.isEmpty()) {
			throw EmptyArgumentException.forArgument(property);
		}
	}
	
	//method
	@Override
	public final void assertIsNotMandatoryAndEmptyBoth(final IProperty<?> property) {
		if (isMandatoryAndEmptyBoth(property)) {
			throw EmptyArgumentException.forArgument(property);
		}
	}
	
	//method
	@Override
	public final void assertKnowsParentColumn(final IProperty<?> property) {
		if (!property.knowsParentColumn()) {
			throw new InvalidArgumentException(property, "does not know its parent column");
		}
	}
	
	//method
	@Override
	public final boolean belongsToLoadedEntity(final IProperty<?> property) {
		return
		property.belongsToEntity()
		&& isLoaded(property.getParentEntity());
	}
	
	//method
	@Override
	public final Class<?> getDataType(final IProperty<?> property) {
		switch (property.getType().getBaseType()) {
			case BASE_VALUE:
				return getDataTypeWhenIsBaseValue(property);
			case BASE_REFERENCE:
			case BASE_BACK_REFERENCE:
				return String.class;
			default:
				throw new InvalidArgumentException(property);
		}
	}
	
	//method
	@Override
	public boolean isForMultiContent(final IProperty<?> property) {
		return (property.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI);
	}
	
	//method
	@Override
	public boolean isForSingleContent(final IProperty<?> property) {
		return (property.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE);
	}
	
	//method
	@Override
	public final boolean isMandatoryAndEmptyBoth(final IProperty<?> property) {
		return
		property.isMandatory()
		&& property.isEmpty();
	}
	
	//method
	private Class<?> getDataTypeWhenDoesNotBelongToEntity(IMultiValue<?, ?> multiValue) {
		
		if (multiValue.isEmpty()) {
			throw new InvalidArgumentException(multiValue, "cannot know its data type");
		}
		
		return multiValue.getRefValues().getRefFirst().getClass();
	}
	
	//method
	private Class<?> getDataTypeWhenDoesNotBelongToEntity(IOptionalValue<?, ?> optionalValue) {
		
		if (optionalValue.isEmpty()) {
			throw new InvalidArgumentException(optionalValue, "cannot know its data type");
		}
		
		return optionalValue.getRefValue().getClass();
	}
	
	//method
	private Class<?> getDataTypeWhenDoesNotBelongToEntity(final IValue<?, ?> value) {
		
		if (value.isEmpty()) {
			throw new InvalidArgumentException(value, "cannot know its data type");
		}
		
		return value.getRefValue().getClass();
	}
	
	//method
	private Class<?> getDataTypeWhenIsBaseValue(final IProperty<?> property) {
		
		if (!property.belongsToEntity()) {
			return getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(property);
		}
		
		return getDataTypeWhenIsBaseValueAndBelongsToEntity(property);
	}
	
	//method
	private Class<?> getDataTypeWhenIsBaseValueAndBelongsToEntity(final IProperty<?> property) {
		final var propertyParentEntity = property.getParentEntity();
		
		final var propertyField = GlobalReflectionHelper.getRefField(propertyParentEntity, property);
		
		final var propertyDeclaredType = (ParameterizedType)propertyField.getGenericType();
		
		final var typeArguments = propertyDeclaredType.getActualTypeArguments();
		
		return (Class<?>)typeArguments[typeArguments.length - 1];
	}

	//method
	private Class<?> getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(final IProperty<?> property) {
		switch (property.getType()) {
			case VALUE:
				return getDataTypeWhenDoesNotBelongToEntity((IValue<?, ?>)property);
			case OPTIONAL_VALUE:
				return getDataTypeWhenDoesNotBelongToEntity((IOptionalValue<?, ?>)property);
			case MULTI_VALUE:
				return getDataTypeWhenDoesNotBelongToEntity((IMultiValue<?, ?>)property);
			default:
				throw new InvalidArgumentException(property, "is not a base value");
		}
	}
}
