//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
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
	
	//TODO: Make that this method is not restricted to IBaseValues.
	//method
	@Override
	@SuppressWarnings("unchecked")
	public final <DT> Class<DT> getDataType(final IProperty<?> property) {
		
		final var propertyParentEntity = property.getParentEntity();
		
		final var propertyField = GlobalReflectionHelper.getRefField(propertyParentEntity, property);
		
		final var propertyDeclaredType = (ParameterizedType)propertyField.getGenericType();
		
		return (Class<DT>)propertyDeclaredType.getActualTypeArguments()[1];
	}
	
	//method
	@Override
	public final boolean isMandatoryAndEmptyBoth(final IProperty<?> property) {
		return
		property.isMandatory()
		&& property.isEmpty();
	}
}
