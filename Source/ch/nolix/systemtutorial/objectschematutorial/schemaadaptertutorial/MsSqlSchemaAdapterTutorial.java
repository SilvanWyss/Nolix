package ch.nolix.systemtutorial.objectschematutorial.schemaadaptertutorial;

//own imports
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.system.objectschema.schemaadapter.MsSqlSchemaAdapter;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

public final class MsSqlSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		try (
			final var databaseSchemaAdapter =
			MsSqlSchemaAdapter
			.toLocalhost()
			.andDefaultPort()
			.toDatabase("CountryDatabase")
			.usingLoginName("sa")
			.andLoginPassword("sa1234")
		) {
			
			final var cityTable =
			new Table("City")
			.addColumn(new Column("Name", new ParametrizedValueType<>(DataType.STRING)))
			.addColumn(new Column("Population", new ParametrizedValueType<>(DataType.STRING)));
			
			final var countryTable = 
			new Table("Country").addColumn(new Column("Name", new ParametrizedValueType<>(DataType.STRING)));
			
			final var citiesColumn = new Column("Cities", new ParametrizedMultiReferenceType(cityTable));
			countryTable.addColumn(citiesColumn);
			
			cityTable.addColumn(new Column("Country", new ParametrizedBackReferenceType(citiesColumn)));
			
			databaseSchemaAdapter.addTable(cityTable).addTable(countryTable).saveChanges();
		}
	}
	
	private MsSqlSchemaAdapterTutorial() {}
}
