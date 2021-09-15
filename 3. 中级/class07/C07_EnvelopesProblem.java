package recursion;

import java.util.Arrays;
import java.util.Comparator;

// 如上图数据所示，给定有限时间为10天。可以获取得最大奖励为:11700，需要的时长为:9天。参加的活动为BDFH 四个。
// 输入描述:
// 第一行输入数据N与D，表示有N项活动，D表示给予的时长。0<N<=1000，0<D<=10000。 
// 从第二行开始到N+1行，每行描述一个活动的信息，其中第一项表示当前活动需要花费的时间t，第二项表示可以获 得的奖励a，之后有N项数据，表示当前活动与其他活动的依赖关系，1表示有依赖，0表示无依赖。每项数据用空格分开。
// 输出描述: 输出两项数据A与T，用空格分割。A表示所获得的最大奖励，T表示所需要的时长。 输入
// 8 10
// 3 2000 0 1 1 0 0 0 0 0
// 3 4000 0 0 0 1 1 0 0 0
// 2 2500 0 0 0 1 0 0 0 0
// 1 1600 0 0 0 0 1 1 1 0
// 4 3800 0 0 0 0 0 0 0 1
// 2 2600 0 0 0 0 0 0 0 1
// 4 4000 0 0 0 0 0 0 0 1
// 3 3500 0 0 0 0 0 0 0 0
// 输出
// 11700 9
public class C07_EnvelopesProblem {

	public static class Envelope {
		public int l;
		public int h;

		public Envelope(int weight, int hight) {
			l = weight;
			h = hight;
		}
	}

	public static class EnvelopeComparator implements Comparator<Envelope> {
		@Override
		public int compare(Envelope o1, Envelope o2) {
			return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
		}
	}

	public static Envelope[] getSortedEnvelopes(int[][] matrix) {
		Envelope[] res = new Envelope[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			res[i] = new Envelope(matrix[i][0], matrix[i][1]);
		}
		Arrays.sort(res, new EnvelopeComparator());
		return res;
	}

	public static int maxEnvelopes(int[][] matrix) {
		Envelope[] envelopes = getSortedEnvelopes(matrix);
		int[] ends = new int[matrix.length];
		ends[0] = envelopes[0].h;
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < envelopes.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (envelopes[i].h > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = envelopes[i].h;
		}
		return right + 1;
	}

	public static void main(String[] args) {
		int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
		System.out.println(maxEnvelopes(test));
	}
}
