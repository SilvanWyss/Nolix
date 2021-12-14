//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IColumn;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataAdapter;

//class
public final class Table<E extends IEntity<DataImplementation>> extends ImmutableDatabaseObject
implements ITable<DataImplementation, E> {
	
	//attributes
	private final String name;
	private final Database parentDatabase;
	
	//constructor
	Table(final String name, final Database parentDatabase) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(parentDatabase).thatIsNamed("parent Database").isNotNull();
		
		this.name = name;
		this.parentDatabase = parentDatabase;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public IDatabase<DataImplementation> getParentDatabase() {
		return parentDatabase;
	}
	
	//method
	@Override
	public IContainer<E> getRefAllEntities() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public E getRefEntityById(final String id) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<IColumn<DataImplementation>> getReferencingColumns() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean hasInsertedEntityWithId(final String id) {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public ITable<DataImplementation, E> insert(final E entity) {
		//TODO: Implement.
		return null;
	}
	
	//method
	IDataAdapter internalGetRefDataAdapter() {
		return parentDatabase.internalGetRefDataAdapter();
	}
}
