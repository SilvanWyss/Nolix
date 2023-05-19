//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.propertyhelper.MultiReferenceEntryHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceEntryHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
public final class DatabaseSaveValidator {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//static attribute
	private static final IMultiReferenceEntryHelper multiReferenceEntryHelper = new MultiReferenceEntryHelper();
	
	//method
	public void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(final Database database) {
		
		final var entitiesInLocalData = databaseHelper.getOriEntitiesInLocalData(database);
		
		for (final var e : entitiesInLocalData) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabase(e, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(
		final IEntity entity,
		final Database database
	) {
		if (entityHelper.isNewOrEdited(entity)) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenEntityIsNewOrEdited(entity, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenEntityIsNewOrEdited(
		final IEntity entity,
		final Database database
	) {
		for (final var p : entity.technicalGetRefProperties()) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabase(database, p);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabase(
		final Database database,
		final IProperty property
	) {
		if (propertyHelper.isNewOrEdited(property)) {
			addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenPropertyIsNewOrEdited(property, database);
		}
	}
	
	//method
	private void addExpectionsThatNewlyReferencedEntitiesExistToDatabaseWhenPropertyIsNewOrEdited(
			final IProperty property,
			final Database database
		) {
		switch (property.getType()) {
			case REFERENCE:
				
				final var reference = (IReference<?>)property;
											
				database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
					reference.getOrierencedTableName(),
					reference.getOrierencedEntityId()
				);
											
				break;
			case OPTIONAL_REFERENCE:
				
				final var optionalReference = (OptionalReference<?>)property;
				
				if (optionalReference.containsAny()) {
					database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
						optionalReference.getOrierencedTableName(),
						optionalReference.getOrierencedEntityId()
					);
				}
				
				break;
			case MULTI_REFERENCE:
				
				final var multiReference = (MultiReference<?>)property;
				final var referencedTableName = multiReference.getOrierencedTableName();
				
				for (final var le : multiReference.getOriLocalEntries()) {
					if (multiReferenceEntryHelper.isNewOrEdited(le)) {
						database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
							referencedTableName,
							le.getOrierencedEntityId()
						);
					}
				}
				
				break;
			default:
				//Does nothing.
		}
	}
}
