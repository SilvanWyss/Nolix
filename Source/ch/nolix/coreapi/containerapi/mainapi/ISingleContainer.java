//package declaration
package ch.nolix.coreapi.containerapi.mainapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ISingleContainer<E> extends IContainer<E> {
	
	//method declaration
	E getRefElement();
}
