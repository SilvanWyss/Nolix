//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface ISingleReference<
	SR extends ISingleReference<SR, E>,
	E extends IEntity<E, SR>
> extends IProperty<SR> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesEntity();
}
