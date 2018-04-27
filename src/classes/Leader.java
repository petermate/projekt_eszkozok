package classes;

public class Leader {
    int fire, shock, man;
    public Leader(int fire, int shock, int man){
        this.fire=fire;
        this.shock=shock;
        this.man=man;
    }
    public Leader(){
        fire = 0;
        shock = 0;
        man = 0;
    }

    int getFire() {
        return fire;
        
    }

    int getShock() {
        return shock;
    }
    
    int getManeuver(){
        return man;
    }
    
}
