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

    @Test
    public void isClosed(){
        Assert.assertEquals("Connection did not close correctly", "Connection is closed", mcFTP.close());
        Assert.assertEquals("Connection is still open", false, mcFTP.getIsOpen());
        Assert.assertEquals("Connection IP did return error message when closed", "Error: Connection is Closed", mcFTP.getIP());
        Assert.assertEquals("Connection Port did not return invalid value when closed", -999999999, mcFTP.getPort());
    }

    @Test
    public void connectTest(){
        Assert.assertEquals("Connection did not report as open", "Connection established\n" + mcDOOM.toString(), mcDOOM.connect());
    }

    @Test
    public void equalityTest(){
        ConnectionManager.ManagedConnection mcDOOM = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");
        ConnectionManager.ManagedConnection mcDOOM2 = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");
        Assert.assertTrue("Identical objects did not return true", mcDOOM.equals(mcDOOM2));
        Connection con = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");
        Assert.assertTrue("Identical ManagedConnections did not return true when one was passed as a Connection", mcDOOM.equals(con));
    }



}
