/**
 * 
 */
package com.ijunit.common.utility;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.ijunit.common.Logger;
import com.ijunit.common.constant.IApplicationMsgCodes;
import com.ijunit.common.constant.IExceptionCodes;
import com.ijunit.common.exception.IJunitException;
import com.ijunit.parser.ApplicationMessageParser;
import com.ijunit.parser.ErrorMessageParser;
import com.ijunit.common.constant.IOtherConstants;

/**
 * @author vcjain
 *
 */
public class Validation {

	static Logger logger = Logger.getLogger(Validation.class);
	
	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH.mm.ss";
	
	public static boolean validateNumber(String value, int precision, int scale) throws IJunitException{
		logger.debug("Entered in Validation.validateNumber()");
		logger.debug("In Validation.validateNumber message= Precision "+precision);
		logger.debug("In Validation.validateNumber message= scale "+scale);
		logger.debug("In Validation.validateNumber message= Value "+value);
		boolean isValid = true;
		try {
			if(value.startsWith(IOtherConstants.PREFIX_RUNTIME_METHOD)){
				return true;
			}
			validateNumeric(value);
			//Testing a Integer Value.
			if(scale == 0){
				if(value.length() > (precision)){
					throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.MAX_LENGTH_MSG , new String[]{(precision)+""}));
				}
				if(value.indexOf('.') != -1){
					throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.NOT_A_INTEGER));
				}
			}else{//Testing a Float Value
				if(value.indexOf('.') == -1){
					if(value.length() > (precision-scale)){
						throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.MAX_LENGTH_BEFORE_DECIMAL_MSG , new String[]{(precision-scale)+""}));
					}
					return true;
				}
				String afterDotValue = value.substring(value.indexOf('.')+1);
				String beforeDotValue = value.substring(0,value.indexOf('.'));
				if(beforeDotValue.length() > (precision-scale)){
					throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.MAX_LENGTH_BEFORE_DECIMAL_MSG , new String[]{(precision-scale)+""}));
				}
				if(value.indexOf('.') != -1){
					if(afterDotValue.length() > scale){
						throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.MAX_LENGTH_AFTER_DECIMAL_MSG ,
										new String[]{scale+""}));
					}
				}
			}
		} catch (NumberFormatException nfe) {
			logger.error("In Validation.validateNumber "+ErrorMessageParser.getMessage(IExceptionCodes.NOT_A_NUMBER_MSG), nfe);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.NOT_A_NUMBER_MSG),nfe);
		}
		
		return isValid;
	}
	
	public static boolean validateLength(String value, int length){
		boolean isValid = true;
		if(value.length() > length){
			JOptionPane.showMessageDialog(null,ErrorMessageParser.getMessage(IExceptionCodes.MAX_LENGTH_MSG
					,new String[]{String.valueOf(length)})
					,"Error Message",JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}
	
	public static  boolean validateNumeric( String sInput )  {
		logger.debug("Entered in Validation.validateNumeric()");
        int nPoint    = 0;
        int nPlusPos  = -1;
        int nMinusPos = -1;

        // Check to see if the plus/minus is only in the first position,
        // and that there is at most one decimal point.
        for ( int i = 0; i < sInput.length(); i++ ){
            switch ( sInput.charAt(i) ){
                case '.':
                    nPoint++; break;
                case '+':
                    nPlusPos = i; break;
                case '-':
                    nMinusPos = i; break;
            }
        }
        if (nPoint > 1 || nPlusPos > 0 || nMinusPos > 0){
            return false;
        }
       	Double.parseDouble( sInput );
        logger.debug("Exited from Validation.validateNumeric()");
        return true;
    }

	public static   boolean validateDate(String sDate) throws IJunitException{
		logger.debug("Entered in Validation.validateDate()");
		logger.debug("In Validation.validateDate message= Date Value "+sDate);
        SimpleDateFormat formatter= new SimpleDateFormat (DATE_FORMAT);
        formatter.setLenient(false);
        try {
			formatter.parse(sDate);
		} catch (ParseException e) {
			logger.error("In Validation.validateDate "+ErrorMessageParser.getMessage(IExceptionCodes.DATE_FORMAT_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.DATE_FORMAT_EXP),e);
		}
		logger.debug("Exited from Validation.validateDate()");
        return true;
	}
	
	public static boolean validateTimestamp(String sDate) throws IJunitException{
		logger.debug("Entered in Validation.validateTimestamp()");
		logger.debug("In Validation.validateTimestamp message= Date Value "+sDate);
        SimpleDateFormat formatter= new SimpleDateFormat (TIMESTAMP_FORMAT);
        formatter.setLenient(false);
        try {
			formatter.parse(sDate);
		} catch (ParseException e) {
			logger.error("In Validation.validateTimestamp "+ErrorMessageParser.getMessage(IExceptionCodes.TIMESTAMP_FORMAT_EXP), e);
			throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.TIMESTAMP_FORMAT_EXP),e);
		}
		logger.debug("Exited from Validation.validateTimestamp()");
        return true;
	}
	
	public static  int getIntFromBoolean(boolean pFlag){
        int retInt = 0;
        if (pFlag == true){
            retInt = 1;
        }
        return retInt;
    }
	
	public static  int getIntFromBoolean(String pInt){
		boolean pFlag = getBooleanFromString(pInt);
        int retInt = 0;
        if (pFlag == true){
            retInt = 1;
        }
        return retInt;
    }

    public static  boolean getBooleanFromInt(String pInt){
        boolean retFlag = false;
        if (pInt != null && !pInt.equals("") && !pInt.equals("0")){
            retFlag = true;
        }
        return retFlag;
    }
    
    public static  boolean getBooleanFromString(String pInt){
        boolean retFlag = false;
        if (pInt != null && !pInt.equals("") && pInt.equalsIgnoreCase("true")){
            retFlag = true;
        }
        return retFlag;
    }
    
    public static boolean validateTime(String sTime){
        SimpleDateFormat formatter= new SimpleDateFormat ("HH:mm");
        formatter.setLenient(false);
         try {
               formatter.parse(sTime);
               return true;
         }catch(Exception e){
           return false;
         }
       }

    public static  boolean isEmpty(String pValue){
        boolean flag = false;

        if (pValue == null || (pValue.trim()).equals("")){
            flag = true;
        }

        return flag;
    }
    
    public static boolean isEmpty(String value, String fieldName, Component cmp){
		logger.debug("Entered in AddObjectAction.isEmpty() value "+value);
		boolean isEmpty = true;
		if(Validation.isEmpty(value)){
			JOptionPane.showMessageDialog(null,ApplicationMessageParser.getMessage(IApplicationMsgCodes.EMPTY_VALUE, 
					new String[]{fieldName}) ,"Message",JOptionPane.INFORMATION_MESSAGE);
			cmp.setFocusable(true);
			cmp.requestFocus();
			isEmpty = false;
		}
		logger.debug("Exited from AddObjectAction.isEmpty() Return Value isEmpty "+isEmpty);
		return isEmpty;
	}
    
    public static boolean validateBoolean(String value) throws IJunitException{
    	logger.debug("Entered in Validation.isBoolean()");
    	if(!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))){
    		logger.error("In Validation.validateBoolean "+ErrorMessageParser.getMessage(IExceptionCodes.BOOLEAN_FORMAT_EXP));
    		throw new IJunitException(ErrorMessageParser.getMessage(IExceptionCodes.BOOLEAN_FORMAT_EXP));
    	}
    	return true;
    }
    
    public static void main(String[] args) {
		try {
			SimpleDateFormat formatter= new SimpleDateFormat (TIMESTAMP_FORMAT);
			System.out.println(formatter.parse("1980-06-02 22.45.45"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
