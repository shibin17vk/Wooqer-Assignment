package com.webapp.wooqerassignment.database;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class SqlConstants {

    public static final String CREATE_TABLE = "CREATE TABLE ";
    public static final String DATA_TEXT = " TEXT, ";
    public static final String DATA_TEXT_END = " TEXT ); ";
    public static final String DATA_INTEGER = " INTEGER, ";
    public static final String DATA_INTEGER_DEFAULT = " INTEGER DEFAULT ";
    public static final String DATA_INTEGER_END = " INTEGER ); ";
    public static final String DATA_REAL = " REAL, ";
    public static final String DATA_REAL_END = " REAL ); ";
    public static final String DATA_INT_PK = " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    public static final String ASCENDING = " ASC ";
    public static final String DESCENDING = " DESC ";
    public static final String LIKE_ARG = " LIKE ?";
    public static final String MATCH = " MATCH ";
    public static final String MATCH_ARG = " MATCH ?";
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    public static final String EQUALS_ARG = " =? ";
    public static final String EQUALS_DQUOTE = "=";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String IN = " IN ";
    public static final String NOT_EQUALS_DQUOTE = "!=\"";
    public static final String NOT_IN = " NOT IN ";
    public static final String SELECT = " SELECT ";
    public static final String GROUP_BY = "GROUP_BY ";
    public static final String HAVING = "HAVING";
    public static final String LIMIT = "LIMIT";
    public static final String DISTINCT = "DISTINCT";
    public static final String UNIQUE = "UNIQUE";
    public static final String CONFLICT_IGNORE = " ON CONFLICT IGNORE";
    public static final String CONFLICT_REPLACE = " ON CONFLICT REPLACE";
    public static final char PARANTHESIS_OPEN = '(';
    public static final char PARANTHESES_CLOSE = ')';
    public static final char LESS_THAN = '<';
    public static final char GREATER_THAN = '>';
    public static final char EQUALS = '=';
    public static final char COMMA = ',';
    public static final char SLASH = '/';
    public static final char STAR = '*';
    public static final char PERCENT = '%';
    public static final char DQUOTE = '"';
    public static final char QUOTE = '\'';
    public static final String DROP_TABLE = "DROP TABLE ";
    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    public static final String IS_NULL = " IS NULL ";
    public static final String IS_NOT_NULL = " IS NOT NULL ";
    public static final String ALTER_TABLE = "ALTER TABLE ";
    public static final String ADD_COLUMN = " ADD COLUMN ";
    public static final String NOT_EQUAL_TO = "!=";
    public static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
    public static final String SEMI_COLON = "\\;";
    public static final String DEFAULT = " DEFAULT ";
}
