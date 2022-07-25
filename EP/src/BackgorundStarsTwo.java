import java.awt.Color;
public class BackgorundStarsTwo {

    		/* estrelas que formam o fundo de segundo plano */

		double[] background2_X = new double[50];
		double[] background2_Y = new double[50];
		double background2_speed = 0.045;
		double background2_count = 0.0;

    public BackgorundStarsTwo(){


    }

	public double[] getBackground2_X() {
		return this.background2_X;
	}

	public void setBackground2_X(double[] background2_X) {
		this.background2_X = background2_X;
	}

	public double[] getBackground2_Y() {
		return this.background2_Y;
	}

	public void setBackground2_Y(double[] background2_Y) {
		this.background2_Y = background2_Y;
	}

	public double getBackground2_speed() {
		return this.background2_speed;
	}

	public void setBackground2_speed(double background2_speed) {
		this.background2_speed = background2_speed;
	}

	public double getBackground2_count() {
		return this.background2_count;
	}

	public void setBackground2_count(double background2_count) {
		this.background2_count = background2_count;
	}

	public void init(){
		for (int i = 0; i < this.background2_X.length; i++) {

			this.background2_X[i] = Math.random() * GameLib.WIDTH;
			this.background2_Y[i] = Math.random() * GameLib.HEIGHT;
		}
	}

	public void DesenhandoPlanoDeFundoDistante(){
		GameLib.setColor(Color.DARK_GRAY);
	
		this.background2_count += this.background2_speed * delta;

		for (int i = 0; i < this.background2_X.length; i++) {

			GameLib.fillRect(this.background2_X[i], (this.background2_Y[i] + this.background2_count) % GameLib.HEIGHT, 2, 2);
		}
	}



    
}
