//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
public final class DatabaseSaveValidator {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//method
	public void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getRefEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabase(e, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(
		final IEntity<DataImplementation> entity,
		final Database database
	) {
		if (entityHelper.isNewOrEdited(entity)) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenEntityIsNewOrEdited(entity, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenEntityIsNewOrEdited(
		final IEntity<DataImplementation> entity,
		final Database database
	) {
		for (final var p : entity.technicalGetRefProperties()) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabase(database, p);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(
		final Database database,
		final IProperty<DataImplementation> property
	) {
		if (propertyHelper.isNewOrEdited(property)) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenPropertyIsNewOrEdited(property, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenPropertyIsNewOrEdited(
			final IProperty<DataImplementation> property,
			final Database database
		) {
		switch (property.getType()) {
			case REFERENCE:
				
				final var reference = (IReference<?, ?>)property;
											
				database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
					reference.getReferencedTableName(),
					reference.getReferencedEntityId()
				);
											
				break;
			case OPTIONAL_REFERENCE:
				
				final var optionalReference = (OptionalReference<?>)property;
				
				if (optionalReference.containsAny()) {
					database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
						optionalReference.getReferencedTableName(),
						optionalReference.getReferencedEntityId()
					);
				}
				
				break;
			case MULTI_REFERENCE:
				//TODO: Implement.
				break;
			default:
				//Does nothing.
		}
	}
}
