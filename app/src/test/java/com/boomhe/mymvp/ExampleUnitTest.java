package com.boomhe.mymvp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        ArrayList<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("兔子");
        list.add("4444");
        list.add("你好");

        System.out.println(list);

        Collections.swap(list,1,3);

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);
    }


    /**
     * 判断一个有符号整型数是否是回文
     */
    @Test
    public void Test01(){
        int temp = 111222111;
        int copyX = temp;
        int reverse = 0;
        while (copyX > 0){
            reverse = reverse * 10 + copyX % 10;
            copyX = copyX / 10;
            System.out.println(copyX);
        }
    }

    /**
     * 罗马数字转整型数，范围从1到3999
     */
    @Test
    public void Test02(){
        String s = "VII";
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int sum = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; --i) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else {
                sum += map.get(s.charAt(i));
            }
        }
        System.out.println(sum);
    }
}