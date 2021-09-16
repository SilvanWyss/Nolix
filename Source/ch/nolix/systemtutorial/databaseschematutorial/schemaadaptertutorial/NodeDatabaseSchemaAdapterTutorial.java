package ch.nolix.systemtutorial.databaseschematutorial.schemaadaptertutorial;

import ch.nolix.common.document.node.Node;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.databaseschema.schema.Column;
import ch.nolix.system.databaseschema.schema.Table;
import ch.nolix.system.databaseschema.schemaadapter.NodeDatabaseSchemaAdapter;

public final class NodeDatabaseSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var database = new Node();
		
		final var nodeDatabaseSchemaAdapter = NodeDatabaseSchemaAdapter.forDatabaseNode("CountryDatabase", database);
		
		final var cityTable =
		new Table("City")
		.addColumn(new Column("Name", new ParametrizedValueType<>(String.class)))
		.addColumn(new Column("Population", new ParametrizedValueType<>(Integer.class)));
		
		final var countryTable = 
		new Table("Country").addColumn(new Column("Name", new ParametrizedValueType<>(String.class)));
				
		final var citiesColumn = new Column("Cities", new ParametrizedMultiReferenceType(cityTable));
		countryTable.addColumn(citiesColumn);
		cityTable.addColumn(new Column("Country", new ParametrizedBackReferenceType(citiesColumn)));
		
		nodeDatabaseSchemaAdapter.addTable(cityTable, countryTable).saveChanges();
		
		System.out.println(database.toFormatedString());
	}
	
	private NodeDatabaseSchemaAdapterTutorial() {}
}
