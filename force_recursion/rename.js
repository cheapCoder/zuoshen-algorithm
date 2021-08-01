/*
 * @Author: your name
 * @Date: 2021-08-01 17:07:56
 * @LastEditTime: 2021-08-01 17:17:17
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \algo\rename.js
 */
/*
 * @Author: 李衡
 * @Date: 2021-08-01 16:27:39
 * @LastEditTime: 2021-08-01 17:14:22
 * @LastEditors: Please set LastEditors
 * @Description: 递归修改文件夹的名字
 * @FilePath: 
 */
const fs = require('fs');
const { resolve } = require("path");

const path = './';
const reg = /^(Code|Problem)(\d{1,2}.*)/
// const reg = /^(\d{2}).*/

/**
 * @description: 期望的名字规则
 * @param {*} nameRemainPart：经过正则过滤后的名字剩余部分
 * @return {*}：新名字
 */
function newNameRule(nameRemainPart) {
  return "C" + nameRemainPart
}



function rename(path) {
  fs.readdir(path, (err, filenames) => {
    if (err) throw err;

    for (let i = 0; i < filenames.length; i++) {
      let pathname = resolve(path, filenames[i])
      fs.stat(pathname, (err, stats) => {
        if (err) throw err;
        if (stats.isDirectory()) {
          rename(pathname);
        } else if (stats.isFile()) {
          if (reg.test(filenames[i])) {
            const new_name = reg.exec(filenames[i])[2];
            // console.log(newNameRule(new_name));
            const new_pathname = resolve(path, newNameRule(new_name));
            console.log(new_pathname);
            fs.rename(pathname, new_pathname, (err) => {
              if (err) { throw err }
            })
          }

        }
      })
    }
  })
}



rename(path);