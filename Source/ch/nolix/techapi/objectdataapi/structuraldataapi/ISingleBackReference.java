//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface ISingleBackReference<
	SBR extends ISingleBackReference<SBR, SE>,
	SE extends IStructuralEntity<SE, SBR>
>
extends IProperty<SBR> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	SE getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
