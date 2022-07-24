import GlobalVars;

public class KeyboardInput {


    public KeyboardInput() {

    }

    public void verifyKeyboardInput(Player p, PlayerProjetil p_pro , RunningState rs){
        if (p.player_state == GlobalVars.ACTIVE) {

            if (GameLib.iskeyPressed(GameLib.KEY_UP))
                p.player_Y -= GlobalVars.delta * p.player_VY;
            if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
                p.player_Y += GlobalVars.delta * p.player_VY;
            if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
                p.player_X -= GlobalVars.delta * p.player_VX;
            if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
                p.player_X += GlobalVars.delta * p.player_VY;

            if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                if (GlobalVars.currentTime > p.player_nextShot) {
                    ArrayOps ao = new ArrayOps(); 

                    int free = ao.findFreeIndex(p_pro.projectile_states);

                    if (free < p_pro.projectile_states.length) {

                        p_pro.projectile_X[free] = p.player_X;
                        p_pro.projectile_Y[free] = p.player_Y - 2 * p.player_radius;
                        p_pro.projectile_VX[free] = 0.0;
                        p_pro.projectile_VY[free] = -1.0;
                        p_pro.projectile_states[free] = GlobalVars.ACTIVE;
                        p.player_nextShot = GlobalVars.currentTime + 100;
                    }
                }
            }
        }

        if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE))
        rs.setRunning(false);

        /* Verificando se coordenadas do player ainda estão dentro */
        /* da tela de jogo após processar entrada do usuário. */

        if (p.player_X < 0.0)
            p.player_X = 0.0;
        if (p.player_X >= GameLib.WIDTH)
            p.player_X = GameLib.WIDTH - 1;
        if (p.player_Y < 25.0)
            p.player_Y = 25.0;
        if (p.player_Y >= GameLib.HEIGHT)
            p.player_Y = GameLib.HEIGHT - 1;
        
        }

    
}
