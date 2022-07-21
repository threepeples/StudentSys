package cn.scnu.com.util;



import java.util.HashMap;
import java.util.Map;

/**
 * @author: tt
 * @Date: 2022/06/27 16:16
 *
 * 当选课开始之后，需要进行按时写回操作
 */


public class WriteBackSetUtil {


    //存储修改的set，使用脏位
    private static Map<Integer, Integer> StudentIdMap= new HashMap<>();

    public static void addId(Integer studentId ){
        if (!StudentIdMap.containsKey(studentId)){
            StudentIdMap.put(studentId,0);
            return;
        }

        if (StudentIdMap.get(studentId)==1){
            StudentIdMap.put(studentId,0);
        };
    }

    public static void Drity(Integer studentId ){
        if (StudentIdMap.containsKey(studentId)){
            StudentIdMap.put(studentId,1);
        }
    }


    public static  Map<Integer, Integer>  getSet(){
        return StudentIdMap;
    }

}


