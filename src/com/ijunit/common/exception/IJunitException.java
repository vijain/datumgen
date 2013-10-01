/**
 * 
 */
package com.ijunit.common.exception;

/**
 * @author vcjain
 *
 */
public class IJunitException extends Exception
{
	int errorCode;
	String errorMsg;
	String userErrorMsg;
	Throwable root;
	
	public IJunitException(String userErrorMsg)
	{
		this(userErrorMsg,null);
	}
	
	public IJunitException(String userErrorMsg,Throwable e)
	{
		this.userErrorMsg = userErrorMsg;
		root = e;
	}
	

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		if(root != null){
			return errorMsg;
		}else return "";
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the root
	 */
	public Throwable getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Throwable root) {
		this.root = root;
	}

	/**
	 * @return the userErrorMsg
	 */
	public String getUserErrorMsg() {
		return userErrorMsg;
	}

	/**
	 * @param userErrorMsg the userErrorMsg to set
	 */
	public void setUserErrorMsg(String userErrorMsg) {
		this.userErrorMsg = userErrorMsg;
	}
}
