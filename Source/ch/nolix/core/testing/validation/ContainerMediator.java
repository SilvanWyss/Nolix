//package declaration
package ch.nolix.core.testing.validation;

//Java imports
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.core.independent.containerhelper.IterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class ContainerMediator<E> extends ValueMediator<Iterable<E>> {
	
	//constructor
	public ContainerMediator(final IElementTaker<String> expectationErrorTaker, final Iterable<E> container) {
		super(expectationErrorTaker, container);
	}
	
	//method
	public boolean containsAsManyElementsAs(final Object[] array) {
		
		if (array == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
		}
		
		return hasElementCount(array.length);
	}
	
	//method
	public void containsExactlyEqualing(final E firstElement, final @SuppressWarnings("unchecked")E... elements) {
		
		final var localElements = GlobalArrayHelper.createArrayWithElements(firstElement, elements);
		
		containsExactlyEqualing(localElements);
	}
	
	//method
	public void containsExactlyEqualing(final E[] elements) {
		if (containsAsManyElementsAs(elements)) {
			var index = 0;
			for (final var e : getOriValue()) {
				
				if (!Objects.equals(e, elements[index])) {
					addCurrentTestCaseError(
						"A container with exactly equaling elements was expected, but the "
						+ (index + 1)
						+ "th element of the container does not equal the given "
						+ (index + 1) + " element.");
				}
				
				index++;
			}
		}
	}
	
	//method
	public boolean hasElementCount(final int elementCount) {
		
		if (elementCount < 0) {
			throw NegativeArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ELEMENT_COUNT, elementCount);
		}
				
		if (getOriValue() == null) {
			
			addCurrentTestCaseError("A container with " + elementCount + " elements was expected, but null was received.");
			
			return false;
		} else {
			
			final var actualElementCount = IterableHelper.getElementCount(getOriValue());
			
			if (actualElementCount != elementCount) {
				
				addCurrentTestCaseError(
					"A container with "
					+ elementCount
					+ " elements was expected, but a container with "
					+ actualElementCount
					+ " elements was received."
				);
				
				return false;
			}
			
			return true;
		}
	}
	
	//method
	public void isEmpty() {
		if (getOriValue() == null) {
			expectIsEmptyWhenIsNull();
		} else {
			expectIsEmptyWhenIsNotNull();
		}
	}
	
	//method
	public void isNotEmpty() {
		if (getOriValue() == null) {
			expectIsNotEmptyWhenIsNull();
		} else {
			expectIsNotEmptyWhenIsNotNull();
		}
	}
	
	//method
	private void expectIsEmptyWhenIsNotNull() {
		
		final var elementCount = IterableHelper.getElementCount(getOriValue());
		
		if (elementCount > 0) {
			addCurrentTestCaseError(
				"An empty container was expected, but a container with "
				+ elementCount
				+ " elements was received."
			);
		}
	}
	
	//method
	private void expectIsEmptyWhenIsNull() {
		addCurrentTestCaseError("An empty container was expected, but null was received.");
	}
	
	//method
	private void expectIsNotEmptyWhenIsNotNull() {
		if (IterableHelper.isEmpty(getOriValue())) {
			addCurrentTestCaseError("A non-empty container was expected, but an empty container received.");
		}
	}
	
	//method
	private void expectIsNotEmptyWhenIsNull() {
		addCurrentTestCaseError("A non-empty container was expected, but null was received.");
	}
}
