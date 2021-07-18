// 9.	实现一个函数clone，可以对JavaScript中的5种主要的数据类型（包括Number、String、Object、Array、Boolean）进行值复制

function clone(obj) {
  if (typeof obj !== "object" || obj == null) {
    return obj;
  } else if (obj instanceof Array) {
    let copyArr = [];
    for (let i = 0; i < obj.length; i++) {
      copyArr[i] = clone(obj[i]);
    }
    return copyArr;
  } else if (typeof obj === "object") {
    let copyObj = {};
    for (const key in obj) {
      if (Object.hasOwnProperty.call(obj, key)) {
        copyObj[key] = clone(obj[key]);
      }
    }
    return copyObj;
  }
}