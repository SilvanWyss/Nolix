//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILinkStyle;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Link extends Control<ILink, ILinkStyle> implements ILink {
	
	//constant
	public static final String DEFAULT_DISPLAY_TEXT = StringCatalogue.QUESTION_MARK;
	
	//constant
	private static final LinkTarget DEFAULT_TARGET = LinkTarget.NEW_TAB;
	
	//constant
	private static final String DISPLAY_TEXT_HEADER = "DisplayText";
	
	//constant
	private static final String TARGET_HEADER = PascalCaseCatalogue.TARGET;
	
	//constant
	private static final String URL_HEADER = PascalCaseCatalogue.URL;
	
	//constant
	private static final LinkHtmlBuilder LINK_HTML_BUILDER = new LinkHtmlBuilder();
	
	//constant
	private static final LinkCssRuleBuilder LINK_CSS_RULE_BUILDER = new LinkCssRuleBuilder();
	
	//attribute
	private final MutableValue<String> displayText =
	MutableValue.forString(DISPLAY_TEXT_HEADER, DEFAULT_DISPLAY_TEXT, this::setDisplayText);
	
	//attribute
	private final MutableValue<LinkTarget> target =
	new MutableValue<>(
		TARGET_HEADER,
		DEFAULT_TARGET,
		this::setTarget,
		s -> LinkTarget.valueOf(s.getSingleChildNodeHeader()),
		Node::fromEnum
	);
	
	//attribute
	private final MutableOptionalValue<String> url = MutableOptionalValue.forString(URL_HEADER, this::setUrl); 
	
	//constructor
	public Link() {
		
		//Info: Reset is technically optional, but required to achieve a custom state on reset.
		reset();
	}
	
	//method
	@Override
	public String getDisplayText() {
		return displayText.getValue();
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public LinkTarget getTarget() {
		return target.getValue();
	}
	
	//method
	@Override
	public String getUrl() {
		return url.getValue();
	}
	
	//method
	@Override
	public String getUserInput() {
		return StringCatalogue.EMPTY_STRING;
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean hasUrl() {
		return url.hasValue();
	}
	
	//method
	@Override
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeUrl() {
		url.clear();
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
	}
	
	//method
	@Override
	public ILink setDisplayText(final String displayText) {
		
		GlobalValidator.assertThat(displayText).thatIsNamed("dipslay text").isNotBlank();
		
		this.displayText.setValue(displayText);
		
		return this;
	}
	
	//method
	@Override
	public ILink setTarget(final LinkTarget target) {
		
		this.target.setValue(target);
		
		return this;
	}
	
	//method
	@Override
	public ILink setUrl(final String url) {
		
		GlobalValidator.assertThat(url).thatIsNamed(LowerCaseCatalogue.URL).isNotBlank();
		
		this.url.setValue(url);
		
		return this;
	}
	
	//method
	@Override
	public ILink setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
		return this;
	}
	
	//method
	@Override
	protected ILinkStyle createStyle() {
		return new LinkStyle();
	}
	
	//method
	@Override
	protected IControlCssRuleBuilder<ILink, ILinkStyle> getCssRuleCreator() {
		return LINK_CSS_RULE_BUILDER;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<ILink> getHtmlBuilder() {
		return LINK_HTML_BUILDER;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		setDisplayText(DEFAULT_DISPLAY_TEXT);
		removeUrl();
		
		setCursorIcon(CursorIcon.HAND);
		getOriStyle().setTextColorForState(ControlState.BASE, Color.BLUE);
	}
}
