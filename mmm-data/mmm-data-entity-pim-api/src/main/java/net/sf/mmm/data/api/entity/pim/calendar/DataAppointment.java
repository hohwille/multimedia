/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api.entity.pim.calendar;

import java.util.Date;

import net.sf.mmm.data.api.reflection.DataClassAnnotation;
import net.sf.mmm.data.api.reflection.DataClassIds;

/**
 * This is the view interface for a {@link net.sf.mmm.data.api.entity.DataEntity} that represents an
 * appointment.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@DataClassAnnotation(id = DataAppointment.CLASS_ID, title = DataAppointment.CLASS_TITLE)
public interface DataAppointment extends DataEvent {

  /**
   * The {@link net.sf.mmm.data.api.datatype.DataId#getClassId() class-ID} of the
   * {@link net.sf.mmm.data.api.reflection.DataClass} reflecting this type.
   */
  long CLASS_ID = DataClassIds.ID_APPOINTMENT;

  /**
   * The {@link net.sf.mmm.data.api.DataObject#getTitle() title} of the
   * {@link net.sf.mmm.data.api.reflection.DataClass} reflecting this type.
   */
  String CLASS_TITLE = "DataAppointment";

  /**
   * This method gets the location where this appointment takes place. This can be a room, a telephone number,
   * an entire address or any other information that identifies where to meet for all participants.
   * 
   * @return the location.
   */
  String getLocation();

  /**
   * This method gets the end date when this event will initially take place.
   * 
   * @see #getInitialStartDate()
   * 
   * @return the initial end date.
   */
  Date getInitialEndDate();

  /**
   * This method sets the {@link #getLocation() location}.
   * 
   * @param location is the location to set.
   */
  void setLocation(String location);

  /**
   * This method sets the {@link #getInitialEndDate() initial end date}.
   * 
   * @param initialEndDate is the initial end date to set.
   */
  void setInitialEndDate(Date initialEndDate);

}
