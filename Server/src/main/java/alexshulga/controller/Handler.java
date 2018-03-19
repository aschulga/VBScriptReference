package alexshulga.controller;

import alexshulga.kind.Kind;
import alexshulga.notation.Notation;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    private String element = "";
    private Notation notation;
    private Controller controller;
    private boolean bType = false;
    private boolean bKind = false;
    private boolean bSubtype = false;
    private boolean bDescription = false;

    public Handler(Controller controller){
        this.controller = controller;
    }

    public void startElement(String namespace, String localname, String qName, Attributes attributes)
    {
        if (qName.equals("notation")){
            notation = new Notation();
        }
        if(qName.equals("type")) {
            bType = true;
        }
        if(qName.equals("kind")) {
            bKind = true;
        }
        if(qName.equals("subtype")) {
            bSubtype = true;
        }
        if(qName.equals("description")) {
            bDescription = true;
        }
    }

    public void endElement(String namespace, String localname, String qName)
    {
        element = "";
        if(qName.equals("notation")) {
            controller.addNotation(notation);
        }
    }

    public void characters(char []ch, int start, int end)
    {
        if(bType)
        {
            notation.setType(new String(ch, start, end));
            bType = false;
        }
        else if(bKind)
        {
            Kind kind = new Kind();
            notation.setKind(kind);
            notation.getKind().setKind((new String(ch, start, end)));
            bKind = false;
        }
        else if(bSubtype)
        {
            notation.setSubtype(new String(ch, start, end));
            bSubtype = false;
        }
        else if(bDescription)
        {
            notation.setDescription(new String(ch, start, end));
            bDescription = false;
        }
    }
}

