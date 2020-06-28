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

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class FindMeetingQuery {
  private ArrayList<TimeRange> unavailableTimes = new ArrayList<TimeRange>();

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
  
  int requestDuration = (int) request.getDuration();

  //if the duration of the meeting is over a day, there are no available times
  if(request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
    return new ArrayList<TimeRange>();
  }

  //if the event time is zero or the number of attendees is zero, the whole day is avalible 
  if(reqest.getDuration() == 0 || request.getAttendees.size() == 0) {
    return Arrays.asList(TimeRange.WHOLE_DAY);
  }

  //if there are no events and the request is less than a say, then return the whole day
  if(events.isEmpty() && requestDuration <= TimeRange.WHOLE_DAY.duration()) {
    return Arrays.asList(TimeRange.WHOLE_DAY);
  }

  for(int i=0 ; i < events.size() ; i++) {
    ArrayList<String> requiredAttendees = new ArrayList<String>();
    requiredAttendees = request.getAttendees();
    boolean isRequired == false 
    while(isvalid == false) {
      for(int j=0 ; j<requiredAttendees.size() ; j++) {
        if(events[i].attendees.contain(requiredAttendees[j]) {
          isRequired = true;
          addUnavailableTime(event[i].when);
        }
      }
    }
  }

  }

  }
  }
   //Add times to an array of unaavalible times, sorting them while doing so.
   public void addUnavailableTime(TimeRange time) {
     if (unavailableTimes.isEmpty() == true) {
       unavailableTimes.add(time);
       return;
     }
     else {
       int i = 0;
       while( i<unavailableTimes.size()){
         //if the start time is greater that the current element keep going
         if(unavailableTimes[i].start()<time.start()){
           i++;
         }
         //if they are equal then sort them in inserasin order based on duration
         elseif(unavailableTimes[i].start()==time.start()) {
           if (unavailableTimes[i].duration > time.duration()) {
             unavailableTimes(i,time)   
             return; 
           }
           else {
             i++;
           }
         }
         else {
           unavailableTimes.add(i,time);
           return;
         } 
       }
     }
   }

   //YOU STILL NEED A FUNCTION TO GO THROUGH  UNAVALIBLE TIMES AN GET THE AVALIBLE ONES
}
