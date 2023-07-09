package algorithm3;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class WordChainGame2 {
    private String lastWord;
    private int turnNumber;
    private Timer timer;
    private int TIME_LIMIT = 10000;

    public WordChainGame2() {
        lastWord = "";
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

    public boolean isMatching(String word) {
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
            // 1ターンに10秒制限あり
            System.out.println("start game round:" + turnNumber);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("시간이 초과되었습니다!");
                    throw new RuntimeException();
                }
            }, TIME_LIMIT);

            String input = scanner.nextLine();
            String[] inputList = input.split(",");
            String userId = inputList[0].trim();
            String word = inputList[1].trim();

            // 単語の成功有無の判定
            if (turnNumber != 0) {
                if (!isValidWord(word)) {
                    System.out.println("유효하지 않은 단어입니다!");
                    continue;
                }

                if (!isMatching(word)) {
                    System.out.println("단어가 매칭되지 않습니다!");
                    continue;
                }
            }

            // 成功した場合
            lastWord = word;
            turnNumber += 1;
            timer.cancel();
            timer = new Timer();
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer);
        while (true) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("失敗、人" + "が負けた");
                }
            }, 10000);
        }
    }
}