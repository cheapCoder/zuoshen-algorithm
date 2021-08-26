package DP;

// TODO: 补充freeCodeCamp的题目
// 计算找零
// link: https://chinese.freecodecamp.org/learn/javascript-algorithms-and-data-structures/javascript-algorithms-and-data-structures-projects/cash-register

// 请编写一个用于收银机的函数 checkCashRegister()：它的第一个参数为售价 price、第二个参数为支付金额 cash、第三个参数为收银机內的金额 cid。
// cid 是包含货币面值的二维数组。
// 函数 checkCashRegister()应返回含有 status 属性和 change 属性的对象。
// 如果收银机內的金额少于应找回的零钱数，或者你无法返回确切的数目时，返回{status:"INSUFFICIENT_FUNDS",change:[]}
// 如果收银机內的金额恰好等于应找回的零钱数，返回{status:"CLOSED",change:[...]}，其中 change 的属性值就是收银机內的金额。
// 否则，返回{status:"OPEN",change:[...]}，其中 change 键值是应找回的零钱数，并将找零的面值由高到低排序。
// 货币单位 Unit 面值 Penny 0.01 美元（PENNY）Nickel 0.05 美元（NICKEL）Dime 0.1 美元（DIME）Quarter 0.25 美元（QUARTER）Dollar 1 美元（ONE）Five Dollars 5 美元（五）Ten Dollars 10 美元（TEN）Twenty Dollars 20 美元（TWENTY）One-hundred Dollars 100 美元（ONE HUNDRED）下面的抽屉里现金数组示例：

// [["PENNY",1.01],
// ["NICKEL",2.05],
// ["DIME",3.1],
// ["QUARTER",4.25],
// ["ONE",90],
// ["FIVE",55],
// ["TEN",20],
// ["TWENTY",60],
// ["ONE HUNDRED",100]
// ]
public class C08_ExtraChange {

  public static void main(String[] args) {

  }

}
