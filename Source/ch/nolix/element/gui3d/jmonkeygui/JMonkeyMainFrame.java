//package declaration
package ch.nolix.element.gui3d.jmonkeygui;

//JMonkey import
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui3d.base.MainFrame;
import ch.nolix.element.gui3d.base.Shape;
import ch.nolix.element.gui3d.planarshape.Rectangle;
import ch.nolix.element.gui3d.shape.BaseCube;
import ch.nolix.element.gui3d.shape.Cylinder;
import ch.nolix.element.gui3d.shape.MultiShape;
import ch.nolix.element.gui3d.shape.Pyramid;
import ch.nolix.element.gui3d.shape.Sphere;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 390
 */
public final class JMonkeyMainFrame extends MainFrame<JMonkeyMainFrame> {
	
	//attributes
	private boolean closed;
	private final AppSettings appSettings = new AppSettings(true);
	
	//attribute
	private final SimpleApplication simpleApplication = new SimpleApplication() {
	
		//method
		/**
		 * Initializes this simple application.
		 */
		@Override
		public void simpleInitApp() {
			setDisplayStatView(false);
			setDisplayFps(false);
			flyCam.setEnabled(false);
			
			inputManager.addRawInputListener(new JMonkeyMainFrameRawInputListener(asConcrete()));
		}
	};
	
	//constructor
	/**
	 * Creates a new JMonkey main frame.
	 */
	public JMonkeyMainFrame() {
		
		//Adds shape classes to this JMonkey main frame.
		addShapeClass(BaseCube.class, new JMonkeyBaseCubeRenderer());
		addShapeClass(Sphere.class, new JMonkeySphereRenderer());
		addShapeClass(Cylinder.class, new JMonkeyCylinderRenderer());
		addShapeClass(MultiShape.class, new JMonkeyMultiShapeRenderer());
		addShapeClass(Pyramid.class, new JMonkeyPyramidRenderer());
		addShapeClass(Rectangle.class, new JMonkeyRectangleRenderer());
		
		reset();
		
		final DirectionalLight directionalLight = new DirectionalLight();
		directionalLight.setColor(new ColorRGBA(1.0F, 1.0F, 1.0F, 1.0F));
		directionalLight.setDirection(new Vector3f(1, 0.5F, -0.5F));
		simpleApplication.getRootNode().addLight(directionalLight);
				
		appSettings.setTitle(getTitle());
		appSettings.setResizable(true);
		simpleApplication.setSettings(appSettings);
		simpleApplication.setShowSettings(false);
		simpleApplication.start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JMonkeyMainFrame} with the given root shape.
	 * 
	 * @param rootShape
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	public JMonkeyMainFrame(final Shape<?> rootShape) {
		
		//Calls other constructor.
		this();
		
		setRootShape(rootShape);
	}
	
	//constructor
	/**
	 * Creates a new {@link JMonkeyMainFrame} with the given title.
	 * 
	 * @param title
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws EmptyArgumentException if the given title is empty.
	 */
	public JMonkeyMainFrame(final String title) {
	
		//Calls other constructor.
		this();
		
		setTitle(title);
	}
	
	//constructor
	/**
	 * Creates a new {@link JMonkeyMainFrame} with the given title and root shape.
	 * 
	 * @param title
	 * @param rootShape
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws EmptyArgumentException if the given title is empty.
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	public JMonkeyMainFrame(final String title, final Shape<?> rootShape) {
		
		//Calls other constructor.
		this(title);
		
		setRootShape(rootShape);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		closed = true;
		simpleApplication.destroy();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorXPositionOnViewArea() {
		return (int)simpleApplication.getInputManager().getCursorPosition().y;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorYPositionOnViewArea() {
		return (int)simpleApplication.getInputManager().getCursorPosition().x;

	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return appSettings.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewAreaHeight() {
		return simpleApplication.getGuiViewPort().getCamera().getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewAreaWidth() {
		return simpleApplication.getGuiViewPort().getCamera().getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return appSettings.getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyPress(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyRelease(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyTyping(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	//method
	@Override
	public void noteLeftMouseButtonClick() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPress() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonRelease() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	//method
	@Override
	public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	//method
	@Override
	public void noteMouseWheelClick() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelPress() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRelease() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonClick() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonRelease() {}
		
	//method
	/**
	 * Refreshes this JMonkey main frmae.
	 */
	@Override
	public void refreshGUI() {
		enqueue(this::direct_refresh);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteSetRootShape(final Shape<?> rootShape) {
		enqueue(() -> direct_attachRootShape(rootShape));
	}
	
	//method
	/**
	 * Enqueues the given function to this JMonkey main frame.
	 * 
	 * @param method
	 */
	private void enqueue(final IAction method) {
		
		Validator.assertThat(method).thatIsNamed(LowerCaseCatalogue.METHOD).isNotNull();
		
		simpleApplication.enqueue(method::run);
	}
	
	//method
	/**
	 * Refreshes this JMonkey main frmae.
	 */
	private void direct_refresh() {
		
		if (!appSettings.getTitle().equals(getTitle())) {
			appSettings.setTitle(getTitle());
			simpleApplication.setSettings(appSettings);
			simpleApplication.restart();
		}
		
		//Paints the background color of this JMonkey main frame.
		simpleApplication
		.getViewPort()
		.setBackgroundColor(JMonkeyColorHelper.createColorRGBA(getBackgroundColor()));
				
		//Calls method of the base class.
		super.refresh();
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	private void direct_attachRootShape(final Shape<?> rootShape) {
		simpleApplication
		.getRootNode()
		.attachChild(rootShape.getRefRenderObject());
	}
}
