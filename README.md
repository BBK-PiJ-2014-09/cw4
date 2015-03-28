# cw4
Contact Manager - PiJ cw4

JUNIT tests:
Contact/ContactManagerImpl
1 addNewContact: @throws NullPointerException if the name is null 
2 addNewContact: @throws NullPointerException if the notes are null 

ContactImpl 
3 Contact getId() Returns the ID of the contact.
4 Contact getName() Returns the name of the contact.
5 Returns our notes about the contact, if any.
6 If we have not written anything about the contact, the empty string is returned.

Meeting/MeetingImpl
7 getId() Returns the id of the meeting.
8 getDate() Return the date of the meeting.
9 Return the details of people that attended the meeting.
10 The list contains a minimum of one contact (if there were just two people: the user and the contact) and may contain an arbitraty number of them.

Past Meeting/ContactManagerImpl
11 @throws IllegalArgumentException if the list of contacts is empty
12 @throws IllegalArgumentException if any of the contacts does not exist 
13 @throws NullPointerException if any of the arguments is null 

PastMeetingImpl
14 Returns the notes from the meeting.
15 If there are no notes, the empty string is returned.

ContactManagerImpl
16 @return the ID for the future meeting
17 @throws IllegalArgumentException if the future meeting is set for a time in the past
18 @throws IllegalArgumentException if if any contact is unknown / non-existent 

PastMeeting getPastMeeting(int id);
19 @return the meeting with the requested ID
20 @return null if it there is no past meeting with the requested ID
21 @throws IllegalArgumentException if there is a meeting with that ID happening in the future 
	
FutureMeeting getFutureMeeting(int id);
22 Returns the FUTURE meeting with the requested ID, or null if there is none.
23 Returns null if there is no future meeting with the requested ID.
24 @throws IllegalArgumentException if there is a meeting with that ID happening in the past 

Meeting getMeeting(int id);
25 Returns the meeting with the requested ID.
26 Returns null if there is no meeting with the requested ID.

List<Meeting> getFutureMeetingList(Contact contact);
27 @return the list of future meeting(s) scheduled with this contact (maybe empty).
28 If there are no meetings, the returned list will be empty.
29 The list will be chronologically sorted 
30 The list will not contain any duplicates.
31 @throws IllegalArgumentException if the contact does not exist 
	
List<Meeting> getFutureMeetingList(Calendar date);
32 Returns the list of meetings that are scheduled for the specified date.
33 Returns the list of meetings that took place on the specified date.
34 If there are no meetings, the returned list will be empty.
35 The list will be chronologically sorted 
36 The list will not contain any duplicates.

List<PastMeeting> getPastMeetingList(Contact contact);
37 @return the list of pastmeeting(s) scheduled with this contact (maybe empty). 
38 If there are no meetings, the returned list will be empty.
39 The list will be chronologically sorted 
40 The list will not contain any duplicates.
41 @throws IllegalArgumentException if the contact does not exist 

void addMeetingNotes(int id, String text);
42 @throws IllegalArgumentException if the meeting does not exist 
43 @throws IllegalStateException if the meeting is set for a date in the future 
44 @throws NullPointerException if the notes are null 
	
Set<Contact> getContacts(int... ids);
45 @return a list containing the contacts that correspond to the IDs. 
46 @throws IllegalArgumentException if any of the IDs does not correspond to a real contact 
	
Set<Contact> getContacts(String name);
47 @return a list with the contacts whose name contains that string. 
48 @throws NullPointerException if the String is null 
49 @throws NullPointerException if the Id parameter is null