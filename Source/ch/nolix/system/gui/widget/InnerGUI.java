//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.functionuniversalapi.I2ElementTaker;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.element.MutableSpecificationValueExtractor;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.base.WidgetGUI;
import ch.nolix.system.gui.image.Image;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.systemapi.guiapi.baseapi.IWidgetGUI;
import ch.nolix.systemapi.guiapi.baseapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;

//class
public final class InnerGUI extends BorderWidget<InnerGUI, InnerGUILook> implements IWidgetGUI<InnerGUI> {
	
	//constant
	private static final String GUI_HEADER = PascalCaseCatalogue.GUI;
	
	//attributes
	private final WidgetGUI<?> internalGUI = new InvisibleGUI();
	private final Label titleLabel = new Label();
	private final ImageWidget iconWidget = new ImageWidget();
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableSpecificationValueExtractor internalGUIExtractor =
	new MutableSpecificationValueExtractor(GUI_HEADER,	internalGUI::resetFromSpecification,	internalGUI::getSpecification);
	
	//constructor
	public InnerGUI() {
		
		reset();
		
		setProposalWidth(200);
		setProposalHeight(200);
	}
	
	//method
	@Override
	public InnerGUI pushLayer(final Layer layer) {
		
		internalGUI.pushLayer(layer);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI pushLayer(final Widget<?, ?> rootWidget) {
		
		internalGUI.pushLayer(rootWidget);
		
		return this;
	}
	
	//method
	@Override
	public void clear() {
		internalGUI.clear();
	}
	
	//method
	@Override
	public int getCursorXPositionOnViewArea() {
		return internalGUI.getCursorXPositionOnViewArea();
	}
	
	//method
	@Override
	public int getCursorYPositionOnViewArea() {
		return internalGUI.getCursorYPositionOnViewArea();
	}
	
	//method
	@Override
	public Image getIcon() {
		return internalGUI.getIcon();
	}
	
	//method
	@Override
	public LinkedList<ChainedNode> getPaintCommands(final I2ElementTaker<String, IImage> imageRegistrator) {
		return internalGUI.getPaintCommands(imageRegistrator);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return internalGUI.getRefCloseController();
	}
	
	//method
	public <W extends Widget<?, ?>> W getRefWidgetById(final String id) {
		return internalGUI.getRefWidgetById(id);
	}
	
	//method
	@Override
	public String getTitle() {
		return internalGUI.getTitle();
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return internalGUI.getViewAreaHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return internalGUI.getViewAreaWidth();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return internalGUI.isEmpty();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	@Override
	public boolean isVisible() {
		return true;
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		internalGUI.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	@Override
	public void refresh() {}
	
	//method
	@Override
	public InnerGUI setBackgroundColor(final IColor backgroundColor) {
		
		internalGUI.setBackgroundColor(backgroundColor);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setBackgroundImage(final IImage backgroundImage) {
		
		internalGUI.setBackgroundImage(backgroundImage);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
		
		internalGUI.setBackgroundImage(backgroundImage, imageApplication);
		
		return null;
	}
	
	//method
	@Override
	public InnerGUI setConfiguration(final Configuration configuration) {
		
		internalGUI.setConfiguration(configuration);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setIcon(final IImage icon) {
		
		internalGUI.setIcon(icon);
		iconWidget.setImage(MutableImage.fromAnyImage(icon));
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setTitle(final String title) {
		
		internalGUI.setTitle(title);
		titleLabel.setText(title);
		
		return this;
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	@Override
	protected InnerGUILook createLook() {
		return new InnerGUILook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(titleLabel);
	}

	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(titleLabel);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return internalGUI.getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return internalGUI.getWidth();
	}
	
	//method
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		internalGUI.noteKeyDown(key);
	}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonClick();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonRelease();
	}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		internalGUI.noteMouseMove(getCursorXPositionOnViewArea(), getCursorYPositionOnViewArea());
	}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelClick();
	}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelPress();
	}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelRelease();
	}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonClick();
	}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonPress();
	}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonRelease();
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final InnerGUILook innerGUILook) {
		painter.translateVertically(titleLabel.getHeight());		
		internalGUI.paint(painter);
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		if (hasTargetWidth() && hasTargetHeight()) {
			internalGUI.noteResize(getContentArea().getTargetWidth(), getContentArea().getTargetHeight());
		}
		
		internalGUI.recalculate();
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetBorderWidget() {
		internalGUI.reset();
	}
}
