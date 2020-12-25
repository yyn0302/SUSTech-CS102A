import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A2Q2 {
    private static class Reader {
        BufferedReader in;
        StringTokenizer tokenizer;

        public Reader(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static char[][] map;
    static ArrayList<int[]> path = new ArrayList<>();
    static int n, m;
    static int x = 0, y = 0;

    static int[][] getSurroundCor() {
        ArrayList<int[]> temp = new ArrayList<>();
        try {
            if (map[x - 1][y] == 'C') {
                int[] u = {x - 1, y};
                temp.add(u);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (map[x + 1][y] == 'C') {
                int[] d = {x + 1, y};
                temp.add(d);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (map[x][y - 1] == 'C') {
                int[] l = {x, y - 1};
                temp.add(l);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (map[x][y + 1] == 'C') {
                int[] r = {x, y + 1};
                temp.add(r);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        int[][] ans = new int[temp.size()][2];
        temp.toArray(ans);
        return ans;
    }

    static boolean isDeadPath(char toDirection) {

        int temp = 0;
        switch (toDirection) {
            case 'u':
                try {
                    if (map[x - 1][y] != 'C') return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x - 1 - 1][y] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x - 1][y - 1] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x - 1][y + 1] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                break;
            case 'd':
                try {
                    if (map[x + 1][y] != 'C') return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                if (!(x == map.length - 2 && y == map[0].length - 1)) {
                    try {
                        temp += map[x + 1 + 1][y] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        temp += map[x + 1][y + 1] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        temp += map[x + 1][y - 1] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                } else {
                    return false;
                }
                break;
            case 'l':
                try {
                    if (map[x][y - 1] != 'C') return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x][y - 1 - 1] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x + 1][y - 1] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    temp += map[x - 1][y - 1] == 'C' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                break;
            case 'r':
                try {
                    if (map[x][y + 1] != 'C') return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                if (!(x == map.length - 1 && y == map[0].length - 2)) {
                    try {
                        temp += map[x - 1][y + 1] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        temp += map[x + 1][y + 1] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        temp += map[x][y + 1 + 1] == 'C' ? 1 : 0;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                } else {
                    return false;
                }
                break;
        }
        return temp == 0;
    }

    static void updateState(int[] newState) {
        int[] now = {x, y};
        path.add(now);
        map[x][y] = 'W';
        x = newState[0];
        y = newState[1];
    }

    static void printPath() {
        for (int[] ints : path) {
            System.out.printf("(%d,%d)\n", ints[0] + 1, ints[1] + 1);
        }
        System.out.printf("(%d,%d)", n, m);
    }

    public static void main(String[] args) {
        Reader sc = new Reader(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String temp = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        while (!(x == n - 1 && y == m - 1)) {
            int[][] temp = getSurroundCor();
            if (temp.length == 1) {
                updateState(temp[0]);
            } else if (temp.length == 0) {
                System.out.println("No");
                return;
            } else {
                int[] ans = new int[2];
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i][0] == x && temp[i][1] == y - 1) {
                        if (!isDeadPath('l')) {
                            ans = temp[i];
                        } else map[temp[i][0]][temp[i][1]] = 'P';
                    }
                    if (temp[i][0] == x - 1 && temp[i][1] == y) {
                        if (!isDeadPath('u')) {
                            ans = temp[i];
                        } else map[temp[i][0]][temp[i][1]] = 'P';
                    }
                    if (temp[i][0] == x + 1 && temp[i][1] == y) {
                        if (!isDeadPath('d')) {
                            ans = temp[i];
                        } else map[temp[i][0]][temp[i][1]] = 'P';
                    }
                    if (temp[i][0] == x && temp[i][1] == y + 1) {
                        if (!isDeadPath('r')) {
                            ans = temp[i];
                        } else map[temp[i][0]][temp[i][1]] = 'P';
                    }
                }
                updateState(ans);
            }
        }
        System.out.println("Yes");
        printPath();
    }
}
