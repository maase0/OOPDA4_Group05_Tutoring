# OOPDA Section 4 Group 5 Tutoring Scheduler

A scheduler to schedule one week's worth of appointments between tutors and students.

##Scenario
The user is a third party that schedules the students or tutors.
The user interface is a grid, Monday to Friday by 10:00 to 18:00.
Tutors can be scheduled in open 2 hour blocks, starting at 10:00, 12:00, 14:00 or 16:00.
Students can be scheduled for 30 minute, 45 minute, or 60 minute appointments, but only during times that tutors are already scheduled.
When a time doees not have a tutor scheduled, it will be empty.
When a time has a tutor scheduled, but the tutor does not have an appointment with a student, the time slot will be green and show the tutor's name, to show it is available.
If a tutor has an appointment scheduled, the time slot will be red and will show the tutor's name and the student's name.
The user will be able to deschedule students or tutors.
Any times that a tutor is scheduled but does not have an appointment will be treated as walk-in tutoring.
The user can also save and load schedules through the file menu, so a week can be scheduled in advance.
