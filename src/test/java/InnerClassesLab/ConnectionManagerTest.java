package InnerClassesLab;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ConnectionManagerTest {


    //I created these and never used them. Brilliant...
    ConnectionManager cm = new ConnectionManager(4);
    ConnectionManager.ManagedConnection mcFTP = new ConnectionManager(). new ManagedConnection("000.111.111.111", "FTP");
    ConnectionManager.ManagedConnection mcQOTD = new ConnectionManager(). new ManagedConnection("000.222.222.222", "QOTD");
    ConnectionManager.ManagedConnection mcSSH = new ConnectionManager(). new ManagedConnection("000.333.333.333", "SSH");
    ConnectionManager.ManagedConnection mcDOOM = new ConnectionManager(). new ManagedConnection("000.444.444.444", "DOOM");

//    @Test
//    public void createConnectionProtocolTest(){
//
//        Assert.assertEquals("Connection was not created (Protocol)", cm.createConnection("000.444.444.444", "DOOM"), cm.getConnections().get(0));
//        cm.getConnection("000.444.444.444", "DOOM");
//        Assert.assertEquals("Number of connections was incorrectly increased", 1, 1);
//    }
//
//    @Test
//    public void createConnectionPortTest(){
//        Assert.assertEquals("Connection was not created (Port)", cm.createConnection("000.444.444.444", 666), cm.getConnections().get(0));
//    }

    @Test
    public void getConnectionProtocolTest(){
        ConnectionManager cm2 = new ConnectionManager(2);
        Connection con = cm2.getConnection("000.111.111.111", "FTP");
        Assert.assertEquals("Number of current connections was not increased", 1, cm2.getCurrentConnections());
        Connection conTheSame = cm2.getConnection("000.111.111.111", "FTP");
        Assert.assertEquals("Number of current connections was increased when it shouldn't have been", 1, cm2.getCurrentConnections());
        Connection conNew = cm2.getConnection("000.444.444.444", "DOOM");
        Assert.assertEquals("Number of current connections was not increased to 2", 2, cm2.getCurrentConnections());
        Connection conTheThird = cm2.getConnection("000.333.333.333", "SSH");
        Assert.assertEquals("New Connection was created despite no available connections", 2, cm2.getCurrentConnections());
        Assert.assertEquals("New Connection was added to the list despite no available connections", 2, cm2.getConnections().size());


    }

    @Test
    public void getConnectionPortTest(){
        ConnectionManager cm3 = new ConnectionManager(2);
        Connection con = cm3.getConnection("000.111.111.111", 20);
        Assert.assertEquals("Number of current connections was not increased", 1, cm3.getCurrentConnections());
        Connection conTheSame = cm3.getConnection("000.111.111.111", 20);
        Assert.assertEquals("Number of current connections was increased when it shouldn't have been", 1, cm3.getCurrentConnections());
        Connection conNew = cm3.getConnection("000.444.444.444", 666);
        Assert.assertEquals("Number of current connections was not increased to 2", 2, cm3.getCurrentConnections());
        Connection conTheThird = cm3.getConnection("000.333.333.333", 22);
        Assert.assertEquals("New Connection was created despite no available connections", 2, cm3.getCurrentConnections());
        Assert.assertEquals("New Connection was added to the list despite no available connections", 2, cm3.getConnections().size());

    }

    @Test
    public void getConnectionAllTest(){
        ConnectionManager cm4 = new ConnectionManager(2);
        Connection con = cm4.getConnection("000.111.111.111", "FTP", 20);
        Assert.assertEquals("Number of current connections was not increased", 1, cm4.getCurrentConnections());
        Connection conTheSame = cm4.getConnection("000.111.111.111", "FTP", 20);
        Assert.assertEquals("Number of current connections was increased when it shouldn't have been", 1, cm4.getCurrentConnections());
        Connection conNew = cm4.getConnection("000.444.444.444", "DOOM", 666);
        Assert.assertEquals("Number of current connections was not increased to 2", 2, cm4.getCurrentConnections());
        Connection conTheThird = cm4.getConnection("000.333.333.333", "SSH", 22);
        Assert.assertEquals("New Connection was created despite no available connections", 2, cm4.getCurrentConnections());
        Assert.assertEquals("New Connection was added to the list despite no available connections", 2, cm4.getConnections().size());
    }

    @Test
    public void closeConnectionTest(){
        ConnectionManager cm5 = new ConnectionManager(2);
        Connection con = cm5.getConnection("000.111.111.111", "FTP", 20);
        cm5.closeConnection(con);
        Assert.assertEquals("Number of current connections did not decrease", 0, cm5.getCurrentConnections());
    }



}
