//package declaration
package ch.nolix.commontest.containertest;

import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.demo.Cat;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class SingleContainerTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new SingleContainer<Cat>();
		
		//verification
		expect(result.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_creation_whenThereIsGiven1Element() {
		
		//setup
		final var garfield = new Cat("Garfield");
		
		//execution
		final var result = new SingleContainer<>(garfield);
		
		//verification
		expect(result.containsOne());
		expect(result.getRefElement()).isSameAs(garfield);
	}
}
