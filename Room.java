import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void setDescription(String descript)
    {
        this.description = descript;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */


    public void rooms(Player jogador){
        Scanner scanner = new Scanner(System.in);
        Random generate = new Random();

        if(description.equals("Loja da cidade: o valor dos itens são meio salgados, mas ainda assim de qualidade")){
            System.out.println("Você deseja comprar algo? (S/N)");
            String resposta = scanner.nextLine();
            if(resposta.equals("s") || resposta.equals("S")){
                jogador.imprime_compras();
        }
    }

        if(description.equals("Ilha do Oeste")){
            int number = generate.nextInt(100);
            if(number <= 60){
                jogador.tomarDano(10);
                System.out.println("Que azar! Você recebeu dano da armadilha.");
            }
            System.out.println("Parabéns, você encontrou o tesouro que tanto buscava");
            System.out.println("Entretanto, a jornada ainda não acabou! O talismã da imortalidade está atrás de uma porta misteriosa");
            System.out.println("Para abri-la você deve acertar a senha de 6 digitos escolhida por Cleberson quando trancou o talismã aqui");
            System.out.println("Se você errar, o lugar vai desabar e você vai morrer imediatamente :) HA HA HA HA!");
            System.out.println("Qual é a senha?");
            int senha = scanner.nextInt();
            if(senha != 1350){
                System.out.println("Senha incorreta! O lugar desabou sobre você!");
                jogador.tomarDano(180000);
            }
            else{
                System.out.println("Parabéns! Você destrancou a porta e obteu o talismã da imortalidade");
                System.out.println("Só tem um pequeno problema: O talismã realmente te dá imortalidade, mas somente na próxima vida");
                System.out.println("Ou seja, ao colocar o talismã no pescoço você morreu e agora, como qualquer outra alma, você é imortal HA HA HA! :)");
                System.out.println("Ps: Sim, você venceu o jogo, afinal completou seu objetivo, mas não deixa de ser um otário HAHAHA!");
            }
        }
        
        if(description.equals("um oceano grande e terrível!")){
            int number = generate.nextInt(100);
            if(number <= 5){
                Enemies Hidra = new Enemies("Hidra");
                System.out.println("Uma terrível Hidra apareceu. Ela está furiosa e deseja destruir o seu barco!");
                jogador.combate(Hidra);
                if(Hidra.getVida() <= 0){
                    System.out.println("Parabéns! Você venceu o combate e ganhou 50 barras de ouro");
                    jogador.ganharOuro(50);
                }
            }
            if(5 < number && number  <= 51){
                Enemies Navio_Pirata = new Enemies("Navio Pirata");
                System.out.println("Essa não, um navio pirata te avistou e querem te roubar!");
                jogador.combate(Navio_Pirata);
                if(Navio_Pirata.getVida() <= 0){
                    System.out.println("Parabéns! Você venceu o combate e ganhou 10 barras de ouro");
                    jogador.ganharOuro(10);
                }
            }
            else{
                System.out.println("O mar parece calmo agora...");
            }
        }
        
        if(description.equals("Base do chefe pirata: tome muito cuidado ao desafiá-lo!")){
            Enemies Piratao = new Enemies("Piratao");
            System.out.println("Essa não, o chefe pirata te avistou e está pronto para o combate!");
            jogador.combate(Piratao);
            if(Piratao.getVida() <= 0){
                System.out.println("Parabéns, você derrotou o chefe pirata. Ele está inconsciente por enquanto");
                System.out.println("Você aproveitou para roubar 20 barras de ouro dele e para ler uma inscrição que estava em seu bolso");
                jogador.ganharOuro(20);
                System.out.println("A inscrição diz: ");
                System.out.println("Há muito tempo, o terrível pirata Cleberson encontrou a Pérola da Imortalidade. Entretanto, ele sabia que se tratava de um tesouro extremamente raro");
                System.out.println("Cleberson temia que seu irmão Cleitinho roubasse a pérola dele");
                System.out.println("Então, Cleberson resolveu esconder a pérola na ilha do Oeste e a protegeu com uma senha para que ninguém a pegasse");
                System.out.println("O problema é que Cleberson acabou morrendo junto com seu irmão em um duelo de espadas e só ele sabia o segredo");
                System.out.println("Porém, certa vez ele me contou que o segredo se baseava no valor exato de 2 vezes área entre as abscissas 1 e 10 de uma parábola com concavidade para cima cujas raizes valem 1 e 5");
                System.out.println("Passei minha vida inteira tentando desvendar esse enigma e infelizmente até hoje, após 50 anos, não faço a menor ideia de como resolver. Só sei que o resultado é justamente o ano em que Cleberson morreu!");
            }
        }
        
        if(description.equals("uma aldeia de nativos canibais da ilha, eles parecem estar loucos para fazer churrasquinho de você")){
            System.out.println("Os canibais te avistaram e vão atacar!");
            Enemies Nativos = new Enemies("Nativos");
            jogador.combate(Nativos);
            if(Nativos.getVida() <= 0){
                System.out.println("Parabéns, você venceu os nativos e ganhou 10 barras de ouro");
                jogador.ganharOuro(10);
            }
        }
        
        if(description.equals("ruinas antigas da ilha")){
            if(!jogador.getResolveu_enigma()){
                System.out.println("Há uma inscrição na parede estranha, nela diz:");
                System.out.println("Atrás dessa parede, há um tesouro incrível, mas só poderá ser acessado se você escrever a resposta da seguinte pergunta:");
                System.out.println("--------------------------");
                System.out.println("Quantos anagramas são possíveis de se formar a partir das letras que estão presentes no nome desse arquipélago?");
                int resposta = scanner.nextInt();
                if(resposta == 181440){
                    System.out.println("Parabéns, você acertou e conseguiu obter 40 barras de ouro!");
                    jogador.ganharOuro(40);
                    jogador.setResolveu_enigma();
            }
                else{
                    System.out.println("Senha incorreta!");
                }
            }
            else{
                System.out.println("O enigma já foi resolvido, não há nada para fazer aqui");
            }
            }
            
            if(description.equals("um covil de tigres famintos")){
                Enemies Tigres = new Enemies("Tigres");
                System.out.println("Essa não! Um tigre está louco para te devorar:");
                jogador.combate(Tigres);
                if(Tigres.getVida() <= 0 ){
                    System.out.println("Você matou o tigre e comeu sua carne. Sua vida foi recuperada em 3 pontos!");
                    jogador.ganharVida(5);
                }
            }
    }

    public String getLongDescription(Player jogador) //
    {
            System.out.println("Você está em " + description);
            rooms(jogador);
            return getExitString();
    }


    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Saídas:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
        returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

