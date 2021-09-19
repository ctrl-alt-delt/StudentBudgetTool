package Constants;

import java.io.File;

public class Constants {
	/**
	 * Directories
	 */
	public static final String DATADIR = "data";
	public static final String LOGDIR = "logs";
	

	/**
	 * Extensions
	 */
	public static final String CSVEXT = ".csv";
	public static final String TXTEXT = ".txt";
	public static final String PRIVGRPEXT = ".pg" + CSVEXT;;
	public static final String BIGGRPEXT = ".bg" + CSVEXT;
	public static final String SUMEXT = ".sum" + CSVEXT;
	public static final String TEACHEREXT = ".tch" + CSVEXT;
	public static final String STUDENTEXT = ".stu" + CSVEXT;
	public static final String LOGEXT = ".log" + TXTEXT;
	
	/**
	 * Filenames
	 */
	public static final String PRIVGROUPFILENAME = "private_groups";
	public static final String BIGGROUPFILENAME = "big_groups";
	public static final String TEACHERFILENAME = "teachers";
	public static final String STUDENTFILENAME = "students";
	public static final String LOGFILENAME = "log";
	
	public static final String PRIVGROUPFILEPATH = DATADIR + File.separator + PRIVGROUPFILENAME + PRIVGRPEXT;
	public static final String BIGGROUPFILEPATH = DATADIR + File.separator + BIGGROUPFILENAME + BIGGRPEXT;
	public static final String TEACHERFILEPATH = DATADIR + File.separator + TEACHERFILENAME + TEACHEREXT;
	public static final String STUDENTFILEPATH = DATADIR + File.separator + STUDENTFILENAME + STUDENTEXT;
	public static final String LOGFILEPATH = LOGDIR + File.separator + LOGFILENAME + LOGEXT;
}
