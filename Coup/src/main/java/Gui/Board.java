package Gui;

import Logic.Actions.*;
import Logic.Card.Card;
import Logic.Card.ImageFinder;
import Logic.Game.Game;
import Logic.Players.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static Logic.Game.Game.fillTurnLabel;

public class Board extends JFrame {
    static JPanel panel = new JPanel();
    static JPanel general = new JPanel();
    static JPanel duke = new JPanel();
    static JPanel captain = new JPanel();
    static JPanel assassin = new JPanel();
    static JPanel ambassador = new JPanel();



    static JButton guide = new JButton();
    static JButton eventWriter = new JButton();
    static JButton income = new JButton();
    static JButton foreignAid = new JButton();
    static JButton challenge = new JButton();
    static JButton swapCard = new JButton();
    static JButton coup = new JButton();
    static JButton tax = new JButton();
    static JButton blockForeignAid = new JButton();
    static JButton assassinate = new JButton();
    static JButton exchange = new JButton();
    static JButton blockStealingAmbassador = new JButton();
    static JButton steal = new JButton();
    static JButton blockStealingCaptain = new JButton();
    static JButton blockAssassination = new JButton();
    static JButton noAction = new JButton();
    static JButton exchangeWithOneCoin = new JButton();
    static JButton showExchangeCards = new JButton();

    static JLabel you = new JLabel();
    static JLabel player1 = new JLabel();
    static JLabel player2 = new JLabel();
    static JLabel player3 = new JLabel();
    static JLabel coinIcon1 = new JLabel();
    static JLabel coinIcon2 = new JLabel();
    static JLabel coinIcon3 = new JLabel();
    static JLabel coinIcon4 = new JLabel();
    static JLabel coinNumber1 = new JLabel();
    static JLabel coinNumber2 = new JLabel();
    static JLabel coinNumber3 = new JLabel();
    static JLabel coinNumber4 = new JLabel();
    static JLabel one1 = new JLabel();
    static JLabel one2 = new JLabel();
    static JLabel two1 = new JLabel();
    static JLabel two2 = new JLabel();
    static JLabel three1 = new JLabel();
    static JLabel three2 = new JLabel();
    static JLabel you1 = new JLabel();
    static JLabel you2 = new JLabel();
    static JLabel title1 = new JLabel();
    static JLabel title2 = new JLabel();
    static JLabel title3 = new JLabel();
    static JLabel title4 = new JLabel();
    static JLabel generalTitle = new JLabel("General");
    static JLabel dukeTitle = new JLabel("Duke");
    static JLabel captainTitle = new JLabel("Captain");
    static JLabel assassinTitle = new JLabel("Assassin");
    static JLabel ambassadorTitle = new JLabel("Ambassador");
    public static JLabel turn = new JLabel();
    public static JLabel event = new JLabel();

    public static JComboBox coupChosenPlayer;
    public static JComboBox stealChosenPlayer;
    public static JComboBox assassinationChosenPlayer;
    public static JComboBox exchangeCards;

    static Border border = BorderFactory.createLineBorder(Color.lightGray,3);

    static Game game;

    public static Card selectedCard;
    public static JLabel selectedLabel;

    public Board() throws FileNotFoundException {
        game = Game.getInstance();
        setSize(new Dimension(1100,850));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        arrangeFrame();
        setVisible(true);
    }

