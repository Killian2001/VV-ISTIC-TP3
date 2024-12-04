# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Smells discussed in classes were the following (in order, from [lecture slides](https://people.irisa.fr/Benoit.Combemale/pub/course/vv/4-dynamic-test-istic.pdf)):

- Piggybacking : accumulating a more than one assertion in a test case instead of dispatch each assertion in its own test case.
- Happy path : consists to write the test following an optimistic scenario, which may not happen in real world environment ; in this case, the test case risks to miss evidents bugs of the tested feature.
- Useless assert : writing asserts which will trivially succeeds, and brings nothing to the test.
- Hidden dependancy (mentionned on slides, infos from [here](https://github.com/abmessaoud/webali.github.io/blob/master/_posts/2017-12-05-Unit-Testing-Anti-Patterns.md)) : implicit use of a component, initialized unknonwingly to the programmer, which lead to difficultly explainable failures.
