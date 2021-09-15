//package declaration
package ch.nolix.system.nodeintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.document.filenode.FileNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediateschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.nodeintermediateschema.schemareader.SchemaReader;
import ch.nolix.system.nodeintermediateschema.schemawriter.SchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaWriter;

//class
public final class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//static method
	public static SchemaAdapter forDatabaseNode(final BaseNode databaseNode) {
		return new SchemaAdapter(databaseNode);
	}
	
	//static method
	public static SchemaAdapter forDatabaseNodeInFile(final String filePath) {
		return new SchemaAdapter(new FileNode(filePath));
	}
	
	//attributes
	private final ISchemaReader schemaReader;
	private final ISchemaWriter schemaWriter;
	
	//constructor
	private SchemaAdapter(final BaseNode databaseNode) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(databaseNode);
		
		schemaReader = new SchemaReader(databaseNode);
		schemaWriter = new SchemaWriter(databaseNode);
	}
	
	//method
	@Override
	public ISchemaReader getRefSchemaReader() {
		return schemaReader;
	}
	
	//method
	@Override
	public ISchemaWriter getRefSchemaWriter() {
		return schemaWriter;
	}
}
