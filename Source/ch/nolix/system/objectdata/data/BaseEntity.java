//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.UUID;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.techapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataAdapter;

//class
public abstract class BaseEntity implements GroupCloseable, IEntity<DataImplementation> {
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//static attribute
	private static final PropertyExtractor propertyExtractor = new PropertyExtractor();
	
	//attribute
	private String id = UUID.randomUUID().toString().replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING);
	
	//attribute
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private Table<IEntity<DataImplementation>> parentTable;
	
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
	public final IContainer<IProperty<DataImplementation>> getRefProperties() {
		
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
	public final boolean isBackReferenced() {
		//TODO: Implement.
		return false;
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
	public final void noteClose() {
		state = DatabaseObjectState.CLOSED;
	}
	
	//method
	final Table<?> internalGetParentTable() {
		
		entityHelper.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	final IDataAdapter internalGetRefDataAdapter() {
		return internalGetParentTable().internalGetRefDataAdapter();
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
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
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
		properties = propertyExtractor.getRefPropertiesFrom(this);
	}
	
	//method
	private void updateRecordForDelete() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAdapter().deleteRecordFromTable(
				getParentTable().getName(),
				entityHelper.createRecordDeletionDTOForEntity(this)
			);
		}
	}
	
	//method
	private void updateStateForDelete() {
		state = DatabaseObjectState.DELETED;
	}
}
