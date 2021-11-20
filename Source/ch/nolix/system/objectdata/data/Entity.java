//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.UUID;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;

//class
public abstract class Entity extends DatabaseObject implements IEntity<DataImplementation> {
	
	//static attributes
	private static final IEntityHelper entityHelper = new EntityHelper();
	private static final EntityMutationValidator mutationValidator = new EntityMutationValidator();
	
	//attributes
	private String id = UUID.randomUUID().toString().replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING);
	
	//optional attribute
	private Table parentTable;
	
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
	public final IContainer<IProperty<DataImplementation>> getRefProperties() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public String getShortDescription() {
		return (getClass().getSimpleName() + " " + getId());
	}
	
	//method
	@Override
	public final boolean isBackReferenced() {
		//TODO: Implement.
		return false;
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
	protected final void noteCloseDatabaseObject() {}
	
	//method
	private void deleteActually() {
		//TODO: Implement.
	}
}
