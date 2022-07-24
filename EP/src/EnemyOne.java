
import GlobalVars; 


public class EnemyOne {
    long currentTime = System.currentTimeMillis();
    int[] enemy1_states = new int[10]; // estados
    double[] enemy1_X = new double[10]; // coordenadas x
    double[] enemy1_Y = new double[10]; // coordenadas y
    double[] enemy1_V = new double[10]; // velocidades
    double[] enemy1_angle = new double[10]; // ângulos (indicam direção do movimento)
    double[] enemy1_RV = new double[10]; // velocidades de rotação
    double[] enemy1_explosion_start = new double[10]; // instantes dos inícios das explosões
    double[] enemy1_explosion_end = new double[10]; // instantes dos finais da explosões
    long[] enemy1_nextShoot = new long[10]; // instantes do próximo tiro
    double enemy1_radius = 9.0; // raio (tamanho do inimigo 1)
    long nextEnemy1 = currentTime + 2000; // instante em que um novo inimigo 1 deve aparecer
    
    public EnemyOne(){
        
    }

		public void init(){
			for (int i = 0; i < this.enemy1_states.length; i++)
			this.enemy1_states[i] = GlobalVars.INACTIVE;
		}
     
}
