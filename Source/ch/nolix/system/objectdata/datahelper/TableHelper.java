//package declaration
package ch.nolix.system.objectdata.datahelper;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final EntityHelper entityHelper = new EntityHelper();
	
	//method
	@Override
	public void assertCanInsertGivenEntity(final ITable<?, ?> table, final IEntity<?> entity) {
		if (!canInsertGivenEntity(table, entity)) {
			throw new InvalidArgumentException(entity, "cannot be inserted into the table " + table.getNameInQuotes());
		}
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
