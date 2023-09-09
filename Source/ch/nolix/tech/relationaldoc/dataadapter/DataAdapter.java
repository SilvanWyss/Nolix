//package declaration
package ch.nolix.tech.relationaldoc.dataadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableObjectEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.AbstractableObject;
import ch.nolix.tech.relationaldoc.datamodel.SchemaCatalogue;
import ch.nolix.techapi.relationaldocapi.dataaapterapi.IDataAdapter;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class DataAdapter implements IDataAdapter {
	
	//constant
	private static final AbstractableObjectEvaluator ABSTRACTABLE_OBJECT_EVALUATOR = new AbstractableObjectEvaluator();
	
	//static method
	public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
		
		final var nodeDataAdapter =
		NodeDataAdapter
		.forNodeDatabase(nodeDatabase)
		.withName(LowerCaseCatalogue.DATABASE)
		.andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);
		
		return new DataAdapter(nodeDataAdapter);
	}
	
	//attribute
	private final ch.nolix.system.objectdatabase.database.DataAdapter internalDataAdapter;
	
	//constructor
	private DataAdapter(final ch.nolix.system.objectdatabase.database.DataAdapter internalDataAdapter) {
		
		GlobalValidator
		.assertThat(internalDataAdapter)
		.thatIsNamed("internal data adapter")
		.isNotNull();
		
		this.internalDataAdapter = internalDataAdapter;
	}
	
	//method
	@Override
	public void close() throws Exception {
		internalDataAdapter.close();
	}
	
	//method
	@Override
	public IAbstractableObject createObject() {
		
		final var object = new AbstractableObject();
		
		internalDataAdapter.insert(object);
		
		return object;
	}
	
	//method
	@Override
	public void deleteObject(IAbstractableObject object) {
		((AbstractableObject)object).delete();
	}
	
	//method
	@Override
	public IDataAdapter getEmptyCopy() {
		return new DataAdapter(internalDataAdapter.getEmptyCopy());
	}
	
	//method
	@Override
	public IContainer<? extends IAbstractableObject> getStoredTopLevelObjects() {
		
		final var table = internalDataAdapter.getStoredTableByEntityType(AbstractableObject.class);
		
		final var objects = table.getStoredEntities();
		
		return objects.getStoredOther(ABSTRACTABLE_OBJECT_EVALUATOR::hasBaseTypes);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDataAdapter.hasChanges();
	}
	
	//method
	@Override
	public void saveChanges() {
		internalDataAdapter.saveChanges();
	}
}
