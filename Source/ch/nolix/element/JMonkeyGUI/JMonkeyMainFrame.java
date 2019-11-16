//package declaration
package ch.nolix.element.JMonkeyGUI;

//Java imports
import java.util.concurrent.Callable;

//JMonkey import
import com.jme3.app.SimpleApplication;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;

import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.MainFrame;
import ch.nolix.element._3D_GUI.Shape;
import ch.nolix.element.planarShapes.Rectangle;
import ch.nolix.element.shapes.Cuboid;
import ch.nolix.element.shapes.Cylinder;
import ch.nolix.element.shapes.MultiShape;
import ch.nolix.element.shapes.Pyramid;
import ch.nolix.element.shapes.Sphere;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 260
 */
public final class JMonkeyMainFrame extends MainFrame<JMonkeyMainFrame> {
	
	//attributes
	private boolean closed = false;
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
			
			inputManager.addRawInputListener(new JMonkeyMainFrameRawInputListener(asConcreteType()));
		}
	};
	
 //constructor
 /**
 * Creates a new JMonkey main frame.
 */
	public JMonkeyMainFrame() {
		
		//Adds shape classes to this JMonkey main frame.
		addShapeClass(Cuboid.class, new JMonkeyCuboidRenderer());
		addShapeClass(Sphere.class, new JMonkeySphereRenderer());
		addShapeClass(Cylinder.class, new JMonkeyCylinderRenderer());
		addShapeClass(MultiShape.class, new JMonkeyMultiShapeRenderer());
		addShapeClass(Pyramid.class, new JMonkeyPyramidRenderer());
		addShapeClass(Rectangle.class, new JMonkeyRectangleRenderer());
		
		reset();
		
		final DirectionalLight directionalLight = new DirectionalLight();
		directionalLight.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
		directionalLight.setDirection(new Vector3f(1, 0.5f, -0.5f));
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
	 * Enqueues the given function to this JMonkey main frame.
	 * 
	 * @param method
	 */
	private void enqueue(final IFunction method) {
		
		Validator.suppose(method).thatIsNamed("method").isNotNull();
		
		simpleApplication.enqueue(
			new Callable<Object>() {

				//method
				/**
				 * Calls this callable object.
				 * 
				 * @return null.
				 */
				@Override
				public Object call() {
					method.run();
					return null;
				}
			}
		);
	}

	//method
	/**
	 * @return true if this JMonkey main frame is closed.
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}

	//method
	/**
	 * Closes this JMonkey main frame.
	 */
	@Override
	public void close() {
		closed = true;
		simpleApplication.destroy();
	}
	
	//method
	/**
	 * Lets this JMonkey main frame note a key press.
	 * 
	 * @param keyInputEvent
	 */
	public void noteKeyPress(final KeyInputEvent keyInputEvent) {}
	
	//method
	/**
	 * Lets this JMonkey main frame note a left mouse button press.
	 */
	@Override
	public void noteLeftMouseButtonPress() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note 
	 */
	@Override
	public void noteLeftMouseButtonRelease() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note a right mouse button press.
	 */
	@Override
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note a right mouse button release.
	 */
	@Override
	public void noteRightMouseButtonRelease() {}

	//method
	/**
	 * Refreshes this JMonkey main frmae.
	 */
	@Override
	public void refresh() {
		enqueue(() -> direct_refresh());
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this main frame.
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	@Override
	public JMonkeyMainFrame setRootShape(final Shape<?> rootShape) {
		
		//Calls method of the base class.
		super.setRootShape(rootShape);
		
		enqueue(() -> direct_attachRootShape(rootShape));
		
		return this;
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
