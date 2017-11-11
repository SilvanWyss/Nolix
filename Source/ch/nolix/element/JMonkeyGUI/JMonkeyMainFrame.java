//package declaration
package ch.nolix.element.JMonkeyGUI;

//Java imports
import java.util.concurrent.Callable;

//JMonkey import
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

//own imports
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element._3DGUI.Cuboid;
import ch.nolix.element._3DGUI.MainFrame;
import ch.nolix.element._3DGUI.MultiShape;
import ch.nolix.element._3DGUI.Shape;
import ch.nolix.element._3DGUI.Sphere;
import ch.nolix.element.color.Color;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 10
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
				
				
				inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
				inputManager.addListener(actionListener, "Pause");
			}
		};
		
		private ActionListener actionListener = new ActionListener() {
		    public void onAction(String name, boolean keyPressed, float tpf) {
		    	testAction();}
		    };
		    
    public void testAction() {  
    }
		  
    //constructor
    /**
     * Creates new JMonkey main frame.
     */
	public JMonkeyMainFrame() {
		
		//Adds shape classes to this JMonkey main frame.
		addShapeClass(Cuboid.class, new JMonkeyCuboidRenderer());
		addShapeClass(Sphere.class, new JMonkeySphereRenderer());
		addShapeClass(MultiShape.class, new JMonkeyMultiShapeRenderer());
		
		final DirectionalLight directionalLight = new DirectionalLight();
		
		directionalLight.setColor(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
		directionalLight.setDirection(new Vector3f(1, 0.5f, -0.5f));
		simpleApplication.getRootNode().addLight(directionalLight);
		
		
		simpleApplication.setShowSettings(false);
		simpleApplication.start();
		
		enqueue(() -> testAction());
	}
	
	//method
	/**
	 * Enqueues the given function to this JMonkey main frame.
	 * 
	 * @param method
	 */
	private void enqueue(final IRunner method) {
		
		Validator.suppose(method).thatIsNamed("method").isNotNull();
		
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

	@Override
	public void resetConfiguration() {
		// TODO Auto-generated method stub
	}
	
	public void refresh() {
		enqueue(() -> internal_refresh());
	}
	
	private void internal_refresh() {
		
		super.refresh();
		
		simpleApplication.getRenderer().setBackgroundColor(JMonkeyColorHelper.createColorRGBA(Color.BEIGE));
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this main frame.
	 * @throws NullArgumentException if the given root shape is null.
	 */
	public JMonkeyMainFrame setRootShape(final Shape<?> rootShape) {
						
		enqueue(() -> {
			
			super.setRootShape(rootShape);
			
			simpleApplication
			.getRootNode()
			.attachChild(
			rootShape.getRefRenderObject());
		});
		
		return this;
	}
}
