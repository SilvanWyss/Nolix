//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.GUI;
import ch.nolix.element.gui.IWidgetGUI;
import ch.nolix.element.gui.InvisibleGUI;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class InnerGUI extends BorderWidget<InnerGUI, InnerGUILook> implements IWidgetGUI<InnerGUI> {
	
	//constant
	public static final String DEFAULT_TITLE = GUI.DEFAULT_TITLE;
	
	//attributes
	private final InvisibleGUI internalGUI = new InvisibleGUI();
	private final Label titleLabel = new Label();
	
	//constructor
	public InnerGUI() {
		
		//TODO: Remove this implementation.
		internalGUI.noteResize(200, 200);
	}
	
	//method
	@Override
	public InnerGUI addLayerOnTop(final Layer layer) {
		
		internalGUI.addLayerOnTop(layer);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI addLayerOnTop(final Widget<?, ?> rootWidget) {
		
		internalGUI.addLayerOnTop(rootWidget);
		
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
	public IContainer<ChainedNode> getPaintCommands() {
		return internalGUI.getPaintCommands();
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
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		internalGUI.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	@Override
	public void refresh() {}
	
	//method
	@Override
	public InnerGUI setBackgroundColor(final Color backgroundColor) {
		
		internalGUI.setBackgroundColor(backgroundColor);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setConfiguration(final Configuration configuration) {
		
		internalGUI.setConfiguration(configuration);
		
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(titleLabel);
	}
	
	//method
	@Override
	protected void fillUpWidgetAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(internalGUI.getSpecification());
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
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {
		internalGUI.noteKeyPress(key);
	}
	
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
	protected void paintContentArea(final InnerGUILook borderWidgetLook, final IPainter painter) {
		painter.translateVertically(titleLabel.getHeight());		
		internalGUI.paint(painter);
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		//TODO: internalGUI.noteResize(getTargetContentAreaWidth(), getTargetContentAreaHeight())
		internalGUI.recalculate();
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	@Override
	protected void resetBorderWidget() {
		internalGUI.reset();
	}
}
