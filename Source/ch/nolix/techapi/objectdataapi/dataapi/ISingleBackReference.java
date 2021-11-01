//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface ISingleBackReference<
	SBR extends ISingleBackReference<SBR, SE>,
	SE extends IEntity<SE, SBR>
>
extends IProperty<SBR> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	SE getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
