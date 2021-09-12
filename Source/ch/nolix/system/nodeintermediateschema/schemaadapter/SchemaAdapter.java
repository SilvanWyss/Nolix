//package declaration
package ch.nolix.system.nodeintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.document.filenode.FileNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.nodeintermediateschema.schemareader.SchemaReader;
import ch.nolix.system.nodeintermediateschema.schemawriter.SchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaWriter;

//class
public final class SchemaAdapter implements ISchemaAdapter {
	
	//static method
	public static SchemaAdapter forNode(final BaseNode databaseNode) {
		return new SchemaAdapter(databaseNode);
	}
	
	//static method
	public static SchemaAdapter forNodeInFile(final String filePath) {
		return new SchemaAdapter(new FileNode(filePath));
	}
	
	//attribute
	private final BaseNode databaseNode;
	
	//constructor
	private SchemaAdapter(final BaseNode databaseNode) {
		
		Validator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
	}
	
	//method
	@Override
	public ISchemaReader getRefSchemaReader() {
		return new SchemaReader(databaseNode);
	}
	
	//method
	@Override
	public ISchemaWriter getRefSchemaWriter() {
		return new SchemaWriter(databaseNode);
	}
}
