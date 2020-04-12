//package declaration
package ch.nolix.system.fileNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;

//class
public final class ColumnAdapter implements IColumnAdapter {

	//attribute
	private final BaseNode columnSpecification;
	
	//constructor
	ColumnAdapter(final BaseNode columnSpecification) {
		
		Validator
		.assertThat(columnSpecification)
		.thatIsNamed("column specification")
		.isNotNull();
		
		this.columnSpecification = columnSpecification;
	}
	
	//method
	@Override
	public String getHeader() {
		return
		columnSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.HEADER)
		.getOneAttributeAsString();
	}
	
	//method
	public void setHeader(final String header) {
		columnSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.HEADER)
		.getRefOneAttribute()
		.setHeader(header);
	}
}
