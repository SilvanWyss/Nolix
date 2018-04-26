//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.PropertyKind;
import ch.nolix.core.databaseAdapter.PropertyoidType;
import ch.nolix.core.interfaces.Headerable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Column implements Headerable<Column>, Specified {
	
	//attribute
	private final EntitySet entitySet;
	private String header;
	private final PropertyoidType<?> valueType;
		
	//package-visible constructor
	Column(
		final EntitySet entitySet,
		final String header,
		final PropertyoidType<?> valueType
	) {

		Validator
		.suppose(entitySet)
		.thatIsOfType(EntitySet.class)
		.isNotNull();
		
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotEmpty();
		
		Validator
		.suppose(valueType)
		.thatIsOfType(PropertyoidType.class)
		.isNotNull();
		
		this.entitySet = entitySet;
		this.header = header;
		this.valueType = valueType;
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		return 
		new List<StandardSpecification>(
			new StandardSpecification(PascalCaseNameCatalogue.HEADER, getHeader()),
			valueType.getSpecification()
		);
	}
	
	//method
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return valueType.getPropertyKind();
	}
	
	//method
	public EntitySet getRefEntitySet() {
		return entitySet;
	}
	
	//method
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public boolean isDataColumn() {
		return valueType.isDataType();
	}
	
	//method
	public boolean isReferenceColumn() {
		return valueType.isReferenceType();
	}

	//method
	public Column setHeader(final String header) {
		
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotEmpty();		
		
		if (entitySet.containsColumn(header)) {
			throw new InvalidStateException(
				entitySet,
				"contains already a column with the header '" + header + "'"
			);
		}
					
		entitySet.noteRenameColumn(this, header);
		
		this.header = header;
		
		return this;
	}
}
