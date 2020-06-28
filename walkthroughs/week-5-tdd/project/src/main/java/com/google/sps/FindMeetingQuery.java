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

  //if the duration of the meeting is over a day, there are no available times
  if(request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
    return new ArrayList<TimeRange>();
  }

  //if the event time is zero or the number of attendees is zero, the whole day is avalible 
  if(reqest.getDuration() == 0 || request.getAttendees.size() == 0) {
    return Arrays.asList(TimeRange.WHOLE_DAY);
  }

  //if there are no events and the request is less than a say, then return the whole day
  if(events.isEmpty() && request.getDuration() <= TimeRange.WHOLE_DAY.duration()) {
    return Arrays.asList(TimeRange.WHOLE_DAY);
  }

  for(int i=0 ; i<events.size() ; i++) {
    ArrayList<String> requiredAttendees = new ArrayList<String>();
    requiredAttendees = request.getAttendees();
    boolean isRequired == false 
    whileLoop: while(isRequired== false) {
      for(int j=0 ; j<requiredAttendees.size() ; j++) {
        if(events[i].attendees.contain(requiredAttendees[j]) {
          isRequired = true;
          addUnavailableTime(event[i].when);
          break whileLoop;
        }
      }
    }
  }

  return(availableTime(unavailableTimes, when))

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
         //if they are equal then keep the one with the longer duration
         elseif(unavailableTimes[i].start()==time.start()) {
           if (unavailableTimes[i].duration < time.duration()) {
             //replace with the larger duration
             unavailableTimes.set(i,time)   
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

  //Itterate through the unavalible times and find the avalible times that work
  public ArrayList<TimeRange> avalibleTimes( ArrayList<TimeRange> unavailable, TimeRange requiredTime) {
    if( unavailable.isEmpty() == true) {
      return new ArrayList<TimeRange>();
    }
    else {
     unavailable.set(0,TimeRange(START_OF_DAY, 0));
     unavailable.add(END_OF_DAY, 0);
    }
    ArrayList<TimeRange> avalible = new ArrayList<TimeRange>();
    for(int i=0 ; i<unavailableTimes.size()-1 ; i++) {
       TimeRange currentTimeRange (unavailable[i].end(), unavailable[i+1].start());
        if(currentTimeRange.duration() >= requiredTime.duration()) {
            available.add(currentTimeRange);
        }
    }
    return available;  
  }

}
