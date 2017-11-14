package com.chengzg.wxshop.enums;

/**
 * 匹配规则对应任务类
 * @Author liyb58
 * @Date 2017/1/19 10:33
 * @return
 */
public enum RuleEnum {
    /**
     * 发短信给客户
     */
    TEST_SIMPLE_JOB("com.chengzg.wxshop.elastic.TestSimpleJob", 1,"TestSimpleJob"),
    ;

    private String jobClass;
    private int code;
    private String jobName;

    private RuleEnum(String jobClass, int code, String jobName) {
        this.jobClass = jobClass;
        this.code = code;
        this.jobName = jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
    * 
    * @Author liyb58
    * @Date 2017/1/19 10:44
    * @return 
    */
    public static String getJobClassByCode(Integer code) {
        for (RuleEnum c : RuleEnum.values()) {
            if (c.code == code) {
                return c.getJobClass();
            }
        }
        return "";
    }

    /**
     *
     * @Author liyb58
     * @Date 2017/1/19 10:44
     * @return
     */
    public static String getJobNameByCode(Integer code) {
        for (RuleEnum c : RuleEnum.values()) {
            if (c.code == code) {
                return c.getJobName();
            }
        }
        return "";
    }

}
