package fr.heriamc.games.shootcraft.setting;

import fr.heriamc.api.game.GameState;
import fr.heriamc.games.engine.map.GameMapManager;
import fr.heriamc.games.engine.map.slime.SlimeGameMap;
import fr.heriamc.games.engine.map.slime.SlimeMap;
import fr.heriamc.games.engine.map.slime.SlimeWorldLoader;
import fr.heriamc.games.shootcraft.ShootCraftGame;

public class ShootCraftMapManager extends GameMapManager<ShootCraftGame, SlimeMap, SlimeWorldLoader> {

    private final SlimeMap waiting, arena;

    public ShootCraftMapManager(ShootCraftGame game) {
        super(game, new SlimeWorldLoader());
        this.waiting = new SlimeGameMap("", "");
        this.arena = new SlimeGameMap("", "");
    }

    @Override
    public void setup() {
        addMap(waiting);
        addMap(arena);

        mapLoader.load(waiting).whenComplete((slimeMap, throwable) -> {

            game.getWaitingRoom().setMap(slimeMap);
        });

        mapLoader.load(arena).whenComplete((slimeMap, throwable) -> {


            game.setState(GameState.WAIT);
        });
    }

    @Override
    public void end() {
        delete(waiting);
        delete(arena);
    }

}