//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
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
	public void assertIsNotEmpty(final IProperty<?> property) {
		if (property.isEmpty()) {
			throw new EmptyArgumentException(property);
		}
	}
	
	//method
	@Override
	public void assertIsNotMandatoryAndEmptyBoth(final IProperty<?> property) {
		if (isMandatoryAndEmptyBoth(property)) {
			throw new EmptyArgumentException(property);
		}
	}
	
	//method
	@Override
	public boolean isMandatoryAndEmptyBoth(final IProperty<?> property) {
		return
		property.isMandatory()
		&& property.isEmpty();
	}
}
