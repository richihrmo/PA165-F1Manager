# Formula One Team
## PA165 Enterprise Applications in Java

A Formula 1 team is in need to manage its participation to the world championship. The team has two cars participating to each race, each one driven by one driver, with other test drivers available to develop the car further. The manager of the team can set the driver for each car and manage a list of other (test) drivers that are under contract and he can also set as main drivers if unhappy about results. Each driver has a name, surname, nationality, and set of characteristics (e.g. driving on the wet, aggressivity, etc..) that the manager can visualize. The car is composed of several components (engine, suspensions), that the different departments of the team (e.g. engine, aerodynamics) evolve during time. The manager can pick from a list of components and assemble the cars that will participate to the next race. Engineers from other departments can login to the system and put newer components available. The manager is notified that those components are available and that they can be used to improve the car.

### REST
```
cd rest
mvn tomcat7:run
```
#### DriverController
culr commands for testing:
* list all drivers: `curl -i -X GET http://localhost:8080/pa165/rest/drivers`
* get specific driver by id: `curl -i -X GET http://localhost:8080/pa165/rest/drivers/{id}`
⋅⋅⋅ for example: `curl -i -X GET http://localhost:8080/pa165/rest/drivers/1`
* create driver: `curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test","surname":"test","nationality":"uk","specialSkill":"POWER_SLIDING"}' http://localhost:8080/pa165/rest/drivers/create`
* delete driver: `curl -i -X DELETE http://localhost:8080/pa165/rest/drivers/delete/{id}`
⋅⋅⋅ for example: `curl -i -X DELETE http://localhost:8080/pa165/rest/drivers/delete/3`
* list all test drivers: `curl -i -X GET http://localhost:8080/pa165/rest/drivers/show-testDrivers`



## Authors

* **Richard Hrmo** - *Team leader* - [richihrmo](https://github.com/richihrmo)
* **Lucie Kurečková** - *Member* - [xkurexk](https://github.com/xkureck)
* **Matúš Macko** - *Member* - [matusmacko](https://github.com/matusmacko)
* **Róbert Tamáš** - *Member* - [RobertTamas](https://github.com/RobertTamas)


