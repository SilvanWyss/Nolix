//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.datahelperapi.IPropertyHelper;

//class
public class PropertyHelper extends DatabaseObjectHelper implements IPropertyHelper {
	
	//method
	@Override
	public void assertBelongsToEntity(final IProperty<?> property) {
		if (!property.belongsToEntity()) {
			throw new ArgumentDoesNotBelongToParentException(property, IEntity.class);
		}
	}
	
	//method
	@Override
	public void assertDoesNotBelongToEntity(final IProperty<?> property) {
		if (property.belongsToEntity()) {
			throw new ArgumentBelongsToParentException(property, property.getParentEntity());
		}
	}
}
