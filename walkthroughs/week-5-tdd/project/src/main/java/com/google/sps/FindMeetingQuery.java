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
 // public ArrayList < TimeRange > unavailableTimes = new ArrayList < TimeRange > ();

  public Collection < TimeRange > query(Collection < Event > events, MeetingRequest request) {
    //if the duration of the meeting is over a day, there are no available times
    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return Collections.emptyList();
    }
    //if there are no events and the request is less than a day, then return the whole day
    if (events.isEmpty() && request.getDuration() <= TimeRange.WHOLE_DAY.duration()) {
      return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    // Arraylist to hold event times with all mandatory attendees    
    List<TimeRange> mandatoryEvents = new ArrayList();

    // Arraylist to hold event times with all attendees, mandatory and optional
    List<TimeRange> withOptionalEvents = new ArrayList();
    for (Event event: events) {
        // Check if attendees from event are requested for meeting
        for (String attendee : event.getAttendees()) {            
            // Check that attendee is a mandatoryattendee
            if ((request.getAttendees().contains(attendee)) ) {
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






    //itterate through events to find all that have a conflic with a required attendee.
    //Add those events to the unavailableTimes array.
    ArrayList < String > requiredAttendees = new ArrayList < > (request.getAttendees());
    for (Iterator < Event > iterator = events.iterator(); iterator.hasNext();) {
      for (int j = 0; j < requiredAttendees.size(); j++) {
        //If the current event has a required attendee then it is not available, add it to the unavailableTimes Array.
        if (iterator.next().getAttendees().contains(requiredAttendees.get(j))) {
          addUnavailableTime(iterator.next().getWhen());
          break;
        }
      }
    }
    return (findAvalibleTimes(request.getDuration()));
  }

  //Add times to an array of unaavalible times, sorting them while doing so.
  public void addUnavailableTime(TimeRange time) {
    if (unavailableTimes.isEmpty() == true) {
      unavailableTimes.add(time);
      return;
    }
    int i = 0;
    while (i < unavailableTimes.size()) {
      //if the start time is greater that the current element keep going
      if (unavailableTimes.get(i).start() < time.start()) {
        i++;
      }
      //if they are equal then keep the one with the longer duration
      else if (unavailableTimes.get(i).start() == time.start() && unavailableTimes.get(i).duration() < time.duration()) {
        unavailableTimes.set(i, time);
        return;
      } else if (unavailableTimes.get(i).start() == time.start() && unavailableTimes.get(i).duration() > time.duration()) {
        i++;
      } else {
        unavailableTimes.add(i, time);
        return;
      }
    }
  }

  //Itterate through the unavalible times and find the avalible times that work
  public Collection < TimeRange > findAvalibleTimes(long requiredTime) {
    if (unavailableTimes.isEmpty() == true) {
      return (unavailableTimes);
    } else {
      unavailableTimes.set(0, (TimeRange.fromStartDuration(TimeRange.START_OF_DAY, 0)));
      unavailableTimes.add(TimeRange.fromStartDuration(TimeRange.END_OF_DAY, 0));
    }
    List < TimeRange > available = new ArrayList < TimeRange > ();
    for (int i = 0; i < unavailableTimes.size() - 1; i++) {
      int curDuration = unavailableTimes.get(i + 1).start() - unavailableTimes.get(i).end();
      if (curDuration >= requiredTime) {
        available.add(TimeRange.fromStartDuration(unavailableTimes.get(i).end(), curDuration));
      }
    }
    return (available);
  }
}