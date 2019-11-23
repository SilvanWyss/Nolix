//package-declaration
package ch.nolix.system.entity;

import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseAPI.IElement;

//class
public final class Column<V> implements Headered, IElement {

	//attributes
	private final String header;
	private final PropertyoidType<V> propertyType;
	
	//constructor
	public Column(final String header, final PropertyoidType<V> propertyType) {
		
		this.header = Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank().andReturn();
		
		Validator
		.suppose(propertyType)
		.thatIsNamed("property type")
		.isNotNull();
		
		this.propertyType = propertyType;
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return getPropertyType().captionsPropertyThatCanReference(entity);
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
			getPropertyType().getSpecification()
		);
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return getPropertyType().getPropertyKind();
	}
	
	//method
	public PropertyoidType<V> getPropertyType() {
		return propertyType;
	}
	
	//method
	@Override
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public Class<V> getValueClass() {
		return getPropertyType().getValueClass();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return getPropertyType().isAnyDataType();
	}
	
	//method
	public boolean isAnyDataOrReferenceColumn() {
		return getPropertyType().isAnyDataOrReferenceType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return getPropertyType().isAnyReferenceType();
	}
	
	//method
	public boolean isDataColumn() {
		return getPropertyType().isDataType();
	}
	
	//method
	public boolean isIdColumn() {
		return getPropertyType().isIdType();
	}
	
	//method
	public boolean isMultiDataColumn() {
		return getPropertyType().isMultiDataType();
	}
	
	//method
	public boolean isMultiReferenceColumn() {
		return getPropertyType().isMultiReferenceType();
	}
	
	//method
	public boolean isOptionalDataColumn() {
		return getPropertyType().isOptionalDataType();
	}
	
	//method
	public boolean isOptionalReferenceColumn() {
		return getPropertyType().isOptionalReferenceType();
	}
	
	//method
	public boolean isReferenceColumn() {
		return getPropertyType().isReferenceType();
	}
}
