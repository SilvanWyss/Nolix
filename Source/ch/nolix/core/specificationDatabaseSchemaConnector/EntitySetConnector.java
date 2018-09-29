//package declaration
package ch.nolix.core.specificationDatabaseSchemaConnector;

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
public final class EntitySetConnector implements IEntitySetConnector {
	
	//constant
	private static final String ENTITY_SET_SPECIFICATION_VARIABLE_NAME = "entity set specification";
	
	//attribute
	private final DocumentNodeoid entitySetSpecification;
	
	//constructor
	EntitySetConnector(final DocumentNodeoid entitySetSpecification) {
		
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
				&& new ColumnConnector(a).hasHeader(header)
		);
	}

	//method
	public void deleteColumn(final Column column) {
		entitySetSpecification
		.removeFirstAttribute(
			a ->
			a.hasHeader(PascalCaseNameCatalogue.COLUMN)
			&& new ColumnConnector(a).hasSameHeaderAs(column)
		);
	}

	//method
	public IColumnConnector getColumnConnector(final Column column) {
		return getColumnConnectors().getRefFirst(cc -> cc.hasSameHeaderAs(column));
	}
	
	//method
	public List<ColumnConnector> getColumnConnectors() {
		return
		entitySetSpecification
		.getRefAttributes(a -> a.hasHeader(PascalCaseNameCatalogue.COLUMN))
		.to(a -> new ColumnConnector(a));
	}
	
	//method
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}
}
