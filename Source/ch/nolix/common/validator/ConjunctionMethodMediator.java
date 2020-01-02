//package declaration
package ch.nolix.common.validator;

//Java import
import java.lang.annotation.Annotation;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.node.Node;

//class
public final class ConjunctionMethodMediator {
	
	//attribute
	private final MethodMediator originalMethodMediator;
	
	//constructor
	ConjunctionMethodMediator(final MethodMediator originalMethodMediator) {
		
		if (originalMethodMediator == null) {
			throw new ArgumentIsNullException("original method mediator");
		}
		
		this.originalMethodMediator = originalMethodMediator;
	}
	
	//method
	public ConjunctionMethodMediator andDoesNotHaveReturnType() {
		
		originalMethodMediator.doesNotReturnAnything();
		
		return this;
	}
	
	//method
	public <A extends Annotation> ConjunctionMethodMediator andHasAnnotation(final Class<A> annotationType) {
		
		originalMethodMediator.hasAnnotation(annotationType);
		
		return this;
	}
	
	//method
	public ConjunctionMethodMediator andHasParametersOfTypeOnly(final Class<String> type) {
		
		originalMethodMediator.hasParametersOfTypeOnly(type);
		
		return this;
	}
	
	//method
	public ConjunctionMethodMediator andHasReturnType(final Class<Node> returnType) {
		
		originalMethodMediator.hasReturnType(returnType);
		
		return this;
	}
}
