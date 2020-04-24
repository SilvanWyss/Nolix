//package declaration
package ch.nolix.system.fileNodeDatabaseSchemaAdapter;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;
import ch.nolix.system.databaseSchemaAdapter.IEntitySetAdapter;

//class
public final class EntitySetAdapter implements IEntitySetAdapter {
	
	//constant
	private static final String ENTITY_SET_SPECIFICATION_VARIABLE_NAME = "entity set specification";
	
	//attribute
	private final BaseNode entitySetSpecification;
	
	//constructor
	EntitySetAdapter(final BaseNode entitySetSpecification) {
		
		Validator
		.assertThat(entitySetSpecification)
		.thatIsNamed(ENTITY_SET_SPECIFICATION_VARIABLE_NAME)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void addColumn(final Column column) {
		
		if (containsColumn(column.getHeader())) {
			throw
			new InvalidArgumentException(
				ENTITY_SET_SPECIFICATION_VARIABLE_NAME,
				"contains already a column with the header" + column.getHeaderInQuotes()
			);
		}
		
		entitySetSpecification.addAttribute(column.getSpecification());
	}
	
	//method
	public boolean containsColumn(final String header) {
		return
		entitySetSpecification.containsAttribute(
			a ->
				a.hasHeader(PascalCaseNameCatalogue.COLUMN)
				&& new ColumnAdapter(a).hasHeader(header)
		);
	}

	//method
	public void deleteColumn(final Column column) {
		entitySetSpecification
		.removeFirstAttribute(
			a ->
			a.hasHeader(PascalCaseNameCatalogue.COLUMN)
			&& new ColumnAdapter(a).hasSameHeaderAs(column)
		);
	}

	//method
	@Override
	public IColumnAdapter getColumnAdapter(final Column column) {
		return getColumnAdapters().getRefFirst(cc -> cc.hasSameHeaderAs(column));
	}
	
	//method
	public LinkedList<ColumnAdapter> getColumnAdapters() {
		return
		entitySetSpecification
		.getRefAttributes(a -> a.hasHeader(PascalCaseNameCatalogue.COLUMN))
		.to(a -> new ColumnAdapter(a));
	}
	
	//method
	@Override
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}
}
