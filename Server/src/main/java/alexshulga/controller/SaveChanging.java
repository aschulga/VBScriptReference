package alexshulga.controller;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Properties;

public class SaveChanging {

    private Controller controller;
    private File file;

    public SaveChanging(Controller controller, File file)
    {
        this.file = file;
        this.controller = controller;
    }

    public void save(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        org.w3c.dom.Document document = documentBuilder.newDocument();

        Element allNotations = document.createElement("allNotations");

        document.appendChild(allNotations);

        for(int i = 0; i < controller.getListNotations().size(); i++) {
            Element notation = document.createElement("notation");
            allNotations.appendChild(notation);

            String str = controller.getListNotations().get(i).getType();
            Element type = document.createElement("type");
            type.appendChild(document.createTextNode(str));
            notation.appendChild(type);

            str = controller.getListNotations().get(i).getKind().getKind();
            Element kind = document.createElement("kind");
            kind.appendChild(document.createTextNode(str));
            notation.appendChild(kind);

            str = controller.getListNotations().get(i).getSubtype();
            Element subtype = document.createElement("subtype");
            subtype.appendChild(document.createTextNode(str));
            notation.appendChild(subtype);

            str = String.valueOf(controller.getListNotations().get(i).getDescription());
            Element description = document.createElement("description");
            description.appendChild(document.createTextNode(str));
            notation.appendChild(description);

            TransformerFactory factory1 = TransformerFactory.newInstance();
            try {
                Transformer transformer = factory1.newTransformer();

                Properties outFormat = new Properties();
                outFormat.setProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperties(outFormat);

                DOMSource domSource = new DOMSource(document);
                StreamResult streamFile = new StreamResult(file);
                transformer.transform(domSource, streamFile);

            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }
}
