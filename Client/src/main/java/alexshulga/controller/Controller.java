package alexshulga.controller;

import alexshulga.notation.Notation;
import alexshulga.services.NotationService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class Controller {

    private TTransport transport;
    private NotationService.Client client;

    public void connect() throws TException {
        transport = new TSocket("localhost", 8000);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        client = new NotationService.Client(protocol);
    }

    public void close(){
        transport.close();
    }

    public List<Notation> getAllNotations() throws TException {
        return client.getAllNotations();
    }

    public void addNotation(Notation notation) throws TException {
        client.addNotation(notation);
    }

    public void deleteNotation(String number) throws TException {
        client.deleteNotation(Integer.parseInt(number));
    }

    public void changeNotation(int index, Notation notation) throws TException {
        client.changeNotation(index, notation);
    }

    public void saveChanging() throws TException {
        client.saveChanging();
    }
}
