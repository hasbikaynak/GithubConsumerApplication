# GithubConsumerApplication
This repository contains a Java 17 application built with Spring Boot 3 that serves as an API consumer for the GitHub API.

<h2>ToDo List before starting the program:</h2>

1-) You have to generate "Github Personal Token" in order to send successful request.

2-)Go to this tab and generate your token

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/1c3f53af-c7ba-4e5a-b3bc-ead5b71e1cd6)

3-)Then go to application.properties in our GithubConsumerApplication project

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/c2b6f281-b48d-4b51-a33b-4fa2e69c8109)

4-)Paste your token there

--------------------------------------------------------------------------------------------------------------------------

<h2>How to send successful request by using Postman?</h2>

Open your Postman and write this url for sending the request: localhost:8080/github/repositories?username={YOUR_USERNAME}
(Don`t forget to write your username into brackets)

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/fdcbd28d-8088-42b4-8acc-076c4c000f70)

Select headers tab and write "Accept" as a key and "application/json" as a value.

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/2fb4def9-4298-4e86-b2d9-6721c24668ef)

Click send 

And this is your result 

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/c125a0ae-75b1-4446-a9f3-6eb45e1db643)

-----------------------------------------------------------------------------------------------------------------------

<h2>Negative scenerios</h2>

When you send wrong username or username that is not in the GitHub db, this is what you see as a result

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/89f54ac5-7201-4212-84a8-41381f4fb002)

If you send "application/xml or application/xml/**" format in the header, this is what you see as a result

![image](https://github.com/hasbikaynak/GithubConsumerApplication/assets/73940626/287d4f88-102b-4a87-9bd9-46e91a482f44)

