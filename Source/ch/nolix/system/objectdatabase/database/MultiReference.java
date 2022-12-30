//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.MultiReferenceHelper;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

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
	private final LinkedList<IMultiReferenceEntry<DataImplementation, E>> localEntries = new LinkedList<>();
	
	//constructor
	private MultiReference(final String referencedTableName) {
		super(referencedTableName);
	}
	
	//method
	@Override
	public void addEntity(final E entity) {
		
		assertCanAddEntity(entity);
		
		updateStateForAddEntity(entity);
		
		updateProbableBackReferencingPropertyForSetOrAddedEntity(entity);
		
		setAsEditedAndRunProbableUpdateAction();
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
	public IContainer<IProperty<DataImplementation>> getRefBackReferencingProperties() {
		
		final var backReferencingProperties = new LinkedList<IProperty<DataImplementation>>();
		
		for (final var re : getReferencedEntities()) {
			
			final var backReferencingProperty =
			re.technicalGetRefProperties().getRefFirstOrNull(p -> p.referencesBackProperty(this));
			
			if (backReferencingProperty != null) {
				backReferencingProperties.addAtEnd(backReferencingProperty);
			}
		}
		
		return backReferencingProperties;
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
		
		return localEntries.to(IMultiReferenceEntry::getReferencedEntityId);
	}
	
	//method
	@Override
	public IContainer<? extends IMultiReferenceEntry<DataImplementation, E>> getRefLocalEntries() {
		return localEntries;
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
	public boolean referencesEntity(final IEntity<?> entity) {
		
		if (entity == null) {
			return false;
		}
		
		return getReferencedEntityIds().containsAnyEqualing(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity() {
		return getReferencedEntities().containsAny(e -> !e.belongsToTable());
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		return new ContentFieldDTO(getName());
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		GlobalValidator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNull();
	}
	
	//method
	@Override
	void internalUpdateProbableBackReferencesWhenIsNew() {
		if (containsAny()) {
			for (final var e : getReferencedEntities()) {
				updateProbableBackReferenceForSetOrAddedEntity(e);
			}
		}
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
		
		setAsEditedAndRunProbableUpdateAction();
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
		
		localEntries.addAtEnd(loadReferencedEntityIds());
	}
	
	//method
	private IContainer<IMultiReferenceEntry<DataImplementation, E>> loadReferencedEntityIds() {
		return
		internalGetRefDataAndSchemaAdapter().loadAllMultiReferenceEntriesForRecord(
			getRefParentEntity().getParentTableName(),
			getRefParentEntity().getId(),
			getName()
		)
		.to(rei -> MultiReferenceEntry.loadedEntryForMultiReferenceAndReferencedEntityId(this, rei));
	}
	
	//method
	private boolean shouldExtractReferencedEntityIds() {
		return
		!extractedReferencedEntityIds()
		&& multiReferenceHelper.belongsToLoadedEntity(this);
	}
	
	//method
	private void updateProbableBackReferencingPropertyForSetOrAddedEntity(final E entity) {
		for (final var p : entity.technicalGetRefProperties()) {
			if (p.getType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE) {
				
				final var baseBackReference = (BaseBackReference<?>)p;
				
				if (
					baseBackReference.getBackReferencedTableName().equals(getRefParentEntity().getParentTableName())
					&& baseBackReference.getBackReferencedPropertyName().equals(getName())
				) {
					
					switch (baseBackReference.getType()) {
						case BACK_REFERENCE:
							final var backReference = (BackReference<?>)baseBackReference;
							backReference.internalSetDirectlyBackReferencedEntityId(getRefParentEntity().getId());
							backReference.setAsEditedAndRunProbableUpdateAction();
							break;
						case OPTIONAL_BACK_REFERENCE:
							final var optionalBackReference = (OptionalBackReference<?>)baseBackReference;
							optionalBackReference.internalSetDirectlyBackReferencedEntityId(getRefParentEntity().getId());
							optionalBackReference.setAsEditedAndRunProbableUpdateAction();
							break;
						case MULTI_BACK_REFERENCE:
							/*
							 * Does nothing.
							 * MultiBackReferences do not need to be updated, because MultiBackReferences do not have redundancies.
							 */
							break;
						default:
							throw InvalidArgumentException.forArgument(baseBackReference.getType());
					}
					
					break;
				}
			}
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteEntriesFromMultiReference(
				getRefParentEntity().getParentTableName(),
				getRefParentEntity().getId(),
				getName()
			);
		}
	}
	
	//method
	private void updateStateForAddEntity(final E entity) {
		localEntries.addAtEnd(MultiReferenceEntry.newEntryForMultiReferenceAndReferencedEntityId(this, entity.getId()));
	}
	
	//method
	private void updateStateForClear() {
		//TODO: Implement.
	}
}
