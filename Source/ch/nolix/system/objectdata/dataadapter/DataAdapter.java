//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.system.objectdata.datahelper.DatabaseHelper;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;

//class
public abstract class DataAdapter implements IDataAdapter<DataImplementation> {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return databaseHelper.hasChanges(getRefDatabase());
	}
}
