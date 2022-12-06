//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final EntityHelper entityHelper = new EntityHelper();
	
	//method
	@Override
	public boolean allNewAndEditedMandatoryPropertiesAreSet(final ITable<?, ?> table) {
		return
		table
		.technicalGetRefEntitiesInLocalData()		
		.containsOnly(
			e -> entityHelper.allNewAndEditedMandatoryPropertiesAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
		);
	}
	
	//method
	@Override
	public boolean canInsertEntity(final ITable<?, ?> table) {
		return table.isOpen();
	}
	
	//method
	@Override
	public boolean canInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity) {
		return
		canInsertEntity(table)
		&& entityHelper.canBeInsertedIntoTable(entity)
		&& !hasInsertedGivenEntityInLocalData(table, entity);
	}
	
	//method
	@Override
	public boolean containsEntityWithGivenIdInLocalData(final ITable<?, ?> table, final String id) {
		return table.technicalGetRefEntitiesInLocalData().containsAny(e -> e.hasId(id));
	}
	
	//method
	@Override
	public <IMPL, E extends IEntity<IMPL>> IContainer<IColumn<IMPL>> getColumsThatReferenceGivenTable(
		final ITable<IMPL, E> table
	) {
		
		final var columns = new LinkedList<IColumn<IMPL>>();
		for (final var t : table.getParentDatabase().getRefTables()) {
			for (final var c : t.getColumns()) {
				if (c.getParametrizedPropertyType().referencesTable(table)) {
					columns.addAtEnd(c);
				}
			}
		}
		
		return columns;
	}
	
	//method
	@Override
	public boolean hasChanges(final ITable<?, ?> table) {
		return table.technicalGetRefEntitiesInLocalData().containsAny(e -> !entityHelper.isLoaded(e));
	}
	
	//method
	@Override
	public boolean hasInsertedGivenEntityInLocalData(final ITable<?, ?> table, final IEntity<?> entity) {
		return containsEntityWithGivenIdInLocalData(table, entity.getId());
	}
}
