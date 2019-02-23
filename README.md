# Mastermind WEB (REST)

## Requirements

* Java : SDK or JRE 1.8 or later
* Maven: 3.6 or later
* RAM : _TODO_
* CPU : _TODO_

## Architecutre

### Domain

* Dependencies: Must not have
* Components: Entities, Value Objects, AgreggatedRoots, Repositories' definitions, Generators' definitions, Configurers' definitions
 
### UseCases

* Dependency: upon **domain**
* Components: UserCaseInteractor, InputBoundary, OutputBoundary
 
### Adapters

* Dependency: upon **UseCases**
* Components: Controllers, Repositories's implementation, Generators' implementations, Configurers' implementation

### Application

* Dependency: upon **Adapters**
* Components: Frameworks specific components and configurations

## Install

## Run

## Tests

## Misc

## Related links

* http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
* https://softwareengineering.stackexchange.com/questions/373413/implementing-a-rest-api-in-a-clean-architecture
