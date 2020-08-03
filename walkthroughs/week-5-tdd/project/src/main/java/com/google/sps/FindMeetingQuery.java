// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Iterator;

public final class FindMeetingQuery {

   // create a set of events to avoid duplicates
  private Collection<Event> events = new HashSet<>();

  // the meeting request
  private MeetingRequest request; 

  // public ArrayList <TimeRange> unavailableTimes = new ArrayList < imeRange> ();
  public Collection <TimeRange> query(Collection <Event> events, MeetingRequest request) {

    this.events = events;
    this.request = request; 

    //if the duration of the meeting is over a day, there are no available times
    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return Collections.emptyList();
    }
    //if there are no events and the request is less than a day, then return the whole day
    if (events.isEmpty() && request.getDuration() <= TimeRange.WHOLE_DAY.duration()) {
      return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    // Arraylist to hold event times with all mandatory attendees    
    List <TimeRange> mandatoryEvents = new ArrayList();

    // Arraylist to hold event times with all attendees, mandatory and optional
    List <TimeRange> withOptionalEvents = new ArrayList();
    for (Event event: events) {
      // Check if attendees from event are requested for meeting
      for (String attendee: event.getAttendees()) {
        // Check that attendee is a mandatoryattendee
        if ((request.getAttendees().contains(attendee))) {
          mandatoryEvents.add(event.getWhen());
          withOptionalEvents.add(event.getWhen());
        }
        // Check if attendee is an optional attendee
        else if ((request.getOptionalAttendees().contains(attendee))) {
          withOptionalEvents.add(event.getWhen());
        }
      }
    }

    // Check for valid events during the day
    if (mandatoryEvents.isEmpty() && withOptionalEvents.isEmpty()) {
      return Arrays.asList((TimeRange.WHOLE_DAY));
    }

    // list of options for all mandatory and optional attendees
    List <TimeRange> optionsForAll = getOptions(withOptionalEvents);

    if (optionsForAll.isEmpty()) {
      // Get list of options for only mandatory attendees
      List <TimeRange> optionsForMandatory = getOptions(mandatoryEvents);
      return optionsForMandatory;
    }
    return optionsForAll;
  }

  /** Creates list of options for meeting time*/
  public List <TimeRange> getOptions(List <TimeRange> collection) {

    // Create empty Arraylist of options
    List <TimeRange> options = new ArrayList();
    // Sort list of time ranges in ascending order
    Collections.sort(collection, TimeRange.ORDER_BY_START);
    int start = TimeRange.START_OF_DAY;
    int lastEventEnd = 0;

    for (TimeRange event: collection) {
      // Get event start and end time
      int eventStart = event.start();
      int eventEnd = event.end();

      // Check for overlap
      if (start < eventStart) {
        // Check if there is enough room for a meeting
        if ((eventStart - start) >= request.getDuration()) {
          options.add(TimeRange.fromStartEnd(start, eventStart, false));
        }
      }
      start = Math.max(start, eventEnd);
      lastEventEnd = start;
    }

    if (! (options.isEmpty())) {
      // Check if last event ends when day ends
      if (lastEventEnd < TimeRange.END_OF_DAY) {
        // Check if there is enough room for a meeting
        if ((TimeRange.END_OF_DAY + 1 - lastEventEnd) >= request.getDuration()) {
          options.add(TimeRange.fromStartEnd(lastEventEnd, TimeRange.END_OF_DAY, true));
        }
      }
    }
    return options;
  }
}





  