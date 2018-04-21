//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;

//interface
public interface IDatabaseSchemaConnector<C> {
		
	//abstract method
	public abstract C createCommandForAddColumn(Column column);
	
	//abstract method
	public abstract C createCommandForAddEntitySet(EntitySet entitySet);

	//abstract method
	public abstract C createCommandForDeleteColumn(Column column);
	
	//abstract method
	public abstract C createCommandForDeleteEntitySet(EntitySet entitySet);
	
	//abstract method
	public abstract C createCommandForRenameColumn(
		EntitySet entitySet,
		String columnHeader,
		String newColumnHeader
	);
	
	//abstract method
	public abstract C createCommandForRenameEntitySet(
		String entitySetName,
		String newEntitySetName
	);
	
	//abstract method
	public abstract List<EntitySet> getRefEntitySets();
	
	//abstract method
	public abstract void run(IContainer<C> commands);
}
