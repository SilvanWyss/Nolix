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
  void testCase_hasTypeOrSuperType_1A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, LinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasTypeOrSuperType_1B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, AbstractContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasTypeOrSuperType_2A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, ILinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasTypeOrSuperType_2B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, IContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasTypeOrSuperType_3A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, Matrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasTypeOrSuperType_3B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, ArrayList.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasTypeOrSuperType_4A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, IMatrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasTypeOrSuperType_4B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldExaminer();

    //execution
    final var result = testUnit.hasTypeOrSuperType(studentsField, IArrayList.class);

    //verification
    expect(result).isFalse();
  }
}
