//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.data.GlobalIdCreator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.systemapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class BaseEntity implements GroupCloseable, IEntity<DataImplementation> {
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//attribute
	private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();
	
	//attribute
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private ITable<DataImplementation, IEntity<DataImplementation>> parentTable;
	
	//optional attribute
	private String saveStamp;
	
	//multi-attribute
	private IContainer<Property> properties;
	
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
	public final ITable<DataImplementation, IEntity<DataImplementation>> getParentTable() {
		
		entityHelper.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final IContainer<IProperty<DataImplementation>> technicalGetRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties.asContainerWithElementsOfEvaluatedType();
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
	public final boolean isDeleted() {
		return (getState() == DatabaseObjectState.DELETED);
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return
		knowsParentTable()
		&& getParentTable().isLinkedWithRealDatabase();
	}
	
	//method
	@Override
	public final boolean isReferencedInPersistedData() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public final boolean knowsParentTable() {
		return (parentTable != null);
	}
	
	//method
	@Override
	public final void noteClose() {
		state = DatabaseObjectState.CLOSED;
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return ((Table<?>)getParentTable()).internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	final Property internalGetRefPropertyByName(final String name) {
		return properties.getRefFirst(p -> p.hasName(name));
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
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
	}
	
	//method
	final void internalSetId(final String id) {
		
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id = id;
	}
	
	//method
	final void internalSetLoaded() {
		
		entityHelper.assertIsNew(this);
		
		state = DatabaseObjectState.LOADED;
	}
	
	//method
	final void internalSetParentTable(final ITable<DataImplementation, IEntity<DataImplementation>> parentTable) {
		
		Validator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.parentTable = parentTable;
	}
	
	//method
	final void internalSetSaveStamp(final String saveStamp) {
		
		Validator.assertThat(saveStamp).thatIsNamed(LowerCaseCatalogue.SAVE_STAMP).isNotNull();
		
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
	}
	
	//method
	private void updateRecordForDelete() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().deleteRecordFromTable(
				getParentTable().getName(),
				entityHelper.createRecordHeadDTOForEntity(this)
			);
		}
	}
	
	//method
	private void updateStateForDelete() {
		state = DatabaseObjectState.DELETED;
	}
}
