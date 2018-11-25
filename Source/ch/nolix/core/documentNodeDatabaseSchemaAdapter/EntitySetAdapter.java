//package declaration
package ch.nolix.core.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseSchemaAdapter.Column;
import ch.nolix.core.databaseSchemaAdapter.IColumnConnector;
import ch.nolix.core.databaseSchemaAdapter.IEntitySetConnector;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitySetAdapter implements IEntitySetConnector {
	
	//constant
	private static final String ENTITY_SET_SPECIFICATION_VARIABLE_NAME = "entity set specification";
	
	//attribute
	private final DocumentNodeoid entitySetSpecification;
	
	//constructor
	EntitySetAdapter(final DocumentNodeoid entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.thatIsNamed(ENTITY_SET_SPECIFICATION_VARIABLE_NAME)
		.isInstance();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void addColumn(final Column column) {
		
		if (containsColumn(column.getHeader())) {
			throw
			new InvalidStateException(
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
	public IColumnConnector getColumnConnector(final Column column) {
		return getColumnConnectors().getRefFirst(cc -> cc.hasSameHeaderAs(column));
	}
	
	//method
	public List<ColumnAdapter> getColumnConnectors() {
		return
		entitySetSpecification
		.getRefAttributes(a -> a.hasHeader(PascalCaseNameCatalogue.COLUMN))
		.to(a -> new ColumnAdapter(a));
	}
	
	//method
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}
}
