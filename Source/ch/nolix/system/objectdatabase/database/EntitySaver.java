//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
public final class EntitySaver {
	
	//static attribute
	private static final IEntityHelper ENTITY_HELPER = new EntityHelper();
	
	//static attribute
	private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();
	
	//constant
	private static final MultiValueSaver MULTI_VALUE_SAVER = new MultiValueSaver();
	
	//constant
	private static final MultiReferenceSaver MULTI_REFERENCE_SAVER = new MultiReferenceSaver();
	
	//method
	public void saveChangesOfEntity(final IEntity<DataImplementation> entity, final Database database) {
		switch (entity.getState()) {
			case NEW:
				saveNewEntity(entity, database);
				break;
			case LOADED:
				//Does nothing.
				break;
			case EDITED:
				saveChangesOfEditedEntity(entity, database);
				break;
			case DELETED:
				saveEntityDeletion(entity, database);
				break;
			default:
				throw InvalidArgumentException.forArgument(entity);
		}
	}
	
	//method
	private void saveNewEntity(final IEntity<DataImplementation> newEntity, final Database database) {
		
		database.internalGetRefDataAndSchemaAdapter().insertNewEntity(
			newEntity.getParentTableName(),
			ENTITY_HELPER.createNewEntityDTOForEntity(newEntity)
		);
		
		saveMultiPropertyChangesOfEntity(newEntity, database);
	}
	
	//method
	private void saveChangesOfEditedEntity(final IEntity<DataImplementation> editedEntity, final Database database) {
		
		database.internalGetRefDataAndSchemaAdapter().updateEntity(
			editedEntity.getParentTableName(),
			ENTITY_HELPER.createEntityUpdateDTOForEntity(editedEntity)
		);
		
		saveMultiPropertyChangesOfEntity(editedEntity, database);
	}
	
	//method
	private void saveEntityDeletion(final IEntity<DataImplementation> deletedEntity, final Database database) {
		database.internalGetRefDataAndSchemaAdapter().deleteEntity(
			deletedEntity.getRefParentTable().getName(),
			ENTITY_HELPER.createEntityHeadDTOForEntity(deletedEntity)
		);
	}
	
	//method
	private void saveMultiPropertyChangesOfEntity(
		final IEntity<DataImplementation> entity,
		final Database database
	) {
		for (final var p : entity.technicalGetRefProperties()) {
			if (PROPERTY_HELPER.isNewOrEdited(p)) {
				switch (p.getType()) {
					case MULTI_VALUE:
						MULTI_VALUE_SAVER.saveChangesOfMultiValue((IMultiValue<?, ?>)p, database);
						break;
					case MULTI_REFERENCE:
						MULTI_REFERENCE_SAVER.saveChangesOfMultiReference((IMultiReference<?, ?>)p, database);
						break;
					default:
						//Does nothing.
				}
			}
		}
	}
}
