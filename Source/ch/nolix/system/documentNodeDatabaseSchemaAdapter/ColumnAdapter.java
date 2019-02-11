//package declaration
package ch.nolix.system.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;

//class
public final class ColumnAdapter implements IColumnAdapter {

	//attribute
	private final DocumentNodeoid columnSpecification;
	
	//package-visible constructor
	ColumnAdapter(final DocumentNodeoid columnSpecification) {
		
		Validator
		.suppose(columnSpecification)
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
