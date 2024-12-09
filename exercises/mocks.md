# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

## Answers

After replacing the initial mocks by Mockito mocks, we test the coverage of the 
code using PIT.

In the first version of our tests, all tests were suceeding even if there was no
code in `prepareSocket`. This was because :

- the test `preparedSocket_NullProtocols` requires to not
call `setEnabledProtcol` : here, it is the intended behaviour.
- the test `typical` is supposed to run the `setEnabledProtocol` with an 
expected parameter ; as the value is checked using a JUnit assertion, the test
fails if the provided parameter is not the one expected ; however, if we empty
the `prepareSocket` method, `setEnabledProtocol` will never be called, hence
the test will pass.

This incorrect behaviour is fixed by using Mockito. We use, to check the value :

```java
verify(mockSocket).setEnabledProtocols(argThat(arg -> Arrays.equals(arg,
                new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"})));
```

`verify` allows us to test calls over the mock class `mockSocker`. `argThat` is
used to test the assertion from the old test. But what that prevents the
test to pass when we empty the method is the use of `verify(mockSocket)`.
When passing only the mock to `verify`, it ensures that one and only call
to corresponding functions is done ; as the empty method never calls
`setEnabledProtocol`, the test will fail.