//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.commontype.constant.StringCatalogue;
//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.webguiapi.controlapi.IImageControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IControlCSSRuleCreator;

//class
public final class ImageControl
extends Control<ImageControl, ImageControlStyle>
implements IImageControl<ImageControl, ImageControlStyle, MutableImage> {
	
	//constant
	private static final String IMAGE_HEADER = PascalCaseCatalogue.IMAGE;
	
	//attribute
	private final MutableOptionalValue<MutableImage> image =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		MutableImage::fromSpecification,
		MutableImage::getSpecification
	);
	
	//attribute
	private final ImageControlCSSRuleCreator mCSSRuleCreator = ImageControlCSSRuleCreator.forImageControl(this);
	
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
	public IControlCSSRuleCreator<ImageControl, ImageControlStyle> getCSSRuleCreator() {
		return mCSSRuleCreator;
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getRefChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public MutableImage getRefImage() {
		return image.getValue();
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
	public void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public ImageControl setImage(final IImage image) {
		
		if (image instanceof MutableImage) {
			this.image.setValue((MutableImage)image);
		} else {
			this.image.setValue(MutableImage.fromAnyImage(image));
		}
		
		return this;
	}
	
	//method
	@Override
	public ImageControl setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();
		
		return null;
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return ImageControlHTMLCreator.INSTANCE.createHTMLElementFromImageControl(this);
	}
	
	//method
	@Override
	protected ImageControlStyle createStyle() {
		return new ImageControlStyle();
	}
	
	//method
	@Override
	protected void resetControl() {
		clear();
	}
}
