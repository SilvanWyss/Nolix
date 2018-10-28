//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.validator2.Validator;

//package-visible class
final class BackGUIClientoidBrowserGUI extends BackGUIClientoidGUI {
	
	//attribute
	private final IElementGetter<FrontBrowserGUIClientoidPainter> painterCreator;

	//constructor
	public BackGUIClientoidBrowserGUI(
		final IElementGetter<FrontBrowserGUIClientoidPainter> painterCreator
	) {
		
		reset();
		approveProperties();
		
		//Checks if the given painter creator is not null.
		Validator
		.suppose(painterCreator)
		.thatIsNamed("painter creator")
		.isInstance();
		
		//Sets the painter creator of the current GUI.
		this.painterCreator = painterCreator;
	}
	
	//method
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	protected void paint() {
		
		//Creates a painter.
		final var painter = painterCreator.getOutput();
		
		//Handles the case that the current GUI has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(getContentWidth(), getContentHeight());
		}
		
		//Handles the case that the current GUI has a background color gradient.
		if (hasBackgroundColorGradient()) {
			painter.setColorGradient(getBackgroundColorGradient());
			painter.paintFilledRectangle(getContentWidth(), getContentHeight());
		}
		
		//Handles the case that the current GUI has a root widget.
		if (hasRootWidget()) {
			getRefRootWidget().paintUsingPositionOnParent(painter);
		}
		
		//Flushes the painter.
		painter.flush();
	}
}
