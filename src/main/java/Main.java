import Controller.Controller;

import Repo.*;
import View.consol;


public class Main {


    public static void main(String[] args) {

        String connURL="jdbc:mysql://localhost:3306/map";
        String connUser="root";
        String connPassword="eusebiu";
        TRepo tr = new TRepo(connURL,connUser,connPassword);
        CRepo cr = new CRepo(connURL,connUser,connPassword);
        SRepo sr = new SRepo(connURL,connUser,connPassword);

        Controller cont = new Controller(cr,tr,sr);
        consol cw = new consol(cont);
        cw.Run();


    }
}