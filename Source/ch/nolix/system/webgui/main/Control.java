//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.data.GlobalIdCreator;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.system.element.mutableelement.ExtensionElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.structure.RelativeOrAbsoluteInt;
import ch.nolix.system.structure.RelativeOrAbsoluteIntValidator;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public abstract class Control<
	C extends Control<C, CS>,
	CS extends IControlStyle<CS>
>
extends StylableElement<C>
implements IControl<C, CS> {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constant
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	
	//constant
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	
	//constant
	private static final String CURSOR_ICON_HEADER = PascalCaseCatalogue.CURSOR_ICON;
	
	//attribute
	//An id works correctly for CSS only when it begins with a letter.
	private final String fixedId = "i" + GlobalIdCreator.createIdOf10HexadecimalCharacters();
	
	//attribute
	private final MutableOptionalValue<RelativeOrAbsoluteInt> maxWidth =
	MutableOptionalValue.forElement(
		MAX_WIDTH_HEADER,
		this::setMaxWidth,
		RelativeOrAbsoluteInt::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<RelativeOrAbsoluteInt> maxHeight =
	MutableOptionalValue.forElement(
		MAX_HEIGHT_HEADER,
		this::setMaxHeight,
		RelativeOrAbsoluteInt::fromSpecification
	);
	
	//attribute
	private final MutableValue<CursorIcon> cursorIcon =
	new MutableValue<>(
		CURSOR_ICON_HEADER,
		DEFAULT_CURSOR_ICON,
		this::setCursorIcon,
		CursorIcon::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final ExtensionElement<CS> style = new ExtensionElement<>(createStyle());
	
	//optional attribute
	private ControlParent parent;
	
	//method
	@Override
	public final boolean belongsToGUI() {
		return (belongsToLayer() && getRefParentLayer().belongsToGUI());
	}
	
	//method
	@Override
	public final boolean belongsToLayer() {
		return (belongsToParent() && parent.belongsToLayer());
	}
	
	//method
	@Override
	public final C editStyle(final IElementTaker<CS> styleEditor) {
		
		styleEditor.run(getRefStyle());
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IContainer<ICSSRule<?>> getCSSRules() {
		return getCSSRuleCreator().createCSSRulesForControl(asConcrete());
	}
	
	//method
	@Override
	public final CursorIcon getCursorIcon() {
		return cursorIcon.getValue();
	}
	
	//method
	@Override
	public final String getFixedId() {
		return fixedId;
	}
	
	//method
	@Override
	public final IRelativeOrAbsoluteInt getMaxHeight() {
		return maxHeight.getValue();
	}
	
	//method
	@Override
	public final IRelativeOrAbsoluteInt getMaxWidth() {
		return maxWidth.getValue();
	}
	
	//method
	@Override
	public final IContainer<? extends IStylableElement<?>> getRefChildStylableElements() {
		return getRefChildControls();
	}
	
	//method
	@Override
	public final IWebGUI<?> getRefParentGUI() {
		return getRefParentLayer().getRefParentGUI();
	}
	
	//method
	@Override
	public final ILayer<?> getRefParentLayer() {
		return getRefParent().getRefRootLayer();
	}
	
	//method
	@Override
	public final CS getRefStyle() {
		return style.getExtensionElement();
	}
	
	//method
	@Override
	public final boolean hasFixedId(final String fixedId) {
		return getFixedId().equals(fixedId);
	}
	
	//method
	public final boolean hasMaxHeight() {
		return maxHeight.hasValue();
	}
	
	//method
	public final boolean hasMaxWidth() {
		return maxWidth.hasValue();
	}
	
	//method
	@Override
	public final void removeMaxHeight() {
		maxHeight.clear();
	}
	
	//method
	@Override
	public final void removeMaxWidth() {
		maxWidth.clear();
	}
	
	//method
	@Override
	public final C setCursorIcon(final CursorIcon cursorIcon) {
		
		this.cursorIcon.setValue(cursorIcon);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final C setMaxHeight(final int maxHeight) {
		
		setMaxHeight(RelativeOrAbsoluteInt.withIntValue(maxHeight));
		
		return asConcrete();
	}
	
	//method
	@Override
	public final C setMaxHeightInPercentOfViewAreaHeight(final double maxHeightInPercentOfViewAreaHeight) {
		
		setMaxHeight(RelativeOrAbsoluteInt.withPercentage(maxHeightInPercentOfViewAreaHeight));
		
		return asConcrete();
	}
	
	//method
	@Override
	public final C setMaxWidth(final int maxWidth) {
		
		setMaxWidth(RelativeOrAbsoluteInt.withIntValue(maxWidth));
		
		return asConcrete();
	}
	
	//method
	@Override
	public final C setMaxWidthInPercentOfViewAreaWidth(final double maxWidthInPercentOfViewAreaWidth) {
		
		setMaxWidth(RelativeOrAbsoluteInt.withPercentage(maxWidthInPercentOfViewAreaWidth));
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void technicalSetParentControl(final IControl<?, ?> parentControl) {
		setParent(ControlParent.forControl(parentControl));
	}
	
	//method
	@Override
	public  final void technicalSetParentLayer(final ILayer<?> parentLayer) {
		setParent(ControlParent.forLayer(parentLayer));		
	}
	
	//method
	@Override
	public final IHTMLElement<?, ?> toHTMLElement() {
		return getHTMLBuilder().createHTMLElementForControl(asConcrete());
	}
	
	//method
	@Override
	public final String toHTMLString() {
		return toHTMLElement().toString();
	}
	
	//method declaration
	protected abstract CS createStyle();
	
	//method declaration
	protected abstract IControlCSSRuleBuilder<C, CS> getCSSRuleCreator();
	
	//method declaration
	protected abstract IControlHTMLBuilder<C> getHTMLBuilder();
	
	//method declaration
	protected abstract void resetControl();
	
	//method
	@Override
	protected final void resetStylableElement() {
		
		setCursorIcon(DEFAULT_CURSOR_ICON);
		
		resetControl();
	}
	
	//method
	@Override
	protected final void resetStyle() {
		getRefStyle().reset();
	}
	
	//method
	private void assertBelongsToParent() {
		if (!belongsToParent()) {
			throw ArgumentDoesNotBelongToParentException.forArgument(this);
		}
	}
	
	//method
	private void assertDoesNotBelongToParent() {
		if (belongsToParent()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(this, parent.getRefElement());
		}
	}
	
	//method
	private boolean belongsToParent() {
		return (parent != null);
	}
	
	//method
	private ControlParent getRefParent() {
		
		assertBelongsToParent();
		
		return parent;
	}
	
	//method
	private void setMaxHeight(final RelativeOrAbsoluteInt maxHeight) {
		
		RelativeOrAbsoluteIntValidator.INSTANCE.assertIsPositive(maxHeight);
		
		this.maxHeight.setValue(maxHeight);
	}
	
	//method
	private void setMaxWidth(final RelativeOrAbsoluteInt maxWidth) {
		
		RelativeOrAbsoluteIntValidator.INSTANCE.assertIsPositive(maxWidth);
		
		this.maxWidth.setValue(maxWidth);
	}
	
	//method
	private void setParent(final ControlParent parent) {
		
		GlobalValidator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
		assertDoesNotBelongToParent();
		
		this.parent = parent;
	}
}
