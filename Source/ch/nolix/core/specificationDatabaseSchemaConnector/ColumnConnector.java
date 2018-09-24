//package declaration
package ch.nolix.core.specificationDatabaseSchemaConnector;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.databaseSchemaAdapter.IColumnConnector;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.primitive.validator2.Validator;

//class
public final class ColumnConnector implements IColumnConnector {

	//attribute
	private final DocumentNodeoid columnSpecification;
	
	//package-visible constructor
	ColumnConnector(final DocumentNodeoid columnSpecification) {
		
		Validator
		.suppose(columnSpecification)
		.thatIsNamed("column specification")
		.isInstance();
		
		this.columnSpecification = columnSpecification;
	}
	
	//method
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
