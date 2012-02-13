<center>
# HT(TPS) Report

<img src="http://cf.drafthouse.com/_uploads/galleries/2164/office-space-copier.jpg" />

## Damn It Feels Good To Be A Gangsta
</center>

HTTPSreport is an HTTP Server written in Java. It allows applications to plug in through the Client interface and be served HTTP requests.

# Architecture
### Receptionist
The Receptionist sits at the highest level and contains the main() method for the server. The Receptionist is also the holder of the ServerSocket and will generate a new thread for each connection. These connections are then passed to the Executive.

###Executive
The executive is responsible for each Socket connection to the server. Each Executive will run on their own thread. The Executive will invoke the Analyst to generate a Memo for the incoming connection. The Executive will also send the Memo back to the user who generated the HTTP request. At the end of execution the Executive will close the Socket and the Executive's thread will die.

###Analyst
The Analyst uses the Socket's InputStream to construct a usable Memo. The Analysts works only for the Executive.

###Memo
The Memo is a data structure that encapsalates an HTTP packet.
Three special fields are contained on the memo: "Verb", "Path", and "Version" which are special cases for the first line of an incoming HTTP Packet.

###Marketer
The Marketer will call the Route to retrieve an instance of Client to invoke. It will then pass the Memo object down to the Client and await its response. When the response is given from the Client it passes it back up to the Executive.

###Route
The Route will hold all of the routes specified. When a certain route is invoked it will create an instance of a Client for the Marketer to use.

###Client
Client is the abstract interface used by an applications which wishes to use HTTPSreport. A Client will receive its Memo from the Marketer during the execute call. When the Client has finished it will invoke the Marketer and pass along the return Memo it has generated.

# Usage
### Creating a Client
Applications using HTTPSreport must make use of the Client Abstract Class provided. The package location of the Client Abstract Class is Server.src.Client.  Clients are required to implement one public method: execute().

execute will take a Marketer instance and an inMemo as its paremeters. The Marketer instance is important because this is the instance the Client must contact after it has run to completion.

contactMarketer is inherited from the Client base class. This will take Marketer and Memo as parameters. The Marketer is the instance to be contacted (given to the Client by the execute method) and the Memo is the memo to pass back up. Marketer contains a method named clientResponse() which takes a Memo as a parameter. This is how a client contacts their Marketer.

### Example Client
``` java
package Implementer.example;

import Server.src.Client;
import Server.src.Marketer;
import Server.src.Memo;

public class AppClient extends Client {

   private Memo inMemo;
   private Memo outMemo;

   public void execute(Marketer marketer, Memo _inMemo) {
      inMemo = _inMemo;
      if(inMemo.getField("Verb").equals("GET")) {
         outMemo = new Memo(200, "HTML", "<html>Hello World!</html>"); // Outgoing Memo
      } else {
         outMemo = new Memo(501, "HTML", "<html>I don't know how to do that!</html>");
      }
      contactMarketer(marketer, outMemo);
   }

}
```

### Creating a Routes File
The routes file is a plaintext file for defining routes. Any route not found on the list will generate a 404 Not Found response. The syntax is simple one route per line with the verb in all caps first followed by the path. For example: <br>
GET / <br>
POST /post/here <br>
PUT /example <br>
DELETE /model <br>
HEAD / <br>

### Starting the Server
The httpsreport jar is an executable jar. There are four parameters to pass to the jar upon execution to link up the server correctly <br>
[-p | --port] specifies the port number for the server to run on <br>
[-r | --routes] an absolute path to the route text file for defined routes <br>
[-j | --jar] an absolute path to your project's jar <br>
[-c | --class] the package and class name of your Client implementation <br>

example: java -jar httpsreport.jar -p 3000 -r /path/to/routes.txt -j /path/to/myapp.jar -c example.package.Client
