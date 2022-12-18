//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.system.sqlrawdata.databasedto.EntityHeadDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.system.sqlrawdata.databasedto.RecordDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;

//class
public final class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//method
	@Override
	public boolean allNewAndEditedMandatoryPropertiesAreSet(final IEntity<?> entity) {
		
		if (isNewOrEdited(entity)) {
			return
			entity.technicalGetRefProperties().containsOnly(propertyHelper::isSetForCaseIsNewOrEditedAndMandatory);
		}
		
		return true;
	}
	
	//method
	@Override
	public boolean canBeDeleted(final IEntity<?> entity) {
		return (isLoaded(entity) && !isReferenced(entity));
	}
	
	//method
	@Override
	public boolean canBeInsertedIntoTable(final IEntity<?> entity) {
		return
		isNew(entity)
		&& entity.belongsToTable();
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
			getRefEditedProperties(entity).to(IProperty::technicalToContentField)
		);
	}
	
	//method
	@Override
	public IEntityHeadDTO createRecordHeadDTOForEntity(IEntity<?> entity) {
		return new EntityHeadDTO(entity.getId(), entity.getSaveStamp());
	}
	
	//method
	@Override
	public IRecordDTO createRecordFor(final IEntity<?> entity) {
		return
		new RecordDTO(entity.getId(), entity.technicalGetRefProperties().to(IProperty::technicalToContentField));
	}
	
	//method
	@Override
	public <IMPL> IContainer<IProperty<IMPL>> getRefBackReferencingProperties(final IEntity<IMPL> entity) {
		return entity.technicalGetRefProperties().toFromMany(IProperty::getRefBackReferencingProperties);
	}
	
	//method
	@Override
	public <IMPL> IContainer<? extends IProperty<IMPL>> getRefEditedProperties(final IEntity<IMPL> entity) {
		return entity.technicalGetRefProperties().getRefSelected(propertyHelper::isEdited);
	}
	
	//method
	@Override
	public boolean isReferenced(final IEntity<?> entity) {
		return (isReferencedInLocalData(entity) || entity.isReferencedInPersistedData());
	}
	
	//method
	@Override
	public <IMPL> boolean isReferencedInLocalData(final IEntity<IMPL> entity) {
		
		if (!entity.belongsToTable()) {
			return false;
		}
		
		for (final var t : entity.getRefParentTable().getRefParentDatabase().getRefTables()) {
			if (t.technicalGetRefEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	@Override
	public <IMPL> boolean referencesGivenEntity(final IEntity<IMPL> sourceEntity, final IEntity<IMPL> entity) {
		return sourceEntity.technicalGetRefProperties().containsAny(p -> p.referencesEntity(entity));
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity(final IEntity<?> entity) {
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
