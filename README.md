Jason Goodwin's "Learning Akka" Book
---

This code is a work in progress following JG's Learning Akka Book. The current plan is to have each chapter as an SBT project. Ideally, you should be able to clone the repository, `cd` to a chapter, and run `sbt test`.

#### Progress
1. Chapter 1, Questions:
    - [x] Create an actor which stores the last string it received
    - [x] Write a unit test which confirms the actor will receive a message correctly
    - [x] Write a unit test which behaves correctly if it is sent two messages
2. Chapter 2:
    1. General Learning:
        - [x] Write an Actor that reverses a string, have it fail for unknown message types
        - [ ] Write a service to exposes the functionality through a plain Scala API, returning a future
        - [ ] Build Unit Tests for Successes and Failures
        - [ ] Write a test which will send a `List[String]` and validate the results
    2. Project Homework:
        - [ ] Add an atomic `SetIfNotExists` and `DeleteKey` to the ` AkkaDbServerActor`
        - [ ] Complete some failure tests for the client
        - [ ] Build an `Actor` that adds some functionality to your project (`QueryActor`?)
        - [ ] Add Unit Tests

#### Tag
LEARNINGAKKAJG
