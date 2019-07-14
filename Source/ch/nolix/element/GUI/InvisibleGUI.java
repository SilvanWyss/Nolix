//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.base.MutableProperty;

//class
public final class InvisibleGUI extends GUI<InvisibleGUI> {
	
	//constants
	private static final String CUSROR_X_POSITION_HEADER = "CursorXPosition";
	private static final String CUSROR_Y_POSITION_HEADER = "CursorYPosition";
	
	//attribute
	private final MutableProperty<Integer> width =
	new MutableProperty<>(
		PascalCaseNameCatalogue.WIDTH,
		w -> setWidth(w),
		s -> s.getOneAttributeAsInt(),		
		w -> new DocumentNode(w)
	);
	
	//attribute
	private final MutableProperty<Integer> height =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h),
		s -> s.getOneAttributeAsInt(),		
		h -> new DocumentNode(h)
	);
	
	//attribute
	private final MutableProperty<Integer> cursorXPosition =
	new MutableProperty<>(
		CUSROR_X_POSITION_HEADER,
		x -> setCursorXPosition(x),
		s -> s.getOneAttributeAsInt(),
		x -> new DocumentNode(x)
	);
	
	//attribute
	private final MutableProperty<Integer> cursorYPosition =
	new MutableProperty<>(
		CUSROR_Y_POSITION_HEADER,
		y -> setCursorYPosition(y),
		s -> s.getOneAttributeAsInt(),
		y -> new DocumentNode(y)
	);
	
	//method
	@Override
	public int getCursorXPosition() {
		return cursorXPosition.getValue();
	}
	
	//method
	@Override
	public int getCursorYPosition() {
		return cursorYPosition.getValue();
	}
	
	//method
	@Override
	public int getHeight() {
		return height.getValue();
	}
	
	//method
	@Override
	public int getWidth() {
		return width.getValue();
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return getHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return getWidth();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	public void paint() {}
	
	//method
	public InvisibleGUI setCursorXPosition(final int cursorXPosition) {
		
		this.cursorXPosition.setValue(cursorXPosition);
		
		return this;
	}
	
	//method
	public InvisibleGUI setCursorYPosition(final int cursorYPosition) {
		
		this.cursorYPosition.setValue(cursorYPosition);
		
		return this;
	}
	
	//method
	private InvisibleGUI setHeight(final int height) {
		
		Validator.suppose(height).thatIsNamed(VariableNameCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	private InvisibleGUI setWidth(final int width) {
		
		Validator.suppose(width).thatIsNamed(VariableNameCatalogue.WIDTH).isPositive();
		
		this.width.setValue(width);
		
		return this;
	}
	
	//method
	@Override
	protected void noteClosing() {}
}
