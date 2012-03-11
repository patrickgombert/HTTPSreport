package Server.src;

import jargs.gnu.CmdLineParser;

public class CommandParser {
    
    CmdLineParser parser;
    CmdLineParser.Option port;
    CmdLineParser.Option routes;
    CmdLineParser.Option jar;
    
    public String usage() {
        return "Usage: [-p --port port_number (default 3000)]" +
                      "[-r --routes route_file (default routes.txt)]" +
                      "[-j --jar jar_directory]";
    }
    
    public CommandParser(String[] args) {
        parser = new CmdLineParser();
        port = parser.addIntegerOption('p', "port");
        routes = parser.addStringOption('r', "routes");
        jar = parser.addStringOption('j', "jar");

        try {
            parser.parse(args);
        } catch(CmdLineParser.OptionException e) {
            System.out.println(usage());
            System.exit(0);
        }
    }
    
    public int getPortNumber() {
        return (Integer)parser.getOptionValue(port, new Integer(3000));
    }
    
    public String getRouteFilePath() {
        return (String)parser.getOptionValue(routes, new String("routes.txt"));
    }
    
    public String getJarDirectory() {
        return (String)parser.getOptionValue(jar);
    }

}
