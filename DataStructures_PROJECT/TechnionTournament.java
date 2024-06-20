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
    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {


        FacultyData nf=new FacultyData(faculty);
        FacultyTree.insert(nf);
        FacultyScore newFacultyScore = new FacultyScore(faculty, 0);
        facultyScoreTree.insert(newFacultyScore);
            newFacultyScore.next = facultyScoreTree.successor(newFacultyScore).value;

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
        FacultyScore newFacultyScore2 =new FacultyScore(faculty, 0);
    }

    @Override
    public void removeFacultyFromTournament(int faculty_id) {
        Node<FacultyData> facultyToRemove = FacultyTree.search(new FacultyData(new Faculty(faculty_id,"saa")));
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
        PlayerTree.insert(new PlayerData(p));
        PlayersScore newPlayerScore = new PlayersScore(p, 0);
        playersScoreTree.insert(newPlayerScore);
        FacultyData nf=new FacultyData(new Faculty(faculty_id, "hh"));
        FacultyData facultyToAdd = FacultyTree.search(faculty_id).value;
        for (int i = 0; i < 11; i++) {
            if (facultyToAdd.PlayersArray[i] == null) {
                facultyToAdd.PlayersArray[i] =newPlayerScore;
                break;
            }
        }

        newPlayerScore.next = playersScoreTree.successor(newPlayerScore).value;
        newPlayerScore.prev = playersScoreTree.predecessor(newPlayerScore).value;
        newPlayerScore.next.prev = newPlayerScore;
    }

    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<PlayerData> playerToRemove = PlayerTree.search(player_id);
        Node<PlayersScore> p1=playersScoreTree.search(new PlayersScore(playerToRemove.value.getPlayer(), playerToRemove.value.Goals));
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
            PlayerData playerCurrentScore1 = PlayerTree.search(id).value;
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
        int i = 0;
        while(i < k) {
            if (true) {
                faculties.add(i, fs.getFaculty());
                faculties.set(i, fs.getFaculty());
                faculties.get(i).setId( fs.getFaculty().getId());
                faculties.get(i).setName( fs.getFaculty().getName());
            }
            fs=fs.prev;

            i++;

        }
        fs=fs.next;
            i=0;
            while(i < k){
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
        PlayersScore playersScore = new PlayersScore(player.getPlayer(), player.Goals);
        playersScoreTree.search(playersScore).value.prev.next = playersScoreTree.search(playersScore).value.next;
            playersScoreTree.search(playersScore).value.next.prev = playersScoreTree.search(playersScore).value.prev;
        playersScoreTree.remove(playersScoreTree.search(playersScore));
        playersScore.Goals += scoreToAdd;
        PlayersScore playersScore1 = new PlayersScore(player.getPlayer(), player.Goals+1);
        playersScoreTree.insert(playersScore1);
        PlayersScore newPlayerScore2 = new PlayersScore(new Player(1, "ss"), 1);
            playersScore1.next = playersScoreTree.successor(playersScore1).value;
            playersScoreTree.successor(playersScore).value.prev=playersScore1;
            playersScore1.prev = playersScoreTree.predecessor(playersScore).value;
            playersScoreTree.predecessor(playersScore).value.next=playersScore1;
        player.Goals++;
    }
}


