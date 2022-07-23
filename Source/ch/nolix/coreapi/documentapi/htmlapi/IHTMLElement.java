//package declaration
package ch.nolix.coreapi.documentapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.keyvalueapi.IKeyValuePair;

//interface
public interface IHTMLElement<E extends IHTMLElement<E, KVP>, KVP extends IKeyValuePair<String, String>> {
	
	//method declaration
	IContainer<KVP> getRefAttributes();
	
	//method declaration
	IContainer<E> getRefChildElements();
	
	//method declaration
	String getType();
}
