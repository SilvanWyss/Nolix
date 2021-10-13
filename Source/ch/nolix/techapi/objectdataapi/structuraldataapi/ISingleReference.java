//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface ISingleReference<
	SR extends ISingleReference<SR, SE>,
	SE extends IStructuralEntity<SE, SR>
> extends IProperty<SR> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	SE getRefEntity();
	
	//method declaration
	boolean referencesEntity();
}
