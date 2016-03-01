package com.synopsys.calc;

import java.util.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * calculator program in Java that evaluates expressions in a very simple
 * integer expression language
 *
 */
public class App {
	// For logging INFO DEBUG ERROR messages
	final static Logger log = Logger.getLogger(App.class);

	/**
	 * Main method that takes command line arguments as input and calls method
	 * parseExpression
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();
		App app = new App();
		// check if arguments are not passed
		if (args[0] == null) {
			log.error("No input is provided");
		} else {

			System.out.println("OUTPUT: of "+args[0]+"= "+app.parseExpression(args[0],
					new HashMap<String, Integer>()));
		}
	}

	/**
	 * parseExpression evaluate given expression and converts them to
	 * Mathematical representation and calculates whole expression value
	 * recursively
	 * 
	 * @param string
	 * @param map
	 * @return
	 */
	public int parseExpression(String string, Map<String, Integer> map) {
		log.debug("Inside parseExpression method");
		List<String> list = new ArrayList<String>();

		int i = string.indexOf('(');
		if (i < 0) {
			if (map.get(string) != null)
				return map.get(string);
			return Integer.decode(string);
		}

		list.add(string.substring(0, i).trim());
		int parenthesis = 0;
		// checking whether the parenthesis are closed properly
		// otherwise it will display error message
		log.debug("verifying Parenthesis match");
		
		for (int j = i; j < string.length(); j++) {
			if ('(' == string.charAt(j))
				parenthesis++;
			if (')' == string.charAt(j))
				parenthesis--;
			if ((',' == string.charAt(j) && parenthesis == 1)
					|| parenthesis == 0) {
				// add values to list which are separated by comma
				list.add(string.substring(i + 1, j).trim());

				i = j;
			}
			if (parenthesis < 0) {
				log.error("Parenthesis doesnot match");
				return Integer.MIN_VALUE;
			}
		}

		if (list.isEmpty())
			return Integer.MIN_VALUE;

		// will check the operation first and verify if it has valid parameters
		// then we evaluate those values by applying correct operators
		if ("mult".equals(list.get(0))) {
			log.info("evaluating multiplication");
			if (list.size() != 3) {
				log.error("Invalid arguments for multiplication");
				return Integer.MIN_VALUE;
			}
			
			return parseExpression(list.get(1), map)
					* parseExpression(list.get(2), map);

		} else if ("add".equals(list.get(0))) {
			log.info("Evaluating add command");
			if (list.size() != 3) {
				log.error("add command syntax is wrong");
				return Integer.MIN_VALUE;
			}
			
			return parseExpression(list.get(1), map)
					+ parseExpression(list.get(2), map);

		} else if ("sub".equals(list.get(0))) {
			log.info("Evaluating substract command");
			if (list.size() != 3) {
				log.error("sub command syntax is wrong");
				return Integer.MIN_VALUE;
			}
			
			return parseExpression(list.get(1), map)
					- parseExpression(list.get(2), map);

		} else if ("div".equals(list.get(0))) {
			log.info("Evaluating div command");
			if (list.size() != 3) {
				log.error("div command syntax is wrong");
				return Integer.MIN_VALUE;
			}
			
			
			return parseExpression(list.get(1), map)
					/ parseExpression(list.get(2), map);

		} else if ("let".equals(list.get(0))) {
			log.info("Evaluating let command");
			if (list.size() != 4) {
				log.debug("Let command syntax is wrong");
				return Integer.MIN_VALUE;
			}
			String var = list.get(1);
			Integer oldValue = map.get(var);
			map.put(var, parseExpression(list.get(2), map));
			int result = parseExpression(list.get(3), map);
			map.put(var, oldValue);
			return result;
		}
		log.error("syntax is wrong");
		return Integer.MIN_VALUE;

	}

}
