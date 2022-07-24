import java.awt.Color;

//Classe responsavel por gerenciar todas as sessões de usuario
public class GameSession {



    public GameSession(){

    
	}

/* Constantes relacionadas aos estados que os elementos */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time. */

	//Clock do jogo
	public static void busyWait(long time) {

		while (System.currentTimeMillis() < time)
			Thread.yield();
	}




    public void runGame(){

		ArrayOps ao = new ArrayOps();
		BackgroundStarsOne bso = new BackgroundStarsOne(); 
		BackgorundStarsTwo bst = new BackgorundStarsTwo(); 
		Player p  = new Player();
		PlayerProjetil p_pro = new PlayerProjetil(); 
		EnemyOne e1 = new EnemyOne();
		EnemyTwo e2 = new EnemyTwo();
		EnemyProjectile ep  = new EnemyProjectile();
		Colissions c = new Colissions();
		StateUpdate su = new StateUpdate();

        
		boolean running = true;
		/* variáveis usadas no controle de tempo efetuado no main loop */

		long delta;
		long currentTime = System.currentTimeMillis();

		/* inicializações */

		p_pro.init();
		ep.init();
		e1.init();
		e2.init();
		bso.init(); 
		bst.init();
	

		GameLib.initGraphics();

		while (running) {


			delta = System.currentTimeMillis() - currentTime;
			currentTime = System.currentTimeMillis();

            /***************************/
			/* Verificação de colisões */
			/***************************/

			if (p.player_state == ACTIVE) {

				/* colisões player - projeteis (inimigo) */

				for (int i = 0; i < ep.e_projectile_states.length; i++) {

					double dx = ep.e_projectile_X[i] - p.player_X;
					double dy = ep.e_projectile_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + ep.e_projectile_radius) * 0.8) {

						p.player_state = EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}

				/* colisões player - inimigos */

				for (int i = 0; i < e1.enemy1_states.length; i++) {

					double dx = e1.enemy1_X[i] - p.player_X;
					double dy = e1.enemy1_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + e1.enemy1_radius) * 0.8) {

						p.player_state = EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}

				for (int i = 0; i < e2.enemy2_states.length; i++) {

					double dx = e2.enemy2_X[i] - p.player_X;
					double dy = e2.enemy2_Y[i] - p.player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (p.player_radius + e2.enemy2_radius) * 0.8) {

						p.player_state = EXPLODING;
						p.player_explosion_start = currentTime;
						p.player_explosion_end = currentTime + 2000;
					}
				}
			}

			/* colisões projeteis (player) - inimigos */

			for (int k = 0; k < p_pro.projectile_states.length; k++) {

				for (int i = 0; i < e1.enemy1_states.length; i++) {

					if (e1.enemy1_states[i] == ACTIVE) {

						double dx = e1.enemy1_X[i] - p_pro.projectile_X[k];
						double dy = e1.enemy1_Y[i] - p_pro.projectile_Y[k];
						double dist = Math.sqrt(dx * dx + dy * dy);

						if (dist < e1.enemy1_radius) {

							e1.enemy1_states[i] = EXPLODING;
							e1.enemy1_explosion_start[i] = currentTime;
							e1.enemy1_explosion_end[i] = currentTime + 500;
						}
					}
				}

				for (int i = 0; i < e2.enemy2_states.length; i++) {

					if (e2.enemy2_states[i] == ACTIVE) {

						double dx = e2.enemy2_X[i] - p_pro.projectile_X[k];
						double dy = e2.enemy2_Y[i] - p_pro.projectile_Y[k];
						double dist = Math.sqrt(dx * dx + dy * dy);

						if (dist < e2.enemy2_radius) {

							e2.enemy2_states[i] = EXPLODING;
							e2.enemy2_explosion_start[i] = currentTime;
							e2.enemy2_explosion_end[i] = currentTime + 500;
						}
					}
				}
			}

			
			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* projeteis (player) */

			for (int i = 0; i < p_pro.projectile_states.length; i++) {

				if (p_pro.projectile_states[i] == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (p_pro.projectile_Y[i] < 0) {

						p_pro.projectile_states[i] = INACTIVE;
					} else {

						p_pro.projectile_X[i] += p_pro.projectile_VX[i] * delta;
						p_pro.projectile_Y[i] += p_pro.projectile_VY[i] * delta;
					}
				}
			}

			/* projeteis (inimigos) */

			for (int i = 0; i < ep.e_projectile_states.length; i++) {

				if (ep.e_projectile_states[i] == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (ep.e_projectile_Y[i] > GameLib.HEIGHT) {

						ep.e_projectile_states[i] = INACTIVE;
					} else {

						ep.e_projectile_X[i] += ep.e_projectile_VX[i] * delta;
						ep.e_projectile_Y[i] += ep.e_projectile_VY[i] * delta;
					}
				}
			}

			/* inimigos tipo 1 */

			for (int i = 0; i < e1.enemy1_states.length; i++) {

				if (e1.enemy1_states[i] == EXPLODING) {

					if (currentTime > e1.enemy1_explosion_end[i]) {

						e1.enemy1_states[i] = INACTIVE;
					}
				}

				if (e1.enemy1_states[i] == ACTIVE) {

					/* verificando se inimigo saiu da tela */
					if (e1.enemy1_Y[i] > GameLib.HEIGHT + 10) {

						e1.enemy1_states[i] = INACTIVE;
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
								ep.e_projectile_states[free] = ACTIVE;

								e1.enemy1_nextShoot[i] = (long) (currentTime + 200 + Math.random() * 500);
							}
						}
					}
				}
			}

			/* inimigos tipo 2 */

			for (int i = 0; i < e2.enemy2_states.length; i++) {

				if (e2.enemy2_states[i] == EXPLODING) {

					if (currentTime > e2.enemy2_explosion_end[i]) {

						e2.enemy2_states[i] = INACTIVE;
					}
				}

				if (e2.enemy2_states[i] == ACTIVE) {

					/* verificando se inimigo saiu da tela */
					if (e2.enemy2_X[i] < -10 || e2.enemy2_X[i] > GameLib.WIDTH + 10) {

						e2.enemy2_states[i] = INACTIVE;
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
									ep.e_projectile_states[free] = ACTIVE;
								}
							}
						}
					}
				}
			}

			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */

			if (currentTime > e1.nextEnemy1) {

				int free = ArrayOps.findFreeIndex(e1.enemy1_states);

				if (free < e1.enemy1_states.length) {

					e1.enemy1_X[free] = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
					e1.enemy1_Y[free] = -10.0;
					e1.enemy1_V[free] = 0.20 + Math.random() * 0.15;
					e1.enemy1_angle[free] = (3 * Math.PI) / 2;
					e1.enemy1_RV[free] = 0.0;
					e1.enemy1_states[free] = ACTIVE;
					e1.enemy1_nextShoot[free] = currentTime + 500;
					e1.nextEnemy1 = currentTime + 500;
				}
			}

			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */

			if (currentTime > e2.nextEnemy2) {

				int free = ao.findFreeIndex(e2.enemy2_states);

				if (free < e2.enemy2_states.length) {

					e2.enemy2_X[free] = e2.enemy2_spawnX;
					e2.enemy2_Y[free] = -10.0;
					e2.enemy2_V[free] = 0.42;
					e2.enemy2_angle[free] = (3 * Math.PI) / 2;
					e2.enemy2_RV[free] = 0.0;
					e2.enemy2_states[free] = ACTIVE;

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

			/* Verificando se a explosão do player já acabou. */
			/* Ao final da explosão, o player volta a ser controlável */
			if (p.player_state == EXPLODING) {

				if (currentTime > p.player_explosion_end) {

					p.player_state = ACTIVE;
				}
			}



			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if (p.player_state == ACTIVE) {

				if (GameLib.iskeyPressed(GameLib.KEY_UP))
					p.player_Y -= delta * p.player_VY;
				if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
					p.player_Y += delta * p.player_VY;
				if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
					p.player_X -= delta * p.player_VX;
				if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
					p.player_X += delta * p.player_VY;

				if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

					if (currentTime > p.player_nextShot) {

						int free = ao.findFreeIndex(p_pro.projectile_states);

						if (free < p_pro.projectile_states.length) {

							p_pro.projectile_X[free] = p.player_X;
							p_pro.projectile_Y[free] = p.player_Y - 2 * p.player_radius;
							p_pro.projectile_VX[free] = 0.0;
							p_pro.projectile_VY[free] = -1.0;
							p_pro.projectile_states[free] = ACTIVE;
							p.player_nextShot = currentTime + 100;
						}
					}
				}
			}

			if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE))
				running = false;

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


			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo distante */

			GameLib.setColor(Color.DARK_GRAY);
			
			bst.background2_count += bst.background2_speed * delta;

			for (int i = 0; i < bst.background2_X.length; i++) {

				GameLib.fillRect(bst.background2_X[i], (bst.background2_Y[i] + bst.background2_count) % GameLib.HEIGHT, 2, 2);
			}

			/* desenhando plano de fundo próximo */

			GameLib.setColor(Color.GRAY);
			bso.background1_count += bso.background1_speed * delta;

			for (int i = 0; i < bso.background1_X.length; i++) {

				GameLib.fillRect(bso.background1_X[i], (bso.background1_Y[i] + bso.background1_count) % GameLib.HEIGHT, 3, 3);
			}

			/* desenhando player */

			if (p.player_state == EXPLODING) {

				double alpha = (currentTime - p.player_explosion_start) / (p.player_explosion_end - p.player_explosion_start);
				GameLib.drawExplosion(p.player_X, p.player_Y, alpha);
			} else {

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(p.player_X, p.player_Y, p.player_radius);
			}

			/* deenhando projeteis (player) */

			for (int i = 0; i < p_pro.projectile_states.length; i++) {

				if (p_pro.projectile_states[i] == ACTIVE) {

					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(p_pro.projectile_X[i], p_pro.projectile_Y[i] - 5, p_pro.projectile_X[i], p_pro.projectile_Y[i] + 5);
					GameLib.drawLine(p_pro.projectile_X[i] - 1, p_pro.projectile_Y[i] - 3, p_pro.projectile_X[i] - 1,
					p_pro.projectile_Y[i] + 3);
					GameLib.drawLine(p_pro.projectile_X[i] + 1, p_pro.projectile_Y[i] - 3, p_pro.projectile_X[i] + 1,
					p_pro.projectile_Y[i] + 3);
				}
			}

			/* desenhando projeteis (inimigos) */

			for (int i = 0; i < ep.e_projectile_states.length; i++) {

				if (ep.e_projectile_states[i] == ACTIVE) {

					GameLib.setColor(Color.RED);
					GameLib.drawCircle(ep.e_projectile_X[i], ep.e_projectile_Y[i], ep.e_projectile_radius);
				}
			}

			/* desenhando inimigos (tipo 1) */

			for (int i = 0; i < e1.enemy1_states.length; i++) {

				if (e1.enemy1_states[i] == EXPLODING) {

					double alpha = (currentTime - e1.enemy1_explosion_start[i])
							/ (e1.enemy1_explosion_end[i] - e1.enemy1_explosion_start[i]);
					GameLib.drawExplosion(e1.enemy1_X[i], e1.enemy1_Y[i], alpha);
				}

				if (e1.enemy1_states[i] == ACTIVE) {

					GameLib.setColor(Color.CYAN);
					GameLib.drawCircle(e1.enemy1_X[i], e1.enemy1_Y[i], e1.enemy1_radius);
				}
			}

			/* desenhando inimigos (tipo 2) */

			for (int i = 0; i < e2.enemy2_states.length; i++) {

				if (e2.enemy2_states[i] == EXPLODING) {

					double alpha = (currentTime - e2.enemy2_explosion_start[i])
							/ (e2.enemy2_explosion_end[i] - e2.enemy2_explosion_start[i]);
					GameLib.drawExplosion(e2.enemy2_X[i], e2.enemy2_Y[i], alpha);
				}

				if (e2.enemy2_states[i] == ACTIVE) {

					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(e2.enemy2_X[i], e2.enemy2_Y[i], e2.enemy2_radius);
				}
			}


			GameLib.display();


			busyWait(currentTime + 3);
		}

		System.exit(0);
	}





}