//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IHtmlElement<HE extends IHtmlElement<HE, A>, A extends IHtmlAttribute> {
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildElements();
	
	//method declaration
	String getInnerText();
	
	//method declaration
	IContainer<A> getStoredAttributes();
	
	//method declaration
	IContainer<HE> getStoredChildElements();
	
	//method declaration
	String getType();
}
