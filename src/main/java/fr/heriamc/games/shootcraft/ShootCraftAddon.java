package fr.heriamc.games.shootcraft;

import fr.heriamc.games.api.addon.GameAddon;
import fr.heriamc.games.shootcraft.data.ShootCraftDataManager;
import fr.heriamc.games.shootcraft.listener.*;
import fr.heriamc.games.shootcraft.pool.ShootCraftPool;
import lombok.Getter;

@Getter
public class ShootCraftAddon extends GameAddon<ShootCraftPool> {

    private ShootCraftDataManager dataManager;

    public ShootCraftAddon() {
        super(new ShootCraftPool());
    }

    @Override
    public void enable() {
        this.dataManager = new ShootCraftDataManager(heriaApi);

        pool.loadDefaultGames();

        registerListener(
                new CancelListener(pool.getGamesManager()),
                new PlayerConnectionListener(dataManager),
                new PlayerDamageListener(pool.getGamesManager()),
                new PlayerInteractListener(this),
                new PlayerChatListener(pool.getGamesManager())
        );
    }

    @Override
    public void disable() {

    }

}