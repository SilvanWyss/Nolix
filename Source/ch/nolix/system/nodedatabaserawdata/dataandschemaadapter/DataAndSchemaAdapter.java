//package declaration
package ch.nolix.system.nodedatabaserawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.document.filenode.FileNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.dataadapter.DataAdapter;
import ch.nolix.system.nodedatabaserawschema.schemaadapter.SchemaAdapter;
import ch.nolix.system.rawdata.dataandschemaadapter.BaseDataAndSchemaAdapter;

//class
public final class DataAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//static method
	public static DataAndSchemaAdapter forNodeDatabase(final BaseNode<?> nodeDatabase) {
		return new DataAndSchemaAdapter(nodeDatabase);
	}
	
	//static method
	public static DataAndSchemaAdapter forNodeDatabaseInFile(final String filePath) {
		return new DataAndSchemaAdapter(new FileNode(filePath));
	}
	
	//constructor
	private DataAndSchemaAdapter(final BaseNode<?> nodeDatabase) {
		super(DataAdapter.forNodeDatabase(nodeDatabase), SchemaAdapter.forDatabaseNode(nodeDatabase));
	}
}
