//package declaration
package ch.nolix.element.elementfactory;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class MutableElementFactory<E> extends ElementFactory<E> {
	
	//method
	public <ME extends IMutableElement<ME>> MutableElementFactory<E> registerElementClass(
		final Class<ME> elementClass
	) {
		
		registerElementClass_(elementClass);
		
		return this;
	}
	
	//method
	public MutableElementFactory<E> registerElementClass(
		final Class<E> elementClass,
		final IElementTakerElementGetter<BaseNode, E> creator
	) {
		
		registerElementClass_(elementClass, creator);
		
		return this;
	}
}
