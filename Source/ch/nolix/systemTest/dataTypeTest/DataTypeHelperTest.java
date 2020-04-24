//package declaration
package ch.nolix.systemTest.dataTypeTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.dataType.BackReferenceType;
import ch.nolix.system.dataType.DataTypeHelper;
import ch.nolix.system.dataType.MultiBackReferenceType;
import ch.nolix.system.dataType.MultiReferenceType;
import ch.nolix.system.dataType.MultiValueType;
import ch.nolix.system.dataType.OptionalBackReferenceType;
import ch.nolix.system.dataType.OptionalReferenceType;
import ch.nolix.system.dataType.OptionalValueType;
import ch.nolix.system.dataType.ReferenceType;
import ch.nolix.system.dataType.ValueType;
import ch.nolix.system.entity.BackReference;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.MultiBackReference;
import ch.nolix.system.entity.MultiReference;
import ch.nolix.system.entity.MultiValueProperty;
import ch.nolix.system.entity.OptionalBackReference;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.OptionalValueProperty;
import ch.nolix.system.entity.Reference;
import ch.nolix.system.entity.ValueProperty;

//class
public final class DataTypeHelperTest extends Test {
		
	//static class
	private static final class Entity1A extends Entity {
		
		//attribute
		public final ValueProperty<String> valueProperty = new ValueProperty<>();
	}
	
	//static class
	private static final class Entity1B extends Entity {
		
		//attribute
		public final OptionalValueProperty<String> optionalValueProperty = new OptionalValueProperty<>();
	}
	
	//static class
	private static final class Entity1C extends Entity {
		
		//attribute
		public final MultiValueProperty<String> multiValueProperty = new MultiValueProperty<>();
	}
	
	//static class
	private static final class Entity2A extends Entity {
		
		//attribute
		public final Reference<Entity2D> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity2B extends Entity {
		
		//attribute
		public final OptionalReference<Entity2D> optionalReference= new OptionalReference<>();
	}
	
	//static class
	private static final class Entity2C extends Entity {
		
		//attribute
		public final MultiReference<Entity2D> multiReference = new MultiReference<>();
	}
	
	//static class
	private static final class Entity2D extends Entity {}
	
	//static class
	private static final class Entity3A extends Entity {
		
		//attribute
		public final BackReference<Entity3D> backReference = new BackReference<>("reference");
	}
	
	//static class
	private static final class Entity3B extends Entity {
		
		//attribute
		public final OptionalBackReference<Entity3E> optionalBackReference =
		new OptionalBackReference<>("optionalReference");
	}
	
	//static class
	private static final class Entity3C extends Entity {
		
		//attribute
		public final MultiBackReference<Entity3F> multiBackReference = new MultiBackReference<>("multiReference");
	}
	
	//static class
	private static final class Entity3D extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final Reference<Entity3A> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity3E extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final OptionalReference<Entity3B> optionalReference = new OptionalReference<>();
	}
	
	//static class
	private static final class Entity3F extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final MultiReference<Entity3C> multiReference = new MultiReference<>();
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForValueProperty() {
		
		//setup
		final var entity = new Entity1A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.valueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(ValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalValueProperty() {
		
		//setup
		final var entity = new Entity1B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiValueProperty() {
		
		//setup
		final var entity = new Entity1C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(MultiValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForReference() {
		
		//setup
		final var entity = new Entity2A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.reference);
		
		//verification
		expect(result.getClass()).isSameAs(ReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalReference() {
		
		//setup
		final var entity = new Entity2B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalReference);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiReference() {
		
		//setup
		final var entity = new Entity2C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiReference);
		
		//verification
		expect(result.getClass()).isSameAs(MultiReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForBackReference() {
		
		//setup
		final var entity = new Entity3A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.backReference);
		
		//verification
		expect(result.getClass()).isSameAs(BackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalBackReference() {
		
		//setup
		final var entity = new Entity3B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.optionalBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(OptionalBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3E.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiBackReference() {
		
		//setup
		final var entity = new Entity3C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = DataTypeHelper.createDatatypeFor(entity.multiBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(MultiBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3F.class);
	}
}
