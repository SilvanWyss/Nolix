package ch.nolix.systemtutorial.objectschematutorial.schemaadaptertutorial;

import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.core.sql.SQLDatabaseEngine;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.system.objectschema.schemaadapter.MSSQLSchemaAdapter;

public final class MSSQLSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		try (
			final var lSQLConnectionPool =
			SQLConnectionPool
			.forIpOrAddressName(IPv4Catalogue.LOOP_BACK_ADDRESS)
			.andDefaultPort()
			.andDatabase("CountryDatabase")
			.withSQLDatabaseEngine(SQLDatabaseEngine.MSSQL)
			.usingLoginName("sa")
			.andLoginPassword("sa1234")
		) {
			try (
				final var nodeDatabaseSchemaAdapter =
				MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
					"CountryDatabase",
					lSQLConnectionPool
				)
			) {
				
				final var cityTable =
				new Table("City")
				.addColumn(new Column("Name", new ParametrizedValueType<>(String.class)))
				.addColumn(new Column("Population", new ParametrizedValueType<>(Integer.class)));
				
				final var countryTable = 
				new Table("Country").addColumn(new Column("Name", new ParametrizedValueType<>(String.class)));
				
				final var citiesColumn = new Column("Cities", new ParametrizedMultiReferenceType(cityTable));
				countryTable.addColumn(citiesColumn);
				cityTable.addColumn(new Column("Country", new ParametrizedBackReferenceType(citiesColumn)));
				
				nodeDatabaseSchemaAdapter.addTable(cityTable).addTable(countryTable).saveChangesAndReset();
			}
		}
	}
	
	private MSSQLSchemaAdapterTutorial() {}
}
