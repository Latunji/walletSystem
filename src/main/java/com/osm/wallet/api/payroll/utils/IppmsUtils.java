package com.osm.wallet.api.payroll.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class IppmsUtils {

    @Autowired
    private JavaMailSender javaMailSender;

	public static String GeneratingRandomNumericString() {
		int length = 10;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string

		for(int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();

		return randomString;
	}

    public void sendMail(String email,String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Token Validation");
        msg.setText("Hello!  \n  \n Kindly use the code - "+code+" to validate your email! \n Regards!");
        javaMailSender.send(msg);
    }

    public String generateUniqueId() {
        return String.valueOf(Math.random()).substring(2, 8);
    }
	
	private static Date parseDateWithLeniency(
            String str, String[] parsePatterns, boolean lenient) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        
        SimpleDateFormat parser = new SimpleDateFormat();
        parser.setLenient(lenient);
        ParsePosition pos = new ParsePosition(0);
        for (String parsePattern : parsePatterns) {

            String pattern = parsePattern;

            // LANG-530 - need to make sure 'ZZ' output doesn't get passed to SimpleDateFormat
            if (parsePattern.endsWith("ZZ")) {
                pattern = pattern.substring(0, pattern.length() - 1);
            }
            
            parser.applyPattern(pattern);
            pos.setIndex(0);

            String str2 = str;
            // LANG-530 - need to make sure 'ZZ' output doesn't hit SimpleDateFormat as it will ParseException
            if (parsePattern.endsWith("ZZ")) {
                str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2"); 
            }

            Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }
	
	public static Date parseDateStrictly(String str, String... parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, parsePatterns, false);
    }
	
	public static String formatDate( Date pDateToBeFormated, String formatPattern ) {
		if( pDateToBeFormated != null && StringUtils.isNotEmpty( formatPattern ) ) {
			DateFormat formatter = new SimpleDateFormat( formatPattern );
			return formatter.format( pDateToBeFormated );
		}
		return "";
	}
	
	public static boolean isValidDate( String dateString, String datePattern ) {
		boolean result = true;
		try{
			parseDateStrictly(dateString, datePattern);
		}catch( ParseException pex ) { result = false;}
		
		return result;
	}
	
	public static String[] getClassFieldNames( Class<?> clazz ) {
		if( clazz != null ) {
			Field[] fields = clazz.getFields();
			if( fields != null ) {
				String[] retVal = new String[ fields.length ];
				int ii = 0;
				for( Field field : fields ) {
					retVal[ ii ] = field.getName();
					ii++;
				}
				
				return retVal;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param pObjToCopy
	 * @param classTypeOfNewObject
	 * @param fieldsToExclude i.e fields that shouldn't be transfered
	 * @return
	 * @throws BeansException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	 public static final Object transferFieldsToAnotherEntity( Object pObjToCopy, Class<?> classTypeOfNewObject, String[] fieldsToExclude ) throws BeansException, 
	 	InstantiationException, IllegalAccessException {
		 
		 if( pObjToCopy != null && classTypeOfNewObject != null ) {
			 Object objectToBeCopiedInto = classTypeOfNewObject.newInstance();
			 org.springframework.beans.BeanUtils.copyProperties( pObjToCopy, objectToBeCopiedInto, fieldsToExclude );
			 
			 return objectToBeCopiedInto;
		 }
		 
		 return null;
	 }
	 
	 public static boolean isNullOrEmpty( String string ) {
		 return ( string == null || string.trim().isEmpty() );
	 }
	 
	 public static boolean isNullOrEmpty( Collection<?> collection ) {
		 return ( collection == null || collection.isEmpty() );
	 }
	 
	 public static boolean isNotNullOrEmpty(Collection<?> col) {
		 return !isNullOrEmpty(col);
	 }
	 
	 
	 public static boolean isNotNullOrEmpty(String str) {
		 return !isNullOrEmpty(str);
	 }
	public static String treatNull(String str) {
		 if(str == null)
		 	str = "";
		return  str.trim();
	}

	 
	 public static boolean isNotNull(Object obj) {
		 return obj != null;
	 }
	 
	 public static boolean isNull(Object obj) {
		 return obj == null;
	 }
	 
	 /**
	  *  Check if a {@link Number Number} isn't 
	  * {@code null} and it is greater than or equal to (>=) zero(0).
	  * 
	  * @param number
	  * @return
	  */
	 public static boolean isNotNullAndGreaterThanOrEqualToZero(Number number) {
		 return isNotNull(number) && number.intValue() >= 0;
	 }
	 
	 /**
	  * Check if a {@link Number Number} isn't 
	  * {@code null} and it is greater than zero(0).
	  * 
	  * @param number The number to check
	  * @return {@code true} if it is and {@code false} otherwise.
	  */
	 public static boolean isNotNullAndGreaterThanZero(Number number) {
		 return isNotNull(number) && number.intValue() > 0;
	 }
	 
	 /**
	  * Check if a {@link Number Number} is 
	  * {@code null} or it is less than one(1).
	  * 
	  * @param number The number to check
	  * @return {@code true} if it is and {@code false} otherwise.
	  */
	 public static boolean isNullOrLessThanOne(Number number) {
		 return isNull(number) || number.intValue() < 1;
	 }
	 
	 public static ArrayList<Integer> getIntKeySetFromMap(Map<Integer, List<?>> wParamMap)
	    {
	      ArrayList<Integer> wRetVal = new ArrayList<Integer>();

	      Set<Map.Entry<Integer, List<?>>> set = wParamMap.entrySet();
	      Iterator<Map.Entry<Integer, List<?>>> i = set.iterator();

	      while (i.hasNext()) {
	        Map.Entry<Integer, List<?>> me = i.next();

	        wRetVal.add(me.getKey());
	      }

	      return wRetVal;
	    }

}
