//Class with all things needed to use the player inside the game
public class Player {

    public Player(){

    }
    /* variáveis usadas no controle de tempo efetuado no main loop */

	long delta;
	long currentTime = System.currentTimeMillis();

    	// ALL ELEMENTS
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;

    /* variáveis do player */

		int player_state = ACTIVE; // estado
		double player_X = GameLib.WIDTH / 2; // coordenada x
		double player_Y = GameLib.HEIGHT * 0.90; // coordenada y
		double player_VX = 0.25; // velocidade no eixo x
		double player_VY = 0.25; // velocidade no eixo y
		double player_radius = 12.0; // raio (tamanho aproximado do player)
		double player_explosion_start = 0; // instante do início da explosão
		double player_explosion_end = 0; // instante do final da explosão
		long player_nextShot = currentTime; // instante a partir do qual pode haver um próximo tiro
}
