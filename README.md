**This is a work in progress**

Sample RPN Calculator
====================

This sample project aims to demonstrate the following : 

* How to write and run acceptance test with jbehave
* How to test a REST service (using jetty and jersey in this case)
* How to integrate all of this with maven

What is a PRN calculator?
=========================

A PRN (Polish Reverse Notation) calculator is a calculator designed to mess with your head. However, it is pretty easy to code.

For example, if you had `1 2 3 + 2 * -`, you would get `-9`. You simple pile numbers from left to right, and when you hit an operator you apply it to the last two numbers. 

The implementation is fairly easy because this project mainly aims to showcase the tools, not the code. 

Here is the [tutorial in haskell](http://learnyouahaskell.com/functionally-solving-problems) where I stole the idea (thanks!).

Current State
=============

Currently, you can run the JBehave tests within eclipse as JUnit tests, or with maven `mvn clean integration-test`

Note that JBehave tests are excluded from infinitest's scope.

Ultimately, the goal is to run the JBehave tests against a REST server to show how to start/stop it. Coming soon.

The code is in english, but the stories are in french. Weird, I know, but that's to showcase the required config to change the langage. There is a lot more you can configure, see the doc.
