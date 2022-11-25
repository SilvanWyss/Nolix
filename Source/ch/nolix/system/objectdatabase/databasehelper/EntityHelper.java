//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.sqlrawdata.databasedto.EntityHeadDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.system.sqlrawdata.databasedto.RecordDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;

//class
public class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//method
	@Override
	public final void assertBelongsToTable(final IEntity<?> entity) {
		if (!entity.belongsToTable()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public final void assertCanBeDeleted(final IEntity<?> entity) {
		if (!canBeDeleted(entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(entity, "cannot be deleted");
		}
	}
	
	//method
	@Override
	public final void assertDoesNotBelongToTable(final IEntity<?> entity) {
		if (entity.belongsToTable()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(entity, entity.getRefParentTable());
		}
	}
	
	//method
	@Override
	public final void assertHasSaveStamp(final IEntity<?> entity) {
		if (!entity.hasSaveStamp()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(entity, LowerCaseCatalogue.SAVE_STAMP);
		}
	}
	
	//method
	@Override
	public final void assertIsNotReferenced(final IEntity<?> entity) {
		if (isReferenced(entity)) {
			throw ReferencedArgumentException.forArgument(entity);
		}
	}
	
	//method
	@Override
	public final boolean canBeDeleted(final IEntity<?> entity) {
		return (isLoaded(entity) && !isReferenced(entity));
	}
	
	//method
	@Override
	public final boolean canBeInsertedIntoTable(final IEntity<?> entity) {
		return
		isNew(entity)
		&& !referencesUninsertedEntity(entity)
		&& !containsMandatoryAndEmptyBaseValuesOrBaseReferences(entity);
	}
	
	//method
	@Override
	public boolean containsMandatoryAndEmptyBaseValuesOrBaseReferences(final IEntity<?> entity) {
		return entity.technicalGetRefProperties().containsAny(this::isMandatoryAndEmptyBaseValueOrBaseReference);
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForEntity(final IEntity<?> entity) {
		return
		new EntityUpdateDTO(
			entity.getId(),
			entity.getSaveStamp(),
			entity.technicalGetRefProperties().to(IProperty::technicalToContentField)
		);
	}
	
	//method
	@Override
	public final IEntityHeadDTO createRecordHeadDTOForEntity(IEntity<?> entity) {
		return new EntityHeadDTO(entity.getId(), entity.getSaveStamp());
	}
	
	//method
	@Override
	public final IRecordDTO createRecordFor(final IEntity<?> entity) {
		return
		new RecordDTO(entity.getId(), entity.technicalGetRefProperties().to(IProperty::technicalToContentField));
	}
	
	//method
	@Override
	public final boolean isReferenced(final IEntity<?> entity) {
		return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
	}
	
	//method
	@Override
	public final <IMPL> boolean isReferencedInLocalData(final IEntity<IMPL> entity) {
		
		if (!entity.belongsToTable()) {
			return false;
		}
		
		for (final var t : entity.getRefParentTable().getParentDatabase().getRefTables()) {
			if (t.technicalGetRefEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	@Override
	public final <IMPL> boolean referencesGivenEntity(final IEntity<IMPL> sourceEntity, final IEntity<IMPL> entity) {
		return sourceEntity.technicalGetRefProperties().containsAny(p -> p.references(entity));
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity(final IEntity<?> entity) {
		return entity.technicalGetRefProperties().containsAny(IProperty::referencesUninsertedEntity);
	}
	
	//method
	private boolean isBaseValueOrBaseReference(final IProperty<?> property) {
		
		final var baseType = property.getType().getBaseType();
		
		return
		baseType == BasePropertyType.BASE_VALUE
		|| baseType == BasePropertyType.BASE_REFERENCE;
	}
	
	//method
	private boolean isMandatoryAndEmptyBaseValueOrBaseReference(final IProperty<?> property) {
		return
		isBaseValueOrBaseReference(property)
		&& propertyHelper.isMandatoryAndEmptyBoth(property);
	}
}
