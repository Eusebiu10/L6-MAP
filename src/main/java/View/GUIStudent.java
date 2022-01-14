package View;

import Controller.Controller;
import Controller.ExistentIdException;
import Controller.MaxSizeException;
import Controller.MissingIdException;
import Main.Model.Student;
import Repo.CRepo;
import Repo.SRepo;
import Repo.TRepo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

class GUIStudentView extends Application {

    Controller controller;
    Student userStudent;

    public static void main(String[] args){
        launch();
    }

    /**
     * Setup and functionality for Student GUI
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        String connURL="jdbc:mysql://localhost:3306/map";
        String connUser="root";
        String connPassword="eusebiu";
        TRepo tr = new TRepo(connURL,connUser,connPassword);
        CRepo cr = new CRepo(connURL,connUser,connPassword);
        SRepo sr = new SRepo(connURL,connUser,connPassword);

        controller = new Controller(cr,tr,sr);


        GridPane layoutLogin = new GridPane();
        Scene sceneLogin = new Scene(layoutLogin,250,80);
        primaryStage.setScene(sceneLogin);
        layoutLogin.setHgap(10);
        layoutLogin.setVgap(10);
        primaryStage.setTitle("Student Manager");
        Label loginLabel = new Label("Student Id:");
        TextField loginIdField = new TextField();
        Button buttonLogin = new Button();
        buttonLogin.setText("Login");
        layoutLogin.add(loginLabel,1,1);
        layoutLogin.add(loginIdField,2,1);
        layoutLogin.add(buttonLogin,1,2);
        GridPane layoutStudent = new GridPane();
        layoutStudent.setHgap(10);
        layoutStudent.setVgap(10);
        Button buttonRegister = new Button();
        buttonRegister.setText("Register");
        TextField fieldRegister = new TextField();
        layoutStudent.add(fieldRegister,1,2);
        layoutStudent.add(buttonRegister,2,2);



 Scene sceneStudent = new Scene(layoutStudent,430,70);
 buttonLogin.setOnAction(e-> {
 try {
 userStudent = controller.getStudent(parseInt(loginIdField.getText()));
 } catch (Exception ex) {
 userStudent = null;
 }
 if(userStudent!=null){
 Label userInfo = new Label("Student Id: "+userStudent.getStudentId()+" First Name: "+userStudent.getFirstName()+" Last Name: " + userStudent.getLastName());
 Label userCredits = new Label("Student Credits: "+ userStudent.getTotalCredits());
 layoutStudent.add(userInfo,1,1);
 layoutStudent.add(userCredits,2,1);
 buttonRegister.setOnAction(e2-> {
 try {
 controller.registerStudent(userStudent.getStudentId(),parseInt(fieldRegister.getText()));
 } catch (MissingIdException | MaxSizeException | SQLException | ExistentIdException ex) {
 ex.printStackTrace();
 }
 try {
 userStudent=controller.getStudent(userStudent.getStudentId());
 } catch (SQLException ex) {
 ex.printStackTrace();
 }
 userCredits.setText("Student Credits: "+ userStudent.getTotalCredits());});
 primaryStage.setScene(sceneStudent);}
 });
 primaryStage.show();

    }
}