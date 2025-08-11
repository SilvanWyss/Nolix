package ch.nolix.coretest.reflection.reflectionexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.container.matrix.IMatrix;

final class FieldExaminerTest extends StandardTest {

  private static final class Lecture {

    @SuppressWarnings("unused")
    public LinkedList<String> students;
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_1A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, LinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_1B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, AbstractContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ILinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_2B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, Matrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_3B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, ArrayList.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IMatrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_canStoreValueOfTypeOrSuperType_4B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.canStoreValueOfTypeOrSuperType(studentsField, IArrayList.class);

    //verification
    expect(result).isFalse();
  }
}
