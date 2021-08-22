package skill;
// 题目：

// 牛牛和羊羊都很喜欢青草,今天他们决定玩青草游戏。 
// 最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。
// 在每个回合中,每个玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,比如1,4,16,64等等。
// 不能在箱子中吃到有效份数青草的玩家落败。
// 假定牛牛和羊羊都是按照最佳方法进行游戏,请输出胜利者的名字。

public class C03_Eat {
	// 暴力解法
	public static String winner1(int n) {
		// 0  1  2  3  4
		// 后 先 后 先 先
		if (n < 5) {
			return (n == 0 || n == 2) ? "后手" : "先手";
		}
		// n >= 5时
		int base = 1; // 先手决定吃多少草
		while (base <= n) {
			// 母过程 先手, 在子过程就是后手
			if (winner1(n - base).equals("后手")) {
				return "后手";
			}
			// 防止base * 4溢出且及时终止，不能写：base * 4 > n
			if (base > n / 4) {
				break;
			}
		}
		return "后手";
	}

	// 发现技巧后
	public static void winner2(int n) {

		if (n % 5 == 0 || n % 5 == 2) {
			System.out.println("yang");
		} else {
			System.out.println("niu");
		}
	}

}
