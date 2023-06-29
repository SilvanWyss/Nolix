//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.ILink;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class LinkHtmlBuilder implements IControlHtmlBuilder<ILink> {
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final ILink control) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.A,
			createHtmlAttributesForControl(control),
			control.getDisplayText()
		);
	}
	
	//method
	private IContainer<? extends IHtmlAttribute> createHtmlAttributesForControl(final ILink control) {
		
		final var htmlAttribtues = new LinkedList<IHtmlAttribute>();
		
		htmlAttribtues.addAtEnd(ControlHelper.INSTANCE.createIdHtmlAttributeForControl(control));
		htmlAttribtues.addAtEnd(createTargetHtmlAttributeForControl(control));
		
		if (control.hasUrl()) {
			htmlAttribtues.addAtEnd(HtmlAttribute.withNameAndValue("href", control.getUrl()));
		}
		
		return htmlAttribtues;
	}
	
	//method
	private HtmlAttribute createTargetHtmlAttributeForControl(final ILink control) {
		
		final var target = control.getTarget();
		
		return createTargetHtmlAttributeForTarget(target);
	}
	
	//method
	private HtmlAttribute createTargetHtmlAttributeForTarget(final LinkTarget target) {
		return
		switch (target) {
			case CURRENT_TAB ->
				HtmlAttribute.withNameAndValue("target", "_self");
			case NEW_TAB ->
				HtmlAttribute.withNameAndValue("target", "_blank");
			default ->
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.TARGET, target);
		};
	}
}