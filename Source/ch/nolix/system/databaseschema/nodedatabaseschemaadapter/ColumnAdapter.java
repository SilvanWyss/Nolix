//package declaration
package ch.nolix.system.databaseschema.nodedatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.databaseschema.databaseschemaadapter.Column;
import ch.nolix.system.databaseschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaDataType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedSchemaValueType;

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
	public ParametrizedSchemaDataType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		return new ParametrizedSchemaValueType<>(Object.class);
	}
	
	//method
	@Override
	public String getHeader() {
		return
		columnNode
		.getRefFirstAttribute(PascalCaseCatalogue.HEADER)
		.getOneAttributeHeader();
	}
	
	//method
	public void setHeader(final String header) {
		columnNode
		.getRefFirstAttribute(PascalCaseCatalogue.HEADER)
		.getRefOneAttribute()
		.setHeader(header);
	}
	
	//method
	@Override
	public Column toColumn() {
		return new Column(getHeader(), getDataType());
	}
}
