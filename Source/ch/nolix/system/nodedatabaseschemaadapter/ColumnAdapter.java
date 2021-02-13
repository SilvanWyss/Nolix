//package declaration
package ch.nolix.system.nodedatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseschemaadapter.Column;
import ch.nolix.system.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.schemadatatype.SchemaDataType;
import ch.nolix.system.schemadatatype.SchemaValueType;

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
