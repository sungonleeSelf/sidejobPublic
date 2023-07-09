package algorithm3;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class WordChainGame {
    private String lastWord;
    private String currentUserId;

    private int turnNumber;
    private Timer timer;
    private int TIME_LIMIT = 10000;

    public WordChainGame() {
        lastWord = "";
        currentUserId = "";
        turnNumber = 0;
        timer = new Timer();
    }

    public boolean isValidWord(String word) {
        // 1桁の単語
        if (word.length() == 1) {
            System.out.println("word length is single digit");
        }
        // 始まる単語に「ん」が入った場合
        if (word.startsWith("ん")) {
            System.out.println("word startsWith ん");
            return false;
        }

        return true;
    }

    public boolean isMatchingWithLastWord(String word) {
        // 前の人が言った単語の最後の文字から始まる単語を書いているか確認
        if (lastWord.charAt(lastWord.length() - 1) == word.charAt(0)) {
            return true;
        }
        return false;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("사람 ID와 10초 이내의 단어를 입력하세요.");
        System.out.println("예) 1, 사과");

        while (true) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("失敗、人"+currentUserId+"が負けた");
                    System.exit(0);
                }
            }, TIME_LIMIT);

            while(true) {
                String input = scanner.nextLine();
                String[] inputList = input.split(",");
                String userId = inputList[0].trim();
                currentUserId = userId;
                String word = inputList[1].trim();

                // 単語の妥当性チェック
                if (turnNumber != 0) {
                    if (!isValidWord(word)) {
                        System.out.println("유효하지 않은 단어입니다!");
                        continue;
                    }

                    if (!isMatchingWithLastWord(word)) {
                        System.out.println("단어가 매칭되지 않습니다!");
                        continue;
                    }
                }


                // 正解の場合
                lastWord = word;
                turnNumber += 1;
                timer.cancel();
                timer = new Timer();
                System.out.println("成功");
                break;
            }
        }
    }

    public static void main(String[] args) {
        WordChainGame game = new WordChainGame();
        game.startGame();
    }
}