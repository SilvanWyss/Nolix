//package declaration
package ch.nolix.system.nodedatabaserawdata.databaseadapter;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.databasereader.DatabaseReader;
import ch.nolix.system.nodedatabaserawdata.databasereader.TableDefinitionLoader;
import ch.nolix.system.nodedatabaserawdata.databasewriter.DatabaseWriter;
import ch.nolix.system.rawdatabase.databaseadapter.BaseDatabaseAdapter;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class DatabaseAdapter extends BaseDatabaseAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//static method
	public static DatabaseAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
		return new DatabaseAdapter(nodeDatabase);
	}
	
	//constructor
	private DatabaseAdapter(final IMutableNode<?> nodeDatabase) {
		this(nodeDatabase, tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(nodeDatabase));
	}
	
	//constructor
	private DatabaseAdapter(
		final IMutableNode<?> nodeDatabase,
		final IContainer<ITableInfo> tableInfos
	) {
		super(
			new DatabaseReader(nodeDatabase, tableInfos),
			new DatabaseWriter(nodeDatabase, tableInfos)
		);
	}
}
