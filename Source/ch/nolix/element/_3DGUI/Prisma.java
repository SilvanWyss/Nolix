//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.core.PositiveFloatingPointNumber;

//abstract class
public abstract class Prisma<P extends Prisma<P>> extends BaseShape<P> {
	
	//default value
	public static final double DEFAULT_HEIGHT = 2.0;

	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> height =
	new MutableProperty<PositiveFloatingPointNumber>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		h -> h.getSpecification()
	);
	
	//method
	public final double getHeight() {
		return height.getValue().getValue();
	}
	
	//method
	public final float getHeightAsFloat() {
		return (float)getHeight();
	}
	
	//method
	public P reset() {
		
		//Calls method of base class.
		super.reset();
		
		setHeight(DEFAULT_HEIGHT);
		
		return getInstance();
	}
	
	//method
	public final P setHeight(final double height) {
		
		this.height.setValue(new PositiveFloatingPointNumber(height));
		
		return getInstance();
	}
}
