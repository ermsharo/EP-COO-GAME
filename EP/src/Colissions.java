

public class Colissions{

    public Colissions(){

    }

    //Vamos tratar as colisões agora
    
	public static long currentTime = System.currentTimeMillis();
	public static long delta = System.currentTimeMillis() - currentTime;
	
    /***************************/
	/* Verificação de colisões */
	/***************************/


	public void VerifyCollisions(Player p ,EnemyProjectile ep, EnemyOne e1, EnemyTwo e2, PlayerProjetil p_pro){
		if (p.player_state == GlobalVars.ACTIVE) {

			ColissionPlayerProjeteisInimigo(ep,p);
			ColissionPlayerInimigo(e1,e2,p);
			CollisonProjetilPlayerInimigos(p_pro,e1,e2);
		}
	}


    public void ColissionPlayerProjeteisInimigo (EnemyProjectile ep,Player p ){
				
        
                /* colisões player - projeteis (inimigo) */

				for (int i = 0; i < ep.e_projectile_states.length; i++) {

					double dx = ep.e_projectile_X[i] - p.player_X;
					double dy = ep.e_projectile_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + ep.e_projectile_radius) * 0.8) {

						p.player_state = GlobalVars.EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}
    }



    public void ColissionPlayerInimigo (EnemyOne e1, EnemyTwo e2 , Player p ){

				/* colisões player - inimigos */

				for (int i = 0; i < e1.enemy1_states.length; i++) {

					double dx = e1.enemy1_X[i] - p.player_X;
					double dy = e1.enemy1_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + e1.enemy1_radius) * 0.8) {

						p.player_state = GlobalVars.EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}
                

				for (int i = 0; i < e2.enemy2_states.length; i++) {

					double dx = e2.enemy2_X[i] - p.player_X;
					double dy = e2.enemy2_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + e2.enemy2_radius) * 0.8) {

						p.player_state = GlobalVars.EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}
			}


public void CollisonProjetilPlayerInimigos(PlayerProjetil p_pro, EnemyOne e1, EnemyTwo e2  ){
/* colisões projeteis (player) - inimigos */

for (int k = 0; k < p_pro.projectile_states.length; k++) {

    for (int i = 0; i < e1.enemy1_states.length; i++) {

        if (e1.enemy1_states[i] == GlobalVars.ACTIVE) {

            double dx = e1.enemy1_X[i] - p_pro.projectile_X[k];
            double dy = e1.enemy1_Y[i] - p_pro.projectile_Y[k];
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < e1.enemy1_radius) {

                e1.enemy1_states[i] = GlobalVars.EXPLODING;
                e1.enemy1_explosion_start[i] = currentTime;
                e1.enemy1_explosion_end[i] = currentTime + 500;
            }
        }
    }

    for (int i = 0; i < e2.enemy2_states.length; i++) {

        if (e2.enemy2_states[i] == GlobalVars.ACTIVE) {

            double dx = e2.enemy2_X[i] - p_pro.projectile_X[k];
            double dy = e2.enemy2_Y[i] - p_pro.projectile_Y[k];
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < e2.enemy2_radius) {

                e2.enemy2_states[i] = GlobalVars.EXPLODING;
                e2.enemy2_explosion_start[i] = currentTime;
                e2.enemy2_explosion_end[i] = currentTime + 500;
            }
        }
    }
}
        }

			
            
}

