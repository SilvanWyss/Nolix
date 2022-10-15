//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class VerticalStackHTMLCreator {
	
	//constant
	public static final String CHILD_CONTROL_CSS_CLASS_NAME = "verticalStackChild";
	
	//static attribute
	public static final VerticalStackHTMLCreator INSTANCE = new VerticalStackHTMLCreator();
	
	//constructor
	private VerticalStackHTMLCreator() {}
	
	//method
	public HTMLElement createHTMLElementForVerticalStack(final VerticalStack verticalStack) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(verticalStack)),
			createHTMLElementsForChildControlsOfVerticalStack(verticalStack)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForChildControlsOfVerticalStack(final VerticalStack verticalStack) {
		return verticalStack.getRefChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HTMLElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return
		HTMLElement.withTypeAndAttributesAndChildElement(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(
				HTMLAttribute.withNameAndValue(
					HTMLAttributeNameCatalogue.CLASS,
					CHILD_CONTROL_CSS_CLASS_NAME
				)
			),
			childControl.toHTMLElement()
		);
	}
}
