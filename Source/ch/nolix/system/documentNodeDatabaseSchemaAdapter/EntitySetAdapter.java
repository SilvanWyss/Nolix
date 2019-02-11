//package declaration
package ch.nolix.system.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;
import ch.nolix.system.databaseSchemaAdapter.IEntitySetAdapter;

//class
public final class EntitySetAdapter implements IEntitySetAdapter {
	
	//constant
	private static final String ENTITY_SET_SPECIFICATION_VARIABLE_NAME = "entity set specification";
	
	//attribute
	private final DocumentNodeoid entitySetSpecification;
	
	//constructor
	EntitySetAdapter(final DocumentNodeoid entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.thatIsNamed(ENTITY_SET_SPECIFICATION_VARIABLE_NAME)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void addColumn(final Column column) {
		
		if (containsColumn(column.getHeader())) {
			throw
			new InvalidArgumentException(
				"entity set specification",
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
	public List<ColumnAdapter> getColumnAdapters() {
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