    public void arrangeFrame(){
        panel.setBounds(0,0,1100,850);
        panel.setBackground(new Color(51,51,51));
        panel.setLayout(null);
        add(panel);

        makeYourPanel();
        makePlayer1Panel();
        makePlayer2Panel();
        makePlayer3Panel();

        setTurnLabel();
        setEventLabel();

        guide.setBounds(10,10,80,35);
        guide.setText("Guide");
        guide.setBackground(Color.lightGray);
        guide.setBorderPainted(false);
        guide.setOpaque(true);
        guide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Guide();
            }
        });
        panel.add(guide);

        eventWriter.setBounds(100,10,130,35);
        eventWriter.setText("Game events");
        eventWriter.setBackground(Color.lightGray);
        eventWriter.setBorderPainted(false);
        eventWriter.setOpaque(true);
        eventWriter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameEventWriter();
            }
        });
        panel.add(eventWriter);

        setUpOptionBar();
        setUpOptionBarTitleLabels();
    }

    public static void setTurnLabel(){
        turn.setBounds(900,60,180,40);
        turn.setBackground(new Color(51,51,51));
        turn.setBorder(border);
        turn.setText("Turn: " + fillTurnLabel());
        turn.setForeground(Color.lightGray);
        turn.setFont(new Font("Serif",Font.PLAIN,16));
        turn.setHorizontalAlignment(JLabel.CENTER);
        panel.add(turn);
    }

    public static void setEventLabel(){
        event.setBounds(780,10,300,40);
        event.setBackground(new Color(51,51,51));
        event.setBorder(border);
        event.setText("Current action:");
        event.setForeground(Color.lightGray);
        event.setFont(new Font("Serif",Font.PLAIN,14));
        event.setHorizontalAlignment(JLabel.CENTER);
        panel.add(event);
    }

    public void setUpOptionBar(){
        general.setBounds(50,550,200,225);
        general.setBackground(new Color(51,51,51));
        general.setBorder(border);
        general.setLayout(null);
        setGeneralButtons();
        panel.add(general);

        duke.setBounds(250,550,200,225);
        duke.setBackground(new Color(51,51,51));
        duke.setBorder(border);
        duke.setLayout(null);
        setDukeButtons();
        panel.add(duke);

        assassin.setBounds(450,550,200,225);
        assassin.setBackground(new Color(51,51,51));
        assassin.setBorder(border);
        assassin.setLayout(null);
        setAssassinButtons();
        panel.add(assassin);

        ambassador.setBounds(650,550,200,225);
        ambassador.setBackground(new Color(51,51,51));
        ambassador.setBorder(border);
        ambassador.setLayout(null);
        setAmbassadorButtons();
        panel.add(ambassador);

        captain.setBounds(850,550,200,225);
        captain.setBackground(new Color(51,51,51));
        captain.setBorder(border);
        captain.setLayout(null);
        setCaptainButtons();
        panel.add(captain);
    }


    public void setUpOptionBarTitleLabels(){
        generalTitle.setBounds(50,520,50,30);
        generalTitle.setBackground(Color.lightGray);
        generalTitle.setOpaque(true);
        generalTitle.setFont(new Font("Serif",Font.PLAIN,12));
        generalTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(generalTitle);

        dukeTitle.setBounds(250,520,50,30);
        dukeTitle.setBackground(Color.lightGray);
        dukeTitle.setOpaque(true);
        dukeTitle.setFont(new Font("Serif",Font.PLAIN,12));
        dukeTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(dukeTitle);

        assassinTitle.setBounds(450,520,50,30);
        assassinTitle.setBackground(Color.lightGray);
        assassinTitle.setOpaque(true);
        assassinTitle.setFont(new Font("Serif",Font.PLAIN,12));
        assassinTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(assassinTitle);

        ambassadorTitle.setBounds(650,520,65,30);
        ambassadorTitle.setBackground(Color.lightGray);
        ambassadorTitle.setOpaque(true);
        ambassadorTitle.setFont(new Font("Serif",Font.PLAIN,12));
        ambassadorTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(ambassadorTitle);

        captainTitle.setBounds(850,520,50,30);
        captainTitle.setBackground(Color.lightGray);
        captainTitle.setOpaque(true);
        captainTitle.setFont(new Font("Serif",Font.PLAIN,12));
        captainTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(captainTitle);
    }


    public void setGeneralButtons(){
        income.setBounds(50,15,100,20);
        income.setText("Income");
        income.setBackground(Color.lightGray);
        income.setBorderPainted(false);
        income.setOpaque(true);
        income.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    GeneralButtons.income(Game.players.get(0));
                    Game.clicked = true;
                }
            }
        });
        general.add(income);

        foreignAid.setBounds(40,40,120,20);
        foreignAid.setText("Foreign aid");
        foreignAid.setBackground(Color.lightGray);
        foreignAid.setBorderPainted(false);
        foreignAid.setOpaque(true);
        foreignAid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    GeneralButtons.foreignAid(Game.players.get(0));
                    Game.clicked = true;
                }
            }
        });
        general.add(foreignAid);

        Player[] bots = {Game.players.get(1), Game.players.get(2), Game.players.get(3)};
        coupChosenPlayer = new JComboBox(bots);
        coupChosenPlayer.setBounds(90, 65, 100, 20);
        general.add(coupChosenPlayer);

        coup.setBounds(10,65,80,20);
        coup.setText("Coup");
        coup.setBackground(Color.lightGray);
        coup.setBorderPainted(false);
        coup.setOpaque(true);
        coup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && !Game.isFinished()) {
                    GeneralButtons.coup(Game.players.get(0),(Player) coupChosenPlayer.getSelectedItem());
                }
            }
        });
        general.add(coup);

        blockAssassination.setBounds(10,90,180,20);
        blockAssassination.setText("Block assassination");
        blockAssassination.setBackground(Color.lightGray);
        blockAssassination.setBorderPainted(false);
        blockAssassination.setOpaque(true);
        blockAssassination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    GeneralButtons.blockAssassination(Game.players.get(0), Game.whichPlayer());
                }
            }
        });
        general.add(blockAssassination);

        challenge.setBounds(20,115,160,20);
        challenge.setText("Launch challenge");
        challenge.setBackground(Color.lightGray);
        challenge.setBorderPainted(false);
        challenge.setOpaque(true);
        challenge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    GeneralButtons.launchChallenge(Game.whichPlayer());
                }
            }
        });
        general.add(challenge);

        swapCard.setBounds(40,140,120,20);
        swapCard.setText("Swap card");
        swapCard.setBackground(Color.lightGray);
        swapCard.setBorderPainted(false);
        swapCard.setOpaque(true);
        swapCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    GeneralButtons.swapCard(Game.players.get(0));
                }

            }
        });
        general.add(swapCard);

        exchangeWithOneCoin.setBounds(15,165,170,20);
        exchangeWithOneCoin.setText("Exchange one card");
        exchangeWithOneCoin.setBackground(Color.lightGray);
        exchangeWithOneCoin.setBorderPainted(false);
        exchangeWithOneCoin.setOpaque(true);
        exchangeWithOneCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    GeneralButtons.exchangeWithOneCoin(Game.players.get(0));
                }
            }
        });
        general.add(exchangeWithOneCoin);

        noAction.setBounds(20,190,160,20);
        noAction.setText("No specific action");
        noAction.setBackground(Color.lightGray);
        noAction.setBorderPainted(false);
        noAction.setOpaque(true);
        noAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    Game.clicked = true;
                }
            }
        });
        general.add(noAction);
    }


    public void setDukeButtons(){
        tax.setBounds(60,55,80,30);
        tax.setText("Tax");
        tax.setBackground(Color.lightGray);
        tax.setBorderPainted(false);
        tax.setOpaque(true);
        tax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    DukeButtons.tax(Game.players.get(0));
                    Game.clicked = true;
                }
            }
        });
        duke.add(tax);

        blockForeignAid.setBounds(20,100,160,30);
        blockForeignAid.setText("Block foreign aid");
        blockForeignAid.setBackground(Color.lightGray);
        blockForeignAid.setBorderPainted(false);
        blockForeignAid.setOpaque(true);
        blockForeignAid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    DukeButtons.blockForeignAid(Game.players.get(0), Game.whichPlayer());
                }
            }
        });
        duke.add(blockForeignAid);
    }


    public void setAssassinButtons(){
        Player[] bots = {Game.players.get(1), Game.players.get(2), Game.players.get(3)};
        assassinationChosenPlayer = new JComboBox(bots);
        assassinationChosenPlayer.setBounds(50, 60, 100, 30);
        assassin.add(assassinationChosenPlayer);

        assassinate.setBounds(30,100,140,30);
        assassinate.setText("Assassinate");
        assassinate.setBackground(Color.lightGray);
        assassinate.setBorderPainted(false);
        assassinate.setOpaque(true);
        assassinate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    AssassinButtons.assassinate(Game.players.get(0),(Player) assassinationChosenPlayer.getSelectedItem());
                }
            }
        });
        assassin.add(assassinate);
    }

    public void setCaptainButtons(){
        Player[] bots = {Game.players.get(1), Game.players.get(2), Game.players.get(3)};
        stealChosenPlayer = new JComboBox(bots);
        stealChosenPlayer.setBounds(95, 55, 100, 30);
        captain.add(stealChosenPlayer);

        steal.setBounds(15,55,80,30);
        steal.setText("Steal");
        steal.setBackground(Color.lightGray);
        steal.setBorderPainted(false);
        steal.setOpaque(true);
        steal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    CaptainButtons.steal(Game.players.get(0),(Player) stealChosenPlayer.getSelectedItem());
                }
            }
        });
        captain.add(steal);

        blockStealingCaptain.setBounds(30,100,140,30);
        blockStealingCaptain.setText("Block stealing");
        blockStealingCaptain.setBackground(Color.lightGray);
        blockStealingCaptain.setBorderPainted(false);
        blockStealingCaptain.setOpaque(true);
        blockStealingCaptain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    CaptainButtons.blockStealing(Game.players.get(0), Game.whichPlayer());
                }
            }
        });
        captain.add(blockStealingCaptain);
    }


    public void setAmbassadorButtons(){
        showExchangeCards.setBounds(30,35,140,30);
        showExchangeCards.setText("Show options");
        showExchangeCards.setBackground(Color.lightGray);
        showExchangeCards.setBorderPainted(false);
        showExchangeCards.setOpaque(true);
        showExchangeCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    AmbassadorButtons.showOptions(Game.players.get(0));
                    String[] options = AmbassadorButtons.cardOptions.toArray(new String[AmbassadorButtons.cardOptions.size()]);
                    exchangeCards = new JComboBox(options);
                    exchangeCards.setBounds(10, 75, 180, 30);
                    ambassador.add(exchangeCards);
                    exchangeCards.setVisible(true);
                }
            }
        });
        ambassador.add(showExchangeCards);


        exchange.setBounds(40,115,120,30);
        exchange.setText("Exchange");
        exchange.setBackground(Color.lightGray);
        exchange.setBorderPainted(false);
        exchange.setOpaque(true);
        exchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.turn % 4 == 1 && Game.players.get(0).getCoins() < 10 && !Game.isFinished()) {
                    AmbassadorButtons.exchange(Game.players.get(0));
                    AmbassadorButtons.cardOptions.clear();
                    AmbassadorButtons.randomCard1 = null;
                    AmbassadorButtons.randomCard2 = null;
                    exchangeCards.setVisible(false);
                    Game.clicked = true;
                }
            }
        });
        ambassador.add(exchange);

        blockStealingAmbassador.setBounds(30,160,140,30);
        blockStealingAmbassador.setText("Block stealing");
        blockStealingAmbassador.setBackground(Color.lightGray);
        blockStealingAmbassador.setBorderPainted(false);
        blockStealingAmbassador.setOpaque(true);
        blockStealingAmbassador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Game.isFinished()) {
                    AmbassadorButtons.blockStealing(Game.players.get(0), Game.whichPlayer());
                }
            }
        });
        ambassador.add(blockStealingAmbassador);
    }


    public static void makePlayer1Panel(){
        Player player = Game.players.get(1);
        player1.setBounds(560,90,280,180);
        player1.setBackground(new Color(51,51,51));
        if (player.isLoser()) {
            player1.setBorder(BorderFactory.createLineBorder(Color.red));
            title2.setForeground(Color.red);
        }else{
            title2.setForeground(Color.lightGray);
            player1.setBorder(border);
        }


        player1.setLayout(null);
        coinIcon2.setBounds(10,10,20,21);
        coinIcon2.setIcon(new ImageIcon("./src/main/resources/coin.png"));
        player1.add(coinIcon2);

        coinNumber2.setBounds(30,10,30,20);
        coinNumber2.setForeground(Color.lightGray);
        coinNumber2.setText(" : " + Game.players.get(1).getCoins());
        player1.add(coinNumber2);

        one1.setBounds(45,40,75,126);
        one1.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(0).getCardName(),player.getCards().get(0).isFace())));
        player1.add(one1);

        one2.setBounds(150,40,75,126);
        one2.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(1).getCardName(),player.getCards().get(1).isFace())));
        player1.add(one2);

        title2.setBounds(110,0,60,30);
        title2.setHorizontalAlignment(JLabel.CENTER);
        title2.setFont(new Font("Serif",Font.PLAIN,16));
        title2.setText("Player 1");
        player1.add(title2);
        panel.add(player1);
    }

    public static void makePlayer2Panel(){
        Player player = Game.players.get(2);
        player2.setBounds(560,290,280,180);
        player2.setBackground(new Color(51,51,51));
        if (player.isLoser()) {
            player2.setBorder(BorderFactory.createLineBorder(Color.red));
            title3.setForeground(Color.red);
        }else{
            title3.setForeground(Color.lightGray);
            player2.setBorder(border);
        }


        player2.setLayout(null);
        coinIcon3.setBounds(10,10,20,21);
        coinIcon3.setIcon(new ImageIcon("./src/main/resources/coin.png"));
        player2.add(coinIcon3);

        coinNumber3.setBounds(30,10,30,20);
        coinNumber3.setForeground(Color.lightGray);
        coinNumber3.setText(" : " + Game.players.get(2).getCoins());
        player2.add(coinNumber3);

        two1.setBounds(45,40,75,126);
        two1.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(0).getCardName(),player.getCards().get(0).isFace())));
        player2.add(two1);

        two2.setBounds(150,40,75,126);
        two2.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(1).getCardName(),player.getCards().get(1).isFace())));
        player2.add(two2);

        title3.setBounds(110,0,60,30);
        title3.setHorizontalAlignment(JLabel.CENTER);
        title3.setFont(new Font("Serif",Font.PLAIN,16));
        title3.setText("Player 2");
        player2.add(title3);
        panel.add(player2);
    }

    public static void makePlayer3Panel(){
        Player player = Game.players.get(3);
        player3.setBounds(260,290,280,180);
        player3.setBackground(new Color(51,51,51));
        if (player.isLoser()) {
            player3.setBorder(BorderFactory.createLineBorder(Color.red));
            title4.setForeground(Color.red);
        }else{
            title4.setForeground(Color.lightGray);
            player3.setBorder(border);
        }


        player3.setLayout(null);
        coinIcon4.setBounds(10,10,20,21);
        coinIcon4.setIcon(new ImageIcon("./src/main/resources/coin.png"));
        player3.add(coinIcon4);

        coinNumber4.setBounds(30,10,30,20);
        coinNumber4.setForeground(Color.lightGray);
        coinNumber4.setText(" : " + Game.players.get(3).getCoins());
        player3.add(coinNumber4);

        three1.setBounds(45,40,75,126);
        three1.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(0).getCardName(),player.getCards().get(0).isFace())));
        player3.add(three1);

        three2.setBounds(150,40,75,126);
        three2.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(1).getCardName(),player.getCards().get(1).isFace())));
        player3.add(three2);

        title4.setBounds(110,0,60,30);
        title4.setHorizontalAlignment(JLabel.CENTER);
        title4.setFont(new Font("Serif",Font.PLAIN,16));
        title4.setText("Player 3");
        player3.add(title4);
        panel.add(player3);
    }

    public static void makeYourPanel(){
        Player player = Game.players.get(0);
        you.setBounds(260,90,280,180);
        you.setBackground(new Color(51,51,51));
        if (player.isLoser()) {
            you.setBorder(BorderFactory.createLineBorder(Color.red));
            title1.setForeground(Color.red);
        }else{
            title1.setForeground(Color.lightGray);
            you.setBorder(border);
        }

        you.setLayout(null);
        coinIcon1.setBounds(10,10,20,21);
        coinIcon1.setIcon(new ImageIcon("./src/main/resources/coin.png"));
        you.add(coinIcon1);

        coinNumber1.setBounds(30,10,30,20);
        coinNumber1.setForeground(Color.lightGray);
        coinNumber1.setText(" : " + Game.players.get(0).getCoins());
        you.add(coinNumber1);

        you1.setBounds(45,40,75,126);
        you1.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(0).getCardName(),!player.getCards().get(0).isFace())));
        you1.addMouseListener(mouse);
        you.add(you1);

        you2.setBounds(150,40,75,126);
        you2.setIcon(new ImageIcon(ImageFinder.findImageAddress(player.getCards().get(1).getCardName(),!player.getCards().get(1).isFace())));
        you2.addMouseListener(mouse);
        you.add(you2);

        title1.setBounds(110,0,60,30);
        title1.setHorizontalAlignment(JLabel.CENTER);
        title1.setFont(new Font("Serif",Font.PLAIN,16));
        title1.setText("You");
        you.add(title1);
        panel.add(you);
    }

    public static void rearrangePlayerPanels(){
        makeYourPanel();
        makePlayer1Panel();
        makePlayer2Panel();
        makePlayer3Panel();
    }

    static MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == you1) {
                selectedCard = Game.players.get(0).getCards().get(0);
                you2.setBorder(null);
                you1.setBorder(BorderFactory.createLineBorder(Color.red));
                selectedLabel = you1;
            }
            if (e.getSource() == you2) {
                selectedCard = Game.players.get(0).getCards().get(1);
                you1.setBorder(null);
                you2.setBorder(BorderFactory.createLineBorder(Color.red));
                selectedLabel = you2;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };



}
