package alexshulga.controller;

import alexshulga.notation.Notation;
import alexshulga.services.NotationService;

import java.io.File;
import java.util.List;

public class NotationsHandler implements NotationService.Iface {

    private Controller controller;
    private final static String FILE_NAME = "fileWithData/newChanging.xml";

    public NotationsHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public List<Notation> getAllNotations(){

        for(int i = 0; i < controller.getListNotations().size(); i++){
            controller.getListNotations().get(i).setId(i+1);
        }
        return controller.getListNotations();
    }

    @Override
    public void addNotation(Notation notation){
        controller.getListNotations().add(notation);
    }

    @Override
    public void deleteNotation(int index){
        int number = index - 1;
        controller.getListNotations().remove(number);
    }

    @Override
    public void changeNotation(int index, Notation notation) {
        int number = index - 1;
        controller.getListNotations().remove(number);
        controller.getListNotations().add(number, notation);
    }

    @Override
    public void saveChanging() {
        SaveChanging saveStudentDialog = new SaveChanging(controller, new File(FILE_NAME));
        saveStudentDialog.save();
    }
}
