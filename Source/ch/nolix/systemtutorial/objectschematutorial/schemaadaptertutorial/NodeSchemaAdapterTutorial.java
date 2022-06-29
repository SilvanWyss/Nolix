package ch.nolix.systemtutorial.objectschematutorial.schemaadaptertutorial;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

public final class NodeSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var database = new MutableNode();
		
		try (final var nodeDatabaseSchemaAdapter = NodeSchemaAdapter.forDatabaseNode("CountryDatabase", database)) {
			
			final var cityTable =
			new Table("City")
			.addColumn(new Column("Name", new ParametrizedValueType<>(DataType.STRING)))
			.addColumn(new Column("Population", new ParametrizedValueType<>(DataType.INTEGER_4BYTE)));
			
			final var countryTable = 
			new Table("Country").addColumn(new Column("Name", new ParametrizedValueType<>(DataType.STRING)));
				
			final var citiesColumn = new Column("Cities", new ParametrizedMultiReferenceType(cityTable));
			countryTable.addColumn(citiesColumn);
			cityTable.addColumn(new Column("Country", new ParametrizedBackReferenceType(citiesColumn)));
			
			nodeDatabaseSchemaAdapter.addTable(cityTable).addTable(countryTable).saveChangesAndReset();
			
			System.out.println(database.toFormattedString());
		}
	}
	
	private NodeSchemaAdapterTutorial() {}
}
