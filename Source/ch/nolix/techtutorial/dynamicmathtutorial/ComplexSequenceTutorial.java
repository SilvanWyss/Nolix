package ch.nolix.techtutorial.dynamicmathtutorial;

import ch.nolix.common.functionapi.IIntTaker;
import ch.nolix.common.implprovider.GlobalImplProvider;
import ch.nolix.tech.dynamicmath.DynamicMathImplRegistrator;
import ch.nolix.techapi.dynamicmathapi.IComplexNumber;
import ch.nolix.techapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.techapi.dynamicmathapi.IImplicitComplexSequenceBuilder;

public final class ComplexSequenceTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var complexNumberFactory = GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance();
		final var startValues = new IComplexNumber[] { complexNumberFactory.create(0.0, 0.0) };
		final var c = complexNumberFactory.create(0.0, 1.0);
		
		//Creates a Sequence.
		final var sequence =
		GlobalImplProvider
		.ofInterface(IImplicitComplexSequenceBuilder.class)
		.createInstance()
		.setStartIndex(1)
		.setStartValues(startValues)
		.setNextValueFunction(z -> z[0].getPower2().getSum(c))
		.build();
		
		final IIntTaker function = (int i) -> System.out.println("a(" + i + ") = " + sequence.getValue(i));
		
		//Prints out to the console some values of the Sequence.
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
