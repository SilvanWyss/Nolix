package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.ComplexNumber;
import ch.nolix.business.dynamicmath.SequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.functionapi.IIntTaker;

public final class ComplexSequenceTutorial {
	
	public static void main(String[] args) {
		
		final var c = new ComplexNumber(0.0, 1.0);
		
		final var sequence =
		new SequenceDefinedBy1Predecessor<>(
			new ComplexNumber(0.0, 0.0),
			p -> p.getPower2().getSum(c),
			IComplexNumber::getSquaredMagnitude
		);
		
		final IIntTaker printFunction = (int i) -> System.out.println("a(" + i + ") = " + sequence.getValueAtIndex(i));
		
		printFunction.run(1);
		printFunction.run(2);
		printFunction.run(3);
		printFunction.run(4);
		printFunction.run(5);
		printFunction.run(10);
		printFunction.run(100);
		printFunction.run(1000);
	}
	
	private ComplexSequenceTutorial() {}
}
