package InnerClassesLab;

/**
 * Created by evanhitchings on 9/27/16.
 */
public class ConnectionManager {






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


        public String getIP(){
            return this.ip;
        }

        public String getProtocol(){
            return this.protocol;
        }

        public int getPort(){
            return this.port;
        }

        public String connect(){
            return null;
        }

        public String close(){
            return null;
        }





    }

}
