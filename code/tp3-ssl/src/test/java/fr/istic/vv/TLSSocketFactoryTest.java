package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class TLSSocketFactoryTest {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    void preparedSocket_NullProtocols() {
        // Create the mock.
        SSLSocket mockSocket = mock(SSLSocket.class);
        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // Run prepareSocket.
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(mockSocket);

        // Check that setEnabledProtocols is never called.
        verify(mockSocket, never()).setEnabledProtocols(any());
    }

    @Test
    void typical() {
        // Create the mock.
        SSLSocket mockSocket = mock(SSLSocket.class);
        when(mockSocket.getSupportedProtocols()).thenReturn(
                shuffle(new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[] {"SSLv3", "TLSv1"}));

        // Run prepareSocket.
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(mockSocket);

        // Check setEnabledProtocols arguments.
        verify(mockSocket).setEnabledProtocols(argThat(arg -> Arrays.equals(arg,
                new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"})));
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}