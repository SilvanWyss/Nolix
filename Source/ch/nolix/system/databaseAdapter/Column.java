//package-declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseAPI.IElement;
import ch.nolix.system.dataTypes.DataType;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class Column<V> implements Headered, IElement {

	//attributes
	private final String header;
	private final DataType<V> propertyType;
	
	//constructor
	public Column(final String header, final DataType<V> propertyType) {
		
		this.header = Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank().andReturn();
		
		Validator
		.suppose(propertyType)
		.thatIsNamed("property type")
		.isNotNull();
		
		this.propertyType = propertyType;
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return getDataType().canReference(entity);
	}
	
	//method
	@Override
	public List<Node> getAttributes() {
		return
		new List<>(
			new Node(
				PascalCaseNameCatalogue.HEADER,
				getHeader()
			),
			new Node(getDataType().getPropertyKind().toString())
		);
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return getDataType().getPropertyKind();
	}
	
	//method
	public DataType<V> getDataType() {
		return propertyType;
	}
	
	//method
	@Override
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public Class<V> getValueClass() {
		return getDataType().getRefContentClass();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return getDataType().isAnyValueType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return getDataType().isAnyReferenceType();
	}
}
