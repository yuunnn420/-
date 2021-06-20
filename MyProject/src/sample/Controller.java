package sample;

import com.sun.scenario.effect.impl.state.AccessHelper;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.EventHandler;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Controller extends Thread {

    int tube = 75;
    int size = 8;
    @FXML
    private GridPane gridpane;
    @FXML
    private Pane pane;
    Random rand = new Random();
    /*int[][] map = new int[][]{
            {1, 4, 3, 2, 5, 4, 3, 2},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 4, 1, 1, 1, 3},
            {1, 1, 5, 1, 1, 1, 3, 1},
            {1, 1, 1, 1, 1, 2, 1, 1},
            {5, 1, 2, 1, 3, 1, 1, 1},
            {3, 1, 1, 3, 1, 1, 1, 1},
            };
    int[][] map2 = new int[][]{
            {1, 4, 3, 2, 5, 4, 3, 2},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 4, 1, 1, 1, 3},
            {1, 1, 5, 1, 1, 1, 3, 1},
            {1, 1, 1, 1, 1, 2, 1, 1},
            {5, 1, 2, 1, 3, 1, 1, 1},
            {3, 1, 1, 3, 1, 1, 1, 1},
    };*/
    int[][] map = new int[size][size];
    int[][] map2 = new int[size][size];
    int[][][] map3 = new int[99999][size][size];
    int[][] click = new int[size][size];
    Button[][] button = new Button[size][size];
    ImageView[][] imageView = new ImageView[size][size];
    Image zeroImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/white.png")), tube, tube, false, false);
    Image firstImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Flareon.png")), tube, tube, false, false);
    Image secondImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Vaporeon.png")), tube, tube, false, false);
    Image thirdImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Jolteon.png")), tube, tube, false, false);
    Image fourthImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Espeon.png")), tube, tube, false, false);
    Image fifthImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Umbreon.png")), tube, tube, false, false);
    public int i = 0 ,j = 0 ;
    public int clickTimes = 0;
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public int i1,j1,i2,j2;
    int x,y;
    boolean flag = true;
    static boolean draw = false;
    public void printMap2(){
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                System.out.print(map2[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void isZero(){
        if(map2[x][y]==0){
            if(x>0){
                x-=1;
                isZero();
            }
            else if(x==0){
                x-=1;
            }
        }
    }
    /*public void run() {
        while(true){
                System.out.println("hi");
                if(draw){
                    System.out.println("map2");
                    drawMap2();
                    draw=false;
                }
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //draw=false;
            }
    }*/
    boolean isChange = false;
    int count3 = 0;
    int c=0;
    public void anime(){
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(1000), new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                    drawMap3(c);
                    c+=1;
                    /*if(c==count3){
                        timeline.stop();
                        drawMap2();
                    }*/
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(count3-1);
        timeline.play();
    }
    public void animation () {
        flag = true;
        if(isChange){
            int t = map[i1][j1];
            map[i1][j1] = map[i2][j2];
            map[i2][j2] = t;
            t = map2[i1][j1];
            map2[i1][j1] = map2[i2][j2];
            map2[i2][j2] = t;
        }
        int count = 1;
        int a = -1;
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                if (j == 0) {
                    a = map[i][j];
                    count = 1;
                } else if (a == map[i][j]) {
                    count++;
                    if (count == 3) {
                        for (int k = 0; k < count; k++) {
                            map2[i][j - k] = 0;
                            flag = false;
                        }
                    }
                    if (count > 3) {
                        map2[i][j] = 0;
                        flag = false;
                    }
                } else {
                    a = map[i][j];
                    count = 1;
                }
            }
        }
        a = -1;
        count = 1;
        for (int j = 0; j < map[0].length; j++) {//w
            for (int i = 0; i < map.length; i++) {//h
                if (i == 0) {
                    a = map[i][j];
                    count = 1;
                } else if (a == map[i][j]) {
                    count++;
                    if (count == 3) {
                        for (int k = 0; k < count; k++) {
                            map2[i - k][j] = 0;
                            flag = false;
                        }
                    }
                    if (count > 3) {
                        map2[i][j] = 0;
                        flag = false;
                    }
                } else {
                    a = map[i][j];
                    count = 1;
                }
            }
        }
        if(isChange) {
            int t = map[i1][j1];
            map[i1][j1] = map[i2][j2];
            map[i2][j2] = t;
            isChange=false;
            if(flag){
                t = map2[i1][j1];
                map2[i1][j1] = map2[i2][j2];
                map2[i2][j2] = t;
            }
        }
        if (flag==false){
            for (int j = 0; j < size; j++) {//w
                for (int i = size-1; i >= 0; i--) {//h
                    if (map2[i][j] == 0) {
                        x = i;
                        y = j;
                        isZero();
                        if (x != -1) {
                            map2[i][j] = map2[x][y];
                            map2[x][y] = 0;
                        }
                    }
                }
            }
        //map3=map2
        for (int i = 0; i < size; i++) {//h
            for (int j = 0; j < size; j++) {//w
                map3[count3][i][j] = map2[i][j];
            }
        }
        System.out.println("map3 "+count3);
        for (int i = 0; i < size; i++) {//h
            for (int j = 0; j < size; j++) {//w
                System.out.print(map3[count3][i][j]);
            }
            System.out.println();
        }
        System.out.println();
        count3+=1;
            //drawMap2();
            for (int i = 0; i < map.length; i++) {//h
                for (int j = 0; j < map[0].length; j++) {//w
                    if (map2[i][j] == 0) {
                        map2[i][j] = rand.nextInt(5) + 1;//1~5
                    }
                }
            }
            for (int i = 0; i < size; i++) {//h
                for (int j = 0; j < size; j++) {//w
                    map3[count3][i][j] = map2[i][j];
                }
            }
            System.out.println("map3 "+count3);
            for (int i = 0; i < size; i++) {//h
                for (int j = 0; j < size; j++) {//w
                    System.out.print(map3[count3][i][j]);
                }
                System.out.println();
            }
            System.out.println();
            count3+=1;
            printMap2();
            //drawMap2();
            for (int i = 0; i < size; i++) {//h
                for (int j = 0; j < size; j++) {//w
                    map[i][j] = map2[i][j];
                }
            }
            animation();
        }
        anime();
    }
    public boolean change () {
        if (clickTimes == 2) {
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    button[i][j].setStyle("-fx-background-color: transparent ;-fx-border-color: white;");
                }
            }
        }
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length - 1; j++) {//w
                if (click[i][j] == 1 && click[i][j + 1] == 1) {
                    System.out.println("change 1");
                    click[i][j] = 0;
                    click[i][j + 1] = 0;
                    i1 = i;
                    j1 = j;
                    i2 = i;
                    j2 = j + 1;
                    clickTimes = 0;
                    isChange=true;
                    return true;
                }
            }
        }
        for (int i = 0; i < map[0].length; i++) {//w
            for (int j = 0; j < map.length - 1; j++) {//h
                if (click[j][i] == 1 && click[j + 1][i] == 1) {
                    System.out.println("change 2");
                    click[j][i] = 0;
                    click[j + 1][i] = 0;
                    i1 = j;
                    j1 = i;
                    i2 = j + 1;
                    j2 = i;
                    clickTimes = 0;
                    isChange=true;
                    return true;
                }
            }
        }
        if (clickTimes == 2) {
            System.out.println("error");
            clickTimes = 0;
            for (int[] row : click)
                Arrays.fill(row, 0);
            return false;
        }
        return false;
    }
    public void action () {
        for (int i = 0; i < size; i++) {//h
            for (int j = 0; j < size; j++) {//w
                this.i = i;
                this.j = j;
                button[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    int i = getI();
                    int j = getJ();
                    @Override
                    public void handle(ActionEvent e) {
                        button[i][j].setStyle("-fx-background-color: transparent ;-fx-border-color: blue;");
                        System.out.println(i + " " + j);
                        click[i][j] = 1;
                        clickTimes += 1;
                        if (change()) {
                            animation();
                        }
                    }
                });
            }
        }
    }
    public void drawMap () {
        for (int i = 0; i < size; i++) {//h
            for (int j = 0; j < size; j++) {//w
                map[i][j] = rand.nextInt(5) + 1;//1~5
                map2[i][j] = map[i][j];
            }
        }
        System.out.println("map1");
        for (int i = 0; i < size; i++) {//h
            for (int j = 0; j < size; j++) {//w
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                switch (map[i][j]) {
                    case 0:
                        imageView[i][j] = new ImageView(zeroImage);
                        break;
                    case 1:
                        imageView[i][j] = new ImageView(firstImage);
                        break;
                    case 2:
                        imageView[i][j] = new ImageView(secondImage);
                        break;
                    case 3:
                        imageView[i][j] = new ImageView(thirdImage);
                        break;
                    case 4:
                        imageView[i][j] = new ImageView(fourthImage);
                        break;
                    case 5:
                        imageView[i][j] = new ImageView(fifthImage);
                        break;
                    default:
                        break;
                }
                GridPane.setConstraints(imageView[i][j], j, i);
                gridpane.getChildren().add(imageView[i][j]);
            }
        }
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                button[i][j] = new Button();
                button[i][j].setPrefSize(tube, tube);
                gridpane.add(button[i][j], j, i);
                button[i][j].setStyle("-fx-background-color: transparent ;-fx-border-color: white;");//rgba(255, 255, 255, .5)
            }
        }
        animation();
        action();
    }
    public synchronized void drawMap2 () {
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                //if (map2[i][j] != map[i][j]) {
                    gridpane.getChildren().remove(imageView[i][j]);
                    gridpane.getChildren().remove(button[i][j]);
                    switch (map2[i][j]) {
                        case 0:
                            imageView[i][j] = new ImageView(zeroImage);
                            break;
                        case 1:
                            imageView[i][j] = new ImageView(firstImage);
                            break;
                        case 2:
                            imageView[i][j] = new ImageView(secondImage);
                            break;
                        case 3:
                            imageView[i][j] = new ImageView(thirdImage);
                            break;
                        case 4:
                            imageView[i][j] = new ImageView(fourthImage);
                            break;
                        case 5:
                            imageView[i][j] = new ImageView(fifthImage);
                            break;
                        default:
                            break;
                    }
                    GridPane.setConstraints(imageView[i][j], j, i);
                    gridpane.getChildren().add(imageView[i][j]);
                    gridpane.add(button[i][j], j, i);
                //}
            }
        }
    }
    public synchronized void drawMap3 (int c) {
        for (int i = 0; i < map.length; i++) {//h
            for (int j = 0; j < map[0].length; j++) {//w
                //if (map2[i][j] != map[i][j]) {
                gridpane.getChildren().remove(imageView[i][j]);
                gridpane.getChildren().remove(button[i][j]);
                switch (map3[c][i][j]) {
                    case 0:
                        imageView[i][j] = new ImageView(zeroImage);
                        break;
                    case 1:
                        imageView[i][j] = new ImageView(firstImage);
                        break;
                    case 2:
                        imageView[i][j] = new ImageView(secondImage);
                        break;
                    case 3:
                        imageView[i][j] = new ImageView(thirdImage);
                        break;
                    case 4:
                        imageView[i][j] = new ImageView(fourthImage);
                        break;
                    case 5:
                        imageView[i][j] = new ImageView(fifthImage);
                        break;
                    default:
                        break;
                }
                GridPane.setConstraints(imageView[i][j], j, i);
                gridpane.getChildren().add(imageView[i][j]);
                gridpane.add(button[i][j], j, i);
                //}
            }
        }
    }
}

/*@Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView imageView = new ImageView(new Image("/image/Eevee.png"));
        imageView.setPickOnBounds(true);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("This is checkmark");
                a.show();
            }
        });
        gridpane.getChildren().add(imageView);
    }*/