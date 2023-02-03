//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class ImmutableListTest extends ContainerTest {
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements) {
		return ImmutableList.forArray(elements);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
		return new ImmutableList<>();
	}
}