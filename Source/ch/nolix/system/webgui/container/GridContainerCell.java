//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.Value;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class GridContainerCell extends MutableElement implements Clearable {
	
	//constant
	private static final String ROW_INDEX_HEADER = PascalCaseCatalogue.ROW_INDEX;
	
	//constant
	private static final String COLUMN_INDEX_HEADER = PascalCaseCatalogue.COLUMN_INDEX;
	
	//constant
	private static final String CONTROL_HEADER = "Control";
	
	//static method
	public static GridContainerCell fromSpecification(final INode<?> specification) {
		
		final var cell = new GridContainerCell();
		cell.resetFromSpecification(specification);
		
		return cell;
	}
	
	//static method
	public static GridContainerCell withRowIndexAndColumnIndex(final int rowIndex, final int columnIndex) {
		
		final var cell = new GridContainerCell();
		cell.setRowIndex(rowIndex);
		cell.setColumnIndex(columnIndex);
		
		return cell;
	}
	
	//attribute
	private final Value<Integer> rowIndex = Value.forInt(ROW_INDEX_HEADER, this::setRowIndex);
	
	//attribute
	private final Value<Integer> columnIndex = Value.forInt(COLUMN_INDEX_HEADER, this::setColumnIndex);
	
	//attribute
	private final MutableOptionalValue<IControl<?, ?>> control =
	new MutableOptionalValue<>(
		CONTROL_HEADER,
		this::setControl,
		GlobalControlFactory::createControlFromSpecification,
		IControl::getSpecification
	);
	
	//constructor
	private GridContainerCell() {}
	
	//method
	@Override
	public void clear() {
		control.clear();
	}
	
	//method
	public int getColumnIndex() {
		return columnIndex.getValue();
	}

	//method
	public IControl<?, ?> getRefControl() {
		return control.getValue();
	}
	
	//method
	public int getRowIndex() {
		return rowIndex.getValue();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return !control.hasValue();
	}
	
	//method
	@Override
	public void reset() {
		clear();
	}
	
	//method
	public void setControl(final IControl<?, ?> control) {
		this.control.setValue(control);
	}
	
	//method
	private void setColumnIndex(final int columnIndex) {
	
		GlobalValidator.assertThat(columnIndex).thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX).isPositive();
		
		this.columnIndex.setValue(columnIndex);
	}
	
	//method
	private void setRowIndex(final int rowIndex) {
		
		GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isPositive();
		
		this.rowIndex.setValue(rowIndex);
	}
}
