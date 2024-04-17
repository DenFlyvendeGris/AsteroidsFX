import dk.sdu.mmmi.cbse.astroidsystem.AsteroidControls;
import dk.sdu.mmmi.cbse.astroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Astroids {
    requires Common;
    requires javafx.graphics;
    exports dk.sdu.mmmi.cbse.astroidsystem;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidControls;
}