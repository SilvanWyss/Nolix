//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalArrayHelper;
import ch.nolix.core.container.readcontainer.ArrayReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class ArrayReadContainerTest extends ContainerTest {
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(
		final E element,
		final @SuppressWarnings("unchecked") E... elements
	) {
		return new ArrayReadContainer<>(GlobalArrayHelper.createArrayWithElements(element, elements));
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
		return new ArrayReadContainer<>();
	}
}
