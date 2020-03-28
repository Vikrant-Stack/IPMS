/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apogee.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/**
 *
 * @author Vikrant Saini
 */
public class QueryUtil{

    static Logger l = Logger.getLogger("exceptionlog");

    private static Connection conn;

    public static String numberedQuery(final String query, final Object[] params) {
        int i = 0;
        String qryString = query;
        for (final Object param : params) {
            qryString = qryString.replaceAll(QueryUtil.queryVariable(++i,
                    ApplicationConstants.COLLEN_CONSTANT,
                    ApplicationConstants.COLLEN_CONSTANT), QueryUtil
                            .paramBuilder(General.checknull((String) param)));
        }
        return qryString;
    }
    
    public static String getComboOption(final String tableName,
			final String optionValue, final String optionText,
			final String selectedValue, final String whereCondition,
			final String orderByCondition) {

		//Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final StringBuffer comboOption = new StringBuffer();
		String query = "SELECT DISTINCT " + optionValue + " AS VALUE_COLUMN, "+ optionText + " AS TEXT_COLUMN FROM " + tableName;
		if (!General.isNullOrBlank(whereCondition)) {
			query = query + " WHERE " + whereCondition;
		}
		if (!General.isNullOrBlank(orderByCondition)) {
			query = query + " ORDER BY " + orderByCondition;
		}
		//System.out.println("Query : "+query);
		try {
		    //conn = conn.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			//System.out.println("Query 1: "+ps);
			while (rs.next()) {
				comboOption.append(getOptionRow(rs, selectedValue));
			}
		} catch (final Exception e) {
			l.error("Error Executing query : " + query);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
//				if (conn != null) {
//					conn.close();
//					conn = null;                                        
//				}

			} catch (final Exception e) {
              l.error("Error while closing connection");
			}
		}
		return comboOption.toString();
	}

    private static String queryVariable(final int varIndex,
            final String startSign, final String endSign) {
        return new StringBuffer().append(startSign).append(varIndex).append(endSign).toString();
    }

    /**
     * Param builder.
     *
     * @param param the param
     * @return the string
     */
    private static String paramBuilder(final String param) {
        return new StringBuffer().append("'").append(param).append("'").toString();
    }


    public static String getOptionRow(final ResultSet rs,
            final String selectedValue) throws SQLException {
        final StringBuffer ddOption = new StringBuffer();
        if (rs != null) {
            final String optionValue = rs
                    .getString(1);
            final String optionText = rs
                    .getString(2);

            if (optionValue.equals(selectedValue)) {
                ddOption.append("<option value='").append(optionValue)
                        .append("' selected= selected>").append(optionText)
                        .append("</option>");
            } else {
                ddOption.append("<option value='").append(optionValue)
                        .append("'>").append(optionText).append("</option>");
            }
        }
        return ddOption.toString();
    }
    
    public static String getComboOption(final ResultSet rs,
            final String selectedValue) throws SQLException {
        final StringBuffer ddOption = new StringBuffer();
        if (rs != null) {
            final String optionValue = rs
                    .getString(1);
            final String optionText = rs
                    .getString(2);

            if (optionValue.equals(selectedValue)) {
                ddOption.append("<option value='").append(optionValue)
                        .append("' selected= selected>").append(optionText)
                        .append("</option>");
            } else {
                ddOption.append("<option value='").append(optionValue)
                        .append("'>").append(optionText).append("</option>");
            }
        }
        return ddOption.toString();
    }
    
    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

}
