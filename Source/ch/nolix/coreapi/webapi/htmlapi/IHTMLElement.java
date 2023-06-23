//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IHTMLElement<HTMLE extends IHTMLElement<HTMLE, A>, A extends IHtmlAttribute> {
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildElements();
	
	//method declaration
	String getInnerText();
	
	//method declaration
	IContainer<A> getOriAttributes();
	
	//method declaration
	IContainer<HTMLE> getOriChildElements();
	
	//method declaration
	String getType();
}
