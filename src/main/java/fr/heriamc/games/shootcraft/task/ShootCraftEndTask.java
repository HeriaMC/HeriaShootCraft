package fr.heriamc.games.shootcraft.task;

import fr.heriamc.games.engine.utils.task.countdown.CountdownTask;
import fr.heriamc.games.engine.utils.task.countdown.GameCountdownTask;
import fr.heriamc.games.shootcraft.ShootCraftGame;

public class ShootCraftEndTask extends GameCountdownTask<ShootCraftGame> {

    public ShootCraftEndTask(ShootCraftGame game) {
        super(10, game);
    }

    @Override
    public void onStart() {
        System.out.println("FIN DU JEU");
    }

    @Override
    public void onNext(CountdownTask countdownTask) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onCancel() {

    }

}