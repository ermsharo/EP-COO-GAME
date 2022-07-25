import java.awt.Color;
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


  public double[] getBackground1_X() {
    return this.background1_X;
  }

  public void setBackground1_X(double[] background1_X) {
    this.background1_X = background1_X;
  }

  public double[] getBackground1_Y() {
    return this.background1_Y;
  }

  public void setBackground1_Y(double[] background1_Y) {
    this.background1_Y = background1_Y;
  }

  public double getBackground1_speed() {
    return this.background1_speed;
  }

  public void setBackground1_speed(double background1_speed) {
    this.background1_speed = background1_speed;
  }

  public double getBackground1_count() {
    return this.background1_count;
  }

  public void setBackground1_count(double background1_count) {
    this.background1_count = background1_count;
  }


  public void DesenhandoPlanoDeFundoProximo(){

    GameLib.setColor(Color.GRAY);
    this.background1_count += this.background1_speed * delta;

    for (int i = 0; i < this.background1_X.length; i++) {

        GameLib.fillRect(this.background1_X[i], (this.background1_Y[i] + this.background1_count) % GameLib.HEIGHT, 3, 3);
    }

}

}
