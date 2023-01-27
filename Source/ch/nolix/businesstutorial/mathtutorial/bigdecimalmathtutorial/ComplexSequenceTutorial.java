package ch.nolix.businesstutorial.mathtutorial.bigdecimalmathtutorial;

import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.business.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTaker;

public final class ComplexSequenceTutorial {
	
	public static void main(String[] args) {
		
		final var complexSequence =
		new ComplexSequenceDefinedBy1Predecessor(
			new ComplexNumber(0.0, 0.0),
			p -> p.getPower2().getSum(new ComplexNumber(0.0, 1.0))
		);
		
		final IIntTaker printFunction =
		(int i) -> System.out.println("a(" + i + ") = " + complexSequence.getValueAt1BasedIndex(i));
		
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
