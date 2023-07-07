//package declaration
package ch.nolix.template.math.sequencecreator;

import ch.nolix.business.math.bigdecimalmath.ComplexNumber;
import ch.nolix.business.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.businessapi.mathapi.bigdecimalmathapi.ISequenceDefinedBy1Predecessor;

//class
public final class GlobalSequenceCreator {
	
	//static method
	public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForIncrement(
		final IComplexNumber increment
	) {
		return
		new ComplexSequenceDefinedBy1Predecessor(
			new ComplexNumber(0.0, 0.0, increment.getBigDecimalScale()),
			z -> z.getPower2().getSum(increment)
		);
	}
	
	//static method
	public static ISequenceDefinedBy1Predecessor<IComplexNumber> createMandelbrotSequenceForStartValueAndIncrement(
		final IComplexNumber startValue,
		final IComplexNumber increment
	) {
		return
		new ComplexSequenceDefinedBy1Predecessor(
			startValue,
			z -> z.getPower2().getSum(increment)
		);
	}
	
	//constructor
	private GlobalSequenceCreator() {}
}
