package InnerClassesLab;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ConnectionManager {

    private int maxConnections;
    private int currentConnections;
    private List<Connection> connections;

    public ConnectionManager(int max){
        this.maxConnections = max;
        this.currentConnections = 0;
        this.connections = new ArrayList<Connection>();
    }

    public ConnectionManager(){
        this.maxConnections = 8;
        this.currentConnections = 0;
        this.connections = new ArrayList<Connection>();
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getCurrentConnections() {
        return currentConnections;
    }

    public void setCurrentConnections(int currentConnections) {
        this.currentConnections = currentConnections;
    }


    public List<Connection> getConnections() {
        return connections;
    }


    public Connection getConnection(String ip, String protocol){
        if(connectionExists(ip)){
            System.out.println("Connection is already active\n" + connectionFromList(ip));
            return connectionFromList(ip);
        }

        return createConnection(ip, protocol);

    }

    public Connection getConnection(String ip, int port){
        if(connectionExists(ip)){
            System.out.println("Connection is already active\n" + connectionFromList(ip));
            return connectionFromList(ip);
        }
        return createConnection(ip, port);
    }

    private Connection connectionFromList(String ip){
        for(Connection con : connections){
            if(con.getIP().equalsIgnoreCase(ip)){
                return con;
            }
        }
        return null;
    }

    private boolean connectionExists(String ip){
        for(Connection con : connections){
            if(con.getIP().equalsIgnoreCase(ip)){
                return true;
            }
        }
        return false;
    }


    public Connection createConnection(String ip, String protocol){
        if (currentConnections >= maxConnections){
            System.out.println("No available connections!");
            return null;
        }
        ManagedConnection toReturn = new ManagedConnection(ip, protocol);
        connections.add(toReturn);
        System.out.println(toReturn.connect());
        return toReturn;
    }

    public Connection createConnection(String ip, int port){
        if (currentConnections >= maxConnections){
            System.out.println("No available connections!");
            return null;
        }
        ManagedConnection toReturn = new ManagedConnection(ip, port);
        connections.add(toReturn);
        System.out.println(toReturn.connect());
        return toReturn;

    }

    public class ManagedConnection implements Connection {
        private String ip;
        private String protocol;
        private int port;
        private boolean isOpen;

        public ManagedConnection(String ip, String protocol){
            this.ip = ip;
            this.protocol = protocol;
            this.port = protocolToPort(protocol);
            this.isOpen = true;

        }

        public ManagedConnection(String ip, int port){
            this.ip = ip;
            this.protocol = portToProtocol(port);
            this.port = port;
            this.isOpen = true;

        }


        private int protocolToPort(String protocol){
            protocol = protocol.toLowerCase();
            switch(protocol){
                case "ftp":
                    return 20;
                case "qotd":
                    return 17;
                case "ssh":
                    return 22;
                case "doom":
                    return 666;
                default:
                    return 80;

            }
        }


        private String portToProtocol(int port){
            switch(port){
                case 20:
                    return "FTP";
                case 17:
                    return "QOTD";
                case 22:
                    return "SSH";
                case 666:
                    return "DOOM";
                default:
                    return "HTML";
            }




        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("IP: " + this.getIP() + "\n");
            sb.append("Protocol: " + this.getProtocol() + "\n");
            sb.append("Port: " + this.getPort());
            return sb.toString();
        }



        public String getIP(){
            return this.ip;
        }

        public String getProtocol(){
            return this.protocol;
        }

        public int getPort(){
            return this.port;
        }

        public boolean getIsOpen(){
            return this.isOpen;
        }

        public String connect(){
            if(this.isOpen){
                currentConnections++;
                return "Connection established\n" + this.toString();
            }
            return "Connection is closed";
        }

        public String close(){
            currentConnections--;
            this.isOpen = false;
            this.port = 999999999;
            this.ip = "999999999999";
            this.protocol = "zzzzzzzzzzzz";
            return this.connect();
        }





    }

}
