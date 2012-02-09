package Server.src;

import jargs.gnu.CmdLineParser;

public class CommandParser {
    
    CmdLineParser parser;
    CmdLineParser.Option port;
    CmdLineParser.Option routes;
    
    public String usage() {
        return "Usage: [-p --port port_number (default 3000)] [-r --routes route_file (default routes.txt)]";
    }
    
    public CommandParser(String[] args) {
        parser = new CmdLineParser();
        port = parser.addIntegerOption('p', "port");
        routes = parser.addStringOption('r', "routes");

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

}
