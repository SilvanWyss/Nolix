package ch.nolix.system.webatomiccontrol.imagecontrol;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControlStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public final class ImageControl extends Control<IImageControl, IImageControlStyle> implements IImageControl {
  public static final String DEFAULT_ALTERNATE_TEXT = StringCatalog.EMPTY_STRING;

  private static final String IMAGE_HEADER = PascalCaseVariableCatalog.IMAGE;

  private static final String ALTERNATE_TEXT_HEADER = "AlternateText";

  private static final ImageControlHtmlBuilder HTML_BUILDER = new ImageControlHtmlBuilder();

  private static final ImageControlCssBuilder CSS_BUILDER = new ImageControlCssBuilder();

  private final MutableOptionalValue<MutableImage> image = //
  new MutableOptionalValue<>(
    IMAGE_HEADER,
    this::setImage,
    MutableImage::fromSpecification,
    MutableImage::getSpecification);

  private final MutableValue<String> alternateText = //
  MutableValue.forString(ALTERNATE_TEXT_HEADER, DEFAULT_ALTERNATE_TEXT, this::setAlternateText);

  private Consumer<IImageControl> leftMouseButtonPressAction;

  private Consumer<IImageControl> leftMouseButtonReleaseAction;

  public ImageControl() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle().forStateSetBackgroundColor(ControlState.BASE, X11ColorCatalog.LIGHT_GREY);
  }

  @Override
  public void clear() {
    image.clear();
  }

  @Override
  public boolean isEmpty() {
    return image.isEmpty();
  }

  @Override
  public String getAlternateText() {
    return alternateText.getValue();
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public MutableImage getStoredImage() {
    return image.getValue();
  }

  @Override
  public String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  @Override
  public boolean hasLeftMouseButtonPressAction() {
    return (leftMouseButtonPressAction != null);
  }

  @Override
  public boolean hasLeftMouseButtonReleaseAction() {
    return (leftMouseButtonReleaseAction != null);
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onclick"));
  }

  @Override
  public void removeLeftMouseButtonPressAction() {
    leftMouseButtonPressAction = null;
  }

  @Override
  public void removeLeftMouseButtonReleaseAction() {
    leftMouseButtonReleaseAction = null;
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    Validator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onclick");

    if (hasLeftMouseButtonPressAction()) {
      leftMouseButtonPressAction.accept(this);
    }
  }

  @Override
  public IImageControl setAlternateText(final String alternateText) {
    this.alternateText.setValue(alternateText);

    return this;
  }

  @Override
  public IImageControl setImage(final IImage image) {
    if (image instanceof final MutableImage mutableImage) {
      this.image.setValue(mutableImage);
    } else {
      this.image.setValue(MutableImage.fromAnyImage(image));
    }

    return this;
  }

  @Override
  @SuppressWarnings("unused")
  public IImageControl setLeftMouseButtonPressAction(final Runnable leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
  }

  @Override
  public IImageControl setLeftMouseButtonPressAction(final Consumer<IImageControl> leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    this.leftMouseButtonPressAction = leftMouseButtonPressAction;

    return this;
  }

  @Override
  @SuppressWarnings("unused")
  public IImageControl setLeftMouseButtonRelaseAction(final Runnable leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
  }

  @Override
  public IImageControl setLeftMouseButtonRelaseAction(
    final Consumer<IImageControl> leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;

    return this;
  }

  @Override
  public IImageControl setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected IImageControlStyle createStyle() {
    return new ImageControlStyle();
  }

  @Override
  protected IControlCssBuilder<IImageControl, IImageControlStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<IImageControl> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetControl() {
    clear();

    removeLeftMouseButtonPressAction();
    removeLeftMouseButtonReleaseAction();
  }
}
