import java.io.IOException;
import java.util.Properties;

public class Utils {

    private Properties props;

    Utils() throws IOException {
        props = new Properties();
        initProps();
    }

    private void initProps() throws IOException {
        props.load(this.getClass().getClassLoader().getResourceAsStream("prop.properties"));
    }

    public String getEndpoint() {
        return props.getProperty("endpoint");
    }
}
