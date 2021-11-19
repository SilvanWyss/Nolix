//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IColumn;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//class
public final class Table extends DatabaseObject implements ITable<Table, Entity, Property> {
	
	//attribute
	private final String name;
	
	//constructor
	Table(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public IDatabase<?, ?, ?, ?> getParentDatabase() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<Entity> getRefAllEntities() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public Entity getRefEntityById(final String id) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public IContainer<IColumn<Property>> getReferencingColumns() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public Table insert(final Entity entity) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {}
}
