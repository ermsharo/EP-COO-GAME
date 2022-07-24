

public class StateUpdate {
    

    public StateUpdate() {

        
    }


    public void UpdateStatus(Player p, EnemyTwo e2, EnemyOne e1, PlayerProjetil p_pro ,EnemyProjectile ep){
        ProjeteisPlayerStateUpdate( p_pro);
        ProjeteisInimigosStateUpdate(ep);
        InimigosTipoUmStateUpdate(e1 , ep ,p);
        ObjetosTipoDoisStateUpdate( e2,  ep);
        VerificaTipoUmStateUpdate(e1);
        VerificaTipoDoisStateUpdate(e2);
        VerificaExplosaoPLayerStateUpdate(p);
    }

            /***************************/
			/* Atualizações de estados */
			/***************************/

           long currentTime = System.currentTimeMillis();
            long delta = System.currentTimeMillis() - currentTime;

			/* projeteis (player) */

            public void ProjeteisPlayerStateUpdate(PlayerProjetil p_pro){
                
                for (int i = 0; i < p_pro.projectile_states.length; i++) {

                    if (p_pro.projectile_states[i] == GlobalVars.ACTIVE) {
    
                        /* verificando se projétil saiu da tela */
                        if (p_pro.projectile_Y[i] < 0) {
    
                            p_pro.projectile_states[i] = GlobalVars.INACTIVE;
                        } else {
    
                            p_pro.projectile_X[i] += p_pro.projectile_VX[i] * delta;
                            p_pro.projectile_Y[i] += p_pro.projectile_VY[i] * delta;
                        }
                    }
                }
            }

		

			/* projeteis (inimigos) */

            public void ProjeteisInimigosStateUpdate(EnemyProjectile ep){
                for (int i = 0; i < ep.e_projectile_states.length; i++) {

                    if (ep.e_projectile_states[i] == GlobalVars.ACTIVE) {
    
                        /* verificando se projétil saiu da tela */
                        if (ep.e_projectile_Y[i] > GameLib.HEIGHT) {
    
                            ep.e_projectile_states[i] = GlobalVars.INACTIVE;
                        } else {
    
                            ep.e_projectile_X[i] += ep.e_projectile_VX[i] * delta;
                            ep.e_projectile_Y[i] += ep.e_projectile_VY[i] * delta;
                        }
                    }
                }

            }

			
            /* inimigos tipo 1 */
            public void InimigosTipoUmStateUpdate(EnemyOne e1 ,EnemyProjectile ep , Player p){
                for (int i = 0; i < e1.enemy1_states.length; i++) {

                    if (e1.enemy1_states[i] == GlobalVars.EXPLODING) {
    
                        if (currentTime > e1.enemy1_explosion_end[i]) {
    
                            e1.enemy1_states[i] = GlobalVars.INACTIVE;
                        }
                    }
    
                    if (e1.enemy1_states[i] == GlobalVars.ACTIVE) {
    
                        /* verificando se inimigo saiu da tela */
                        if (e1.enemy1_Y[i] > GameLib.HEIGHT + 10) {
    
                            e1.enemy1_states[i] = GlobalVars.INACTIVE;
                        } else {
    
                            e1.enemy1_X[i] += e1.enemy1_V[i] * Math.cos(e1.enemy1_angle[i]) * delta;
                            e1.enemy1_Y[i] += e1.enemy1_V[i] * Math.sin(e1.enemy1_angle[i]) * delta * (-1.0);
                            e1.enemy1_angle[i] += e1.enemy1_RV[i] * delta;
    
                            if (currentTime > e1.enemy1_nextShoot[i] && e1.enemy1_Y[i] < p.player_Y) {
    
                                int free = ArrayOps.findFreeIndex(ep.e_projectile_states);
    
                                if (free < ep.e_projectile_states.length) {
    
                                    ep.e_projectile_X[free] = e1.enemy1_X[i];
                                    ep.e_projectile_Y[free] = e1.enemy1_Y[i];
                                    ep.e_projectile_VX[free] = Math.cos(e1.enemy1_angle[i]) * 0.45;
                                    ep.e_projectile_VY[free] = Math.sin(e1.enemy1_angle[i]) * 0.45 * (-1.0);
                                    ep.e_projectile_states[free] = GlobalVars.ACTIVE;
    
                                    e1.enemy1_nextShoot[i] = (long) (currentTime + 200 + Math.random() * 500);
                                }
                            }
                        }
                    }
                }

            }

			/* inimigos tipo 2 */

