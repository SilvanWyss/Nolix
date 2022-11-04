//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdata.propertyhelper.MultiReferenceHelper;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

//class
public final class MultiReference<E extends IEntity<DataImplementation>> extends BaseReference<E>
implements IMultiReference<DataImplementation, E> {
	
	//static attribute
	private static final IMultiReferenceHelper multiReferenceHelper = new MultiReferenceHelper();
	
	//static method
	public static <E2 extends Entity> MultiReference<E2> forEntity(final Class<E2> type) {
		return new MultiReference<>(type.getSimpleName());
	}
	
	//static method
	public static MultiReference<BaseEntity> forEntityWithTableName(final String tableName) {
		return new MultiReference<>(tableName);
	}
	
	//attribute
	private boolean extractedReferencedEntityIds;
	
	//multi-attribute
	private final LinkedList<String> referencedEntityIds = new LinkedList<>();
	
	//constructor
	private MultiReference(final String referencedTableName) {
		super(referencedTableName);
	}
	
	//method
	@Override
	public void addEntity(final E entity) {
		
		assertCanAddEntity(entity);
		
		updateStateForAddEntity(entity);
		
		updateRecordForAddEntity(entity);
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	@Override
	public void clear() {
		if (containsAny()) {
			clearWhenContainsAny();
		}
	}
	
	//method
	@Override
	public IContainer<E> getReferencedEntities() {
		return getReferencedEntityIds().to(getReferencedTable()::getRefEntityById);
	}
	
	//method
	@Override
	public IContainer<String> getReferencedEntityIds() {
		
		extractReferencedEntityIdsIfNeeded();
		
		return referencedEntityIds;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_REFERENCE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return getReferencedEntityIds().isEmpty();
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public boolean references(final IEntity<?> entity) {
		
		if (entity == null) {
			return false;
		}
		
		return getReferencedEntityIds().containsAnyEqualing(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity() {
		return getReferencedEntities().containsOnly(IEntity::belongsToTable);
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName(), StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		GlobalValidator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNull();
	}
	
	//method
	private void assertCanAddEntity(final E entity) {
		multiReferenceHelper.assertCanAddGivenEntity(this, entity);
	}
	
	//method
	private void assertCanClear() {
		multiReferenceHelper.assertCanClear(this);
	}
	
	//method
	private void clearWhenContainsAny() {
		
		assertCanClear();
		
		updateStateForClear();
		
		updateRecordForClear();
		
		internalSetParentEntityAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private boolean extractedReferencedEntityIds() {
		return extractedReferencedEntityIds;
	}
	
	//method
	private void extractReferencedEntityIdsIfNeeded() {
		if (shouldExtractReferencedEntityIds()) {
			extractReferencedEntityIdsWhenNotLoaded();
		}
	}
	
	//method
	private void extractReferencedEntityIdsWhenNotLoaded() {
		
		extractedReferencedEntityIds = true;
		
		referencedEntityIds.addAtEnd(loadReferencedEntityIds());
	}
	
	//method
	private IContainer<String> loadReferencedEntityIds() {
		return
		internalGetRefDataAndSchemaAdapter().loadAllMultiReferenceEntriesForRecord(
			getParentEntity().getParentTableName(),
			getParentEntity().getId(),
			getName()
		);
	}
	
	//method
	private boolean shouldExtractReferencedEntityIds() {
		return
		!extractedReferencedEntityIds()
		&& multiReferenceHelper.belongsToLoadedEntity(this);
	}
	
	//method
	private void updateRecordForAddEntity(final E entity) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().insertEntryIntoMultiReference(
				getParentEntity().getParentTableName(),
				getParentEntity().getId(),
				getName(),
				entity.getId()
			);
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteEntriesFromMultiReference(
				getParentEntity().getParentTableName(),
				getParentEntity().getId(),
				getName()
			);
		}
	}
	
	//method
	private void updateStateForAddEntity(final E entity) {
		referencedEntityIds.addAtEnd(entity.getId());
	}
	
	//method
	private void updateStateForClear() {
		referencedEntityIds.clear();
	}
}
