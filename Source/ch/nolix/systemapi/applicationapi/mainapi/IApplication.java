//package declaration
package ch.nolix.systemapi.applicationapi.mainapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;

//interface
public interface IApplication<AC> {
	
	//method declaration
	IApplicationInstanceTarget asTarget();
	
	//method declaration
	boolean belongsToServer();
	
	//method declaration
	String getApplicationName();
	
	//method declaration
	String getInstanceName();
	
	//method declaration
	AC getOriApplicationContext();
}
