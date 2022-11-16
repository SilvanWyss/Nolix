//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class BaseEntity implements IEntity<DataImplementation> {
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//attribute
	private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();
	
	//attribute
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//optional attribute
	private ITable<DataImplementation, IEntity<DataImplementation>> parentTable;
	
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
		
		entityHelper.assertCanBeDeleted(this);
		
		updateStateForDelete();
		
		internalSetEdited();
		
		updateRecordForDelete();
	}
	
	//method
	@Override
	public final String getId() {
		return id;
	}
	
	//method
	@Override
	public final ITable<DataImplementation, IEntity<DataImplementation>> getRefParentTable() {
		
		entityHelper.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public final String getSaveStamp() {
		
		entityHelper.assertHasSaveStamp(this);
		
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
		&& getRefParentTable().isLinkedWithRealDatabase();
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
	public final IContainer<? extends IProperty<DataImplementation>> technicalGetRefProperties() {
		return getRefProperties();
	}
	
	//method
	protected final void initialize() {
		extractPropertiesIfNotExtracted();
	}
	
	//method
	final void internalClose() {
		state = DatabaseObjectState.CLOSED;
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return ((Table<?>)getRefParentTable()).internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	final Property internalGetRefPropertyByName(final String name) {
		return getRefProperties().getRefFirst(p -> p.hasName(name));
	}
	
	//method declaration
	abstract IContainer<Property> internalLoadProperties();
	
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
	final void internalSetParentTable(final ITable<DataImplementation, IEntity<DataImplementation>> parentTable) {
		
		GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.parentTable = parentTable;
		
		getRefProperties().forEach(Property::internalSetParentColumnFromParentTable);
	}
	
	//method
	final void internalSetSaveStamp(final String saveStamp) {
		
		GlobalValidator.assertThat(saveStamp).thatIsNamed(LowerCaseCatalogue.SAVE_STAMP).isNotNull();
		
		this.saveStamp = saveStamp;
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
	private IContainer<Property> getRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	private boolean isReferencedInPersistedDataWhenBelongsToTable() {
		
		final var lId = getId();
		
		return
		((Table<?>)getRefParentTable())
		.internalGetColumnsThatReferencesCurrentTable()
		.containsAny(c -> c.technicalContainsGivenValueInPersistedData(lId));
	}
	
	//method
	private void updateRecordForDelete() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteRecordFromTable(
				getRefParentTable().getName(),
				entityHelper.createRecordHeadDTOForEntity(this)
			);
		}
	}
	
	//method
	private void updateStateForDelete() {
		state = DatabaseObjectState.DELETED;
	}
}
