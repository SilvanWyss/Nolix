//package declaration
package ch.nolix.system.objectschema.nodedatabaseschemaadapter;

import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.databaseschemaadapter.Column;
import ch.nolix.system.objectschema.databaseschemaadapter.IColumnAdapter;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;

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
	public ParametrizedPropertyType<?> getDataType() {
		
		//TODO: return SchemaDataTypeFactory.fromSpecification(...)
		//return new ParametrizedValueType<>(Object.class);
		return null;
	}
	
	//method
	@Override
	public String getName() {
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
		return new Column(getName(), getDataType());
	}
}
