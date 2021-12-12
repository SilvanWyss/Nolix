//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.UUID;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
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
public abstract class Entity implements GroupCloseable, IEntity<DataImplementation> {
	
	//static attributes
	private static final IEntityHelper entityHelper = new EntityHelper();
	private static final EntityMutationValidator mutationValidator = new EntityMutationValidator();
	
	//attributes
	private String id = UUID.randomUUID().toString().replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING);
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	private final CloseController closeController = new CloseController(this);
	
	//optional attributes
	private Table parentTable;
	private String saveStamp;
	
	//method
	@Override
	public final boolean belongsToTable() {
		return (parentTable != null);
	}
	
	//method
	@Override
	public final void delete() {
		
		mutationValidator.assertCanDeleteEntity(this);
		
		deleteActually();
	}
	
	//method
	@Override
	public final String getId() {
		return id;
	}
	
	//method
	@Override
	public final ITable<DataImplementation> getParentTable() {
		
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
		//TODO: Implement.
		return null;
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
	public String getShortDescription() {
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
		return (belongsToTable() && getParentTable().isLinkedWithRealDatabase());
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
	final IDataAdapter internalGetRefDataAdapter() {
		return parentTable.internalGetRefDataAdapter();
	}
	
	//method
	final void internalSetEdited() {
		//TODO: Implement.
	}
	
	//method
	private void deleteActually() {
		//TODO: Implement.
	}
}
