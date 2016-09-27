package InnerClassesLab;

/**
 * Created by evanhitchings on 9/27/16.
 */
public interface Connection {
    String getIP();
    String getProtocol();
    int getPort();
    String connect();
    String close();
}
