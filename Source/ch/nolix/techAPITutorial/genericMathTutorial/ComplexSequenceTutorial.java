package ch.nolix.techAPITutorial.genericMathTutorial;

import ch.nolix.common.functionAPI.IIntTaker;
import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IImplicitComplexSequenceBuilder;

public final class ComplexSequenceTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var complexNumberFactory = CentralInstanceProvider.create(IComplexNumberFactory.class);
		final var startValues = new IComplexNumber[] { complexNumberFactory.create(0.0, 0.0) };
		final var c = complexNumberFactory.create(0.0, 1.0);
		
		//Creates a Sequence.
		final var sequence =
		CentralInstanceProvider
		.create(IImplicitComplexSequenceBuilder.class)
		.setStartIndex(1)
		.setStartValues(startValues)
		.setNextValueFunction(z -> z[0].getPower2().getSum(c))
		.build();
		
		final IIntTaker function = (int i) -> System.out.println("a(" + i + ") = " + sequence.getValue(i));
		
		//Prints out to the console some values of the sequence.
		function.run(1);
		function.run(2);
		function.run(3);
		function.run(4);
		function.run(5);
		function.run(10);
		function.run(100);
		function.run(1000);
	}
	
	private ComplexSequenceTutorial() {}
}
