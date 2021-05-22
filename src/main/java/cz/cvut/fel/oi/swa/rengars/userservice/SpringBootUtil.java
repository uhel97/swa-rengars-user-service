package cz.cvut.fel.oi.swa.rengars.userservice;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

public class SpringBootUtil {

    final static Logger log = LoggerFactory.getLogger(SpringBootUtil.class);

    public static void setPort(String[] args, int minPort, int maxPort) {

        try {

            Options options = new Options();
            options.addOption("p", "port", true, "Port to listen to.");

            CommandLineParser parser = new BasicParser();
            CommandLine cmd = parser.parse(options, args);

            String userDefinedPort = cmd.getOptionValue("port");

            if (StringUtils.isEmpty(userDefinedPort)) {

                userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
            }

            if (StringUtils.isEmpty(userDefinedPort)) {

                int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                System.setProperty("server.port", String.valueOf(port));
                log.info("Random Server Port is set to {}.", port);

            } else {

                System.setProperty("server.port", userDefinedPort);
                log.info("Server Port is set to {}.", userDefinedPort);
            }

        } catch(IllegalStateException | ParseException e) {

            log.warn("No port available in range {}-{}. Default embedded server configuration will be used.", minPort, maxPort);
        }

    }

}
