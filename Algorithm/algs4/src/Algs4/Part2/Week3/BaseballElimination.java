import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class BaseballElimination {
    private final int numTeams;
    private final HashMap<String, Integer> teamsToId;
    private final HashMap<Integer, String> idToTeam;

    private final HashMap<String, ArrayList<String>> eliminatedTeams;
    private final HashSet<String> nonEliminatedTeams;
    private final int[] wins;
    private final int[] loss;
    private final int[] remaining;
    private final int[][] games;

    public BaseballElimination(String filename)  {
        // create a baseball division from given filename in format specified below
        In input = new In(filename);
        numTeams = input.readInt();

        wins = new int[numTeams];
        loss = new int[numTeams];
        remaining = new int[numTeams];
        games = new int[numTeams][numTeams];

        teamsToId = new HashMap<>();
        idToTeam = new HashMap<>();
        eliminatedTeams = new HashMap<>();
        nonEliminatedTeams = new HashSet<>();

        for (int i=0; i<numTeams; i++) {
            String name = input.readString();
            teamsToId.put(name, i);
            idToTeam.put(i, name);
            wins[i] = input.readInt();
            loss[i] = input.readInt();
            remaining[i] = input.readInt();

            for (int j=0; j<numTeams; j++) {
                games[i][j] = input.readInt();
            }
        }
    }

    public int numberOfTeams()    {
        // number of teams
        return numTeams;
    }

    public Iterable<String> teams()    {
        // all teams
        return teamsToId.keySet();
    }

    public int wins(String team)    {
        // number of wins for given team
        if (!teamsToId.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return wins[teamsToId.get(team)];
    }

    public int losses(String team)  {
        // number of losses for given team
        if (!teamsToId.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return loss[teamsToId.get(team)];
    }

    public int remaining(String team) {
        // number of remaining games for given team
        if (!teamsToId.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return remaining[teamsToId.get(team)];
    }

    public int against(String team1, String team2)   {
        // number of remaining games between team1 and team2
        if (!teamsToId.containsKey(team1))
            throw new java.lang.IllegalArgumentException();
        if (!teamsToId.containsKey(team2))
            throw new java.lang.IllegalArgumentException();
        return games[teamsToId.get(team1)][teamsToId.get(team2)];
    }

    private void addElimination(String team, String eteam) {
        if (eliminatedTeams.containsKey(team)) {
            eliminatedTeams.get(team).add(eteam);
        }
        else {
            ArrayList<String> a = new ArrayList<>();
            a.add(eteam);
            eliminatedTeams.put(team, a);
        }
    }


    private boolean isTrivialElimination(String team) {
        int w = wins(team);
        int r = remaining(team);
        int maxWin = w + r;

        for (String s: teams()) {
            if (s.equals(team))
                continue;
            if (wins(s) > maxWin) {
                addElimination(team, s);
                return true;
            }
        }
        return false;
    }

    private boolean isNonTrivialElimination(String name) {
        int teamId = teamsToId.get(name);
        int w = wins(name);
        int r = remaining(name);
        int maxWin = w + r;
        int matchBetnTeams = (numTeams*(numTeams-1))/2;
        HashMap<Integer, Integer> mapId = new HashMap<>();

        // number of vertex from source to target
        int v = 2 + (numTeams-1) + matchBetnTeams;
        int source = 0;
        int target = v-1;
        int gameId = 1;
        int countTotalGames = 0;

        FlowNetwork fn = new FlowNetwork(v);

        // generate team IDs w.r.t. current team
        int map = 1 + matchBetnTeams;
        for (int i=0; i<numTeams; i++) {
            if (i==teamId) continue;
            mapId.put(i, map);
            map++;
        }

        for (int i=0; i<numTeams; i++) {
            if (i==teamId)
                continue;

            for (int j=i+1; j<numTeams; j++) {
                if (j==teamId)
                    continue;

                fn.addEdge(new FlowEdge(source, gameId, games[i][j]));
                fn.addEdge(new FlowEdge(gameId, mapId.get(i), Double.POSITIVE_INFINITY));
                fn.addEdge(new FlowEdge(gameId, mapId.get(j), Double.POSITIVE_INFINITY));
                gameId++;
                countTotalGames+=games[i][j];
            }
        }

        for (int i=0; i<numTeams; i++) {
            if (i==teamId)
                continue;
            fn.addEdge(new FlowEdge(mapId.get(i), target, maxWin - wins[i]));
        }

        FordFulkerson ff = new FordFulkerson(fn, source, target);

        // check edges which are not full
        if (countTotalGames > ff.value()) {
            for (int i=0; i<numTeams; i++) {
                if (i==teamId)
                    continue;
                if (ff.inCut(mapId.get(i))) {
                    addElimination(name, idToTeam.get(i));
                }
            }
            return true;
        }

        return false;
    }


    public boolean isEliminated(String team)     {
        // is given team eliminated?
        if (!teamsToId.containsKey(team))
            throw new java.lang.IllegalArgumentException();

        if (eliminatedTeams.containsKey((team)))
            return true;

        if (isTrivialElimination(team))
            return true;

        if (isNonTrivialElimination(team))
            return true;

        // not eliminated
        nonEliminatedTeams.add(team);
        return false;
    }

    public Iterable<String> certificateOfElimination(String team)  {
        // subset R of teams that eliminates given team; null if not eliminated
        if (!teamsToId.containsKey(team))
            throw new java.lang.IllegalArgumentException();

        if (nonEliminatedTeams.contains(team))
            return null;

        if (!eliminatedTeams.containsKey(team)) {
            if (isEliminated(team)) {
                return eliminatedTeams.get(team);
            }
            return null;
        }
        else {
            return eliminatedTeams.get(team);
        }
    }

    public static void main(String[] args) {


    }
}
