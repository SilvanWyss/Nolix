//package-declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.bases.HeaderedElement;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Column<V> extends HeaderedElement implements Specified {

	//attribute
	private final PropertyoidType<V> propertyType;
	
	//constructor
	public Column(final String header, final PropertyoidType<V> propertyType) {
		
		super(header);
		
		Validator
		.suppose(propertyType)
		.thatIsNamed("property type")
		.isNotNull();
		
		this.propertyType = propertyType;
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			new StandardSpecification(
				PascalCaseNameCatalogue.HEADER,
				getHeader()
			),
			getPropertyType().getSpecification()
		);		
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
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public Class<V> getValueClass() {
		return getPropertyType().getValueClass();
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
	public boolean isReferenceColumn() {
		return getPropertyType().isReferenceType();
	}
}
