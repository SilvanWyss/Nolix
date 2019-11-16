//package declaration
package ch.nolix.element.shapes;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.BaseShape;
import ch.nolix.element.base.MutableProperty;

//class
public final class Pyramid extends BaseShape<Pyramid> {
	
	//default values
	public static final double DEFAULT_SIDE_LENGTH = 1.0;
	public static final double DEFAULT_HEIGHT = 2.0;

	//attribute
	private final MutableProperty<Double> sideLength =
	new MutableProperty<>(
		PascalCaseNameCatalogue.SIDE_LENGTH,
		sl -> setSideLength(sl),
		s -> s.getOneAttributeAsDouble(),
		sl -> Node.withOneAttribute(sl)
	);
	
	//attribute
	private final MutableProperty<Double> height =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h),
		s -> s.getOneAttributeAsDouble(),
		h -> Node.withOneAttribute(h)
	);
	
	//constructor
	public Pyramid() {
		reset();
	}
	
	//method
	public double getHeight() {
		return height.getValue();
	}
	
	//method
	public float getHeightAsFloat() {
		return sideLength.getValue().floatValue();
	}
	
	//method
	public double getSideLength() {
		return sideLength.getValue();
	}
	
	//method
	public float getSideLengthAsFloat() {
		return sideLength.getValue().floatValue();
	}
	
	//method
	@Override
	public Pyramid reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setSideLength(DEFAULT_SIDE_LENGTH);
		setHeight(DEFAULT_HEIGHT);
		
		return this;
	}
	
	//method
	public Pyramid setHeight(final double height) {
		
		Validator.suppose(height).thatIsNamed(VariableNameCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	public Pyramid setSideLength(final double sideLength) {
		
		Validator.suppose(sideLength).thatIsNamed(VariableNameCatalogue.SIDE_LENGTH).isPositive();
		
		this.sideLength.setValue(sideLength);
		
		return this;
	}
}
