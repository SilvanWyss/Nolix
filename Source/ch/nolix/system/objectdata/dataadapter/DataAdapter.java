//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;

//class
public abstract class DataAdapter implements IDataAdapter<DataImplementation> {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
}
