import java.sql.SQLOutput;
import java.util.ArrayList;



public class TechnionTournament implements Tournament {
    final PlayersScore maxPlayer =
            new PlayersScore(new Player(Integer.MAX_VALUE, "maxPlayer"), Integer.MAX_VALUE);
    final PlayersScore minPlayer =
            new PlayersScore(new Player( Integer.MIN_VALUE, "maxPlayer"),  Integer.MIN_VALUE);
    final FacultyScore maxFaculty =
            new FacultyScore(new Faculty(Integer.MAX_VALUE, "maxFaculty"), Integer.MAX_VALUE);
    final FacultyScore minFaculty =
            new FacultyScore(new Faculty(Integer.MIN_VALUE, "maxFaculty"), Integer.MIN_VALUE);
    TwoThreeTree<FacultyData> FacultyTree;
    TwoThreeTree<PlayerData> PlayerTree;
    TwoThreeTree<PlayersScore> playersScoreTree;
    TwoThreeTree<FacultyScore> facultyScoreTree;

    TechnionTournament() {
    }

    ;

    @Override
    public void init() {
        FacultyTree = new TwoThreeTree<FacultyData>(new FacultyData(minFaculty.getFaculty()), new FacultyData(maxFaculty.getFaculty()));
        PlayerTree = new TwoThreeTree<PlayerData>(new PlayerData(minPlayer.getPlayer()), new PlayerData(maxPlayer.getPlayer()));
        playersScoreTree = new TwoThreeTree<PlayersScore>(minPlayer, maxPlayer);
        facultyScoreTree = new TwoThreeTree<FacultyScore>(minFaculty, maxFaculty);


        //    minPlayer.next=maxPlayer;
      //  maxPlayer.prev=minPlayer;
     //   minFaculty.next=maxFaculty;
     //   maxFaculty.prev=minFaculty;
       //playersScoreTree.insert(maxPlayer);
      //  facultyScoreTree.insert(maxFaculty);


    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {


        FacultyData nf=new FacultyData(faculty);
        FacultyTree.insert(nf);
        FacultyScore newFacultyScore = new FacultyScore(faculty, 0);
        facultyScoreTree.insert(newFacultyScore);
      //  if(facultyScoreTree.successor(new Node<FacultyScore>(newFacultyScore))!=null)
            newFacultyScore.next = facultyScoreTree.successor(newFacultyScore).value;
      //  System.out.println(newFacultyScore.next.getFaculty()+"ssssss");
     //   System.out.println(newFacultyScore.getFaculty()+"ssssss");

        if(facultyScoreTree.predecessor(newFacultyScore)!=null)
            newFacultyScore.prev = facultyScoreTree.predecessor(newFacultyScore).value;
        else {
            newFacultyScore.prev = minFaculty;
            minFaculty.next=newFacultyScore;

        }
        if (newFacultyScore.next!=null)
            newFacultyScore.next.prev = newFacultyScore;
        else {
            newFacultyScore.next = maxFaculty;
            maxFaculty.prev=newFacultyScore;

        }
        if(newFacultyScore.prev!=null)
            newFacultyScore.prev.next = newFacultyScore;
       // System.out.println(FacultyTree.search(1)+"  beitar");
        FacultyScore newFacultyScore2 =new FacultyScore(faculty, 0);
     //   System.out.println(facultyScoreTree.search(newFacultyScore2)+"   my   ");
     //   System.out.println(newFacultyScore2.compareTo(newFacultyScore));







    }

    @Override
    public void removeFacultyFromTournament(int faculty_id) {
        Node<FacultyData> facultyToRemove = FacultyTree.search(new FacultyData(new Faculty(faculty_id,"saa")));
     //   System.out.println(FacultyTree.search(faculty_id).value.facultyPoints+ "points");
        FacultyScore f=facultyScoreTree.search(new FacultyScore
                        (facultyToRemove.value.getFaculty(), facultyToRemove.value.facultyPoints)).value;
        f.prev.next=f.next;
        f.next.prev=f.prev;

        facultyScoreTree.remove(facultyScoreTree.search(new FacultyScore
                (facultyToRemove.value.getFaculty(), facultyToRemove.value.facultyPoints)));
        FacultyTree.remove(facultyToRemove);

    }

    @Override
    public void addPlayerToFaculty(int faculty_id, Player player) {
        Player p=new Player(player.getId(), player.getName());
    //    System.out.println(p.getId()+"ffffff");
        PlayerTree.insert(new PlayerData(p));
        PlayersScore newPlayerScore = new PlayersScore(p, 0);
        playersScoreTree.insert(newPlayerScore);
     //   System.out.println(playersScoreTree.search(newPlayerScore) + " daii ");
        FacultyData nf=new FacultyData(new Faculty(faculty_id, "hh"));

        FacultyData facultyToAdd = FacultyTree.search(faculty_id).value;

        for (int i = 0; i < 11; i++) {
            if (facultyToAdd.PlayersArray[i] == null) {
                facultyToAdd.PlayersArray[i] =newPlayerScore;
                break;

            }

        }

        newPlayerScore.next = playersScoreTree.successor(newPlayerScore).value;
        //  System.out.println(newFacultyScore.next.getFaculty()+"ssssss");
        //   System.out.println(newFacultyScore.getFaculty()+"ssssss");

      //  if(playersScoreTree.predecessor(newPlayerScore)!=null)
            newPlayerScore.prev = playersScoreTree.predecessor(newPlayerScore).value;
    //    else {
     //       newPlayerScore.prev = minPlayer;
     //       minPlayer.next=newPlayerScore;

     //   }
   //     if (newPlayerScore.next!=null)
            newPlayerScore.next.prev = newPlayerScore;
      //  else {
      //      newPlayerScore.next = maxPlayer;
       //     maxPlayer.prev=newPlayerScore;

     //   }
    //    if(newPlayerScore.prev!=null)
       //     newPlayerScore.prev.next = newPlayerScore;









      //  PlayersScore newPlayerScore2 = new PlayersScore(new Player(1, "ss"), 0);
       // PlayersScore newPlayerScore3 = new PlayersScore(new Player(1, "ss"), 0);
    //    System.out.println(playersScoreTree.search(newPlayerScore2)+"   moy   ");
      //  System.out.println(newPlayerScore2.compareTo(newPlayerScore3));
    }

    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<PlayerData> playerToRemove = PlayerTree.search(player_id);
        Node<PlayersScore> p1=playersScoreTree.search(new PlayersScore(playerToRemove.value.getPlayer(), playerToRemove.value.Goals));
     //   p1.value.prev.next=p1.value.next;
     //   p1.value.next.prev=p1.value.prev;
    //    PlayerTree.remove(playerToRemove);
     //   playersScoreTree.remove(p1);
        FacultyData facultyToRemove = FacultyTree.search(new FacultyData(new Faculty(faculty_id, "Sss"))).value;
        for (int i = 0; i < 11; i++) {
            if (facultyToRemove.PlayersArray[i] == null)
                continue;
            if (facultyToRemove.PlayersArray[i].getPlayer().getId() == player_id) {
                facultyToRemove.PlayersArray[i] = null;


            }
        }


    }


    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {



        FacultyData facultyCurrentScore1 = FacultyTree.search(faculty_id1).value;
        FacultyData facultyCurrentScore2 = FacultyTree.search(faculty_id2).value;

        if (winner == 1) {

            updateScore(facultyCurrentScore1, 3);

        }
        if (winner == 2) {
            updateScore(facultyCurrentScore2, 3);
        } else if (winner == 0) {
            updateScore(facultyCurrentScore1, 1);
            updateScore(facultyCurrentScore2, 1);

        }

        for (int id : faculty1_goals) {





            //  System.out.println(faculty1_goals);
     //       System.out.println(i);



            PlayerData playerCurrentScore1 = PlayerTree.search(id).value;
      //      System.out.println(PlayerTree.search(playerCurrentScore1)+ "ggg");
            updatePlayerScore(playerCurrentScore1, 1);
            for (int i=0; i<11; i++) {
                if (facultyCurrentScore1.PlayersArray[i] == null)
                    continue;
                if (facultyCurrentScore1.PlayersArray[i].getPlayer().getId() == id)
                    facultyCurrentScore1.PlayersArray[i].Goals++;

            }

        }
        for (int id : faculty2_goals) {

            PlayerData playerCurrentScore2 = PlayerTree.search(id).value;
            updatePlayerScore(playerCurrentScore2, 1);
            for (int i=0; i<11; i++) {
                if (facultyCurrentScore2.PlayersArray[i] == null)
                    continue;
                if (facultyCurrentScore2.PlayersArray[i].getPlayer().getId() == id)
                    facultyCurrentScore2.PlayersArray[i].Goals++;

            }


        }


    }


