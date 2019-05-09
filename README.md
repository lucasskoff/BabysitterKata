## Author Notes:
Created as part of my application to Pillar Technology and as an excercise in TDD. Uses JUnit 4.12 for test cases, Java 8 for code and Maven for build and dependencies. 
To build the code and run the test cases simply use command:
> mvn test

Inside the code directory containing the pom.xml file. Maven was set up on a windows machine following this tutorial: https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows
On Windows it is important to have the JAVA_HOME and M2_HOME environment variables set up in order to run mvn through the command line. 
Details on setting up the JAVA_HOME variable: http://roufid.com/no-compiler-is-provided-in-this-environment/
Thank you and have a wonderful day. :)
# Original Requirements:

## Background
This kata simulates a babysitter working and getting paid for one night.  The rules are pretty straight forward.

## Feature
*As a babysitter<br>
In order to get paid for 1 night of work<br>
I want to calculate my nightly charge<br>*

## Requirements
The babysitter:
- starts no earlier than 5:00PM
- leaves no later than 4:00AM
- only babysits for one family per night
- gets paid for full hours (no fractional hours)
- should be prevented from mistakes when entering times (e.g. end time before start time, or outside of allowable work hours)

The job:
- Pays different rates for each family (based on bedtimes, kids and pets, etc...)
- Family A pays $15 per hour before 11pm, and $20 per hour the rest of the night
- Family B pays $12 per hour before 10pm, $8 between 10 and 12, and $16 the rest of the night
- Family C pays $21 per hour before 9pm, then $15 the rest of the night
- The time ranges are the same as the babysitter (5pm through 4am)

Deliverable:
- Calculate total pay, based on babysitter start and end time, and a family.
