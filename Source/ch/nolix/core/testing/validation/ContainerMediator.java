//package declaration
package ch.nolix.core.testing.validation;

//Java imports
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.core.independent.containerhelper.GlobalIterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class ContainerMediator<E> extends ValueMediator<Iterable<E>> {
	
	//constructor
	public ContainerMediator(final IElementTaker<String> expectationErrorTaker, final Iterable<E> container) {
		super(expectationErrorTaker, container);
	}
	
	//method
	public void contains(final E element) {
		
		isNotNull();
		
		for (final var e : getStoredValue()) {
			if (e == element) {
				return;
			}
		}
		
		addCurrentTestCaseError(
			"A container that contains the given element was expected, "
			+ "but the contains does not contain the given element."
		);
	}
	
	//method
	public boolean containsAsManyElementsAs(final Object[] array) {
		
		if (array == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
		}
		
		return hasElementCount(array.length);
	}
	
	//method
	public void containsExactly(final E element, @SuppressWarnings("unchecked") final E... elements) {
		
		if (elements == null) {
			throw ArgumentIsNullException.forArgumentName(PluralLowerCaseCatalogue.ELEMENTS);
		}
		
		hasElementCount(1 + elements.length);
		
		contains(element);
		
		for (final var e : elements) {
			contains(e);
		}
	}
	
	//method
	public void containsExactlyInSameOrder(final E element, final @SuppressWarnings("unchecked")E... elements) {
		
		final var localElements = GlobalArrayHelper.createArrayWithElement(element, elements);
		
		containsAsManyElementsAs(localElements);
		
		var index = 0;
		for (final var e : getStoredValue()) {
			
			if (e != localElements[index]) {
				addCurrentTestCaseError(
					"A container with exactly the given elements was expected, but the "
					+ (index + 1)
					+ "th element of the container is not the same element as the "
					+ (index + 1) + " element of the given elements.");
			}
			
			index++;
		}
	}
	
	//method
	public void containsExactlyEqualing(final E firstElement, final @SuppressWarnings("unchecked")E... elements) {
		
		final var localElements = GlobalArrayHelper.createArrayWithElement(firstElement, elements);
		
		containsExactlyEqualing(localElements);
	}
	
	//method
	public void containsExactlyEqualing(final E[] elements) {
		
		containsAsManyElementsAs(elements);
		
		var index = 0;
		for (final var e : getStoredValue()) {
			
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
	
	//method
	public boolean hasElementCount(final int elementCount) {
		
		if (elementCount < 0) {
			throw NegativeArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ELEMENT_COUNT, elementCount);
		}
				
		if (getStoredValue() == null) {
			
			addCurrentTestCaseError("A container with " + elementCount + " elements was expected, but null was received.");
			
			return false;
		} else {
			
			final var actualElementCount = GlobalIterableHelper.getElementCount(getStoredValue());
			
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
		if (getStoredValue() == null) {
			expectIsEmptyWhenIsNull();
		} else {
			expectIsEmptyWhenIsNotNull();
		}
	}
	
	//method
	public void isNotEmpty() {
		if (getStoredValue() == null) {
			expectIsNotEmptyWhenIsNull();
		} else {
			expectIsNotEmptyWhenIsNotNull();
		}
	}
	
	//method
	private void expectIsEmptyWhenIsNotNull() {
		
		final var elementCount = GlobalIterableHelper.getElementCount(getStoredValue());
		
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
		if (GlobalIterableHelper.isEmpty(getStoredValue())) {
			addCurrentTestCaseError("A non-empty container was expected, but an empty container received.");
		}
	}
	
	//method
	private void expectIsNotEmptyWhenIsNull() {
		addCurrentTestCaseError("A non-empty container was expected, but null was received.");
	}
}