    @Override
    public void getTopScorer(Player player) {
      //  System.out.println(maxPlayer.prev.Goals+" goals  ");
        player.setName(maxPlayer.prev.getPlayer().getName());
        player.setId(maxPlayer.prev.getPlayer().getId());

    }

    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {
        FacultyData currntFaculty= FacultyTree.search(faculty_id).value;
        PlayersScore maxGoalscorer= new PlayersScore(player,-1);
        for(int i=0; i<11; i++)
        {
            if(currntFaculty.PlayersArray[i]!=null){
                if(currntFaculty.PlayersArray[i].compareTo(maxGoalscorer) > 0){
                    maxGoalscorer = currntFaculty.PlayersArray[i];
                }
            }
        }
        player.setId(maxGoalscorer.getPlayer().getId());
        player.setName(maxGoalscorer.getPlayer().getName());



    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
        FacultyScore fs = maxFaculty.prev;
      //  System.out.println(fs.prev.prev.getFaculty().getId()+" hhhhhhhhhhhhhhhhhh");
        int i = 0;
        while(i < k) {
            if (true) {
           //     System.out.println(i+ " "+ fs.facultyPoints+ " "+ fs.getFaculty().getId());
                faculties.add(i, fs.getFaculty());
                faculties.set(i, fs.getFaculty());
                faculties.get(i).setId( fs.getFaculty().getId());
                faculties.get(i).setName( fs.getFaculty().getName());
            }
            fs=fs.prev;

            i++;

        }
        fs=fs.next;
       // System.out.println(fs.getFaculty().getId()+""+"gg");
            i=0;
            while(i < k){

             //   System.out.println(i+ " "+ fs.facultyPoints+ " getid "+ fs.getFaculty().getId());
                if (ascending) {
                    faculties.set(i, fs.getFaculty());
                    faculties.get(i).setId(fs.getFaculty().getId());
                    faculties.get(i).setName(fs.getFaculty().getName());
                }


                fs = fs.next;
                i++;
        }
    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        PlayersScore ps = maxPlayer.prev;
        int i = 0;

     //   System.out.println( maxPlayer.prev.getPlayer().getId()+" namee");
        players.add(maxPlayer.prev.prev.getPlayer());
        while(i < k) {

                players.add(i, ps.getPlayer());
                players.get(i).setName(ps.getPlayer().getName());
            players.get(i).setId(ps.getPlayer().getId());

            i++;
                ps = ps.prev;


        }
        ps=ps.next;
        i=0;
            while(i < k) {
                if (ascending==true) {

                    players.add(i, ps.getPlayer());
                   players.get(i).setName(ps.getPlayer().getName());
                    players.get(i).setId(ps.getPlayer().getId());
                }
                    ps = ps.next;
                    i++;



            }
     //   System.out.println(players.get(0).getName());
     //   System.out.println(players.get(1).getName()+ " 1");
      ///  System.out.println(playersScoreTree.search(6, 3));



    }


