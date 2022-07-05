//package declaration
package ch.nolix.core.testing.validation;

import ch.nolix.core.independent.independenthelper.IterableHelper;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class ContainerMediator<E> extends ValueMediator<Iterable<E>> {
	
	//constructor
	public ContainerMediator(final IElementTaker<String> expectationErrorTaker, final Iterable<E> container) {
		super(expectationErrorTaker, container);
	}
	
	//method
	public void isEmpty() {
		if (getRefValue() == null) {
			expectIsEmptyWhenIsNull();
		} else {
			expectIsEmptyWhenIsNotNull();
		}
	}
	
	//method
	public void isNotEmpty() {
		if (getRefValue() == null) {
			expectIsNotEmptyWhenIsNull();
		} else {
			expectIsNotEmptyWhenIsNotNull();
		}
	}
	
	//method
	private void expectIsEmptyWhenIsNotNull() {
		
		final var elementCount = IterableHelper.getElementCount(getRefValue());
		
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
		if (IterableHelper.isEmpty(getRefValue())) {
			addCurrentTestCaseError("A non-empty container was expected, but an empty container received.");
		}
	}
	
	//method
	private void expectIsNotEmptyWhenIsNull() {
		addCurrentTestCaseError("A non-empty container was expected, but null was received.");
	}
}
