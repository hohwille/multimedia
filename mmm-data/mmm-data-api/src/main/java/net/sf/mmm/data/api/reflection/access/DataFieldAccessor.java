/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api.reflection.access;

import net.sf.mmm.data.api.DataObject;
import net.sf.mmm.security.api.SecurityException;
import net.sf.mmm.util.nls.api.NlsClassCastException;
import net.sf.mmm.util.nls.api.ReadOnlyException;

/**
 * This is the interface for an accessor used to {@link #getFieldValue(DataObject) read} or
 * {@link #setFieldValue(DataObject, Object) write} a {@link net.sf.mmm.data.api.reflection.DataField field}
 * of a {@link DataObject}. According to the {@link net.sf.mmm.data.api.reflection.DataField#getModifiers()
 * modifiers} the field may be {@link net.sf.mmm.data.api.reflection.DataFieldModifiers#isReadOnly()
 * read-only}. Then the write access will NOT be allowed and therefore fail.
 * 
 * @param <CLASS> is the generic type for the bound of
 *        {@link net.sf.mmm.data.api.reflection.DataField#getJavaClass()}.
 * @param <FIELD> is the generic type of the value reflected by this field.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface DataFieldAccessor<CLASS extends DataObject, FIELD> {

  /**
   * This method gets the value of the field from the given <code>object</code>.
   * 
   * @param object is where to read the field value from.
   * @return the value of the field. May be <code>null</code>.
   * @throws NlsClassCastException if the given <code>value</code> is not compatible with the
   *         {@link net.sf.mmm.data.api.reflection.DataField#getFieldType() type} of the field.
   * @throws SecurityException is the access failed for security reasons.
   */
  FIELD getFieldValue(CLASS object) throws NlsClassCastException, SecurityException;

  /**
   * This method sets the field in the <code>object</code> to the given <code>value</code>. The field must NOT
   * be {@link net.sf.mmm.data.api.reflection.DataFieldModifiers#isReadOnly() read-only}.
   * 
   * @param object is where to write the fields value to. Will be ignored if the field is
   *        {@link net.sf.mmm.data.api.reflection.DataFieldModifiers#isStatic() static}.
   * @param value is the value of the field. May be <code>null</code>.
   * @throws ReadOnlyException if the field is
   *         {@link net.sf.mmm.data.api.reflection.DataFieldModifiers#isReadOnly() read-only}.
   * @throws NlsClassCastException if the given <code>value</code> is not compatible with the
   *         {@link net.sf.mmm.data.api.reflection.DataField#getFieldType() type} of the field.
   * @throws SecurityException is the access failed for security reasons.
   */
  void setFieldValue(CLASS object, FIELD value) throws ReadOnlyException, NlsClassCastException, SecurityException;

}
