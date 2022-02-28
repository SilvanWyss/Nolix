//package declaration
package ch.nolix.system.nodedatabaserawdata.dataadapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.datareader.DataReader;
import ch.nolix.system.nodedatabaserawdata.datareader.TableDefinitionLoader;
import ch.nolix.system.nodedatabaserawdata.datawriter.DataWriter;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.system.rawdata.dataadapter.BaseDataAdapter;

//class
public final class DataAdapter extends BaseDataAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//constructor
	public DataAdapter(final BaseNode nodeDatabase) {
		this(nodeDatabase, tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
	}
	
	//constructor
	private DataAdapter(
		final BaseNode nodeDatabase,
		final IContainer<TableInfo> tableInfos
	) {
		super(
			new DataReader(nodeDatabase, tableInfos),
			new DataWriter(nodeDatabase, tableInfos)
		);
	}
}
