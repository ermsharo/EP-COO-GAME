import GlobalVars;

public class EnemyProjectile {
                /*
		 * variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2)
		 */

		int[] e_projectile_states = new int[200]; // estados
		double[] e_projectile_X = new double[200]; // coordenadas x
		double[] e_projectile_Y = new double[200]; // coordenadas y
		double[] e_projectile_VX = new double[200]; // velocidade no eixo x
		double[] e_projectile_VY = new double[200]; // velocidade no eixo y
		double e_projectile_radius = 2.0; // raio (tamanho dos projéteis inimigos)

        public EnemyProjectile(){

        }

		public void init(){
			for (int i = 0; i < this.e_projectile_states.length; i++)
			this.e_projectile_states[i] = GlobalVars.INACTIVE;
		}
 }
        
        
    