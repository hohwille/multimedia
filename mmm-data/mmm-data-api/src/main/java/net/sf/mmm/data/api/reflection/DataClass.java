/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api.reflection;

import java.util.Collection;
import java.util.List;

import net.sf.mmm.data.api.DataObject;
import net.sf.mmm.data.api.DataSelectionTree;
import net.sf.mmm.util.lang.api.BooleanEnum;

/**
 * This is the interface for the type of an entity. It reflects the structure of the
 * {@link net.sf.mmm.data.api.DataObject content-object} types in an object-oriented way. <br>
 * A content-class is the analogy to a {@link java.lang.Class} that reflects a {@link java.lang.Object}. <br>
 * A content-class may be used to render a generic UI editor, synchronize the schema of the persistence store
 * (e.g. a DB), etc. <br>
 * 
 * @see net.sf.mmm.data.api.DataObject
 * @see DataReflectionService#getDataClass(net.sf.mmm.data.api.DataObject)
 * 
 * @param <CLASS> is the generic type of the reflected {@link #getJavaClass() class}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@DataClassAnnotation(id = DataClass.CLASS_ID, title = DataClass.CLASS_TITLE, isFinal = BooleanEnum.TRUE)
public interface DataClass<CLASS extends DataObject> extends DataReflectionObject<CLASS>,
    DataSelectionTree<DataClass<? extends DataObject>> {

  /**
   * The {@link net.sf.mmm.data.api.datatype.DataId#getClassId() class-ID} of the {@link DataClass} reflecting
   * this type.
   */
  long CLASS_ID = DataClassIds.ID_CLASS;

  /**
   * The {@link net.sf.mmm.data.api.DataObject#getTitle() name} of the {@link DataClass} reflecting this type.
   */
  String CLASS_TITLE = "DataClass";

  /**
   * The first {@link net.sf.mmm.data.api.datatype.DataId#getClassId() class-ID} that can be used for custom
   * classes. All {@link net.sf.mmm.data.api.datatype.DataId#getClassId() class-ID} lower than this are
   * reserved for {@link DataClassModifiers#isSystem() system} classes.
   */
  int CLASS_ID_MINIMUM_CUSTOM = 4096;

  /** the xml tag for a {@link DataClass}. */
  String XML_TAG_CLASS = "Class";

  /**
   * The name of the {@link net.sf.mmm.data.api.reflection.DataField field} {@link #getSubClasses()
   * subClasses} for generic access.
   */
  String FIELD_NAME_SUB_CLASSES = "subClasses";

  /**
   * The name of the {@link net.sf.mmm.data.api.reflection.DataField field} {@link #getFields() fields} for
   * generic access.
   */
  String FIELD_NAME_FIELDS = "fields";

  /**
   * The name of the {@link net.sf.mmm.data.api.reflection.DataField field} {@link #getDeclaredFields()
   * declaredFields} for generic access.
   */
  String FIELD_NAME_DECLARED_FIELDS = "declaredFields";

  /**
   * The name of the {@link net.sf.mmm.data.api.reflection.DataField field} {@link #getJavaClass() javaClass}
   * for generic access.
   */
  String FIELD_NAME_JAVA_CLASS = "javaClass";

  /**
   * the attribute for the {@link #getTitle() name}.
   */
  String XML_TAG_CONTENT_MODEL = "content-model";

  /**
   * This method gets the super-class of this class. Like in java this class inherits from its super-classes.<br>
   * This method exists only for expressiveness - it does the same as {@link #getParent()}.
   * 
   * @return the super-class that is extended by this class or <code>null</code> if this is the root-class (
   *         {@link DataClass} reflecting {@link net.sf.mmm.data.api.DataObject}).
   */
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_SUPERCLASS, isReadOnly = BooleanEnum.TRUE)
  DataClass<? extends DataObject> getSuperClass();

  /**
   * This method gets an iterator of all fields declared by this class. This does NOT include fields inherited
   * from the {@link #getSuperClass() super-class} except they are overridden by this class. An inherited
   * field can be overridden (if supported by the {@link DataReflectionService content-model}) in order to
   * declare it more specific. Then the type of the field is a subtype of the field that is overridden or the
   * validator is more restrictive.<br>
   * 
   * @return a (read-only) collection of all declared fields.
   */
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_DECLAREDFIELDS, isReadOnly = BooleanEnum.TRUE)
  Collection<? extends DataField<CLASS, ?>> getDeclaredFields();

  /**
   * This method gets the declared field with the given {@link DataField#getTitle() name}. Declared means that
   * the field is {@link DataField#getInitiallyDefiningClass() initially defined} or overridden in this class.<br>
   * An inherited field can be overridden (if supported by the {@link DataReflectionService content-model}) in
   * order to declare it more specific (typically the {@link DataField#getFieldType() field-type} is
   * specialized). Such field can be identified via {@link DataField#getInitiallyDefiningClass()}.
   * 
   * @see #getField(String)
   * 
   * @param title is the name of the requested field of this class.
   * @return the field with the given name or <code>null</code> if no such field is declared by this class.
   */
  DataField<CLASS, ?> getDeclaredField(String title);

  /**
   * This method gets all fields defined in this class or inherited by the super-class(es). An inherited field
   * can be identified via {@link DataField#getDeclaringClass()}.<br>
   * <b>ATTENTION:</b><br>
   * The {@link Collection#size()} method of the returned instance may be very expensive. Please avoid
   * unnecessary or repetitive calls.
   * 
   * @return a (read-only) collection of fields of this class.
   */
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_FIELDS, isReadOnly = BooleanEnum.TRUE, isTransient = BooleanEnum.TRUE)
  Collection<? extends DataField<? extends DataObject, ?>> getFields();

  /**
   * This method gets the field with the given {@link DataField#getTitle() title}. A field is either
   * {@link #getDeclaredFields() declared} in this class or inherited from a {@link #getSuperClass()
   * super-class}.
   * 
   * @param title is the name of the requested field of this class.
   * @return the field with the given name or <code>null</code> if no such field exists for this class.
   */
  DataField<? extends DataObject, ?> getField(String title);

  /**
   * This method gets the list of all sub-classes.
   * 
   * @return an un-modifiable list of all sub-class.
   */
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_SUBCLASSES, isReadOnly = BooleanEnum.TRUE, inverseRelationFieldId = DataFieldIds.ID_CLASS_SUPERCLASS)
  List<? extends DataClass<? extends CLASS>> getSubClasses();

  /**
   * {@inheritDoc}
   */
  @Override
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_MODIFIERS, isReadOnly = BooleanEnum.TRUE)
  DataClassModifiers getModifiers();

  /**
   * This method determines the {@link DataClassCachingStrategy} for this {@link DataClass}.
   * 
   * @return the {@link DataClassCachingStrategy}.
   */
  DataClassCachingStrategy getCachingStrategy();

  /**
   * This method determines is this class is a super class of the given class. <br>
   * 
   * @param contentClass is the class to compare with.
   * @return <code>true</code> if this class is a super-class of the given class.
   */
  boolean isSuperClassOf(DataClass<? extends DataObject> contentClass);

  /**
   * This is the opposite of the method {@link DataClass#isSuperClassOf(DataClass)}. This means that
   * <code>class1.isSubClassOf(class2)</code> is equal to <code>class2.isSuperClassOf(class1)</code>.
   * 
   * @param contentClass is the class to compare with.
   * @return <code>true</code> if this class is a sub-class of the given class.
   */
  boolean isSubClassOf(DataClass<? extends DataObject> contentClass);

  /**
   * This method gets the {@link DataClassGroupVersion} of this class.
   * 
   * @return the {@link DataClassGroupVersion}.
   */
  @DataFieldAnnotation(id = DataFieldIds.ID_CLASS_GROUPVERSION, isReadOnly = BooleanEnum.TRUE)
  DataClassGroupVersion getGroupVersion();

  // /**
  // * This method determines if the {@link ContentObject entity} represented by
  // * this {@link ContentClass} is a {@link ContentObject#isFolder() folder}.
  // *
  // * @see ContentObject#isFolder()
  // *
  // * @return <code>true</code> if the represented entity is a folder,
  // * <code>false</code> if it is a leaf.
  // */
  // boolean isFolderClass();

  // /**
  // * This method determines if the {@link net.sf.mmm.data.api.DataObject}s of
  // * this {@link DataClass} are revision-controlled. Fur such entity
  // historical
  // * {@link net.sf.mmm.data.api.DataObject#getRevision() revisions} can be
  // * stored in the history. <br>
  // * If an entity that is NOT under revision-control is modified, every except
  // * the current state is lost.
  // *
  // * @return <code>true</code> if this type is revisioned, <code>false</code>
  // * otherwise.
  // */
  // boolean isRevisionControlled();

}
