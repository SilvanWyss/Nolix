//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.validator2.Validator;

//package-visible class
final class BackGUIClientoidBrowserGUI extends BackGUIClientoidGUI {
	
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
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	public void paint() {}
}
