//package declaration
package ch.nolix.coreapi.containerapi.mainapi;

//interface
public interface ISingleContainer<E> extends IContainer<E> {
	
	//method declaration
	E getRefElement();
}
