# PaperDragon
FH Joannum SWENGA Project

##Teammembers:
-Andreas Staudacher
-Florian Gartner
-Georg Schmitzer

##Work distribution:
###Andreas Staudacher:
Project Leader
Projekt Setup (Create Dynamic Web Project, Configer XMLs, upload to GitHub)
Head of Coding
Major Model creation
DAOs
Controllers
Picture Upload
EXCEL export (Character stats)
Generall assisting and problem solving
ER-Diagramm, UserRequirement-Specification
README file

###Florian Gartner:
Model Creation (Chat, GameSession, Post, Threat)
Template Research
HTML Design & Thymeleaf
Itemlist website,...
ER-Diagramm, UserRequirement-Specification
Chat design implementation
ItemController
PowerPoint
assisting with Controllers
assisting in security
README file
testing

###Georg Schmitzer
Head of Design
Find/choose pictures
HTML Design & Thymeleaf
Documentation (mockups)
ER-Diagramm, UserRequirement-Specification
Security of banning users
assisting with Controllers
Excel export (added items)
PowerPoint
README file
testing

##Setup instructions
1. Download From Master Branch the project
2. Unzip and copy the project into your eclipse workspace
3. In eclipe: File->Import->Existing Project into Workspace->Select root directory->Finish
4. In JavaResources->src->db.properties put in your own credentials
5. Right Click your Project->Properties -> Check Settings for Server and Runtime ...
6. Right Click the Project -> Run on Server -> Remove other resources (For Performance and Database conflicts) -> Run
7. Wait till server started
8. Open the url(localhost:8080/PaperDragon/login) of the application in a browser of your choice
9. Open the url localhost:8080/PaperDragon/fillUsers
  -This creates User Roles and the users: user, admin
                       with the password: password
10. Optional: Create your own user by clicking Sign Up on the login
11. Log in with an account of your choice (admin, user or self created)
12. Open the url localhost:8080/PaperDragon/fillItems
13. Enjoy!

Login Daten: 
admin password
user password

URLs to run:
localhost:8080/PaperDragon/fillUsers
localhost:8080/PaperDragon/fillItems (after Login)

