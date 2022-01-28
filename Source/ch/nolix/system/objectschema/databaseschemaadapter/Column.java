//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnsupportedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.schemahelper.ParametrizedPropertyTypeHelper;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParametrizedPropertyTypeHelper;

//class
public final class Column implements Named {
	
	//static attributes
	private final IParametrizedPropertyTypeHelper parametrizedPropertyTypeHelper = new ParametrizedPropertyTypeHelper();
	
	//attributes
	private final String name;
	private final ParametrizedPropertyType<?> dataType;
	
	//constructor
	public Column(final String name, final ParametrizedPropertyType<?> dataType) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.name = name;
		this.dataType = dataType;
	}
	
	//method
	public ParametrizedPropertyType<?> getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getName() {
		return name;
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
		return parametrizedPropertyTypeHelper.isABaseBackReferenceType(dataType);
	}
	
	//method
	public boolean isAnyDataColumn() {
		return parametrizedPropertyTypeHelper.isABaseValueType(dataType);
	}
	
	//method
	public boolean isAnyReferenceColumn() {
		return parametrizedPropertyTypeHelper.isABaseReferenceType(dataType);
	}
}
