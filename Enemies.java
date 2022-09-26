public class Enemies{
    private int vida, dano;
    private String name;
    private boolean naval;

    public Enemies(String name){
        switch(name){
            case "Piratao":
                this.vida = 20;
                this.dano = 4;
                this.naval = false;
                break;
                        
            case "Navio Pirata":
                this.vida = 15;
                this.dano = 3;
                this.naval = true;
                break;
            
            case "Hidra":
                this.vida = 60;
                this.dano = 8;
                this.naval = true;
                break;
            
            case "Nativos":
                this.vida = 5;
                this.dano = 2;
                this.naval = false;
                break;
            
            case "Tigres":
                this.vida = 6;
                this.dano = 3;
                this.naval = false;
                break;
            }
    }

    public int getDano(){
        return dano;
    }

    public int getVida(){
        return vida;
    }

    public void tomarDano(int dano){
        this.vida -= dano;
    }

    public String getName(){
        return this.name;
    }

    public boolean getNaval(){
        return this.naval;
    }
}

