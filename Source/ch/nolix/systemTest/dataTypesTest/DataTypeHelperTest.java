//package declaration
package ch.nolix.systemTest.dataTypesTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.dataTypes.BackReferenceType;
import ch.nolix.system.dataTypes.DataTypeHelper;
import ch.nolix.system.dataTypes.MultiBackReferenceType;
import ch.nolix.system.dataTypes.MultiReferenceType;
import ch.nolix.system.dataTypes.MultiValueType;
import ch.nolix.system.dataTypes.OptionalBackReferenceType;
import ch.nolix.system.dataTypes.OptionalReferenceType;
import ch.nolix.system.dataTypes.OptionalValueType;
import ch.nolix.system.dataTypes.ReferenceType;
import ch.nolix.system.dataTypes.ValueType;
import ch.nolix.system.entity.BackReference;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.MultiBackReference;
import ch.nolix.system.entity.MultiReference;
import ch.nolix.system.entity.MultiValueProperty;
import ch.nolix.system.entity.OptionalBackReference;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.OptionalValueProperty;
import ch.nolix.system.entity.Reference;
import ch.nolix.system.entity.ValueProperty;

//test class
public final class DataTypeHelperTest extends Test {
		
	//static class
	private static final class Entity1A extends Entity {
		public final ValueProperty<String> valueProperty = new ValueProperty<>();
	}
	
	//static class
	private static final class Entity1B extends Entity {
		public final OptionalValueProperty<String> optionalValueProperty = new OptionalValueProperty<>();
	}
	
	//static class
	private static final class Entity1C extends Entity {
		public final MultiValueProperty<String> multiValueProperty = new MultiValueProperty<>();
	}
	
	//static class
	private static final class Entity2A extends Entity {
		public final Reference<Entity2D> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity2B extends Entity {
		public final OptionalReference<Entity2D> optionalReference= new OptionalReference<>();
	}

	
	//static class
	private static final class Entity2C extends Entity {
		public final MultiReference<Entity2D> multiReference = new MultiReference<>();
	}
	
	//static class
	private static final class Entity2D extends Entity {}
	
	//static class
	private static final class Entity3A extends Entity {
		public final BackReference<Entity3D> backReference = new BackReference<>("reference");
	}
	
	//static class
	private static final class Entity3B extends Entity {
		public final OptionalBackReference<Entity3E> optionalBackReference =
		new OptionalBackReference<>("optionalReference");
	}
	
	//static class
	private static final class Entity3C extends Entity {
		public final MultiBackReference<Entity3F> multiBackReference = new MultiBackReference<>("multiReference");
	}
	
	//static class
	private static final class Entity3D extends Entity {
		
		@SuppressWarnings("unused")
		public final Reference<Entity3A> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity3E extends Entity {
		
		@SuppressWarnings("unused")
		public final OptionalReference<Entity3B> optionalReference = new OptionalReference<>();
	}
	
	//static class
	private static final class Entity3F extends Entity {
		
		@SuppressWarnings("unused")
		public final MultiReference<Entity3C> multiReference = new MultiReference<>();
	}
	
	//test case
	public void testCase_createDatatypeForValueProperty() {
		
		//setup
		final var entity = new Entity1A();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.valueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(ValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//test case
	public void testCase_createDatatypeForOptionalValueProperty() {
		
		//setup
		final var entity = new Entity1B();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//test case
	public void testCase_createDatatypeForMultiValueProperty() {
		
		//setup
		final var entity = new Entity1C();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(MultiValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//test case
	public void testCase_createDatatypeForReference() {
		
		//setup
		final var entity = new Entity2A();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.reference);
		
		//verification
		expect(result.getClass()).isSameAs(ReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//test case
	public void testCase_createDatatypeForOptionalReference() {
		
		//setup
		final var entity = new Entity2B();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalReference);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//test case
	public void testCase_createDatatypeForMultiReference() {
		
		//setup
		final var entity = new Entity2C();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiReference);
		
		//verification
		expect(result.getClass()).isSameAs(MultiReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//test case
	public void testCase_createDatatypeForBackReference() {
		
		//setup
		final var entity = new Entity3A();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.backReference);
		
		//verification
		expect(result.getClass()).isSameAs(BackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3D.class);
	}
	
	//test case
	public void testCase_createDatatypeForOptionalBackReference() {
		
		//setup
		final var entity = new Entity3B();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3E.class);
	}
	
	//test case
	public void testCase_createDatatypeForMultiBackReference() {
		
		//setup
		final var entity = new Entity3C();
		entity.extractProperties();
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(MultiBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3F.class);
	}
}
