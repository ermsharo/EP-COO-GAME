public class RunningState {

    private boolean running;


    public RunningState(boolean inicialState){
        this.running = inicialState; 
    }

    public void setRunning(boolean state){
        this.running = state; 
    }

    public boolean getRunning(){
    
        return this.running;
    }
    
}
