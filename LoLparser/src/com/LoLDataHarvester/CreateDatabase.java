package com.LoLDataHarvester;

import java.sql.ResultSet;
import java.sql.Statement;

public class CreateDatabase {

    private DatabaseConnection dbConn;

    public CreateDatabase(String user, String password, String databaseName , int port, String ipAdress){
        this.dbConn = new DatabaseConnection(user, password, databaseName, port, ipAdress);

        dropAllTables();

        if(!tablesAreMade()){
            System.out.println("Tables are not made yet");
            createChampionsTable();
            createSummonerTable();
            createChampionMasteryTable();
            createMatchHistoryTable();
            createTeamDataTable();
            createBansTable();
            createSpellTable();
            createItemTable();
            System.out.println("Tables are made!");
        }else{
            System.out.println("Tables are already made!");
        }
    }

    public DatabaseConnection getDbConn() {
        return dbConn;
    }

    public boolean tablesAreMade(){
        dbConn.connectToDatabaseServer();
        try{
            Statement stmt =dbConn.getConn().createStatement();
            String sql = "SELECT * FROM BANS";
            ResultSet rs = stmt.executeQuery(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void createChampionsTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE CHAMPION " +
                    "(ChampionID       INT           PRIMARY KEY," +
                    "Name              TEXT             NOT NULL " +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created ChampionsTable");
    }

    public void createSummonerTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE SUMMONER " +
                    "(" +
                    " AccountID         TEXT            PRIMARY KEY," +
                    " SummonerID        TEXT            NOT NULL, " +
                    " Name              TEXT            NOT NULL, " +
                    " Rank              TEXT            NOT NULL, " +
                    " Tier              TEXT            NOT NULL, " +
                    " SummonerLevel     INT             NOT NULL, " +
                    " LeaguePoints      INT             NOT NULL, " +
                    " TotalGamesPlayed  INT             NOT NULL, " +
                    " Wins              INT             NOT NULL, " +
                    " Losses            INT             NOT NULL, " +
                    " Veteran           BOOLEAN                 , " +
                    " FreshBlood        BOOLEAN                 " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created SummonerTable");
    }

    public void createChampionMasteryTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE CHAMPIONMASTERY " +
                    "(" +
                    "PRIMARY KEY (ChampionID,AccountID)," +
                    " ChampionID                        INT  REFERENCES CHAMPION, " +
                    " AccountID                         TEXT                    , " +
                    " chestGranted                      BOOLEAN                 , " +
                    " championLevel                     INT                     , " +
                    " championPoints                    INT                     , " +
                    " championPointsSinceLastLevel      INT                     , " +
                    " championPointsUntilNextLevel      INT                     , " +
                    " tokensEarned                      INT                     , " +
                    " lastPlayTime                      DECIMAL                   " +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created ChampionMasteryTable");
    }

    public void createMatchHistoryTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE MATCHHISTORY " +
                    "(" +
                    " MatchAccountID    TEXT  PRIMARY KEY       , " +
                    " MatchID           TEXT                    , " +
                    " ChampionID        INT  REFERENCES CHAMPION, " +
                    " AccountID         TEXT                    , " +
                    " Lane              TEXT            NOT NULL, " +
                    " Role              TEXT            NOT NULL, " +
                    " Region            TEXT            NOT NULL, " +
                    " TeamID            INT                     , " +
                    " Spell1            INT                     , " +
                    " Spell2            INT                     , " +
                    " FirstBlood        BOOLEAN                 , " +
                    " FirstInhibitor    BOOLEAN                 , " +
                    " FirstTower        BOOLEAN                 , " +
                    " GoldEarned        INT                     , " +
                    " CreepKills        INT                     , " +
                    " PlayerKills       INT                     , " +
                    " PlayerAssists     INT                     , " +
                    " Item0             INT                     , " +
                    " Item1             INT                     , " +
                    " Item2             INT                     , " +
                    " Item3             INT                     , " +
                    " Item4             INT                     , " +
                    " Item5             INT                     , " +
                    " Item6             INT                     , " +
                    " VisionScore       INT                       " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created MatchHistoryTable");
    }

    public void createTeamDataTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE TEAMDATA " +
                    "(" +
                    " MatchTeamID        TEXT PRIMARY KEY         , " +
                    " MatchID            TEXT                     , " +
                    " TeamID             INT                     , " +
                    " Win                TEXT                    , " +
                    " MatchDuration      INT                     , " +
                    " firstBloodTeam     BOOLEAN                 , " +
                    " firstRiftTeam      BOOLEAN                 , " +
                    " countRift          INT                     , " +
                    " firstBaronTeam     BOOLEAN                 , " +
                    " countBaron         INT                     , " +
                    " firstDragonTeam    BOOLEAN                 , " +
                    " countDragon        INT                     , " +
                    " firstInhibitorTeam BOOLEAN                 , " +
                    " countInhibitor     INT                     , " +
                    " firstTowerTeam     BOOLEAN                 , " +
                    " countTower         INT                       " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created TeamTable");
    }

    public void createSpellTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE SPELL " +
                    "(" +
                    " SpellID            INT PRIMARY KEY         , " +
                    " Name               TEXT                      " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created SpellTable");
    }

    public void createItemTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE ITEM " +
                    "(" +
                    " ItemID             INT PRIMARY KEY         , " +
                    " Name               TEXT                      " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created Item");
    }

    public void createBansTable() {
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql = "CREATE TABLE BANS " +
                    "(" +
                    " ID                  INT PRIMARY KEY              , " +
                    " MatchID             TEXT                      , " +
                    " BannedChampion      INT REFERENCES CHAMPION        " +
                    ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            dbConn.getConn().close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Succesfully created Bans");
    }

    public void dropAllTables(){
        dbConn.connectToDatabaseServer();
        try {
            Statement stmt = dbConn.getConn().createStatement();
            String sql_champion =           "DROP TABLE CHAMPION        CASCADE ";
            String sql_championmastery =    "DROP TABLE CHAMPIONMASTERY CASCADE ";
            String sql_matchhistory =       "DROP TABLE MATCHHISTORY    CASCADE ";
            String sql_summoner =           "DROP TABLE SUMMONER        CASCADE ";
            String sql_teamdata =           "DROP TABLE TEAMDATA        CASCADE ";
            String sql_item =               "DROP TABLE ITEM            CASCADE ";
            String sql_ban =                "DROP TABLE BANS            CASCADE ";
            String sql_spell =              "DROP TABLE SPELL           CASCADE ";
            stmt.executeUpdate(sql_ban);
            stmt.executeUpdate(sql_item);
            stmt.executeUpdate(sql_spell);
            stmt.executeUpdate(sql_champion);
            stmt.executeUpdate(sql_championmastery);
            stmt.executeUpdate(sql_matchhistory);
            stmt.executeUpdate(sql_summoner);
            stmt.executeUpdate(sql_teamdata);
            //stmt.executeUpdate(sql_team);
            stmt.close();
            dbConn.getConn().close();
            System.out.println("Succesfully droped all tables");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
