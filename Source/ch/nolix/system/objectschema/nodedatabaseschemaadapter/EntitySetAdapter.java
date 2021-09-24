//package declaration
package ch.nolix.system.objectschema.nodedatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.databaseschemaadapter.Column;
import ch.nolix.system.objectschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.objectschema.databaseschemaadapter.IEntitySetAdapter;

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
		
		entitySetSpecification.addAttribute(createSpecificationFor(column));
	}
	
	//method
	public boolean containsColumn(final String header) {
		return
		entitySetSpecification.containsAttribute(
			a ->
				a.hasHeader(PascalCaseCatalogue.COLUMN)
				&& new ColumnAdapter(a).hasHeader(header)
		);
	}

	//method
	public void deleteColumn(final Column column) {
		entitySetSpecification
		.removeFirstAttribute(
			a ->
			a.hasHeader(PascalCaseCatalogue.COLUMN)
			&& new ColumnAdapter(a).hasSameHeaderAs(column)
		);
	}

	//method
	@Override
	public IColumnAdapter getColumnAdapter(final Column column) {
		return getColumnAdapters().getRefFirst(cc -> cc.hasSameHeaderAs(column));
	}
	
	//method
	@Override
	public LinkedList<IColumnAdapter> getColumnAdapters() {
		return
		entitySetSpecification
		.getRefAttributes(a -> a.hasHeader(PascalCaseCatalogue.COLUMN))
		.to(ColumnAdapter::new);
	}
	
	//method
	@Override
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute(PascalCaseCatalogue.NAME)
		.getOneAttributeHeader();
	}
	
	//method
	private Node createSpecificationFor(final Column column) {
		return
		Node.withAttribute(
			Node.withHeaderAndAttribute(PascalCaseCatalogue.HEADER, column.getHeader()),
			Node.withHeader(column.getPropertyKind().toString())
		);
	}
}
