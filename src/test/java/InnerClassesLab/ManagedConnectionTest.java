package InnerClassesLab;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ManagedConnectionTest {
    ConnectionManager.ManagedConnection mcFTP = new ConnectionManager(). new ManagedConnection("000.111.111.111", "FTP");
    ConnectionManager.ManagedConnection mcQOTD = new ConnectionManager(). new ManagedConnection("000.222.222.222", "QOTD");
    ConnectionManager.ManagedConnection mcSSH = new ConnectionManager(). new ManagedConnection("000.333.333.333", "SSH");
    ConnectionManager.ManagedConnection mcDOOM = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");
    ConnectionManager.ManagedConnection mcHTTP = new ConnectionManager(). new ManagedConnection("000.555.555.555", "HTTP");
    ConnectionManager.ManagedConnection mc666 = new ConnectionManager(). new ManagedConnection("000.666.666.666", 666);


    @Test
    public void protocolToPortTest(){
        Assert.assertEquals("Port was not 20", 20 , mcFTP.getPort());
        Assert.assertEquals("Port was not 17", 17 , mcQOTD.getPort());
        Assert.assertEquals("Port was not 22", 22 , mcSSH.getPort());
        Assert.assertEquals("Port was not 666", 666 , mcDOOM.getPort());
        Assert.assertEquals("Port was not 80", 80 , mcHTTP.getPort());
    }

    @Test
    public void portToProtocol(){
        Assert.assertEquals("Protocol was not Doom", "DOOM", mc666.getProtocol());
    }

}
