//package declaration
package ch.nolix.core.specificationDatabaseSchemaConnector;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseSchemaAdapter.Column;
import ch.nolix.core.databaseSchemaAdapter.IColumnConnector;
import ch.nolix.core.databaseSchemaAdapter.IEntitySetConnector;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySetConnector
implements IEntitySetConnector<IFunction> {
	
	//constant
	private static final String ENTITY_SET_SPECIFICATION_VARIABLE_NAME = "entity set specification";
	
	//attribute
	private final Specification entitySetSpecification;
	
	//constructor
	EntitySetConnector(final Specification entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.thatIsNamed(ENTITY_SET_SPECIFICATION_VARIABLE_NAME)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}

	//method
	public IFunction createCommandForAdd(final Column column) {
		return
		() -> entitySetSpecification.addAttribute(column.getSpecification());
	}

	//method
	public IFunction createCommandForDelete(final Column column) {
		return
		() ->
		entitySetSpecification
		.removeFirstAttribute(
			a ->
			a.hasHeader(PascalCaseNameCatalogue.COLUMN)
			&& new ColumnConnector(a).hasSameHeaderAs(column)
		);
	}

	//method
	public IFunction createCommandForRename(final String name) {
		return
		() ->
		entitySetSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getRefOneAttribute()
		.setHeader(name);
	}

	//method
	public IColumnConnector<IFunction> getColumnConnector(final Column column) {
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
