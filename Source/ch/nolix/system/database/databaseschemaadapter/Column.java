//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnsupportedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaDataType;

//class
public final class Column implements Headered {
	
	//attributes
	private final String header;
	private final SchemaDataType<?> dataType;
	
	//constructor
	public Column(final String header, final SchemaDataType<?> dataType) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		Validator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.header = header;
		this.dataType = dataType;
	}
	
	//method
	public SchemaDataType<?> getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public DataType getPropertyKind() {
		return dataType.getPropertyKind();
	}
	
	//method
	public Class<?> getRefContentClass() {
		return dataType.getRefContentClass();
	}
	
	//method
	public ColumnSQLHelper getSQLHelper(final SQLDatabaseEngine pSQLDatabaseEngine) {
		
		//Enumerates the given SQLDatabaseEngine.
		switch (pSQLDatabaseEngine) {
			case MSSQL:
				return new ColumnMSSQLHelper(this);
			default:
				throw new UnsupportedArgumentException(pSQLDatabaseEngine);
		}
	}
	
	//method
	public boolean isAnyBackReferenceColumn() {
		return dataType.isAnyBackReferenceType();
	}
	
	//method
	public boolean isAnyControlColumn() {
		return dataType.isAnyControlType();
	}
	
	//method
	public boolean isAnyDataColumn() {
		return dataType.isAnyValueType();
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return dataType.isAnyReferenceType();
	}
}
