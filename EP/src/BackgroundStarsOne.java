public class BackgroundStarsOne {
    
	/* estrelas que formam o fundo de primeiro plano */

    double[] background1_X = new double[20];
    double[] background1_Y = new double[20];
    double background1_speed = 0.070;
    double background1_count = 0.0;


    public BackgroundStarsOne(){

        

    }
    
    public void init(){

		for (int i = 0; i < this.background1_X.length; i++) {

			this.background1_X[i] = Math.random() * GameLib.WIDTH;
			this.background1_Y[i] = Math.random() * GameLib.HEIGHT;
		}

    }


}
