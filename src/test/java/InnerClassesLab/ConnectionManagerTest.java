package InnerClassesLab;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ConnectionManagerTest {

    ConnectionManager cm = new ConnectionManager(4);
    ConnectionManager.ManagedConnection mcFTP = new ConnectionManager(). new ManagedConnection("000.111.111.111", "FTP");
    ConnectionManager.ManagedConnection mcQOTD = new ConnectionManager(). new ManagedConnection("000.222.222.222", "QOTD");
    ConnectionManager.ManagedConnection mcSSH = new ConnectionManager(). new ManagedConnection("000.333.333.333", "SSH");
    ConnectionManager.ManagedConnection mcDOOM = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");

    @Test
    public void createConnectionProtocolTest(){

        Assert.assertEquals("Connection was not created (Protocol)", cm.createConnection("000.444.444.444", "DOOM"), cm.getConnections().get(0));
        cm.getConnection("000.444.444.444", "DOOM");
        Assert.assertEquals("Number of connections was incorrectly increased", 1, 1);
    }

    @Test
    public void createConnectionPortTest(){
        Assert.assertEquals("Connection was not created (Port)", cm.createConnection("000.444.444.444", 666), cm.getConnections().get(0));
    }
}
