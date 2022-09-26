import java.util.Scanner;

public class Player{ 
    private int vida = 10, vidaMax = 10, vida_navio = 20, vida_navioMax = 20, dano = 1, dano_navio = 5, ouro = 20;
    private String criticalPath = "Ilha do Sul";
    private boolean resolveu_enigma = false;
    private boolean flee = false;
    Scanner scanner = new Scanner(System.in);


    public void player(){

    }

    public int getVida(){
        return vida;
    }

    public int getVidaNavio(){
        return vida_navio;
    }

    public boolean getResolveu_enigma(){
        return resolveu_enigma;
    }

    public void setResolveu_enigma(){
        resolveu_enigma = true;
    }

    public void ganharVida(int cura){
        vida += cura;
    }

    public int getVidaMax(){
        return vidaMax;
    }

    public void ganharOuro(int ouro_recebido){
        ouro += ouro_recebido;
    }

    public int getDano(){
        return dano;
    }

    public int getDanoNavio(){
        return dano_navio;
    }

    public String getCriticalPath(){
        return criticalPath;
    }
    
    public void tomarDano(int dano){
        this.vida -= dano;
    }

    public void tomarDanoNavio(int dano){
        this.vida_navio -= dano;
    }

    public boolean getFlee(){
        return flee;
    }

    public void doFlee(){
        flee = false;
    }

    public void imprime_atributos(){
        System.out.println("Seus atributos atuais são: ");
        System.out.println("Vida: " + vida);
        System.out.println("VidaMax: " + vidaMax);
        System.out.println("Dano: " + dano);
        System.out.println("Vida do navio: " + vida_navio);
        System.out.println("Vida máxima do navio: " + vida_navioMax);
        System.out.println("Dano do navio: " + dano_navio);
        System.out.println("Ouro: " + ouro);
    }

    public void imprime_compras(){
        System.out.println("Seja Bem-Vindo à minha loja, estrangeiro!");
        System.out.println("-------------------------------");
        System.out.println("Opções de compra:");
        
        System.out.println("1) Poção de Cura (custa 15 barras de ouro): ");
        System.out.println("Descrição:   Regenera a sua vida até o máximo");
        
        System.out.println("2) Armadura (custa 30 barras de ouro): ");
        System.out.println("Descrição:   Não regenera a sua vida, mas aumenta a sua vida máxima possível em 10 pontos");
        
        System.out.println("3): Conserto de Navio (custa 35 barras de ouro)");
        System.out.println("Descrição:   Conserta o seu navio, regenerando a sua vida do até o máximo");

        System.out.println("4): Poção de Dano (custa 20 barras de ouro)");
        System.out.println("Descrição:   Uma estranha poção que aumenta o seu dano em 5 pontos");
    
        System.out.println("5): Canhões de batalha (custa 25 barras de ouro)");
        System.out.println("Descrição:   Canhões extremamente poderosos, úteis para a batalha naval, aumenta o dano do seu navio em 5 pontos");

        System.out.println("6) Nada.");

        System.out.println("-------------------------------");
        
        loopLoja();
        
    }

    public void loopLoja(){
        boolean satisfeito = false;
        System.out.println("Você possui " + this.ouro + " barras de ouro");
        while(!satisfeito){   
            System.out.println("O que deseja comprar, estrangeiro? (Digite o número correspondente ao produto)");
            int resposta = scanner.nextInt();
            if(resposta != 1 && resposta != 2  && resposta != 3 && resposta != 4  && resposta != 5){
                resposta = 6;
            }

            comprar(resposta);
            System.out.println("Você possui " + this.ouro + " barras de ouro");
            System.out.println("Quer comprar algo mais? (0 - Não/ 1 - Sim)");

            int sat = scanner.nextInt();
            switch(sat){
                case 1:
                    System.out.println("Sábia escolha!");
                    break;
                default:
                    satisfeito = true;
                    System.out.println("Ok, já que não deseja comprar nada, suma daqui!");
                break;
            }

        }
    }

    public void comprar(int resposta){
        switch(resposta){
            case 1:
                if(this.ouro >= 15){
                    ouro -= 15;
                    vida = vidaMax;
                    System.out.println("He He, Pix recebido com sucesso! Volte sempre, que Cleberson esteja com você em sua jornada");
                    imprime_atributos();
                    break;
                }
                else{
                    System.out.println("Você não possui ouro suficiente! Dê o fora da minha loja!");
                    break; 
                }
            
            case 2:
                if(this.ouro >= 30){
                    ouro -= 30;
                    vidaMax += 10;
                    System.out.println("He He, Pix recebido com sucesso! Volte sempre, que Cleberson esteja com você em sua jornada");
                    imprime_atributos();
                    break;
                }
                else{
                    System.out.println("Você não possui ouro suficiente! Dê o fora da minha loja!");
                    break; 
                }

            case 3:
                if(this.ouro >= 35){
                    ouro -= 35;
                    vida_navio = vida_navioMax;
                    System.out.println("He He, Pix recebido com sucesso! Volte sempre, que Cleberson esteja com você em sua jornada");
                    imprime_atributos();
                    break;
                }
                else{
                    System.out.println("Você não possui ouro suficiente! Dê o fora da minha loja!");
                    break; 
                }
            
            case 4:
                if(this.ouro >= 20){
                    ouro -= 20;
                    dano += 5;
                    System.out.println("He He, Pix recebido com sucesso! Volte sempre, que Cleberson esteja com você em sua jornada");
                    imprime_atributos();
                    break;
                }
                else{
                    System.out.println("Você não possui ouro suficiente! Dê o fora da minha loja!");
                    break; 
            }
            
            case 5:
                if(this.ouro >= 25){
                    ouro -= 25;
                    dano_navio += 5;
                    System.out.println("He He, Pix recebido com sucesso! Volte sempre, que Cleberson esteja com você em sua jornada!");
                    imprime_atributos();
                    break;
                }
                else{
                    System.out.println("Você não possui ouro suficiente! Dê o fora da minha loja!");
                    break; 
            }
            case 6:
                System.out.println("Ok, já que não deseja comprar nada, suma daqui!");
                break; 
        }
    }

    public void combate(Enemies inimigo){
        System.out.println("vida do adversário: " + inimigo.getVida());
        System.out.println("dano do adversário: " + inimigo.getDano());
        
        if(inimigo.getNaval()){
            System.out.println("Sua vida " + vida_navio);
            System.out.println("Seu dano: " + dano_navio);
        }
    
        else{
            System.out.println("Sua vida " + vida);
            System.out.println("Seu dano: " + dano);
        }
        
        System.out.println("O que deseja fazer? (atacar/fugir)");
        String resposta = scanner.next();
        
        if(resposta.equals("fugir")){
            System.out.println("Você perdeu 5 barras de ouro por fugir da luta!");
            if(ouro >= 5) ouro -= 5;
            else ouro = 0;
            flee = true;
        }
    
        if(resposta.equals("atacar")){
            while(this.vida > 0 && this.vida_navio > 0 && inimigo.getVida() > 0){
                if(inimigo.getNaval()){
                    inimigo.tomarDano(this.dano_navio);
                    this.tomarDanoNavio(inimigo.getDano());
                }

                else{
                    inimigo.tomarDano(this.dano);
                    this.tomarDano(inimigo.getDano());
                }

                
            }
        }

        else{
            System.out.println("Por favor, digite um comando válido");
            }
        }

        public boolean alive(){
            if(this.vida <= 0 || this.vida_navio <= 0){
                return false;
            }
            else return true;
        }
}

