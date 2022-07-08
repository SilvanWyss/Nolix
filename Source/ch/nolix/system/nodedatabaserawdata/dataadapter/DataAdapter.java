//package declaration
package ch.nolix.system.nodedatabaserawdata.dataadapter;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.datareader.DataReader;
import ch.nolix.system.nodedatabaserawdata.datareader.TableDefinitionLoader;
import ch.nolix.system.nodedatabaserawdata.datawriter.DataWriter;
import ch.nolix.system.rawdata.dataadapter.BaseDataAdapter;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class DataAdapter extends BaseDataAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//static method
	public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
		return new DataAdapter(nodeDatabase);
	}
	
	//constructor
	private DataAdapter(final IMutableNode<?> nodeDatabase) {
		this(nodeDatabase, tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
	}
	
	//constructor
	private DataAdapter(
		final IMutableNode<?> nodeDatabase,
		final IContainer<ITableInfo> tableInfos
	) {
		super(
			new DataReader(nodeDatabase, tableInfos),
			new DataWriter(nodeDatabase, tableInfos)
		);
	}
}
