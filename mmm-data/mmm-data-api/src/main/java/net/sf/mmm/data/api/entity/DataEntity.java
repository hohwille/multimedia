/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api.entity;

import net.sf.mmm.data.api.DataObject;
import net.sf.mmm.data.api.reflection.DataClassAnnotation;
import net.sf.mmm.data.api.reflection.DataClassIds;

/**
 * This is the interface for a {@link net.sf.mmm.data.api.DataObject} that is a regular persistent entity.
 * This is the top-most {@link net.sf.mmm.data.api.reflection.DataClass type} that can be
 * {@link net.sf.mmm.data.api.reflection.DataClassModifiers#isExtendable() extended} by end-users.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@DataClassAnnotation(id = DataEntity.CLASS_ID, title = DataEntity.CLASS_TITLE)
public interface DataEntity extends DataObject {

  /**
   * The {@link net.sf.mmm.data.api.datatype.DataId#getClassId() class-ID} of the
   * {@link net.sf.mmm.data.api.reflection.DataClass} reflecting this type.
   */
  long CLASS_ID = DataClassIds.ID_ENTITY;

  /**
   * The {@link DataObject#getTitle() title} of the {@link net.sf.mmm.data.api.reflection.DataClass}
   * reflecting this type.
   */
  String CLASS_TITLE = "DataEntity";

  /**
   * This method gets the proxy-source of this object. If the {@link #getProxyTarget() proxy-target} is NOT
   * <code>null</code>, this object is a proxy on another instance of the same
   * {@link net.sf.mmm.data.api.reflection.DataClass type}. This method returns the unwrapped instance where
   * the proxy feature is switched off.<br/>
   * This is e.g. useful to implement an editor where the end-user can see which
   * {@link net.sf.mmm.data.api.reflection.DataField fields} are actually set in the proxy object itself.
   * 
   * @return the proxy-source of this entity or the instance itself ( <code>this</code>) if this is already
   *         the unwrapped instance.
   */
  DataEntity getProxySource();

  /**
   * This method gets the proxy-target of this object. If the proxy-target is NOT <code>null</code>, this
   * object is a proxy on another instance of the same {@link net.sf.mmm.data.api.reflection.DataClass type}.
   * Then all unset {@link net.sf.mmm.data.api.reflection.DataField fields} are "inherited" from the
   * {@link #getProxyTarget() proxy-target}. This rule does NOT apply for {@link #getId() ID},
   * {@link #getModificationCounter() modification counter}, {@link #getRevision() revision},
   * {@link #getProxySource() proxy source}, and {@link #getProxyTarget() proxy target}. Further, only getters
   * are delegated to the {@link #getProxyTarget() proxy target}, whereas setters act on the
   * {@link #getProxySource() proxy source} . A proxy that has no field set acts like a link in a Unix
   * filesystem.<br>
   * <b>INFORMATION:</b><br>
   * The returned object needs to have the same {@link net.sf.mmm.data.api.reflection.DataClass type} as this
   * instance.
   * 
   * @return the proxy-target or <code>null</code> if this is a regular content-object.
   */
  DataEntity getProxyTarget();

  /**
   * This method sets the {@link #getProxyTarget() proxy target}.
   * 
   * @param proxyTarget is the {@link DataEntity} this entity will delegate to.
   */
  void setProxyTarget(DataEntity proxyTarget);

}
