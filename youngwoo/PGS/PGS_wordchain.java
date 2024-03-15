import java.util.HashSet;

public class PGS_wordchain {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];      
        //Ż���� ������ ��� int �迭. ù ��° ĭ�� Ż���� ��ȣ�� ����.�� ��° ĭ�� Ż�� ������ ����.
        HashSet<String> usedWords = new HashSet<>();
        //HashSet�� �ߺ��� ���� ���X, ���� �˻� �ӵ��� ����

        String prevWord = "";
        for (int i = 0; i < words.length; i++) {
            String currWord = words[i];

            // Ż�� ���� �˻�
            /*
            prevWord�� ������� �ʰ�, prevWord�� ������ ���ڰ� currWord�� ù ��° ���ڿ� ��ġ���� ������ Ż��
            usedWords�� currWord�� �����ϸ� Ż�� (�̹� ���� �ܾ�)
            currWord�� ���̰� 1���� �̸��̸� Ż��
            Ż�� ���ǿ� �ش��ϴ� ��� answer �迭�� Ż���� ������ �����ϰ� for ���� ����.
            */
            if (!prevWord.isEmpty() && prevWord.charAt(prevWord.length() - 1) != currWord.charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }
            if (usedWords.contains(currWord)) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }
            if (currWord.length() < 2) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }

            // ���� �ܾ� ����
            prevWord = currWord;
            usedWords.add(currWord);
        }

        // Ż���ڰ� �߻����� ���� ���
        /*
        Ż�� ���ǿ� �ش����� ������ currWord�� prevWord�� �����ϰ� ���� �ܾ��� �������� ����.
        �� �� 0�̸� Ż���ڰ� �߻����� �ʴ� ��. answer �迭�� [0, 0]�� ����.
        */
        if (answer[0] == 0 && answer[1] == 0) {
            answer[0] = 0;
            answer[1] = 0;
        }

        return answer;
    }
}