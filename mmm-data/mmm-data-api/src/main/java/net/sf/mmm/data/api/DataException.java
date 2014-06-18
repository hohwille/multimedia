/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.mmm.util.exception.api.NlsRuntimeException;
import net.sf.mmm.util.nls.api.NlsAccess;

/**
 * This is the abstract base exception of all failures related to {@link DataObject}.
 *
 * @see net.sf.mmm.data.api.reflection.DataReflectionException
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class DataException extends NlsRuntimeException {

  /** UID for serialization. */
  private static final long serialVersionUID = -3877257188725888994L;

  /**
   * The constructor.
   *
   * @param nested is the {@link #getCause() cause} of this exception.
   * @param internationalizedMessage is a short description of the problem. It is used for
   *        {@link net.sf.mmm.util.nls.api.NlsMessage#getInternationalizedMessage() internationalization} and
   *        should be in English language.
   * @param arguments are the {@link net.sf.mmm.util.nls.api.NlsMessage#getArgument(String) arguments}.
   */
  public DataException(Throwable nested, String internationalizedMessage, Map<String, Object> arguments) {

    super(nested, NlsAccess.getFactory().create(internationalizedMessage, arguments));
  }

  /**
   * The constructor.
   *
   * @param internationalizedMessage is a short description of the problem. It is used for
   *        {@link net.sf.mmm.util.nls.api.NlsMessage#getInternationalizedMessage() internationalization} and
   *        should be in English language.
   * @param arguments are the {@link net.sf.mmm.util.nls.api.NlsMessage#getArgument(String) arguments}.
   */
  public DataException(String internationalizedMessage, Map<String, Object> arguments) {

    this(null, internationalizedMessage, arguments);
  }

  /**
   * The constructor.
   *
   * @param internationalizedMessage is a short description of the problem. It is used for
   *        {@link net.sf.mmm.util.nls.api.NlsMessage#getInternationalizedMessage() internationalization} and
   *        should be in English language.
   */
  public DataException(String internationalizedMessage) {

    super(internationalizedMessage);
  }

  /**
   * The constructor.
   *
   * @param nested is the {@link #getCause() cause} of this exception.
   * @param internationalizedMessage is a short description of the problem. It is used for
   *        {@link net.sf.mmm.util.nls.api.NlsMessage#getInternationalizedMessage() internationalization} and
   *        should be in English language.
   */
  public DataException(Throwable nested, String internationalizedMessage) {

    super(nested, internationalizedMessage);
  }

  protected static Map<String, Object> toMap(String key, Object value) {

    Map<String, Object> result = new HashMap<String, Object>();
    result.put(key, value);
    return result;
  }

}
