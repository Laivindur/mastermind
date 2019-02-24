# Mastermind WEB (REST)

## Requirements

* Java : SDK or JRE 1.8 or later
* Maven: 3.6 or later

## Architecutre

I have tried to follow Clean Architecture premisses for the project's composition and structure. Encapsulation, cohesion and decoupling

I have tried to follow DDD techniques to implement the domain model.

**Disclaimers** : Mastermind-adapter module should provide with more decoupling. Right now it allows upper layers to be aware of too many domain's
 model details. I should add also a Service layer so that Controllers can be decoupled from the use cases handlers.
 
**More disclaimers**: Initially, I spent too much time on making the API REST to be Hateoas. It took too much and I was running out of time to finish the exam, so I left. I have implemented HATEOAS REST APIs before and it could have made a big deal improvement better the final result of the exam. Regarding Spring Boot configuration componenets, I took mines from one fo my personal projects to save time. Some of them are unecessary for the exam.

### Domain

* Dependencies: Must not have
* Components: Entities, Value Objects, AgreggatedRoots, Repositories' definitions, Generators' definitions, Configurers' definitions
 
### UseCases

* Dependency: upon **Domain**
* Components: UserCaseInteractor, InputBoundary, OutputBoundary
 
### Adapters

* Dependency: upon **UseCases**
* Components: Controllers, Repositories's implementation, Generators' implementations, Rest resources, Mappers

### Application

* Dependency: upon **Adapters**
* Components: Frameworks specific components and configurations

## Install

```cmd
$ git clone https://github.com/Laivindur/mastermind.git
```

## Compile

```cmd
$ mvn clean package
```

## Run

```cmd
$ java -jar /<workspace>/mastermind/mastermind-application/target/mastermind-application-0.0.1-SNAPSHOT.jar .xmx128m -xms128m -Duser.timezone=UTC -Dfile.encoding=utf-8 
```

### Find boards

```cmd
GET http://localhost:8080/boards/ HTTP 1.1

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
[]
```

### Create Cheewe as player and future code maker

```cmd
POST http://localhost:8080/players/ HTTP 1.1
Content-Type: application/json;encoding=utf-8
{
 "name":"Cheewaka"
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "a63bea31-4d00-4929-86e7-28bf6b89a525",
    "name": "Cheewaka"
}
```

### Create Han as player and future code breaker

```cmd
POST http://localhost:8080/players/ HTTP 1.1
Content-Type: application/json;encoding=utf-8
{
 "name":"Han Solo"
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
    "name": "Han Solo"
}
```

### Create Board

```cmd
POST http://localhost:8080/boards/ HTTP 1.1
Content-Type: application/json;encoding=utf-8
{
 "codeMakerId":"7c74d494-aa2a-42e4-a729-f7ba7245f45e",
 "code": ["GREEN", "YELLOW", "YELLOW","PINK", "BLUE"]
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
    "code": [
        "GREEN",
        "YELLOW",
        "YELLOW",
        "PINK",
        "BLUE"
    ],
    "solved": false,
    "leftMoreGames": true,
    "codeMaker": {
        "id": "a63bea31-4d00-4929-86e7-28bf6b89a525",
        "name": "Cheewaka"
    }
}
```

### Anonymous access to the board list

```cmd
GET http://localhost:8080/boards/ HTTP 1.1

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
[
    {
        "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
        "code": [
            "*"
        ],
        "solved": false,
        "leftMoreGames": true
    }
]
```

### Guess and join boards

```cmd
PUT http://localhost:8080/boards/{boardId}/guess HTTP 1.1
Content-Type : application/json
{
 "codeBreakerId":"29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
 "guess": ["RED", "RED", "RED","RED", "RED"]
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
    "guesses": [
        {
            "code": [
                "GREEN",
                "YELLOW",
                "YELLOW",
                "PINK",
                "BLUE"
            ],
            "coloredPegs": 0,
            "whitePegs": 0,
            "solved": false
        }
    ],
    "solved": false,
    "leftMoreGames": true,
    "codeMaker": {
        "id": "29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
        "name": "Han Solo"
    }
}
```

### Guess again one matching

```cmd
PUT http://localhost:8080/boards/{boardId}/guess HTTP 1.1
Content-Type : application/json
{
 "codeBreakerId":"29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
 "guess": ["GREEN", "RED", "RED","RED", "RED"]
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
    "code": [
        "*"
    ],
    "guesses": [
       ...,
        {
            "code": [
                "GREEN",
                "YELLOW",
                "YELLOW",
                "PINK",
                "BLUE"
            ],
            "coloredPegs": 1,
            "whitePegs": 0,
            "solved": false
        }
    ],
    "solved": false,
    "leftMoreGames": true,
    "codeMaker": {
        "id": "29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
        "name": "Han Solo"
    }
}
```

### Guess again and one color matching

```cmd
PUT http://localhost:8080/boards/{boardId}/guess HTTP 1.1
Content-Type : application/json
{
 "codeBreakerId":"29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
 "guess": ["GREEN", "PINK", "RED","RED", "RED"]
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
    "code": [
        "*"
    ],
    "guesses": [
       ...,
        {
            "code": [
                "GREEN",
                "YELLOW",
                "YELLOW",
                "PINK",
                "BLUE"
            ],
            "coloredPegs": 1,
            "whitePegs": 1,
            "solved": false
        }
    ],
    "solved": false,
    "leftMoreGames": true,
    "codeMaker": {
        "id": "29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
        "name": "Han Solo"
    }
}
```

### Guess and win


```cmd
PUT http://localhost:8080/boards/{boardId}/guess HTTP 1.1
Content-Type : application/json
{
 "codeBreakerId":"29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
 "guess": ["GREEN",
        "YELLOW",
        "YELLOW",
        "PINK",
        "BLUE"]
}

HTTP Status 200 Ok
Content-Type: application/json;encoding=utf-8
{
    "id": "f23a6abb-ea8d-400d-afe5-06f7211c2be6",
    "code": [
        "*"
    ],
    "guesses": [
        ...,
        {
            "code": [
                "GREEN",
                "YELLOW",
                "YELLOW",
                "PINK",
                "BLUE"
            ],
            "coloredPegs": 5,
            "whitePegs": 0,
            "solved": true
        }
    ],
    "solved": true,
    "leftMoreGames": false,
    "codeMaker": {
        "id": "29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
        "name": "Han Solo"
    }
}
```

### Keep guessing ?

```cmd
PUT http://localhost:8080/boards/{boardId}/guess HTTP 1.1
Content-Type : application/json
{
 "codeBreakerId":"29d66dde-7b11-4f6f-9cfe-2fd37cd34ef9",
 "guess": ["GREEN",
        "YELLOW",
        "YELLOW",
        "PINK",
        "BLUE"]
}

HTTP Status 500 Internal Serv Error
Content-Type: application/json;encoding=utf-8

{
    "code": "Ups! something went wrong up here",
    "cause": "com.cyeste.games.mastermind.domain.exception.BoardClosedException",
    "threadId": 23,
    "date": {
        "zone": "UTC",
        "date_time": "2019-02-24T22:27:31.83Z"
    },
    "causeMessage": "The board left no more games or is solved."
}
```

## Tests

* Unitari tests all over the project's module
* End 2 End tests : A proof of concept with Postamn scripts
* Functional Test : Pending to implement with Spring Test

## Misc

## Related links

* [Uncle Bob Blog](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* [REST + Clean Architecture hints](https://softwareengineering.stackexchange.com/questions/373413/implementing-a-rest-api-in-a-clean-architecture)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
