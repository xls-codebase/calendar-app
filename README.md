# calendar-app
This is a demo-web project for my application as a web developer at microlab.

The mail-scheduler is preconfigured for Gmail.

The following parameters must be added at startup:

- recipient
- spring.mail.username
- spring.mail.password
- cronExpression

where "recipient" is the recipient email address, 
"spring.mail.username" ist the username(email address) from Gmail
and "spring.mail.password" is a Google app password (https://support.google.com/accounts/answer/185833?hl=en)

cronExpression is another parameter. It is used to set the time at which the daily email is sent. It is predefined with 0 0 1 * * ? (01:00 a.m.).

The jar file (calendar-app-0.0.1-SNAPSHOT.jar) can be opened from the command line with the following command:
_java -jar -DcronExpression="0 0 1 * * ?” -Drecipient=“tester@gmail.com” -Dspring.mail.username=“tester@gmail.com” -Dspring.mail.password=“1234567890” calendar-app-0.0.1-SNAPSHOT.jar_ can be started.

The website can then be accessed under 127.0.0.1:8080 in Google Chrome.