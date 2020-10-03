package brkckr.first.eleven;

import java.util.List;

public class FirstEleven
{
    public String club;
    public String coach;
    public String formation;
    public List<Player> playerList;

    public FirstEleven(String club, String coach, String formation,  List<Player> playerList)
    {
        this.club = club;
        this.coach = coach;
        this.formation = formation;
        this.playerList = playerList;
    }
}
