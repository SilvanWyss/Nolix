//package declaration
package ch.nolix.core.container.base;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
final class ExtractorIterator<E, E2> implements Iterator<E2> {
	
	//attributes
	private final Iterator<E> internalIterator;
	private final IElementTakerElementGetter<E, E2> extractor;
	
	//static method
	/**
	 * @param <T> is the type of the elements of the given container.
	 * @param <T2> is the type of the elements of the created {@link ExtractorIterator}.
	 * @param container
	 * @param extractor
	 * @return a new {@link ExtractorIterator} for the given container and with the given extractor.
	 * @throws ArgumentIsNullException if the given extractor is null.
	 */
	public static <T, T2> ExtractorIterator<T, T2> forContainerWithExtractor(
		final Container<T> container,
		final IElementTakerElementGetter<T, T2> extractor
	) {
		return new ExtractorIterator<>(container, extractor);
	}
	
	//constructor
	/**
	 * Creates a new {@link ExtractorIterator} for the given container and with the given extractor.
	 * 
	 * @param container
	 * @param extractor
	 * @throws ArgumentIsNullException if the given extractor is null.
	 */
	private ExtractorIterator(final Container<E> container, final IElementTakerElementGetter<E, E2> extractor) {
		
		GlobalValidator.assertThat(extractor).thatIsNamed("extractor").isNotNull();
		
		internalIterator = container.iterator();
		this.extractor = extractor;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return internalIterator.hasNext();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E2 next() {
		return extractor.getOutput(internalIterator.next());
	}
}
