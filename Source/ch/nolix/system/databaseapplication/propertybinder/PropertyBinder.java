//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own mports
import ch.nolix.core.functionapi.IAction;
import ch.nolix.element.gui.widget.Widget;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;

//class
public abstract class PropertyBinder<P extends IProperty<?>, W extends Widget<W, ?>> {
	
	//method
	public final PropertyBinding bindWidgetWithProperty(final W widget, final P property) {
		
		final var propertyBinding = new PropertyBinding(property, widget);
		
		bindWidgetToProperty(widget, property, propertyBinding);
		updateWidgetFromProperty(widget, property);
		
		return propertyBinding;
	}
	
	//method
	public final PropertyBinding createWidgetAndBindItWith(final P property) {
		return bindWidgetWithProperty(createWidget(), property);
	}
	
	//method declaration
	protected abstract void addSelectionOptionsToWidgetForProperty(W widget, P property);
	
	//method declaration
	protected abstract W createWidget();
	
	//method declaration
	protected abstract void setNoteUpdateActionToWidget(W widget, IAction noteUpdateAction);
	
	//method declaration
	protected abstract void updatePropertyFromWidget(P property, W widget);
	
	//method declaration
	protected abstract void updateWidgetFromProperty(W widget, P property);
	
	//method
	private void bindWidgetToProperty(final W widget, final P property, final PropertyBinding propertyBinding) {
		
		addSelectionOptionsToWidgetForProperty(widget, property);
		
		setNoteUpdateActionToProperty(property, () -> updateWidgetFromProperty(widget, property));
		
		setNoteUpdateActionToWidget(
			widget,
			() -> updatePropertyFromWidgetCatchingErrors(property, widget, propertyBinding)
		);
	}
	
	//method
	private void setNoteUpdateActionToProperty(final P property, final IAction noteUpdateAction) {
		//TODO: Add addNodeUpdateAction method to IEntity. 
		// property.getParentEntity().addNoteUpdateAction(noteUpdateAction);
	}
	
	//method
	private void updatePropertyFromWidgetCatchingErrors(
		final P property,
		final W widget,
		final PropertyBinding propertyBinding
	) {
		try {
			updatePropertyFromWidget(property, widget);
			propertyBinding.removeCurrentError();
		} catch (final Throwable error) {
			propertyBinding.setCurrentError(error);
		}
	}
}
