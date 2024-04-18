package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        AstroidSplitterLogic.setWorld(world);
        AstroidSplitterLogic.setGameData(gameData);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
