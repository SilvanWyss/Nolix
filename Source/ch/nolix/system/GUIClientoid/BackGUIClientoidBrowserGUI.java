//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.validator.Validator;

//package-visible class
final class BackGUIClientoidBrowserGUI extends BackGUIClientoidGUI {
	
	//constructor
	public BackGUIClientoidBrowserGUI(
		final IElementGetter<BackBrowserGUIClientoidPainter> painterCreator
	) {
		
		reset();
		
		//Checks if the given painter creator is not null.
		Validator
		.suppose(painterCreator)
		.thatIsNamed("painter creator")
		.isNotNull();
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
