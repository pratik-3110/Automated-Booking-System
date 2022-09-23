# Automated-Booking-System

Repository for CodeFury 2022 Project

From the application folder, import or clone the project and open it in IDE (SpringToolSuite here).

In database folder in repository there are two files namely: a) InsertQuery.txt b) TableQuery.txt

Startup the derbyDb in system by going to bin folder in installed derby. Open terminal there and run "StartNetworkServer;". So, derby would be running at post 1527. (Note keep it running. Further no use of this terminal to run command)

Now open another cmd at same bin location and run "ij.bat;" command. So, this makes terminal ready to connect to derbyDb.

From TableQuery.txt run entire content(copy/paste) in this ij connected terminal. This would create Database named "RoomBooking2". You can see tables created using "show tables;" command.

Now same way to insert base data run entire content(copy/paste) of InsertQuery.txt in that ij ternimal. So raw data is inserted in terminal.

This data contains following roles in Users table.

a) Member data userid: 2019101 email: karan@gmail.com

b) Manager data userid: 2019104 email: poki@gmail.com

c) Admin data usreid: 2019102 email: kush@gmail.com

Using this userid and email you can login in system and run the functionalities defined according to their role.

By logging as Admin, you have right to insert users using json file which is kept in database folder having titled "sample.json"

The base url of our application is "http://localhost:8080/AutomatedBookingSystem2/"
