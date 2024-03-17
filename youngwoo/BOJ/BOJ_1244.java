import java.io.BufferedReader; //���� ���ڿ� �Է��� ���� Ŭ����
import java.io.IOException;
import java.io.InputStreamReader; //����Ʈ ��� �Է� ��Ʈ���� ���� ��� ��Ʈ������ ����
import java.util.StringTokenizer;

public class BOJ_1244 {

	static int[] switches; //����ġ�� ����(����: 1, ����: 0)�� �����ϴ� �迭

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // ����ġ ����
		switches = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			switches[i] = Integer.parseInt(st.nextToken()); // ����ġ �ʱ� ����
		}

		int studentNum = Integer.parseInt(br.readLine()); // �л� ��

        //�л� ����ŭ �ݺ��ϸ� gender�� ���� male �Ǵ� female �Լ� ȣ��
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken()); // ����
			int num = Integer.parseInt(st.nextToken()); // ����ġ ��ȣ

			if (gender == 1) {
				male(num);
			} else {
				female(num);
			}
		}
        //���� ����ġ ���¸� 20���� �ٹٲ��Ͽ� ���
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (i != 1 && i % 20 == 1) {
				sb.append("\n");
			}
			sb.append(switches[i]).append(" ");
		}
		System.out.println(sb.toString());

	}

    //�ڱ� ��ġ�� �߽����� �¿�� ��Ī�� ����ġ�� ã�� �ش� ��Ī������ ����ġ ���� ����
	private static void female(int idx) {

		int i = 1;
		int startIdx = idx;
		int endIdx = idx;

		while (true) {

			if (idx - i > 0 && idx + i < switches.length && switches[idx - i] == switches[idx + i]) {
				startIdx = idx - i;
				endIdx = idx + i;
				i++;
			} else {
				break;
			}

		}

		for (int j = startIdx; j <= endIdx; j++) {
			switches[j] = switches[j] == 0 ? 1 : 0;
		}

	}

    //����ġ ��ȣ�� ����� �ش��ϴ� ����ġ�� ���¸� ����
	private static void male(int idx) {

		int n = idx;

		while (idx < switches.length) {
			switches[idx] = switches[idx] == 0 ? 1 : 0;
			idx += n;
		}

	}

}