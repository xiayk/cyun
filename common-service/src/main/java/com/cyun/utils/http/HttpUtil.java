package com.cyun.utils.http;

public class HttpUtil {

	public static JSONResult writeJSONObject(Object obj) {
		if(obj!=null) {
			return writeSuccessJSON(obj);
		}else{
			return writeFailJSON();
		}
	}


	/**
	 * 页面打印JSON
	 * @param obj
	 * @param msg
	 * @param status
	 */
	public static JSONResult writeJSON(Object obj, String msg, Integer status) {
		JSONResult result = new JSONResult();
		result.setData(obj==null? new Object():obj);
		result.setStatus(status);
		result.setMsg(msg);
		return result;
	}

    /**
     * 页面打印JSON
     *
     */
    public static JSONResult writeSuccessJSON() {

		return writeJSON("", HttpRewriteStatus.SUCCESS_INFO, HttpRewriteStatus.SUCCESS_STATUS);
    }

    /**
     * 页面打印JSON
     *
     */
    public static JSONResult writeSuccessJSON(Object obj) {
		return writeJSON(obj, HttpRewriteStatus.SUCCESS_INFO, HttpRewriteStatus.SUCCESS_STATUS);
    }
	
	/**
	 * 页面打印JSON
	 *
	 * @param obj
	 * @param msg
	 */
	public static JSONResult writeSuccessJSON(Object obj,String msg) {

		return writeJSON(obj,msg, HttpRewriteStatus.SUCCESS_STATUS);
	}

    /**
     * 页面打印JSON
     *
     */
    public static JSONResult writeFailJSON() {

		return writeJSON("", HttpRewriteStatus.FAIL_INFO, HttpRewriteStatus.FAIL_STATUS);
    }

    /**
	 * 页面打印JSON
	 *
	 * @param obj
	 */
	public static JSONResult writeFailJSON(Object obj) {

		return writeJSON(obj, HttpRewriteStatus.FAIL_INFO, HttpRewriteStatus.FAIL_STATUS);
	}
	public static JSONResult writeFailJSON(Object obj,String msg) {

		return writeJSON(obj,msg, HttpRewriteStatus.FAIL_STATUS);
	}

	/**
	 * 页面打印JSON
	 *
	 * @param msg
	 */
	public static JSONResult writePromptJSON(String msg) {
		return writeJSON("",msg, null);
	}



    /**
     * 页面打印JSON失败的方法，与fail的状态区分开来
     *
     * @param obj
     */
    public static JSONResult writeLoseJSON(Object obj) {
		return writeJSON(obj, HttpRewriteStatus.LOSE_INFO, HttpRewriteStatus.LOSE_STATUS);
    }

}