			public void ObjetosTipoDoisStateUpdate(EnemyTwo e2, EnemyProjectile ep){
      
                for (int i = 0; i < e2.enemy2_states.length; i++) {

                    if (e2.enemy2_states[i] == GlobalVars.EXPLODING) {
    
                        if (currentTime > e2.enemy2_explosion_end[i]) {
    
                            e2.enemy2_states[i] = GlobalVars.INACTIVE;
                        }
                    }
    
                    if (e2.enemy2_states[i] == GlobalVars.ACTIVE) {
    
                        /* verificando se inimigo saiu da tela */
                        if (e2.enemy2_X[i] < -10 || e2.enemy2_X[i] > GameLib.WIDTH + 10) {
    
                            e2.enemy2_states[i] = GlobalVars.INACTIVE;
                        } else {
    
                            boolean shootNow = false;
                            double previousY = e2.enemy2_Y[i];
    
                            e2.enemy2_X[i] += e2.enemy2_V[i] * Math.cos(e2.enemy2_angle[i]) * delta;
                            e2.enemy2_Y[i] += e2.enemy2_V[i] * Math.sin(e2.enemy2_angle[i]) * delta * (-1.0);
                            e2.enemy2_angle[i] += e2.enemy2_RV[i] * delta;
    
                            double threshold = GameLib.HEIGHT * 0.30;
    
                            if (previousY < threshold && e2.enemy2_Y[i] >= threshold) {
    
                                if (e2.enemy2_X[i] < GameLib.WIDTH / 2)
                                e2.enemy2_RV[i] = 0.003;
                                else
                                e2.enemy2_RV[i] = -0.003;
                            }
    
                            if (e2.enemy2_RV[i] > 0 && Math.abs(e2.enemy2_angle[i] - 3 * Math.PI) < 0.05) {
    
                                e2.enemy2_RV[i] = 0.0;
                                e2.enemy2_angle[i] = 3 * Math.PI;
                                shootNow = true;
                            }
    
                            if (e2.enemy2_RV[i] < 0 && Math.abs(e2.enemy2_angle[i]) < 0.05) {
    
                                e2.enemy2_RV[i] = 0.0;
                                e2.enemy2_angle[i] = 0.0;
                                shootNow = true;
                            }
    
                            if (shootNow) {
    
                                double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 - Math.PI / 8 };
                                int[] freeArray = ArrayOps.findFreeIndex(ep.e_projectile_states, angles.length);
    
                                for (int k = 0; k < freeArray.length; k++) {
    
                                    int free = freeArray[k];
    
                                    if (free < ep.e_projectile_states.length) {
    
                                        double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
                                        double vx = Math.cos(a);
                                        double vy = Math.sin(a);
    
                                        ep.e_projectile_X[free] = e2.enemy2_X[i];
                                        ep.e_projectile_Y[free] = e2.enemy2_Y[i];
                                        ep.e_projectile_VX[free] = vx * 0.30;
                                        ep.e_projectile_VY[free] = vy * 0.30;
                                        ep.e_projectile_states[free] = GlobalVars.ACTIVE;
                                    }
                                }
                            }
                        }
                    }
                }
            }



		

			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */

            public void VerificaTipoUmStateUpdate(EnemyOne e1){

                if (currentTime > e1.nextEnemy1) {

                    int free = ArrayOps.findFreeIndex(e1.enemy1_states);
    
                    if (free < e1.enemy1_states.length) {
    
                        e1.enemy1_X[free] = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
                        e1.enemy1_Y[free] = -10.0;
                        e1.enemy1_V[free] = 0.20 + Math.random() * 0.15;
                        e1.enemy1_angle[free] = (3 * Math.PI) / 2;
                        e1.enemy1_RV[free] = 0.0;
                        e1.enemy1_states[free] = GlobalVars.ACTIVE;
                        e1.enemy1_nextShoot[free] = currentTime + 500;
                        e1.nextEnemy1 = currentTime + 500;
                    }
                }
            }

            
            /* verificando se novos inimigos (tipo 2) devem ser "lançados" */

            public void VerificaTipoDoisStateUpdate(EnemyTwo e2){

                if (currentTime > e2.nextEnemy2) {

                    int free = ArrayOps.findFreeIndex(e2.enemy2_states);
    
                    if (free < e2.enemy2_states.length) {
    
                        e2.enemy2_X[free] = e2.enemy2_spawnX;
                        e2.enemy2_Y[free] = -10.0;
                        e2.enemy2_V[free] = 0.42;
                        e2.enemy2_angle[free] = (3 * Math.PI) / 2;
                        e2.enemy2_RV[free] = 0.0;
                        e2.enemy2_states[free] = GlobalVars.ACTIVE;
    
                        e2.enemy2_count++;
    
                        if (e2.enemy2_count < 10) {
    
                            e2.nextEnemy2 = currentTime + 120;
                        } else {
    
                            e2.enemy2_count = 0;
                            e2.enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
                            e2.nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
                        }
                    }
                }
    
            }
			


            public void VerificaExplosaoPLayerStateUpdate(Player p){

                /* Verificando se a explosão do player já acabou. */
			/* Ao final da explosão, o player volta a ser controlável */
			if (p.player_state == GlobalVars.EXPLODING) {

				if (currentTime > p.player_explosion_end) {

					p.player_state = GlobalVars.ACTIVE;
				}
			}
            }
			
			

}
