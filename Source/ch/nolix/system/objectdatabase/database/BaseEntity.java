//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.databasevalidator.EntityValidator;
import ch.nolix.system.objectdatabase.entityflyweight.EntityFlyWeight;
import ch.nolix.system.objectdatabase.entityflyweight.VoidEntityFlyWeight;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IEntityFlyWeight;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class BaseEntity implements IEntity {
	
	//constant
	private static final EntityValidator ENTITY_VALIDATOR = new EntityValidator();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//attribute
	private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();
	
	//attribute
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//attribute
	private IEntityFlyWeight entityFlyweight = VoidEntityFlyWeight.INSTANCE;
	
	//optional attribute
	private ITable<IEntity> parentTable;
	
	//optional attribute
	private String saveStamp;
	
	//multi-attribute
	private IContainer<Property> properties;
	
	//method
	@Override
	public final boolean belongsToTable() {
		return (parentTable != null);
	}
	
	//method
	@Override
	public final void delete() {
		
		ENTITY_VALIDATOR.assertCanBeDeleted(this);
		
		/*
		 * An Entity must not be referenced on deletion. This is validated.
		 * But an Entity must update all back referencing properties to itself on deletion.
		 */
		updateBackReferencingPropertiesForDeletion();
		
		updateStateForDelete();
	}
	
	//method
	public final void deleteWithoutReferenceCheck() {
		
		updateBackReferencingPropertiesForDeletion();
		
		updateStateForDelete();
	}
	
	//method
	@Override
	public final String getId() {
		return id;
	}
	
	//method
	@Override
	public final IDatabase getOriParentDatabase() {
		return getOriParentTable().getOriParentDatabase();
	}
	
	//method
	@Override
	public final ITable<IEntity> getOriParentTable() {
		
		ENTITY_VALIDATOR.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public final String getSaveStamp() {
		
		ENTITY_VALIDATOR.assertHasSaveStamp(this);
		
		return saveStamp;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public final String getShortDescription() {
		return (getClass().getSimpleName() + " " + getId());
	}
	
	//method
	@Override
	public final boolean hasSaveStamp() {
		return (saveStamp != null);
	}
	
	//method
	@Override
	public final boolean isClosed() {
		return (getState() == DatabaseObjectState.CLOSED);
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		return (getState() == DatabaseObjectState.DELETED);
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return
		belongsToTable()
		&& getOriParentTable().isLinkedWithRealDatabase();
	}
	
	//method
	@Override
	public final boolean isReferencedInPersistedData() {
		
		if (!belongsToTable()) {
			return false;
		}
		
		return isReferencedInPersistedDataWhenBelongsToTable();
	}
	
	//method
	@Override
	public final IContainer<? extends IProperty> technicalGetRefProperties() {
		return getOriProperties();
	}
	
	//method
	@Override
	public String toString() {
		return (getClass().getSimpleName() + " (id: " + getId() + ")");
	}
	
	//method
	protected final void initialize() {
		extractPropertiesIfNotExtracted();
	}
	
	//method
	protected final void setInsertAction(final IAction insertAction) {
		
		setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid();
		
		entityFlyweight.setInsertAction(insertAction);
	}
	
	//method
	final void internalClose() {
		state = DatabaseObjectState.CLOSED;
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return ((Table<?>)getOriParentTable()).internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	final Property internalGetRefPropertyByName(final String name) {
		return getOriProperties().getOriFirst(p -> p.hasName(name));
	}
	
	//method declaration
	abstract IContainer<Property> internalLoadProperties();
	
	//method
	final void internalNoteInsert() {
		entityFlyweight.noteInsert();
	}
	
	//method
	final void internalSetEdited() {
		switch (getState()) {
			case NEW:
				break;
			case LOADED:
				state = DatabaseObjectState.EDITED;
				break;
			case EDITED:
				break;
			case DELETED:
				throw DeletedArgumentException.forArgument(this);
			case CLOSED:
				throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	final void internalSetId(final String id) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id = id;
	}
	
	//method
	final void internalSetLoaded() {
		
		entityHelper.assertIsNew(this);
		
		state = DatabaseObjectState.LOADED;
	}
	
	//method
	final void internalSetParentTable(final ITable<IEntity> parentTable) {
		
		GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.parentTable = parentTable;
		
		getOriProperties().forEach(Property::internalSetParentColumnFromParentTable);
	}
	
	//method
	final void internalSetSaveStamp(final String saveStamp) {
		
		GlobalValidator.assertThat(saveStamp).thatIsNamed(LowerCaseCatalogue.SAVE_STAMP).isNotNull();
		
		this.saveStamp = saveStamp;
	}
	
	//method
	final void internalUpdateProbableBackReferencesWhenIsNew() {
		getOriProperties().forEach(Property::internalUpdateProbableBackReferencesWhenIsNew);
	}
	
	//method
	private boolean extractedProperties() {
		return (properties != null);
	}
	
	//method
	private void extractPropertiesIfNotExtracted() {
		if (!extractedProperties()) {
			extractPropertiesWhenNotExtracted();
		}
	}
	
	//method
	private void extractPropertiesWhenNotExtracted() {
		
		properties = internalLoadProperties();
		
		properties.forEach(p -> p.internalSetParentEntity(this));
	}
	
	//method
	private IContainer<Property> getOriProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	private boolean isReferencedInPersistedDataWhenBelongsToTable() {
		
		final var lId = getId();
		
		return
		((Table<?>)getOriParentTable())
		.internalGetColumnsThatReferencesCurrentTable()
		.containsAny(c -> c.technicalContainsGivenValueInPersistedData(lId));
	}
	
	//method
	private void setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid() {
		if (entityFlyweight.isVoid()) {
			setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid();
		}
	}
	
	//method
	private void setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid() {
		entityFlyweight = new EntityFlyWeight();
	}
	
	//method
	private void updateBackReferenceForDeletion(final BackReference<?> backReference) {
		backReference.internalClear();
		backReference.setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private void updateBackReferencingPropertiesForDeletion() {
		entityHelper.getOriBackReferencingProperties(this).forEach(this::updateBackReferencingPropertyForDeletion);
	}
	
	//method
	private void updateBackReferencingPropertyForDeletion(
		final IProperty backReferencingProperty
	) {
		updateBackReferencingPropertyForDeletion((IBaseBackReference<?>)backReferencingProperty);
	}
	
	//method
	private void updateBackReferencingPropertyForDeletion(
		final IBaseBackReference<?> baseBackReference
	) {
		switch (baseBackReference.getType()) {
			case BACK_REFERENCE:
				updateBackReferenceForDeletion((BackReference<?>)baseBackReference);
				break;
			case OPTIONAL_BACK_REFERENCE:
				updateOptionalBackReferenceForDeletion((OptionalBackReference<?>)baseBackReference);
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
	}
	
	//method
	private void updateOptionalBackReferenceForDeletion(final OptionalBackReference<?> optionalBackReference) {
		optionalBackReference.internalClear();
		optionalBackReference.setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private void updateStateForDelete() {
		state = DatabaseObjectState.DELETED;
	}
}
