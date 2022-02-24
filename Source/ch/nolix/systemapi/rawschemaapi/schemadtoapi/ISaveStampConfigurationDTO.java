//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//interface
public interface ISaveStampConfigurationDTO {
	
	//method declaration
	String getBaseTableName();
	
	//method declaration
	SaveStampStrategy getSaveStampStrategy();
}
