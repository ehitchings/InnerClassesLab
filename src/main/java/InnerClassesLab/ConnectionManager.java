package InnerClassesLab;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ConnectionManager {

//    This contains a lot of commented out code, left over from refactoring.
//    I'm too paranoid and loss-averse to remove it, for fear of later discovering
//    that my current implementation has a hole that my tests haven't spotted.
//    This means you can see my thought process as I developed this, though. Enjoy.

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


//    public Connection getConnection(String ip, String protocol){
//        if(connectionExists(ip)){
//            System.out.println("Connection is already active\n" + connectionFromList(ip));
//            return connectionFromList(ip);
//        }
//
//        return createConnection(ip, protocol);
//
//    }

    public Connection getConnection(String ip, String protocol){
        ManagedConnection toComp = new ManagedConnection(ip, protocol);
        return connections.contains(toComp) ? getActiveConnection(toComp) : connectConnection(toComp);

        //if(connections.contains(toComp)){
            //System.out.println("Connection is already active\n" + connectionFromList(ip));
            //return connectionFromList(ip);
            //System.out.println("Connection is already active\n" + toComp);
            //return toComp;
        //}

        //return createConnection(ip, protocol);

    }


//    public Connection getConnection(String ip, int port){
//        if(connectionExists(ip)){
//            System.out.println("Connection is already active\n" + connectionFromList(ip));
//            return connectionFromList(ip);
//        }
//        return createConnection(ip, port);
//    }

    public Connection getConnection(String ip, int port){
        ManagedConnection toComp = new ManagedConnection(ip, port);
        return connections.contains(toComp) ? getActiveConnection(toComp) : connectConnection(toComp);
//        if(connections.contains(toComp)){
//            System.out.println("Connection is already active\n" + connectionFromList(ip));
//            return connectionFromList(ip);
//            System.out.println("Connection is already active\n" + toComp);
//            return toComp;
//       }
//        return createConnection(ip, port);
    }

    public Connection getConnection(String ip, String protocol, int port ){
        ManagedConnection toComp = new ManagedConnection(ip, protocol, port);
        return connections.contains(toComp) ? getActiveConnection(toComp) : connectConnection(toComp);
    }



//    private Connection connectionFromList(String ip){
//        for(Connection con : connections){
//            if(con.getIP().equalsIgnoreCase(ip)){
//                return con;
//            }
//        }
//        return null;
//    }
//
//    private boolean connectionExists(String ip){
//        for(Connection con : connections){
//            if(con.getIP().equalsIgnoreCase(ip)){
//                return true;
//            }
//        }
//        return false;
//    }

    private Connection getActiveConnection(Connection mc){
        if(isConnectionOpen(mc)){
            System.out.println("Connection is already active\n" + mc);
            return mc;
        }

        return openClosedConnection(mc);

    }

    private Connection openClosedConnection(Connection mc){
        connections.remove(mc);
        System.out.println("Reopening connection\n");
        ManagedConnection mcToOpen = (ManagedConnection)mc;
        return connectConnection(mcToOpen);
    }

    private boolean isConnectionOpen(Connection mc){
        int index = connections.indexOf(mc);
        ManagedConnection existingCon = (ManagedConnection)connections.get(index);
        return(existingCon.isOpen);
    }

    private Connection connectConnection(Connection mc){
        if (currentConnections >= maxConnections) {
            System.out.println("No available connections!");
            return null;
        }
        ManagedConnection mcToOpen = (ManagedConnection)mc;
        mcToOpen.setOpen(true);
        connections.add(mcToOpen);
        System.out.println(mcToOpen.connect());
        return mcToOpen;

    }

    public void closeConnection(Connection mc){
        System.out.println(mc.close());
    }


//    public Connection createConnection(String ip, String protocol){
//        if (currentConnections >= maxConnections){
//            System.out.println("No available connections!");
//            return null;
//        }
//        ManagedConnection toReturn = new ManagedConnection(ip, protocol);
//        connections.add(toReturn);
//        System.out.println(toReturn.connect());
//        return toReturn;
//    }
//
//    public Connection createConnection(String ip, int port){
//        if (currentConnections >= maxConnections){
//            System.out.println("No available connections!");
//            return null;
//        }
//        ManagedConnection toReturn = new ManagedConnection(ip, port);
//        connections.add(toReturn);
//        System.out.println(toReturn.connect());
//        return toReturn;
//
//    }

    public class ManagedConnection implements Connection {
        private final String ip;
        private final String protocol;
        private final int port;
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

        public ManagedConnection(String ip, String protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if(!ManagedConnection.class.isAssignableFrom(o.getClass())){
                return false;
            }
            //if (!(o instanceof ManagedConnection)) return false;
            ManagedConnection that = (ManagedConnection) o;
            if (this.port != that.port) return false;
            //commented out because I want to keep this field mutable
            //if (isOpen != that.isOpen) return false;


            //THIS CHECKS TO MAKE SURE THE VALUES AREN'T NULL BEFORE COMPARING THEM!
            //I DIDN'T WANT TO IMPLEMENT IT THIS WAY, BUT IT LOOKED WAY MORE CONFUSING
            //WHEN I TRIED TO IMPLEMENT IT DIFFERENTLY
            if ((this.ip == null) ? (that.ip != null) : !this.ip.equals(that.ip)) {
                return false;
            }

            if ((this.protocol == null) ? (that.protocol != null) : !this.protocol.equalsIgnoreCase(that.protocol)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = this.port;
            //THIS IS TO AVOID A NULL POINTER EXCEPTION
            result = 31 * result + (this.protocol != null ? protocol.hashCode() : 0);
            result = 31 * result + (this.ip != null ? ip.hashCode() : 0);
            //result = 31 * result + (isOpen ? 1 : 0); COMMENTED OUT BECAUSE ISOPEN IS A MUTABLE VALUE
            return result;
        }



        public String getIP(){

            return this.isOpen ? this.ip : "Error: Connection is Closed";
        }

        public String getProtocol(){
            return this.protocol;
        }

        public int getPort(){
            return this.isOpen ? this.port : -999999999;
        }

        public boolean getIsOpen(){
            return this.isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
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
            return this.connect();
        }





    }

}
