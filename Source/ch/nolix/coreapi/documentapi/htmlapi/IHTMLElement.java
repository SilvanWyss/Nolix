//package declaration
package ch.nolix.coreapi.documentapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.keyvalueapi.IKeyValuePair;

//interface
public interface IHTMLElement<E extends IHTMLElement<E, KVP>, KVP extends IKeyValuePair> {
	
	//method declaration
	IContainer<KVP> getAttributes();
	
	//method declaration
	IContainer<E> getChildElements();
	
	//method declaration
	String getType();
}
