//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflectionhelper.GlobalReflectionHelper;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IPropertyHelper;

//class
public class PropertyHelper extends DatabaseObjectHelper implements IPropertyHelper {
	
	//method
	@Override
	public final void assertBelongsToEntity(final IProperty<?> property) {
		if (!property.belongsToEntity()) {
			throw new ArgumentDoesNotBelongToParentException(property, IEntity.class);
		}
	}
	
	//method
	@Override
	public final void assertDoesNotBelongToEntity(final IProperty<?> property) {
		if (property.belongsToEntity()) {
			throw new ArgumentBelongsToParentException(property, property.getParentEntity());
		}
	}
	
	//method
	@Override
	public final void assertIsNotEmpty(final IProperty<?> property) {
		if (property.isEmpty()) {
			throw new EmptyArgumentException(property);
		}
	}
	
	//method
	@Override
	public final void assertIsNotMandatoryAndEmptyBoth(final IProperty<?> property) {
		if (isMandatoryAndEmptyBoth(property)) {
			throw new EmptyArgumentException(property);
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
	public final boolean isMandatoryAndEmptyBoth(final IProperty<?> property) {
		return
		property.isMandatory()
		&& property.isEmpty();
	}
	
	//method
	private Class<?> getDataTypeWhenIsBaseValue(final IProperty<?> property) {
		
		final var propertyParentEntity = property.getParentEntity();
		
		final var propertyField = GlobalReflectionHelper.getRefField(propertyParentEntity, property);
		
		final var propertyDeclaredType = (ParameterizedType)propertyField.getGenericType();
		
		return (Class<?>)propertyDeclaredType.getActualTypeArguments()[1];
	}
}
