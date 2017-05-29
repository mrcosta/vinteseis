# vinteseis (is 26 in portuguese :sunglasses:)

[![Build Status](https://travis-ci.org/mrcosta/vinteseis.svg?branch=master)](https://travis-ci.org/mrcosta/vinteseis)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c6e06df7bf3549789e8d8f4e90eb3c93)](https://www.codacy.com/app/mrcosta/vinteseis?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mrcosta/vinteseis&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/mrcosta/vinteseis/badge.svg?branch=master)](https://coveralls.io/github/mrcosta/vinteseis?branch=master)

The idea of the project is to provide an endpoint to receive transactions. This is a request example with the following information that you can send:
```bash
$ curl -H "Content-Type: application/json" -X POST -d '{"amount": 12.3, "timestamp": 1478192204000}' http://localhost:8080/transactions
```
A better visualization of a transaction's structure:
```json
{
    "amount": 12.3,
    "timestamp": 1478192204000
}
```

And another endpoint to retrieve the information related to the transactions of the last 60 seconds. 
Request example:

```bash
$ curl -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://localhost:8080/statistics'
```
That it will return something like:

```json
{
    "sum": 1000,
    "avg": 100,
    "max": 200,
    "min": 50,
    "count": 10
}
```

# how to run the application

```bash
$ git clone https://github.com/mrcosta/vinteseis.git 
$ cd vinteseis
$ ./gradlew bootRun
```

## how to run unit tests

```bash
$ ./gradlew test
```

## how to run integration tests

```bash
$ ./gradlew integrationTest 
```

# techstack
basically java8 with spring-boot (and all resources that automagically provide), gradle and some external tools like jacoco (to generate test coverage reports), codacy, coveralls and travis to build the application.

# some notes

* I did the timestamp validation creating a new annotation to be validated

# improvements to be done
* better handling for concurrency (I did a way that retrieve in not O(1) and then start this current commit with concurrency)
* logging
* creation of a Dto for TransactionStatistics
* containerization (docker)
* some small refactor (after two days working in just one thing, it's hard to see all the refactors haha. Is better to rest a little haha)
* performance tests
* swagger documentation for the API's
