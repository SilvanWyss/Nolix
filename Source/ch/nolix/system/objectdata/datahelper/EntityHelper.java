//package declaration
package ch.nolix.system.objectdata.datahelper;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordDeletionDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;

//class
public class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//method
	@Override
	public final void assertBelongsToTable(final IEntity<?> entity) {
		if (!entity.knowsParentTable()) {
			throw new ArgumentDoesNotBelongToParentException(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public final void assertCanBeDeleted(final IEntity<?> entity) {
		if (!canBeDeleted(entity)) {
			throw new InvalidArgumentException(entity, "cannot be deleted");
		}
	}
	
	//method
	@Override
	public final void assertDoesNotBelongToTable(final IEntity<?> entity) {
		if (entity.knowsParentTable()) {
			throw new ArgumentBelongsToParentException(entity, entity.getParentTable());
		}
	}
	
	//method
	@Override
	public final void assertHasSaveStamp(final IEntity<?> entity) {
		if (!entity.hasSaveStamp()) {
			throw new ArgumentDoesNotHaveAttributeException(entity, LowerCaseCatalogue.SAVE_STAMP);
		}
	}
	
	//method
	@Override
	public final void assertIsNotReferenced(final IEntity<?> entity) {
		if (isReferenced(entity)) {
			throw new ReferencedArgumentException(entity);
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
	public final IRecordDeletionDTO createRecordDeletionDTOForEntity(IEntity<?> entity) {
		return new RecordDeletionDTO(entity.getId(), entity.getSaveStamp());
	}
	
	//method
	@Override
	public final IRecordDTO createRecordFor(final IEntity<?> entity) {
		return
		new RecordDTO(entity.getId(), entity.technicalGetRefProperties().to(propertyHelper::createContentFieldFor));
	}
	
	//method
	@Override
	public final boolean isReferenced(final IEntity<?> entity) {
		return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
	}
	
	//method
	@Override
	public final <IMPL> boolean isReferencedInLocalData(final IEntity<IMPL> entity) {
		
		if (!entity.knowsParentTable()) {
			return false;
		}
		
		for (final var t : entity.getParentTable().getParentDatabase().technicalGetRefTablesInLocalData()) {
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
