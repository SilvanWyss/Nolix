//package declaration
package ch.nolix.system.elementfactory;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;
import ch.nolix.systemapi.elementuniversalapi.mainuniversalapi.IMutableElement;

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
