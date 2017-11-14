package com.chengzg.wxshop.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongh38@ziroom
 * @Date 2016/11/3
 * @Time 14:43
 * @Description
 * @Since 1.0.0
 */
public class HisStatus {

    public static long updateHisStatus(long hisStatus,int code) {
        long result = (hisStatus*10)+code;
        return result;
    }

    public static boolean checkStatus(long hisStatus, int status) {
        List<Integer> list = longToList(hisStatus);
        for (Integer value : list) {
            if (value == status) {
                return  true;
            }
        }
        return false;
    }

    public static List<Integer> longToList(long lValue) {
        List<Integer> list = new ArrayList<Integer>();
        int result=0;//存反转的数字
        while(true)
        {
            int n=(int)(lValue%10);//取出最低位上的数字
            list.add(n);
            result=result*10+n;//依次的反转存储得到反转的数字
            lValue=lValue/10;//降位
            if(lValue==0)
            {
                break;
            }
        }
        return  list;
    }
    public static void main(String[] args) {
        Long lll = 123l;
        boolean result = checkStatus(lll, 4);
        System.out.println(result);
    }
}
