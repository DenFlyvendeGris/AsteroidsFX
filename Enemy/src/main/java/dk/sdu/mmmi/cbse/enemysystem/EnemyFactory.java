package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class EnemyFactory {

    public Enemy createEnemies(GameData gameData) {
        Enemy enemy = new Enemy();
        enemy.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemy.setY(gameData.getDisplayWidth() * Math.random());
        enemy.setX(gameData.getDisplayHeight() * Math.random());
        enemy.setRadius(20);
        return enemy;
    }
}
