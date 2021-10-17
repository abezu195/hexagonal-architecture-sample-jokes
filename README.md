# Hexagonal architecture sample
This is a sample application, for educational purposes in order to experiment and better understand the hexagonal architecture. 

The hexagonal architecture is based on three principles and techniques:
- Explicitly separate User-Side, Business Logic, and Server-Side
- Dependencies are going from User-Side and Server-Side to the Business Logic
- We isolate the boundaries by using Ports and Adapters
![hexagonal-architecture-design](https://blog.octo.com/wp-content/uploads/2020/06/archi_hexa_en_06-1024x526.png)

## Jokes app
Based on the architecture presented above, I tried to separate the project in 4 Maven modules:
1. **service** - the main module, which holds all the business rules, and is the cleanest module. It has few dependencies (Lombok, JUnit, Mockito, Assertj), mainly tried not to clutter it with too many dependencies.
2. **console-ui** - module responsible for being the user-side, which will start the interaction with the rest of the application modules.
3. **repository** - module responsible for supplying data for the service module, which is responsible for the communication of the service layer with the database.
4. **context-launcher** - module responsible for actually running the application. It gathers all the modules between them.

## Running the project
Simply clone the project, and import it as a Maven project in your favorite IDE (mine was Intelijj IDEA). Should be able to run the project as is.

Or you can do it without the IDE, running the usual ``` mvn clean install ``` in order to compile, run unit tests & build the project in the parent module. Then into the context-launcher folder and run the command ``` mvn spring-boot:run ``` in order to run the app. 
