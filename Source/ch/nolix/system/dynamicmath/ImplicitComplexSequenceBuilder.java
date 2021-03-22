//package declaration
package ch.nolix.system.dynamicmath;

//Java import
import java.util.ArrayList;

import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IImplicitComplexSequenceBuilder;
import ch.nolix.common.commontype.commontypehelper.GlobalArrayHelper;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public final class ImplicitComplexSequenceBuilder implements IImplicitComplexSequenceBuilder {
	
	//constant
	public static final int DEFAULT_START_INDEX = 1;
	public static final ComplexNumber DEFAULT_START_VALUE = new ComplexNumber(0.0, 0.0);
	
	//constant
	public static final IElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber>
	DEFAULT_NEXT_VALUE_FUNCTION =
	z -> new ComplexNumber(0.0, 0.0);
	
	//attribute
	private int startIndex = DEFAULT_START_INDEX;
	
	//multi-attribute
	private IComplexNumber[] startValues;
	
	//attribute
	private IElementTakerElementGetter<IComplexNumber[], IComplexNumber> nextValueFunction;
	
	//constructor
	public ImplicitComplexSequenceBuilder() {
		startValues = new IComplexNumber[] {DEFAULT_START_VALUE};
	}
	
	//method
	@Override
	public ImpliciteSequence<IComplexNumber> build() {
		return
		new ImpliciteSequence<>(
			startIndex,
			startValues,
			nextValueFunction,
			IComplexNumber::getSquaredMagnitude
		);
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setNextValueFunction(
		final IElementTakerElementGetter<IComplexNumber[], IComplexNumber> nextValueFunction
	){
		this.nextValueFunction = nextValueFunction;
		
		return this;
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setStartIndex(final int startIndex) {
		
		this.startIndex = startIndex;
		
		return this;
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setStartValues(final IComplexNumber... startValues) {
		
		this.startValues = GlobalArrayHelper.createCopyOf(startValues);
		
		return this;
	}
}
