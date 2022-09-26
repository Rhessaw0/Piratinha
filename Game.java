import javax.swing.text.StyledEditorKit.BoldAction;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Player jogador;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        jogador = new Player();
        createRooms();
        parser = new Parser();
    }

    /*
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        Room ocean = new Room("um oceano grande e terrível!");

        Room iSul = new Room("Cidade Central, Ilha do Sul");  //Cidade central
        Room loja = new Room("Loja da cidade: o valor dos itens são meio salgados, mas ainda assim de qualidade");
        
        Room iNorte = new Room("Ilha do Norte");
        Room basePirata = new Room("Base do chefe pirata: tome muito cuidado ao desafiá-lo!");
        
        Room iLeste = new Room("Ilha do Leste");
        Room aldeia = new Room("uma aldeia de nativos canibais da ilha, eles parecem estar loucos para fazer churrasquinho de você");
        Room enigma = new Room("ruinas antigas da ilha");
        Room tigres = new Room("um covil de tigres famintos");


        Room iOeste = new Room("Ilha do Oeste");
        Room kiko = new Room("ruinas antigas e misteriosas.");

        // initialise room exits

        iSul.setExit("atracar", loja);
        iSul.setExit("navegar", ocean);
        loja.setExit("navegar", iSul);  

        iNorte.setExit("atracar", basePirata);
        basePirata.setExit("navegar", iNorte);
        iNorte.setExit("navegar", ocean);

        iLeste.setExit("atracar", aldeia);
        aldeia.setExit("leste", enigma);
        aldeia.setExit("oeste", tigres);
        aldeia.setExit("navegar", iLeste);
        iLeste.setExit("navegar", ocean);

        tigres.setExit("voltar", aldeia);
        enigma.setExit("voltar", aldeia);

        iOeste.setExit("atracar", kiko);
        kiko.setExit("navegar", iOeste);
        iOeste.setExit("navegar", ocean);

        ocean.setExit("sul", iSul);
        ocean.setExit("norte", iNorte);
        ocean.setExit("leste", iLeste);
        ocean.setExit("oeste", iOeste);

        previousRoom = loja;
        currentRoom = iSul;
    }    

    private void run(){
        jogador.doFlee();
        currentRoom = previousRoom;
        System.out.println(currentRoom.getLongDescription(jogador)); //////////////
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Para onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Tem esse lugar ai não, parça!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription(jogador)); //////////////
        }
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished && jogador.alive()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(jogador.getFlee()) run();;
        }

        if(!jogador.alive()) System.out.println("Você morreu!");
        System.out.println("Obrigado por jogar! Adios.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-Vindo(a) ao arquipelago de Cleberson!");
        System.out.println("Você é um(a) pirata chamado Mustache (Isso mesmo, nada de barbas) em busca da Pérola da Imortalidade que as lendas dizem estar neste arquipelago");
        System.out.println("Você tem " + jogador.getVida() + " de Vida e causa " + jogador.getDano() + " de Dano");
        System.out.println("Seu navio tem " + jogador.getVidaNavio() + " de Vida e causa " + jogador.getDanoNavio() + " de Dano");
        System.out.println("Você atracou na Ilha do Sul. Navegue entre as ilhas para encontrar informações da localização do tesouro!");
        System.out.println("Digite '" + CommandWord.HELP + "' se precisar de ajuda.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription(jogador));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Não sei o que você quer dizer amigo(a)...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case INFO:
                printInfo();
                break;
            
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Se sentindo perdido(a)?");
        System.out.println("Talvez ir na '" + jogador.getCriticalPath() + "' ajude.");
        System.out.println();
        System.out.println("Os comandos possíveis são:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    public void printInfo()
    {
        System.out.println("");
    }
}
