
public class EnemyTwo {

	long currentTime = System.currentTimeMillis();
		/* variáveis dos inimigos tipo 2 */

		int[] enemy2_states = new int[10]; // estados
		double[] enemy2_X = new double[10]; // coordenadas x
		double[] enemy2_Y = new double[10]; // coordenadas y
		double[] enemy2_V = new double[10]; // velocidades
		double[] enemy2_angle = new double[10]; // ângulos (indicam direção do movimento)
		double[] enemy2_RV = new double[10]; // velocidades de rotação
		double[] enemy2_explosion_start = new double[10]; // instantes dos inícios das explosões
		double[] enemy2_explosion_end = new double[10]; // instantes dos finais das explosões
		double enemy2_spawnX = GameLib.WIDTH * 0.20; // coordenada x do próximo inimigo tipo 2 a aparecer
		int enemy2_count = 0; // contagem de inimigos tipo 2 (usada na "formação de voo")
		double enemy2_radius = 12.0; // raio (tamanho aproximado do inimigo 2)
		long nextEnemy2 = currentTime + 7000; // instante em que um novo inimigo 2 deve aparecer


        public EnemyTwo(){
            
        }

		public void init(){
			for (int i = 0; i < this.enemy2_states.length; i++)
			this.enemy2_states[i] = GlobalVars.INACTIVE;
		}
}
