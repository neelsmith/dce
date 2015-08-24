/*
Compare sets of strings in two files, one string per line.
Normalize content before comparison so we can safely compare unicode
text content.
*/

import java.text.Normalizer
import java.text.Normalizer.Form

String usg = "groovy cfTokenSets.groovy -[hoabs] TOKENS_A_FILE TOKENS_B_FILE"
CliBuilder cli = new CliBuilder(usage: usg)
cli.with {
  h longOpt: 'help', 'Show usage information'
  o longOpt: 'overlap', 'Print to stdout tokens in both A and B'
  a longOpt: 'tokens-a', 'Print to stdout tokens in list A only'
  b longOpt: 'tokens-b', 'Print to stdout tokens in list B only'
  s longOpt: 'summary', 'Print summary to stderr'
  
}
def options = cli.parse(args)

if ((!options) || (options.h)) {
  cli.usage()
  System.exit(0)
}

def fileArgs = options.arguments()
if (fileArgs.size() != 2) {
  cli.usage()
  System.exit(-1)
}

File tokensA = new File(fileArgs[0])
File tokensB = new File(fileArgs[1])

def listA = []
tokensA.readLines().each {
  listA.add(Normalizer.normalize(it, Form.NFC))
}
def setA = listA as Set

def listB = []
tokensB.readLines().each {
  listB.add(Normalizer.normalize(it, Form.NFC))
}
def setB = listB as Set
def overlap = setA.intersect(setB)
def bOnly = setB - overlap
def aOnly = setA - overlap

if (options.o) {
  overlap.each {
    println it
  }  
}
if (options.a) {
  aOnly.each {
    println it
  }
}
if (options.b) {
  bOnly.each {
    println it
  }
}
if (options.s) {
  System.err.println "Comparing ${setA.size()} tokens in set A to ${setB.size()} tokens in set B."
  System.err.println "${overlap.size()} tokens appear in both A and B"
  System.err.println "${aOnly.size()} tokens appear only in set A"
  System.err.println "${bOnly.size()} tokens appear only in set B"
}

