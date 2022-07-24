import GlobalVars; 

public class PlayerProjetil {
    
		/* variáveis dos projéteis disparados pelo player */

		int[] projectile_states = new int[10]; // estados
		double[] projectile_X = new double[10]; // coordenadas x
		double[] projectile_Y = new double[10]; // coordenadas y
		double[] projectile_VX = new double[10]; // velocidades no eixo x
		double[] projectile_VY = new double[10]; // velocidades no eixo y

    public PlayerProjetil(){

    }

	public void init(){
		for (int i = 0; i < this.projectile_states.length; i++)
		this.projectile_states[i] = GlobalVars.INACTIVE;
	}

}
