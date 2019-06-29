//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.SQLDatabaseEngine;
import ch.nolix.core.attributeAPI.Headered;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseAdapter.PropertyKind;
import ch.nolix.system.databaseAdapter.PropertyoidType;

//class
public final class Column implements Headered, Specified {
	
	//attributes
	private final String header;
	private final EntitySet entitySet;
	private final PropertyoidType<?> valueType;
		
	//package-visible constructor
	Column(
		final EntitySet entitySet,
		final String header,
		final PropertyoidType<?> valueType
	) {
		
		this.header = Validator.suppose(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank().andReturn();
		
		Validator.suppose(entitySet).isOfType(EntitySet.class);
		Validator.suppose(valueType).isOfType(PropertyoidType.class);
		
		this.entitySet = entitySet;
		this.valueType = valueType;
	}
	
	//method
	@Override
	public List<DocumentNode> getAttributes() {
		return 
		new List<>(
			new DocumentNode(PascalCaseNameCatalogue.HEADER, getHeader()),
			valueType.getSpecification()
		);
	}
	
	//method
	@Override
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
	public ColumnSQLHelper getSQLHelper(final SQLDatabaseEngine SQLDatabaseEngine) {
		switch (SQLDatabaseEngine) {
			case MSSQL:
				return new ColumnMSSQLHelper(this);
			default:
				throw
				new RuntimeException("The given SQL database engine '" + SQLDatabaseEngine + "' is not supported.");
		}
	}
	
	//method
	@Override
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public Class<?> getValueClass() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return valueType.getValueClass();
	}
	
	//method
	public PropertyoidType<?> getValueType() {
		return valueType;
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
	public boolean references(final EntitySet entitySet) {
		return valueType.referencesEntitySet(entitySet.getName());
	}
}
