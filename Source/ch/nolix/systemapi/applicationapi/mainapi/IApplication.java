//package declaration
package ch.nolix.systemapi.applicationapi.mainapi;

//interface
public interface IApplication<AC> {
	
	//method declaration
	String getApplicationName();
	
	//method declaration
	String getInstanceName();
	
	//method declaration
	AC getRefApplicationContext();
}