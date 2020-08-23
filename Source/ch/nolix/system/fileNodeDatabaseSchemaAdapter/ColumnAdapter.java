//package declaration
package ch.nolix.system.fileNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.Column;
import ch.nolix.system.databaseSchemaAdapter.IColumnAdapter;
import ch.nolix.system.schemaDataType.SchemaDataType;
import ch.nolix.system.schemaDataType.SchemaValueType;

//class
public final class ColumnAdapter implements IColumnAdapter {

	//attribute
	private final BaseNode columnNode;
	
	//constructor
	ColumnAdapter(final BaseNode columnSpecification) {
		
		Validator
		.assertThat(columnSpecification)
		.thatIsNamed("column specification")
		.isNotNull();
		
		this.columnNode = columnSpecification;
	}
	
	//method
	@Override
	public SchemaDataType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		return new SchemaValueType<>(Object.class);
	}
	
	//method
	@Override
	public String getHeader() {
		return
		columnNode
		.getRefFirstAttribute(PascalCaseNameCatalogue.HEADER)
		.getOneAttributeHeader();
	}
	
	//method
	public void setHeader(final String header) {
		columnNode
		.getRefFirstAttribute(PascalCaseNameCatalogue.HEADER)
		.getRefOneAttribute()
		.setHeader(header);
	}
	
	//method
	@Override
	public Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
