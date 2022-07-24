public class BackgorundStarsTwo {

    		/* estrelas que formam o fundo de segundo plano */

		double[] background2_X = new double[50];
		double[] background2_Y = new double[50];
		double background2_speed = 0.045;
		double background2_count = 0.0;

    public BackgorundStarsTwo(){


    }

	public void init(){
		for (int i = 0; i < this.background2_X.length; i++) {

			this.background2_X[i] = Math.random() * GameLib.WIDTH;
			this.background2_Y[i] = Math.random() * GameLib.HEIGHT;
		}
	}
    
}
