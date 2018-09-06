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

//own imports
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.element._3DGUI.Cuboid;
import ch.nolix.element._3DGUI.MainFrame;
import ch.nolix.element._3DGUI.MultiShape;
import ch.nolix.element._3DGUI.Shape;
import ch.nolix.element._3DGUI.Sphere;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 210
 */
public final class JMonkeyMainFrame extends MainFrame<JMonkeyMainFrame> {

	//attribute
	private boolean closed = false;
	
	//attribute
	private final SimpleApplication simpleApplication = new SimpleApplication() {
	
		//method
		/**
		 * Initializes this simple application.
		 */
		public void simpleInitApp() {
			setDisplayStatView(false);
			setDisplayFps(false);
			flyCam.setEnabled(false);
			
			inputManager.addRawInputListener(new JMonkeyMainFrameRawInputListener(getInstance()));
		}
	};
		
	AppSettings appSettings = new AppSettings(true);
		  
    //constructor
    /**
     * Creates a new JMonkey main frame.
     */
	public JMonkeyMainFrame() {
		
		//Adds shape classes to this JMonkey main frame.
		addShapeClass(Cuboid.class, new JMonkeyCuboidRenderer());
		addShapeClass(Sphere.class, new JMonkeySphereRenderer());
		addShapeClass(MultiShape.class, new JMonkeyMultiShapeRenderer());
		
		reset();
		
		final DirectionalLight directionalLight = new DirectionalLight();		
		directionalLight.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
		directionalLight.setDirection(new Vector3f(1, 0.5f, -0.5f));
		simpleApplication.getRootNode().addLight(directionalLight);		
		
		appSettings.setTitle(getTitle());
		simpleApplication.setSettings(appSettings);
		
		simpleApplication.setShowSettings(false);
		simpleApplication.start();
	}
	
	//method
	/**
	 * Enqueues the given function to this JMonkey main frame.
	 * 
	 * @param method
	 */
	private void enqueue(final IFunction method) {
		
		Validator.suppose(method).thatIsNamed("method").isInstance();
		
		simpleApplication.enqueue(
			new Callable<Object>() {

				//method
				/**
				 * Calls this callable object.
				 * 
				 * @return null.
				 */
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
	public boolean isClosed() {
		return closed;
	}

	//method
	/**
	 * Closes this JMonkey main frame.
	 */
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
	public void noteLeftMouseButtonPress() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note 
	 */
	public void noteLeftMouseButtonRelease() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets this JMonkey main frame note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {}

	//method
	/**
	 * Refreshes this JMonkey main frmae.
	 */
	public void refresh() {
		enqueue(() -> direct_refresh());
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this main frame.
	 * @throws NullArgumentException if the given root shape is not an instance.
	 */
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
	 * @throws NullArgumentException if the given root shape is not an instance.
	 */
	private void direct_attachRootShape(final Shape<?> rootShape) {
		simpleApplication
		.getRootNode()
		.attachChild(rootShape.getRefRenderObject());
	}
}
