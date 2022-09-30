//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;

//class
public final class ButtonHTMLCreator {
	
	//static attribute
	public static final ButtonHTMLCreator INSTANCE = new ButtonHTMLCreator();
	
	//constructor
	private ButtonHTMLCreator() {}
	
	//method
	public HTMLElement createHTMLElementForButton(final IButton<?, ?> button) {
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HTMLElementTypeCatalogue.BUTTON,
			ImmutableList.withElements(
				ControlHelper.INSTANCE.createIdHTMLAttributeForControl(button),
				HTMLAttribute.withNameAndValue(
					"onclick",
					
					//TODO: Create ControlCommandCreator.
					"NoteLeftMouseButtonPress_" + button.getFixedId()
				)
			),
			button.getText()
		);
	}
}
