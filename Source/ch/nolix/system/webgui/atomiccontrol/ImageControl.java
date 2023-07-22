//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControlStyle;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class ImageControl extends Control<IImageControl, IImageControlStyle> implements IImageControl {
	
	//constant
	private static final String IMAGE_HEADER = PascalCaseCatalogue.IMAGE;
	
	//constant
	private static final ImageControlHtmlBuilder HTML_BUILDER = new ImageControlHtmlBuilder();
	
	//constant
	private static final ImageControlCssRuleBuilder CSS_RULE_BUILDER = new ImageControlCssRuleBuilder();
	
	//attribute
	private final MutableOptionalValue<MutableImage> image =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		MutableImage::fromSpecification,
		MutableImage::getSpecification
	);
	
	//optional attribute
	private IElementTaker<IImageControl> leftMouseButtonPressAction;
	
	//optional attribute
	private IElementTaker<IImageControl> leftMouseButtonReleaseAction;
	
	//method
	@Override
	public void clear() {
		image.clear();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return !image.hasValue();
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getStoredChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public MutableImage getStoredImage() {
		return image.getValue();
	}
	
	//method
	@Override
	public String getUserInput() {
		return StringCatalogue.EMPTY_STRING;
	}
	
	//method
	@Override
	public boolean hasLeftMouseButtonPressAction() {
		return (leftMouseButtonPressAction != null);
	}
	
	//method
	@Override
	public boolean hasLeftMouseButtonReleaseAction() {
		return (leftMouseButtonReleaseAction != null);
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onclick"));
	}
	
	//method
	@Override
	public void removeLeftMouseButtonPressAction() {
		leftMouseButtonPressAction = null;
	}
	
	//method
	@Override
	public void removeLeftMouseButtonReleaseAction() {
		leftMouseButtonReleaseAction = null;
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		
		GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onclick");
		
		if (hasLeftMouseButtonPressAction()) {
			leftMouseButtonPressAction.run(this);
		}
	}
	
	//method
	@Override
	public IImageControl setImage(final IImage image) {
		
		if (image instanceof MutableImage mutableImage) {
			this.image.setValue(mutableImage);
		} else {
			this.image.setValue(MutableImage.fromAnyImage(image));
		}
		
		return this;
	}
	
	//method
	@Override
	public IImageControl setLeftMouseButtonPressAction(final IAction leftMouseButtonPressAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonPressAction)
		.thatIsNamed("left mouse button press action")
		.isNotNull();
		
		return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
	}
	
	//method
	@Override
	public IImageControl setLeftMouseButtonPressAction(final IElementTaker<IImageControl> leftMouseButtonPressAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonPressAction)
		.thatIsNamed("left mouse button press action")
		.isNotNull();
		
		this.leftMouseButtonPressAction = leftMouseButtonPressAction;
		
		return this;
	}
	
	//method
	@Override
	public IImageControl setLeftMouseButtonRelaseAction(final IAction leftMouseButtonReleaseAction) {
		
		GlobalValidator
		.assertThat(leftMouseButtonReleaseAction)
		.thatIsNamed("left mouse button release action")
		.isNotNull();
		
		return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
	}
	
	//method
	@Override
	public IImageControl setLeftMouseButtonRelaseAction(
		final IElementTaker<IImageControl> leftMouseButtonReleaseAction
	) {
		
		GlobalValidator
		.assertThat(leftMouseButtonReleaseAction)
		.thatIsNamed("left mouse button release action")
		.isNotNull();
		
		this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;
		
		return this;
	}
	
	//method
	@Override
	public IImageControl setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
		return null;
	}
	
	//method
	@Override
	protected IImageControlStyle createStyle() {
		return new ImageControlStyle();
	}
	
	//method
	@Override
	protected IControlCssBuilder<IImageControl, IImageControlStyle> getCssRuleCreator() {
		return CSS_RULE_BUILDER;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IImageControl> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected void resetControl() {
		
		clear();
		
		removeLeftMouseButtonPressAction();
		removeLeftMouseButtonReleaseAction();
	}
}
