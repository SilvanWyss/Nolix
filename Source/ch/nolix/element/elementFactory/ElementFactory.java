//package declaration
package ch.nolix.element.elementFactory;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IMutableElement;

//class
public class ElementFactory<E> {
	
	//multi-attribute
	private final LinkedList<OneTypeElementFactory<?>> oneTypeElementFactories = new LinkedList<>();
		
	//method
	public final <E2 extends E> boolean canCreateElementOf(final Class<E2> type) {
		return oneTypeElementFactories.contains(otef -> otef.canCreateElementOf(type));
	}
	
	//method
	public final boolean canCreateElementOf(final String type) {
		return oneTypeElementFactories.contains(oeef -> oeef.canCreateElementOf(type));
	}
	
	//method
	@SuppressWarnings("unchecked")
	public <E2 extends E> E2 createElementFrom(final BaseNode specification) {
		return (E2)getOneTypeElementFactoryFor(specification).createElementFrom(specification);
	}
	
	//method
	protected final <E2 extends E, ME extends IMutableElement<ME>> void registerElementClass_(
		final Class<ME> elementClass
	) {
		registerOneTypeElementFactory(new OneTypeElementFactory<E2>(elementClass));
	}
	
	//method
	protected final <E2 extends E> void registerElementClass_(
		final Class<E2> elementClass,
		final IElementTakerElementGetter<BaseNode, E2> creator
	) {
		registerOneTypeElementFactory(new OneTypeElementFactory<>(elementClass, creator));
	}
	
	//method
	private <E2 extends E> void assertCannotCreateElementOf(final Class<E2> type) {
		if (canCreateElementOf(type)) {
			throw new InvalidArgumentException(this, "can already create a " + type.getName());
		}
	}

	//method
	private OneTypeElementFactory<?> getOneTypeElementFactoryFor(final BaseNode specification) {
		return getOneTypeElementFactoryFor(specification.getHeader());
	}
	
	//method
	private OneTypeElementFactory<?> getOneTypeElementFactoryFor(final String type) {
		return oneTypeElementFactories.getRefFirst(otef -> otef.canCreateElementOf(type));
	}
	
	//method
	private <E2 extends E> void registerOneTypeElementFactory(final OneTypeElementFactory<E2> oneTypeElementFactory) {
		
		assertCannotCreateElementOf(oneTypeElementFactory.getRefElementClass());
		
		oneTypeElementFactories.addAtEnd(oneTypeElementFactory);
	}
}
