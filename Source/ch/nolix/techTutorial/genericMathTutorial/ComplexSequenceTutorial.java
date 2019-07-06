package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.CentralClassProvider;
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IIntTaker;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IImplicitComplexSequenceBuilder;

public class ComplexSequenceTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var complexNumberFactory = CentralClassProvider.create(IComplexNumberFactory.class);
		final var startValues = new List<IComplexNumber>(complexNumberFactory.create(0.0, 0.0));
		final var c = complexNumberFactory.create(0.0, 1.0);
		
		//Creates a Sequence.
		final var sequence =
		CentralClassProvider
		.create(IImplicitComplexSequenceBuilder.class)
		.setStartIndex(1)
		.setStartValues(startValues)
		.setNextValueFunction(z -> z.get(0).getPower2().getSum(c))
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
}
