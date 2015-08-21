---
title: Digital critical editions
layout: page
---

Simple tools for critical comparison of citable digital texts.  The project includes a compiled code library you can use from any JVM language, and command-line scripts in [groovy](http://www.groovy-lang.org/).

## Background ##

What is a [digital critical edition](whatis)?

## Prerequisites

- two or more citable digital diplomatic editions of a text
- tools for analyzing the diplomatic editions as normalized tokens

## Functionality


- simple 2-way diff'ing, identifying:
    - tokens in  text A, but not text B
    - tokens in text B, but not text A
    - longest common sequence of tokens in A and B
- n-way diff'ing with reference to any single text in the corpus
- hierarchical diffing  ("vertical" and "horizontal" diff'ing), that is:
    - vertical diff'ing on citable node URNs
    - within each node, horizontal diff'ing on tokens

## Documentation ##

- Worked examples
- Library specification
- API docs
