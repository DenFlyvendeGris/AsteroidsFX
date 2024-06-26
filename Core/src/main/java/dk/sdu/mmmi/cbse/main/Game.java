package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();

    private Pane gameWindow;
    private int currentEntityAmount;
    private Text text = new Text(10, 20, "Destroyed asteroids: 0");

    private List<IGamePluginService> iGamePluginServices;
    private List<IEntityProcessingService> iEntityProcessingServices;
    private List<IPostEntityProcessingService> iPostEntityProcessingServices;

    public Game(List<IGamePluginService> iGamePluginServices, List<IEntityProcessingService> iEntityProcessingServices, List<IPostEntityProcessingService> iPostEntityProcessingServices) {
        this.iGamePluginServices = iGamePluginServices;
        this.iEntityProcessingServices = iEntityProcessingServices;
        this.iPostEntityProcessingServices = iPostEntityProcessingServices;
    }


    public void start(Stage window) throws Exception {
        timer.scheduleAtFixedRate(task, 0, 500);
        gameWindow = new Pane();
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.DOWN)) {
                gameData.getKeys().setKey(GameKeys.REVERSE, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.DOWN)) {
                gameData.getKeys().setKey(GameKeys.REVERSE, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

    }

    public void render() {
        new AnimationTimer() {
            private long then = System.nanoTime();

            @Override
            public void handle(long now) {
                long deltaTime = (now - then) / 1000000;
                then = now;
                update(deltaTime);
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update(long deltaTime) {

        gameData.setDeltaTime(deltaTime);

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessingService : getPostEntityProcessingServices()) {
            postEntityProcessingService.process(gameData, world);
        }

        List<Entity> entityListDelete = new ArrayList<>();
        for (Entity entity : world.getEntities()) {
            if (entity.isDestroyed()) {
                entityListDelete.add(entity);
            }
        }
        for (Entity entity : entityListDelete) {
            gameWindow.getChildren().remove(polygons.get(entity));
            polygons.remove(entity);
            world.removeEntity(entity);
        }

        currentEntityAmount = world.getEntities().size();
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());

            //TODO move this to individual entityProcessors
            //List<Entity> removeBullet = new ArrayList<>();
            //if (entity.getY() > gameData.getDisplayHeight() || entity.getX() > gameData.getDisplayWidth() || entity.getX() < 0 || entity.getY() < 0) {
            //    removeBullet.add(entity);
            //}
        }
    }

    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            scoringSystemText();
        }
    };

    HttpClient textHTTP = HttpClient.newHttpClient();
    private void scoringSystemText() {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/points/mypoints")).GET().build();
        try {
            HttpResponse<String> httpResponse = textHTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            text.setText("score: " + httpResponse.body());
        } catch (IOException | InterruptedException exception) {
            exception.getStackTrace();
        }
    }



    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
