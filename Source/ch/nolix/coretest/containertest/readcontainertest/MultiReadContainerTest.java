//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.MultiReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class MultiReadContainerTest extends ContainerTest {
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(
		final E element,
		final @SuppressWarnings("unchecked") E... elements
	) {
		
		final var listOfLists = new LinkedList<IContainer<E>>();
		listOfLists.addAtEnd(LinkedList.withElement(element));
		for (final var e : elements) {
			listOfLists.addAtEnd(LinkedList.withElement(e));
		}
		
		return new MultiReadContainer<>(listOfLists);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
		return new MultiReadContainer<>();
	}
}