    @Override
    public void getTheWinner(Faculty faculty) {
        faculty.setId(maxFaculty.prev.getFaculty().getId());
        faculty.setName(maxFaculty.prev.getFaculty().getName());

    }

    ///TODO - add below your own variables and methods


    public void updateScore(FacultyData faculty, int scoreToAdd) {

        FacultyScore facultyScore = new FacultyScore(faculty.getFaculty(), faculty.facultyPoints);

        facultyScoreTree.search(facultyScore).value.prev.next = facultyScoreTree.search(facultyScore).value.next;
        facultyScoreTree.search(facultyScore).value.next.prev = facultyScoreTree.search(facultyScore).value.prev;
        facultyScoreTree.remove(facultyScoreTree.search(facultyScore));

        FacultyScore facultyScore1=new FacultyScore(faculty.getFaculty(),facultyScore.facultyPoints+scoreToAdd );

        //change the pointers of the new updated node
        facultyScoreTree.insert(facultyScore1);
        facultyScore1.next = facultyScoreTree.successor(facultyScore1).value;
        facultyScore1.prev = facultyScoreTree.predecessor(facultyScore1).value;
        facultyScore1.next.prev = facultyScore1;
        facultyScore1.prev.next = facultyScore1;
        facultyScore.facultyPoints+=scoreToAdd;
        FacultyTree.search(faculty).value.facultyPoints+=scoreToAdd;


    }

    public void updatePlayerScore(PlayerData player, int scoreToAdd) {
    //    System.out.println(player.Goals + "goals");

        PlayersScore playersScore = new PlayersScore(player.getPlayer(), player.Goals);
      //  System.out.println(playersScoreTree.search(playersScore)+"   ss");
      //  System.out.println(playersScore.hashCode()+ " haschod");
     //   System.out.println(playersScoreTree.search(playersScore).key+ "ttt");
     //   if (playersScoreTree.search(playersScore).value.prev!=null)
        playersScoreTree.search(playersScore).value.prev.next = playersScoreTree.search(playersScore).value.next;
     //   if (playersScoreTree.search(playersScore).value.next!=null)
            playersScoreTree.search(playersScore).value.next.prev = playersScoreTree.search(playersScore).value.prev;
        playersScoreTree.remove(playersScoreTree.search(playersScore));
        playersScore.Goals += scoreToAdd;
        //change the pointers of the new updated node
        PlayersScore playersScore1 = new PlayersScore(player.getPlayer(), player.Goals+1);
        playersScoreTree.insert(playersScore1);
      //  System.out.println(playersScore1.getPlayer().getId()+ "ccc");
        PlayersScore newPlayerScore2 = new PlayersScore(new Player(1, "ss"), 1);
        // PlayersScore newPlayerScore3 = new PlayersScore(new Player(1, "ss"), 0);
      //  System.out.println(playersScoreTree.search(newPlayerScore2)+"   gameeee   ");
    ///    if (playersScoreTree.successor(playersScore1)!=null) {
            playersScore1.next = playersScoreTree.successor(playersScore1).value;
            playersScoreTree.successor(playersScore).value.prev=playersScore1;
     //   }
    //    if (playersScoreTree.predecessor(playersScore)!=null) {
            playersScore1.prev = playersScoreTree.predecessor(playersScore).value;
            playersScoreTree.predecessor(playersScore).value.next=playersScore1;
     //   }
//        playersScore.next.prev = playersScore;
    //    playersScore.prev.next = playersScore;
        player.Goals++;


    }
}


