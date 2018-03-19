package alexshulga;

import alexshulga.controller.Controller;
import alexshulga.controller.NotationsHandler;
import alexshulga.model.BaseNotations;
import alexshulga.services.NotationService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {

    private static final int PORT = 8000;

    public static NotationsHandler handler;
    public static NotationService.Processor processor;

    public static void main(String[] args) {

        BaseNotations base = new BaseNotations();
        Controller controller = new Controller(base);
        controller.fillDataBase();

        try {
            handler = new NotationsHandler(controller);
            processor = new NotationService.Processor(handler);

            Runnable simple = () -> perform(processor);

            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void perform(NotationService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(PORT);
            TServer server = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(processor)
            );

            System.out.println("Server start");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
