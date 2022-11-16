//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IOptionalBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends Clearable, IBaseBackReference<IMPL, E> {
	
	//method declaration
	String getEntityId();
	
	//method declaration
	E getRefEntity();
	
	//method declaration
	boolean referencesBackEntity();
}
