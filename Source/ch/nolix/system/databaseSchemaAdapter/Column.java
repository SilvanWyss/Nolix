//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.SQL.SQLDatabaseEngine;
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.UnsupportedArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementAPI.IElement;
import ch.nolix.system.dataTypes.DataType;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class Column implements Headered, IElement {
	
	//attributes
	private final String header;
	private final DataType<?> dataType;
	
	//constructor
	Column(final String header,	final DataType<?> valueType) {
		
		Validator.assertThat(header).thatIsNamed(VariableNameCatalogue.HEADER).isNotBlank();
		Validator.assertThat(valueType).isOfType(DataType.class);
		
		this.header = header;
		this.dataType = valueType;
	}
	
	//method
	public <E extends Entity> boolean canReferenceBackEntityOfType(final Class<E> type) {
		return (isAnyBackReferenceColumn() && getRefContentClass() == type);
	}
	
	//method
	public <E extends Entity> boolean canReferenceEntityOfType(final Class<E> type) {
		return (isAnyReferenceColumn() && getRefContentClass() == type);
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		return 
		new LinkedList<>(
			new Node(PascalCaseNameCatalogue.HEADER, getHeader()),
			new Node(dataType.getPropertyKind().toString())
		);
	}
	
	//method
	public DataType<?> getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return dataType.getPropertyKind();
	}
	
	//method
	public Class<?> getRefContentClass() {
		return dataType.getRefContentClass();
	}
	
	//method
	public ColumnSQLHelper getSQLHelper(final SQLDatabaseEngine SQLDatabaseEngine) {
		
		//Enumerates the given SQLDatabaseEngine.
		switch (SQLDatabaseEngine) {
			case MSSQL:
				return new ColumnMSSQLHelper(this);
			default:
				throw new UnsupportedArgumentException(SQLDatabaseEngine);
		}
	}
	
	//method
	@Override
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public boolean isAnyBackReferenceColumn() {
		return dataType.isAnyBackReferenceType();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return dataType.isAnyValueType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return dataType.isAnyReferenceType();
	}
	
	//method
	public boolean isAnyTechnicalColumn() {
		return dataType.isAnyTechnicalType();
	}
}
