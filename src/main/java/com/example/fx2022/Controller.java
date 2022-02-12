package com.example.fx2022;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //лист для таблицы рекомендации
    private ObservableList<BookResult> bookresults = FXCollections.observableArrayList();
    //лист для таблицы классификации
    private ObservableList<Classifier> classifier = FXCollections.observableArrayList();
    static ArrayList<String> filenames = new ArrayList<String>();
    double class1;
    double class2;
    double class3;
    double class4;
    double class5;


    static ArrayList<File> filepath = new ArrayList<File>();
    int n;
    int numberR = 0;
    private Window primaryStage;

    //таблица для рекомендаций и её колонки
    @FXML
    TableView<BookResult> TableR;
    @FXML
    TableColumn<BookResult, Integer> number, percentage;
    @FXML
    TableColumn<BookResult, String> text, author;

    //таблица для классификации и её колонки
    @FXML
    TableView<Classifier> tableClassifier;
    @FXML
    TableColumn<Classifier, Integer> columnClass1, columnClass2,columnClass3,columnClass4, columnClass5;
    @FXML
    TableColumn<Classifier, String> columnFiles;

    File thisFile;

    @FXML
    TextField textK;
    @FXML
    TextField textD;
    //для округления - сколько чисел после запятой оставлять
    double scale = Math.pow(10, 2);

    @FXML
    Label choose1;
    @FXML
    Label choose2;
    @FXML
    Label choose3;
    @FXML
    Label choose4;
    @FXML
    Label choose5;
    @FXML
    Label labelNumUserFile;

    //юзер выбирает классы и их пути сохраняются в этот список
    ArrayList<String> pathDocClass = new ArrayList<>();
    //юзер выбирает докты для класции и добавляет в этот список
    ArrayList<String> pathDocUsers = new ArrayList<>();

    //юзер выбирает классы и их названия сохраняются в этот список
    ArrayList<String> namesDocClass = new ArrayList<>();
    //юзер выбирает докты для класции и добавляет в этот список их названия
    ArrayList<String> namesDocUsers = new ArrayList<>();


    //вызываться по нажатию кнопки Классифицировать
    //TODO РЕЗУЛЬТАТ В ТАБЛИЦУ!!!!
    //TODO получать названия файлов из пути
    //считывание pdf
    //график

    public void compareDocuments() {
        //TODO завести поля чтобы считывать k, d
        //удаляем все из старой таблицы (если что-то было)
        classifier.removeAll();
        //считываем к и д из полей
        int k = Integer.parseInt(textK.getText());
        int d = Integer.parseInt(textD.getText());

        //количество файлов классов (1-5)
        int n = pathDocClass.size();
        //массив файлов - классов
        Scheme[] sketches = new Scheme[n];

        //количество файлов с компа - считать колво выбранных файлов.
        int n1 = pathDocUsers.size();
        //массив файлов с компа
        Scheme[] mySketches = new Scheme[n1];

        //считывание документов - классов
        for(int i =0;i<n;i++){
            In in = new In(pathDocClass.get(i));
            String text = in.readAll();
            sketches[i] = new Scheme(text,k,d);
        }

        //считывание документов с компа для класции
        for(int i =0;i<n1;i++){
            In in = new In(pathDocUsers.get(i));
            String text = in.readAll();
            mySketches[i] = new Scheme(text,k,d);
        }

        StdOut.print("    ");
        //для консоли вывод
        for(int i = 0;i<n;i++){
            StdOut.printf("%8.4s", pathDocClass.get(i));
        }
        StdOut.println();

        //очистка предыщих данных, которые были в таблице
        for ( int i = 0; i<tableClassifier.getItems().size(); i++) {
            tableClassifier.getItems().clear();
        }

        for(int i = 0;i<n1;i++){ //файлы с компа, кот надо распределить
            String name = namesDocUsers.get(i);
            for(int j = 0;j<n;j++){//файлы  -образцы-классы для классификации
                System.out.println(j);
                //сравнение- косинусные расстояния числа!
                if(j==0){  //добавляем значение в переменную нужного класса и округляем до 2-ух знаков после запятой
                    class1 = sketches[j].similarTo(mySketches[i]);
                    //class1 = Math.ceil(class1 * scale) / scale;
                }
                if(j==1){
                    class2 = sketches[j].similarTo(mySketches[i]);
                    //class2= Math.ceil(class2 * scale) / scale;
                }
                if(j==2){
                    class3 = sketches[j].similarTo(mySketches[i]);
                    //class3 = Math.ceil(class3 * scale) / scale;
                }
                if(j==3){
                    class4 = sketches[j].similarTo(mySketches[i]);
                    //class4 = Math.ceil(class4 * scale) / scale;
                }
                if(j==4){
                    class5 = sketches[j].similarTo(mySketches[i]);
                    //class5 = Math.ceil(class5 * scale) / scale;
                }
            }
            //добавляем данные в таблицу классификатора
            classifier.add(new Classifier(name,class1,class2,class3,class4, class5));
            StdOut.println();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public File upload() { //загрузка файлов
        FileChooser fileChooser = new FileChooser();

        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);

        return file;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //получения путей к файлам, кот юзер хочет классифицировать Выбрать файлы
    public void listFilenames(ActionEvent actionEvent) {
        thisFile =  null;
        //загружаем файл
        thisFile = upload();
        //имя файла
        namesDocUsers.add(thisFile.getName());
        //путь к файлу
        pathDocUsers.add(thisFile.getAbsolutePath());
        labelNumUserFile.setText(""+ pathDocUsers.size());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //инициализация данных, которые будут в таблицах
        number.setCellValueFactory(new PropertyValueFactory<>("Id"));
        text.setCellValueFactory(new PropertyValueFactory<>("Name"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("Percentage"));
        //таблица для рекомендаций
        TableR.setItems(bookresults);

        columnFiles.setCellValueFactory(new PropertyValueFactory<>("Files"));
        columnClass1.setCellValueFactory(new PropertyValueFactory<>("Class1"));
        columnClass2.setCellValueFactory(new PropertyValueFactory<>("Class2"));
        columnClass3.setCellValueFactory(new PropertyValueFactory<>("Class3"));
        columnClass4.setCellValueFactory(new PropertyValueFactory<>("Class4"));
        columnClass5.setCellValueFactory(new PropertyValueFactory<>("Class5"));
        //таблица для классификатора
        tableClassifier.setItems(classifier);
    }

    public void Recomend(ActionEvent actionEvent) {
        bookresults.removeAll();
        compareDocuments();
        numberR = numberR + 1;
        bookresults.add(new BookResult(numberR,"Идиот","Достоевский",80));
    }

    //загружаем файлы (классы - примеры документов)
    public void choose1(ActionEvent actionEvent) {
        thisFile =  null;
        thisFile = upload();
        choose1.setText(thisFile.getName());
        columnClass1.setText(thisFile.getName());
        pathDocClass.add(thisFile.getAbsolutePath());
    }

    public void choose2(ActionEvent actionEvent) {
        thisFile =  null;
        thisFile = upload();
        choose2.setText(thisFile.getName());
        columnClass2.setText(thisFile.getName());
        pathDocClass.add(thisFile.getAbsolutePath());
    }

    public void choose3(ActionEvent actionEvent) {
        thisFile =  null;
        thisFile = upload();
        choose3.setText(thisFile.getName());
        columnClass3.setText(thisFile.getName());
        pathDocClass.add(thisFile.getAbsolutePath());
    }

    public void choose4(ActionEvent actionEvent) {
        thisFile =  null;
        thisFile = upload();
        choose4.setText(thisFile.getName());
        columnClass4.setText(thisFile.getName());
        pathDocClass.add(thisFile.getAbsolutePath());
    }

    public void choose5(ActionEvent actionEvent) {
        thisFile =  null;
        thisFile = upload();
        choose5.setText(thisFile.getName());
        columnClass5.setText(thisFile.getName());
        pathDocClass.add(thisFile.getAbsolutePath());
    }
}