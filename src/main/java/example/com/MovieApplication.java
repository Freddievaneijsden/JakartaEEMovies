package example.com;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
public class MovieApplication extends Application {
    // Needed to enable Jakarta REST and specify path
}
