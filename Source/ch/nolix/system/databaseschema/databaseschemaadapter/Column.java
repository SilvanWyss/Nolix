//package declaration
package ch.nolix.system.databaseschema.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnsupportedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class Column implements Headered {
	
	//attributes
	private final String header;
	private final ParametrizedPropertyType<?> dataType;
	
	//constructor
	public Column(final String header, final ParametrizedPropertyType<?> dataType) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		Validator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.header = header;
		this.dataType = dataType;
	}
	
	//method
	public ParametrizedPropertyType<?> getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	public PropertyType getPropertyKind() {
		return dataType.getPropertyType();
	}
	
	//method
	public Class<?> getRefContentClass() {
		return dataType.getDataType();
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
